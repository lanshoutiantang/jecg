package org.jeecg.modules.demo3.cs.service.impl;

import org.jeecg.modules.demo3.cs.entity.MdZnPromPlan;
import org.jeecg.modules.demo3.cs.entity.MdZnPromShop;
import org.jeecg.modules.demo3.cs.mapper.MdZnPromShopMapper;
import org.jeecg.modules.demo3.cs.mapper.MdZnPromPlanMapper;
import org.jeecg.modules.demo3.cs.service.IMdZnPromPlanService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Service
public class MdZnPromPlanServiceImpl extends ServiceImpl<MdZnPromPlanMapper, MdZnPromPlan> implements IMdZnPromPlanService {

	@Autowired
	private MdZnPromPlanMapper mdZnPromPlanMapper;
	@Autowired
	private MdZnPromShopMapper mdZnPromShopMapper;
	
	@Override
	@Transactional
	public void saveMain(MdZnPromPlan mdZnPromPlan, List<MdZnPromShop> mdZnPromShopList) {
		mdZnPromPlanMapper.insert(mdZnPromPlan);
		if(mdZnPromShopList!=null && mdZnPromShopList.size()>0) {
			for(MdZnPromShop entity:mdZnPromShopList) {
				//外键设置
				entity.setSerialid(mdZnPromPlan.getId());
				mdZnPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(MdZnPromPlan mdZnPromPlan,List<MdZnPromShop> mdZnPromShopList) {
		mdZnPromPlanMapper.updateById(mdZnPromPlan);
		
		//1.先删除子表数据
		mdZnPromShopMapper.deleteByMainId(mdZnPromPlan.getId());
		
		//2.子表数据重新插入
		if(mdZnPromShopList!=null && mdZnPromShopList.size()>0) {
			for(MdZnPromShop entity:mdZnPromShopList) {
				//外键设置
				entity.setSerialid(mdZnPromPlan.getId());
				mdZnPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mdZnPromShopMapper.deleteByMainId(id);
		mdZnPromPlanMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mdZnPromShopMapper.deleteByMainId(id.toString());
			mdZnPromPlanMapper.deleteById(id);
		}
	}
	
}
