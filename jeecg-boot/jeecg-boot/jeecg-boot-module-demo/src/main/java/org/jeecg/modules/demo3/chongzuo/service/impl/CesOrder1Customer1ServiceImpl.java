package org.jeecg.modules.demo3.chongzuo.service.impl;

import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Customer1;
import org.jeecg.modules.demo3.chongzuo.mapper.CesOrder1Customer1Mapper;
import org.jeecg.modules.demo3.chongzuo.service.ICesOrder1Customer1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单1客户
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
@Service
public class CesOrder1Customer1ServiceImpl extends ServiceImpl<CesOrder1Customer1Mapper, CesOrder1Customer1> implements ICesOrder1Customer1Service {
	
	@Autowired
	private CesOrder1Customer1Mapper cesOrder1Customer1Mapper;
	
	@Override
	public List<CesOrder1Customer1> selectByMainId(String mainId) {
		return cesOrder1Customer1Mapper.selectByMainId(mainId);
	}
}
