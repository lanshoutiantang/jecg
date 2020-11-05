package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.CesOrderGoods1;
import org.jeecg.modules.demo3.bpm.mapper.CesOrderGoods1Mapper;
import org.jeecg.modules.demo3.bpm.service.ICesOrderGoods1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class CesOrderGoods1ServiceImpl extends ServiceImpl<CesOrderGoods1Mapper, CesOrderGoods1> implements ICesOrderGoods1Service {
	
	@Autowired
	private CesOrderGoods1Mapper cesOrderGoods1Mapper;
	
	@Override
	public List<CesOrderGoods1> selectByMainId(String mainId) {
		return cesOrderGoods1Mapper.selectByMainId(mainId);
	}
}
