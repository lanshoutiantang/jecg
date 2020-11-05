package org.jeecg.modules.demo3.intel.service.impl;

import org.jeecg.modules.demo3.intel.entity.ZnPromPlan;
import org.jeecg.modules.demo3.intel.entity.ZnPromGoods;
import org.jeecg.modules.demo3.intel.entity.ZnPromShop;
import org.jeecg.modules.demo3.intel.mapper.ZnPromGoodsMapper;
import org.jeecg.modules.demo3.intel.mapper.ZnPromShopMapper;
import org.jeecg.modules.demo3.intel.mapper.ZnPromPlanMapper;
import org.jeecg.modules.demo3.intel.service.IZnPromPlanService;
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
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Service
public class ZnPromPlanServiceImpl extends ServiceImpl<ZnPromPlanMapper, ZnPromPlan> implements IZnPromPlanService {

	@Autowired
	private ZnPromPlanMapper znPromPlanMapper;
	@Autowired
	private ZnPromGoodsMapper znPromGoodsMapper;
	@Autowired
	private ZnPromShopMapper znPromShopMapper;
	
	@Override
	@Transactional
	public void saveMain(ZnPromPlan znPromPlan, List<ZnPromGoods> znPromGoodsList,List<ZnPromShop> znPromShopList) {
		znPromPlanMapper.insert(znPromPlan);
		if(znPromGoodsList!=null && znPromGoodsList.size()>0) {
			for(ZnPromGoods entity:znPromGoodsList) {
				//外键设置
				entity.setSerialid(znPromPlan.getId());
				znPromGoodsMapper.insert(entity);
			}
		}
		if(znPromShopList!=null && znPromShopList.size()>0) {
			for(ZnPromShop entity:znPromShopList) {
				//外键设置
				entity.setSerialid(znPromPlan.getId());
				znPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ZnPromPlan znPromPlan,List<ZnPromGoods> znPromGoodsList,List<ZnPromShop> znPromShopList) {
		znPromPlanMapper.updateById(znPromPlan);
		
		//1.先删除子表数据
		znPromGoodsMapper.deleteByMainId(znPromPlan.getId());
		znPromShopMapper.deleteByMainId(znPromPlan.getId());
		
		//2.子表数据重新插入
		if(znPromGoodsList!=null && znPromGoodsList.size()>0) {
			for(ZnPromGoods entity:znPromGoodsList) {
				//外键设置
				entity.setSerialid(znPromPlan.getId());
				znPromGoodsMapper.insert(entity);
			}
		}
		if(znPromShopList!=null && znPromShopList.size()>0) {
			for(ZnPromShop entity:znPromShopList) {
				//外键设置
				entity.setSerialid(znPromPlan.getId());
				znPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		znPromGoodsMapper.deleteByMainId(id);
		znPromShopMapper.deleteByMainId(id);
		znPromPlanMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			znPromGoodsMapper.deleteByMainId(id.toString());
			znPromShopMapper.deleteByMainId(id.toString());
			znPromPlanMapper.deleteById(id);
		}
	}
	
}
