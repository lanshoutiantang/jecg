package org.jeecg.modules.demo3.oooo.service.impl;

import org.jeecg.modules.demo3.oooo.entity.SpZnPromPlan;
import org.jeecg.modules.demo3.oooo.entity.SpZnPromGoods;
import org.jeecg.modules.demo3.oooo.mapper.SpZnPromGoodsMapper;
import org.jeecg.modules.demo3.oooo.mapper.SpZnPromPlanMapper;
import org.jeecg.modules.demo3.oooo.service.ISpZnPromPlanService;
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
public class SpZnPromPlanServiceImpl extends ServiceImpl<SpZnPromPlanMapper, SpZnPromPlan> implements ISpZnPromPlanService {

	@Autowired
	private SpZnPromPlanMapper spZnPromPlanMapper;
	@Autowired
	private SpZnPromGoodsMapper spZnPromGoodsMapper;
	
	@Override
	@Transactional
	public void saveMain(SpZnPromPlan spZnPromPlan, List<SpZnPromGoods> spZnPromGoodsList) {
		spZnPromPlanMapper.insert(spZnPromPlan);
		if(spZnPromGoodsList!=null && spZnPromGoodsList.size()>0) {
			for(SpZnPromGoods entity:spZnPromGoodsList) {
				//外键设置
				entity.setSerialid(spZnPromPlan.getId());
				spZnPromGoodsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(SpZnPromPlan spZnPromPlan,List<SpZnPromGoods> spZnPromGoodsList) {
		spZnPromPlanMapper.updateById(spZnPromPlan);
		
		//1.先删除子表数据
		spZnPromGoodsMapper.deleteByMainId(spZnPromPlan.getId());
		
		//2.子表数据重新插入
		if(spZnPromGoodsList!=null && spZnPromGoodsList.size()>0) {
			for(SpZnPromGoods entity:spZnPromGoodsList) {
				//外键设置
				entity.setSerialid(spZnPromPlan.getId());
				spZnPromGoodsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		spZnPromGoodsMapper.deleteByMainId(id);
		spZnPromPlanMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			spZnPromGoodsMapper.deleteByMainId(id.toString());
			spZnPromPlanMapper.deleteById(id);
		}
	}
	
}
