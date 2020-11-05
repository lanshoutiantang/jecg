package org.jeecg.modules.demo3.tree.service.impl;

import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.tree.entity.CesShopType1;
import org.jeecg.modules.demo3.tree.mapper.CesShopType1Mapper;
import org.jeecg.modules.demo3.tree.service.ICesShopType1Service;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class CesShopType1ServiceImpl extends ServiceImpl<CesShopType1Mapper, CesShopType1> implements ICesShopType1Service {

	@Override
	public void addCesShopType1(CesShopType1 cesShopType1) {
		if(oConvertUtils.isEmpty(cesShopType1.getPid())){
			cesShopType1.setPid(ICesShopType1Service.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChildren 为1
			CesShopType1 parent = baseMapper.selectById(cesShopType1.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(cesShopType1);
	}
	
	@Override
	public void updateCesShopType1(CesShopType1 cesShopType1) {
		CesShopType1 entity = this.getById(cesShopType1.getId());
		if(entity==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		String old_pid = entity.getPid();
		String new_pid = cesShopType1.getPid();
		if(!old_pid.equals(new_pid)) {
			updateOldParentNode(old_pid);
			if(oConvertUtils.isEmpty(new_pid)){
				cesShopType1.setPid(ICesShopType1Service.ROOT_PID_VALUE);
			}
			if(!ICesShopType1Service.ROOT_PID_VALUE.equals(cesShopType1.getPid())) {
				baseMapper.updateTreeNodeStatus(cesShopType1.getPid(), ICesShopType1Service.HASCHILD);
			}
		}
		baseMapper.updateById(cesShopType1);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteCesShopType1(String id) throws JeecgBootException {
		//查询选中节点下所有子节点一并删除
        id = this.queryTreeChildIds(id);
        if(id.indexOf(",")>0) {
            StringBuffer sb = new StringBuffer();
            String[] idArr = id.split(",");
            for (String idVal : idArr) {
                if(idVal != null){
                    CesShopType1 cesShopType1 = this.getById(idVal);
                    String pidVal = cesShopType1.getPid();
                    //查询此节点上一级是否还有其他子节点
                    List<CesShopType1> dataList = baseMapper.selectList(new QueryWrapper<CesShopType1>().eq("pid", pidVal).notIn("id",Arrays.asList(idArr)));
                    if((dataList == null || dataList.size()==0) && !Arrays.asList(idArr).contains(pidVal)
                            && !sb.toString().contains(pidVal)){
                        //如果当前节点原本有子节点 现在木有了，更新状态
                        sb.append(pidVal).append(",");
                    }
                }
            }
            //批量删除节点
            baseMapper.deleteBatchIds(Arrays.asList(idArr));
            //修改已无子节点的标识
            String[] pidArr = sb.toString().split(",");
            for(String pid : pidArr){
                this.updateOldParentNode(pid);
            }
        }else{
            CesShopType1 cesShopType1 = this.getById(id);
            if(cesShopType1==null) {
                throw new JeecgBootException("未找到对应实体");
            }
            updateOldParentNode(cesShopType1.getPid());
            baseMapper.deleteById(id);
        }
	}
	
	@Override
    public List<CesShopType1> queryTreeListNoPage(QueryWrapper<CesShopType1> queryWrapper) {
        List<CesShopType1> dataList = baseMapper.selectList(queryWrapper);
        List<CesShopType1> mapList = new ArrayList<>();
        for(CesShopType1 data : dataList){
            String pidVal = data.getPid();
            //递归查询子节点的根节点
            if(pidVal != null && !"0".equals(pidVal)){
                CesShopType1 rootVal = this.getTreeRoot(pidVal);
                if(rootVal != null && !mapList.contains(rootVal)){
                    mapList.add(rootVal);
                }
            }else{
                if(!mapList.contains(data)){
                    mapList.add(data);
                }
            }
        }
        return mapList;
    }
	
	/**
	 * 根据所传pid查询旧的父级节点的子节点并修改相应状态值
	 * @param pid
	 */
	private void updateOldParentNode(String pid) {
		if(!ICesShopType1Service.ROOT_PID_VALUE.equals(pid)) {
			Integer count = baseMapper.selectCount(new QueryWrapper<CesShopType1>().eq("pid", pid));
			if(count==null || count<=1) {
				baseMapper.updateTreeNodeStatus(pid, ICesShopType1Service.NOCHILD);
			}
		}
	}

	/**
     * 递归查询节点的根节点
     * @param pidVal
     * @return
     */
    private CesShopType1 getTreeRoot(String pidVal){
        CesShopType1 data =  baseMapper.selectById(pidVal);
        if(data != null && !"0".equals(data.getPid())){
            return this.getTreeRoot(data.getPid());
        }else{
            return data;
        }
    }

    /**
     * 根据id查询所有子节点id
     * @param ids
     * @return
     */
    private String queryTreeChildIds(String ids) {
        //获取id数组
        String[] idArr = ids.split(",");
        StringBuffer sb = new StringBuffer();
        for (String pidVal : idArr) {
            if(pidVal != null){
                if(!sb.toString().contains(pidVal)){
                    if(sb.toString().length() > 0){
                        sb.append(",");
                    }
                    sb.append(pidVal);
                    this.getTreeChildIds(pidVal,sb);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 递归查询所有子节点
     * @param pidVal
     * @param sb
     * @return
     */
    private StringBuffer getTreeChildIds(String pidVal,StringBuffer sb){
        List<CesShopType1> dataList = baseMapper.selectList(new QueryWrapper<CesShopType1>().eq("pid", pidVal));
        if(dataList != null && dataList.size()>0){
            for(CesShopType1 tree : dataList) {
                if(!sb.toString().contains(tree.getId())){
                    sb.append(",").append(tree.getId());
                }
                this.getTreeChildIds(tree.getId(),sb);
            }
        }
        return sb;
    }

}
