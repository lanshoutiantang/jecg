package org.jeecg.modules.demo3.chongzuo.service.impl;

import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Main1;
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Goods1;
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Customer1;
import org.jeecg.modules.demo3.chongzuo.mapper.CesOrder1Goods1Mapper;
import org.jeecg.modules.demo3.chongzuo.mapper.CesOrder1Customer1Mapper;
import org.jeecg.modules.demo3.chongzuo.mapper.CesOrder1Main1Mapper;
import org.jeecg.modules.demo3.chongzuo.service.ICesOrder1Main1Service;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 商品1订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
@Service
public class CesOrder1Main1ServiceImpl extends ServiceImpl<CesOrder1Main1Mapper, CesOrder1Main1> implements ICesOrder1Main1Service {

	@Autowired
	private CesOrder1Main1Mapper cesOrder1Main1Mapper;
	@Autowired
	private CesOrder1Goods1Mapper cesOrder1Goods1Mapper;
	@Autowired
	private CesOrder1Customer1Mapper cesOrder1Customer1Mapper;
	
	@Override
	@Transactional
	public void saveMain(CesOrder1Main1 cesOrder1Main1, List<CesOrder1Goods1> cesOrder1Goods1List,List<CesOrder1Customer1> cesOrder1Customer1List) {
		cesOrder1Main1Mapper.insert(cesOrder1Main1);
		if(cesOrder1Goods1List!=null && cesOrder1Goods1List.size()>0) {
			for(CesOrder1Goods1 entity:cesOrder1Goods1List) {
				//外键设置
				entity.setOrderMainId1(cesOrder1Main1.getId());
				cesOrder1Goods1Mapper.insert(entity);
			}
		}
		if(cesOrder1Customer1List!=null && cesOrder1Customer1List.size()>0) {
			for(CesOrder1Customer1 entity:cesOrder1Customer1List) {
				//外键设置
				entity.setOrderMainId(cesOrder1Main1.getId());
				cesOrder1Customer1Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(CesOrder1Main1 cesOrder1Main1,List<CesOrder1Goods1> cesOrder1Goods1List,List<CesOrder1Customer1> cesOrder1Customer1List) {
		cesOrder1Main1Mapper.updateById(cesOrder1Main1);
		
		//1.先删除子表数据
		cesOrder1Goods1Mapper.deleteByMainId(cesOrder1Main1.getId());
		cesOrder1Customer1Mapper.deleteByMainId(cesOrder1Main1.getId());
		
		//2.子表数据重新插入
		if(cesOrder1Goods1List!=null && cesOrder1Goods1List.size()>0) {
			for(CesOrder1Goods1 entity:cesOrder1Goods1List) {
				//外键设置
				entity.setOrderMainId1(cesOrder1Main1.getId());
				cesOrder1Goods1Mapper.insert(entity);
			}
		}
		if(cesOrder1Customer1List!=null && cesOrder1Customer1List.size()>0) {
			for(CesOrder1Customer1 entity:cesOrder1Customer1List) {
				//外键设置
				entity.setOrderMainId(cesOrder1Main1.getId());
				cesOrder1Customer1Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		cesOrder1Goods1Mapper.deleteByMainId(id);
		cesOrder1Customer1Mapper.deleteByMainId(id);
		cesOrder1Main1Mapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			cesOrder1Goods1Mapper.deleteByMainId(id.toString());
			cesOrder1Customer1Mapper.deleteByMainId(id.toString());
			cesOrder1Main1Mapper.deleteById(id);
		}
	}
	
}
