package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.mapper.ErpCesOrderCustomer1Mapper;
import org.jeecg.modules.demo3.bpm.service.IErpCesOrderCustomer1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Erp订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class ErpCesOrderCustomer1ServiceImpl extends ServiceImpl<ErpCesOrderCustomer1Mapper, ErpCesOrderCustomer1> implements IErpCesOrderCustomer1Service {
	
	@Autowired
	private ErpCesOrderCustomer1Mapper erpCesOrderCustomer1Mapper;
	
	@Override
	public List<ErpCesOrderCustomer1> selectByMainId(String mainId) {
		return erpCesOrderCustomer1Mapper.selectByMainId(mainId);
	}
}
