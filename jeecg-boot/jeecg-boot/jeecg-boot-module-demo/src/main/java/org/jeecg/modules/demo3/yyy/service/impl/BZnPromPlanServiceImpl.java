package org.jeecg.modules.demo3.yyy.service.impl;

import org.jeecg.modules.demo3.yyy.entity.BZnPromPlan;
import org.jeecg.modules.demo3.yyy.entity.BZnPromGoods;
import org.jeecg.modules.demo3.yyy.entity.BZnPromShop;
import org.jeecg.modules.demo3.yyy.mapper.BZnPromGoodsMapper;
import org.jeecg.modules.demo3.yyy.mapper.BZnPromShopMapper;
import org.jeecg.modules.demo3.yyy.mapper.BZnPromPlanMapper;
import org.jeecg.modules.demo3.yyy.service.IBZnPromPlanService;
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
public class BZnPromPlanServiceImpl extends ServiceImpl<BZnPromPlanMapper, BZnPromPlan> implements IBZnPromPlanService {

	@Autowired
	private BZnPromPlanMapper bZnPromPlanMapper;
	@Autowired
	private BZnPromGoodsMapper bZnPromGoodsMapper;
	@Autowired
	private BZnPromShopMapper bZnPromShopMapper;
	
	@Override
	@Transactional
	public void saveMain(BZnPromPlan bZnPromPlan, List<BZnPromGoods> bZnPromGoodsList,List<BZnPromShop> bZnPromShopList) {
		bZnPromPlanMapper.insert(bZnPromPlan);
		if(bZnPromGoodsList!=null && bZnPromGoodsList.size()>0) {
			for(BZnPromGoods entity:bZnPromGoodsList) {
				//外键设置
				entity.setSerialid(bZnPromPlan.getId());
				bZnPromGoodsMapper.insert(entity);
			}
		}
		if(bZnPromShopList!=null && bZnPromShopList.size()>0) {
			for(BZnPromShop entity:bZnPromShopList) {
				//外键设置
				entity.setSerialid(bZnPromPlan.getId());
				bZnPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(BZnPromPlan bZnPromPlan,List<BZnPromGoods> bZnPromGoodsList,List<BZnPromShop> bZnPromShopList) {
		bZnPromPlanMapper.updateById(bZnPromPlan);
		
		//1.先删除子表数据
		bZnPromGoodsMapper.deleteByMainId(bZnPromPlan.getId());
		bZnPromShopMapper.deleteByMainId(bZnPromPlan.getId());
		
		//2.子表数据重新插入
		if(bZnPromGoodsList!=null && bZnPromGoodsList.size()>0) {
			for(BZnPromGoods entity:bZnPromGoodsList) {
				//外键设置
				entity.setSerialid(bZnPromPlan.getId());
				bZnPromGoodsMapper.insert(entity);
			}
		}
		if(bZnPromShopList!=null && bZnPromShopList.size()>0) {
			for(BZnPromShop entity:bZnPromShopList) {
				//外键设置
				entity.setSerialid(bZnPromPlan.getId());
				bZnPromShopMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		bZnPromGoodsMapper.deleteByMainId(id);
		bZnPromShopMapper.deleteByMainId(id);
		bZnPromPlanMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			bZnPromGoodsMapper.deleteByMainId(id.toString());
			bZnPromShopMapper.deleteByMainId(id.toString());
			bZnPromPlanMapper.deleteById(id);
		}
	}
	
}
