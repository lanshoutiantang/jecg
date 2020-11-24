package org.jeecg.modules.demo3.ttt.service.impl;

import org.jeecg.modules.demo3.ttt.entity.AZnPromPlan;
import org.jeecg.modules.demo3.ttt.entity.AZnPromGoods;
import org.jeecg.modules.demo3.ttt.entity.AZnPromShop;
import org.jeecg.modules.demo3.ttt.mapper.AZnPromGoodsMapper;
import org.jeecg.modules.demo3.ttt.mapper.AZnPromShopMapper;
import org.jeecg.modules.demo3.ttt.mapper.AZnPromPlanMapper;
import org.jeecg.modules.demo3.ttt.service.IAZnPromPlanService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: A促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Service
public class AZnPromPlanServiceImpl extends ServiceImpl<AZnPromPlanMapper, AZnPromPlan> implements IAZnPromPlanService {

	@Autowired
	private AZnPromPlanMapper aZnPromPlanMapper;
	@Autowired
	private AZnPromGoodsMapper aZnPromGoodsMapper;
	@Autowired
	private AZnPromShopMapper aZnPromShopMapper;
	
	@Override
	@Transactional
	public void saveMain(AZnPromPlan aZnPromPlan, List<AZnPromGoods> aZnPromGoodsList,List<AZnPromShop> aZnPromShopList) {
		aZnPromPlanMapper.insert(aZnPromPlan);
		if(aZnPromGoodsList!=null && aZnPromGoodsList.size()>0) {
			for(AZnPromGoods entity:aZnPromGoodsList) {
				//外键设置
				entity.setSerialid(aZnPromPlan.getId());
				aZnPromGoodsMapper.insert(entity);
			}
		}
		if(aZnPromShopList!=null && aZnPromShopList.size()>0) {
			for(AZnPromShop entity:aZnPromShopList) {
				//外键设置
				entity.setSerialid(aZnPromPlan.getId());
				aZnPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(AZnPromPlan aZnPromPlan,List<AZnPromGoods> aZnPromGoodsList,List<AZnPromShop> aZnPromShopList) {
		aZnPromPlanMapper.updateById(aZnPromPlan);
		
		//1.先删除子表数据
		aZnPromGoodsMapper.deleteByMainId(aZnPromPlan.getId());
		aZnPromShopMapper.deleteByMainId(aZnPromPlan.getId());
		
		//2.子表数据重新插入
		if(aZnPromGoodsList!=null && aZnPromGoodsList.size()>0) {
			for(AZnPromGoods entity:aZnPromGoodsList) {
				//外键设置
				entity.setSerialid(aZnPromPlan.getId());
				aZnPromGoodsMapper.insert(entity);
			}
		}
		if(aZnPromShopList!=null && aZnPromShopList.size()>0) {
			for(AZnPromShop entity:aZnPromShopList) {
				//外键设置
				entity.setSerialid(aZnPromPlan.getId());
				aZnPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		aZnPromGoodsMapper.deleteByMainId(id);
		aZnPromShopMapper.deleteByMainId(id);
		aZnPromPlanMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			aZnPromGoodsMapper.deleteByMainId(id.toString());
			aZnPromShopMapper.deleteByMainId(id.toString());
			aZnPromPlanMapper.deleteById(id);
		}
	}
	
}
