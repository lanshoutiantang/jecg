package org.jeecg.modules.demo3.bpm.service.impl;

import org.jeecg.modules.demo3.bpm.entity.NeCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.mapper.NeCesOrderGoods1Mapper;
import org.jeecg.modules.demo3.bpm.service.INeCesOrderGoods1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Ne订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Service
public class NeCesOrderGoods1ServiceImpl extends ServiceImpl<NeCesOrderGoods1Mapper, NeCesOrderGoods1> implements INeCesOrderGoods1Service {
	
	@Autowired
	private NeCesOrderGoods1Mapper neCesOrderGoods1Mapper;
	
	@Override
	public List<NeCesOrderGoods1> selectByMainId(String mainId) {
		return neCesOrderGoods1Mapper.selectByMainId(mainId);
	}
}
