package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.CesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.mapper.CesOrderCustomer1Mapper;
import org.jeecg.modules.demo3.bpm.service.ICesOrderCustomer1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class CesOrderCustomer1ServiceImpl extends ServiceImpl<CesOrderCustomer1Mapper, CesOrderCustomer1> implements ICesOrderCustomer1Service {
	
	@Autowired
	private CesOrderCustomer1Mapper cesOrderCustomer1Mapper;
	
	@Override
	public List<CesOrderCustomer1> selectByMainId(String mainId) {
		return cesOrderCustomer1Mapper.selectByMainId(mainId);
	}
}
