package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.NeCesOrderMain1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.mapper.NeCesOrderGoods1Mapper;
import org.jeecg.modules.demo3.bpm.mapper.NeCesOrderCustomer1Mapper;
import org.jeecg.modules.demo3.bpm.mapper.NeCesOrderMain1Mapper;
import org.jeecg.modules.demo3.bpm.service.INeCesOrderMain1Service;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: Ne商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class NeCesOrderMain1ServiceImpl extends ServiceImpl<NeCesOrderMain1Mapper, NeCesOrderMain1> implements INeCesOrderMain1Service {

	@Autowired
	private NeCesOrderMain1Mapper neCesOrderMain1Mapper;
	@Autowired
	private NeCesOrderGoods1Mapper neCesOrderGoods1Mapper;
	@Autowired
	private NeCesOrderCustomer1Mapper neCesOrderCustomer1Mapper;
	
	@Override
	@Transactional
	public void saveMain(NeCesOrderMain1 neCesOrderMain1, List<NeCesOrderGoods1> neCesOrderGoods1List,List<NeCesOrderCustomer1> neCesOrderCustomer1List) {
		neCesOrderMain1Mapper.insert(neCesOrderMain1);
		if(neCesOrderGoods1List!=null && neCesOrderGoods1List.size()>0) {
			for(NeCesOrderGoods1 entity:neCesOrderGoods1List) {
				//外键设置
				entity.setOrderMainId1(neCesOrderMain1.getId());
				neCesOrderGoods1Mapper.insert(entity);
			}
		}
		if(neCesOrderCustomer1List!=null && neCesOrderCustomer1List.size()>0) {
			for(NeCesOrderCustomer1 entity:neCesOrderCustomer1List) {
				//外键设置
				entity.setOrderMainId(neCesOrderMain1.getId());
				neCesOrderCustomer1Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(NeCesOrderMain1 neCesOrderMain1,List<NeCesOrderGoods1> neCesOrderGoods1List,List<NeCesOrderCustomer1> neCesOrderCustomer1List) {
		neCesOrderMain1Mapper.updateById(neCesOrderMain1);
		
		//1.先删除子表数据
		neCesOrderGoods1Mapper.deleteByMainId(neCesOrderMain1.getId());
		neCesOrderCustomer1Mapper.deleteByMainId(neCesOrderMain1.getId());
		
		//2.子表数据重新插入
		if(neCesOrderGoods1List!=null && neCesOrderGoods1List.size()>0) {
			for(NeCesOrderGoods1 entity:neCesOrderGoods1List) {
				//外键设置
				entity.setOrderMainId1(neCesOrderMain1.getId());
				neCesOrderGoods1Mapper.insert(entity);
			}
		}
		if(neCesOrderCustomer1List!=null && neCesOrderCustomer1List.size()>0) {
			for(NeCesOrderCustomer1 entity:neCesOrderCustomer1List) {
				//外键设置
				entity.setOrderMainId(neCesOrderMain1.getId());
				neCesOrderCustomer1Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		neCesOrderGoods1Mapper.deleteByMainId(id);
		neCesOrderCustomer1Mapper.deleteByMainId(id);
		neCesOrderMain1Mapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			neCesOrderGoods1Mapper.deleteByMainId(id.toString());
			neCesOrderCustomer1Mapper.deleteByMainId(id.toString());
			neCesOrderMain1Mapper.deleteById(id);
		}
	}
	
}
