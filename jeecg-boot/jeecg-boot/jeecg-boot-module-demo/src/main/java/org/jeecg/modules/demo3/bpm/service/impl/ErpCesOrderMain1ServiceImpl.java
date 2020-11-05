package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderMain1;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.mapper.ErpCesOrderGoods1Mapper;
import org.jeecg.modules.demo3.bpm.mapper.ErpCesOrderCustomer1Mapper;
import org.jeecg.modules.demo3.bpm.mapper.ErpCesOrderMain1Mapper;
import org.jeecg.modules.demo3.bpm.service.IErpCesOrderMain1Service;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: Erp商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class ErpCesOrderMain1ServiceImpl extends ServiceImpl<ErpCesOrderMain1Mapper, ErpCesOrderMain1> implements IErpCesOrderMain1Service {

	@Autowired
	private ErpCesOrderMain1Mapper erpCesOrderMain1Mapper;
	@Autowired
	private ErpCesOrderGoods1Mapper erpCesOrderGoods1Mapper;
	@Autowired
	private ErpCesOrderCustomer1Mapper erpCesOrderCustomer1Mapper;
	
	@Override
	@Transactional
	public void delMain(String id) {
		erpCesOrderGoods1Mapper.deleteByMainId(id);
		erpCesOrderCustomer1Mapper.deleteByMainId(id);
		erpCesOrderMain1Mapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			erpCesOrderGoods1Mapper.deleteByMainId(id.toString());
			erpCesOrderCustomer1Mapper.deleteByMainId(id.toString());
			erpCesOrderMain1Mapper.deleteById(id);
		}
	}
	
}
