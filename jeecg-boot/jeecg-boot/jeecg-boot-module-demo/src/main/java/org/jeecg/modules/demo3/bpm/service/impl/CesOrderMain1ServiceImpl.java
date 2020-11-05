package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.CesOrderMain1;
import org.jeecg.modules.demo3.bpm.entity.CesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.CesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.mapper.CesOrderGoods1Mapper;
import org.jeecg.modules.demo3.bpm.mapper.CesOrderCustomer1Mapper;
import org.jeecg.modules.demo3.bpm.mapper.CesOrderMain1Mapper;
import org.jeecg.modules.demo3.bpm.service.ICesOrderMain1Service;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class CesOrderMain1ServiceImpl extends ServiceImpl<CesOrderMain1Mapper, CesOrderMain1> implements ICesOrderMain1Service {

	@Autowired
	private CesOrderMain1Mapper cesOrderMain1Mapper;
	@Autowired
	private CesOrderGoods1Mapper cesOrderGoods1Mapper;
	@Autowired
	private CesOrderCustomer1Mapper cesOrderCustomer1Mapper;
	
	@Override
	@Transactional
	public void saveMain(CesOrderMain1 cesOrderMain1, List<CesOrderGoods1> cesOrderGoods1List,List<CesOrderCustomer1> cesOrderCustomer1List) {
		cesOrderMain1Mapper.insert(cesOrderMain1);
		if(cesOrderGoods1List!=null && cesOrderGoods1List.size()>0) {
			for(CesOrderGoods1 entity:cesOrderGoods1List) {
				//外键设置
				entity.setOrderMainId1(cesOrderMain1.getId());
				cesOrderGoods1Mapper.insert(entity);
			}
		}
		if(cesOrderCustomer1List!=null && cesOrderCustomer1List.size()>0) {
			for(CesOrderCustomer1 entity:cesOrderCustomer1List) {
				//外键设置
				entity.setOrderMainId(cesOrderMain1.getId());
				cesOrderCustomer1Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(CesOrderMain1 cesOrderMain1,List<CesOrderGoods1> cesOrderGoods1List,List<CesOrderCustomer1> cesOrderCustomer1List) {
		cesOrderMain1Mapper.updateById(cesOrderMain1);
		
		//1.先删除子表数据
		cesOrderGoods1Mapper.deleteByMainId(cesOrderMain1.getId());
		cesOrderCustomer1Mapper.deleteByMainId(cesOrderMain1.getId());
		
		//2.子表数据重新插入
		if(cesOrderGoods1List!=null && cesOrderGoods1List.size()>0) {
			for(CesOrderGoods1 entity:cesOrderGoods1List) {
				//外键设置
				entity.setOrderMainId1(cesOrderMain1.getId());
				cesOrderGoods1Mapper.insert(entity);
			}
		}
		if(cesOrderCustomer1List!=null && cesOrderCustomer1List.size()>0) {
			for(CesOrderCustomer1 entity:cesOrderCustomer1List) {
				//外键设置
				entity.setOrderMainId(cesOrderMain1.getId());
				cesOrderCustomer1Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		cesOrderGoods1Mapper.deleteByMainId(id);
		cesOrderCustomer1Mapper.deleteByMainId(id);
		cesOrderMain1Mapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			cesOrderGoods1Mapper.deleteByMainId(id.toString());
			cesOrderCustomer1Mapper.deleteByMainId(id.toString());
			cesOrderMain1Mapper.deleteById(id);
		}
	}
	
}
