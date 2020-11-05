package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.NeCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.mapper.NeCesOrderCustomer1Mapper;
import org.jeecg.modules.demo3.bpm.service.INeCesOrderCustomer1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Ne订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class NeCesOrderCustomer1ServiceImpl extends ServiceImpl<NeCesOrderCustomer1Mapper, NeCesOrderCustomer1> implements INeCesOrderCustomer1Service {
	
	@Autowired
	private NeCesOrderCustomer1Mapper neCesOrderCustomer1Mapper;
	
	@Override
	public List<NeCesOrderCustomer1> selectByMainId(String mainId) {
		return neCesOrderCustomer1Mapper.selectByMainId(mainId);
	}
}
