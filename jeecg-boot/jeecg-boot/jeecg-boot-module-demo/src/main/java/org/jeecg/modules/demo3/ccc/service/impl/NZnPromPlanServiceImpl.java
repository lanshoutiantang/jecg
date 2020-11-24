package org.jeecg.modules.demo3.ccc.service.impl;

import org.jeecg.modules.demo3.ccc.entity.NZnPromPlan;
import org.jeecg.modules.demo3.ccc.entity.NZnPromShop;
import org.jeecg.modules.demo3.ccc.entity.NZnPromGoods;
import org.jeecg.modules.demo3.ccc.mapper.NZnPromShopMapper;
import org.jeecg.modules.demo3.ccc.mapper.NZnPromGoodsMapper;
import org.jeecg.modules.demo3.ccc.mapper.NZnPromPlanMapper;
import org.jeecg.modules.demo3.ccc.service.INZnPromPlanService;
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
 * @Date:   2020-11-17
 * @Version: V1.0
 */
@Service
public class NZnPromPlanServiceImpl extends ServiceImpl<NZnPromPlanMapper, NZnPromPlan> implements INZnPromPlanService {

	@Autowired
	private NZnPromPlanMapper nZnPromPlanMapper;
	@Autowired
	private NZnPromShopMapper nZnPromShopMapper;
	@Autowired
	private NZnPromGoodsMapper nZnPromGoodsMapper;
	
	@Override
	@Transactional
	public void saveMain(NZnPromPlan nZnPromPlan, List<NZnPromShop> nZnPromShopList,List<NZnPromGoods> nZnPromGoodsList) {
		nZnPromPlanMapper.insert(nZnPromPlan);
		if(nZnPromShopList!=null && nZnPromShopList.size()>0) {
			for(NZnPromShop entity:nZnPromShopList) {
				//外键设置
				entity.setSerialid(nZnPromPlan.getId());
				nZnPromShopMapper.insert(entity);
			}
		}
		if(nZnPromGoodsList!=null && nZnPromGoodsList.size()>0) {
			for(NZnPromGoods entity:nZnPromGoodsList) {
				//外键设置
				entity.setSerialid(nZnPromPlan.getId());
				nZnPromGoodsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(NZnPromPlan nZnPromPlan,List<NZnPromShop> nZnPromShopList,List<NZnPromGoods> nZnPromGoodsList) {
		nZnPromPlanMapper.updateById(nZnPromPlan);
		
		//1.先删除子表数据
		nZnPromShopMapper.deleteByMainId(nZnPromPlan.getId());
		nZnPromGoodsMapper.deleteByMainId(nZnPromPlan.getId());
		
		//2.子表数据重新插入
		if(nZnPromShopList!=null && nZnPromShopList.size()>0) {
			for(NZnPromShop entity:nZnPromShopList) {
				//外键设置
				entity.setSerialid(nZnPromPlan.getId());
				nZnPromShopMapper.insert(entity);
			}
		}
		if(nZnPromGoodsList!=null && nZnPromGoodsList.size()>0) {
			for(NZnPromGoods entity:nZnPromGoodsList) {
				//外键设置
				entity.setSerialid(nZnPromPlan.getId());
				nZnPromGoodsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		nZnPromShopMapper.deleteByMainId(id);
		nZnPromGoodsMapper.deleteByMainId(id);
		nZnPromPlanMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			nZnPromShopMapper.deleteByMainId(id.toString());
			nZnPromGoodsMapper.deleteByMainId(id.toString());
			nZnPromPlanMapper.deleteById(id);
		}
	}
	
}
