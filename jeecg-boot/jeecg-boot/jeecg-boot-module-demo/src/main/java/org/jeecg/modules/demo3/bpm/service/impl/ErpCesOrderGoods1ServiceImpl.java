package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.mapper.ErpCesOrderGoods1Mapper;
import org.jeecg.modules.demo3.bpm.service.IErpCesOrderGoods1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Erp订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class ErpCesOrderGoods1ServiceImpl extends ServiceImpl<ErpCesOrderGoods1Mapper, ErpCesOrderGoods1> implements IErpCesOrderGoods1Service {
	
	@Autowired
	private ErpCesOrderGoods1Mapper erpCesOrderGoods1Mapper;
	
	@Override
	public List<ErpCesOrderGoods1> selectByMainId(String mainId) {
		return erpCesOrderGoods1Mapper.selectByMainId(mainId);
	}
}
