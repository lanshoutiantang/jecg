package org.jeecg.modules.demo3.chongzuo.service.impl;

import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Goods1;
import org.jeecg.modules.demo3.chongzuo.mapper.CesOrder1Goods1Mapper;
import org.jeecg.modules.demo3.chongzuo.service.ICesOrder1Goods1Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单1商品
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
@Service
public class CesOrder1Goods1ServiceImpl extends ServiceImpl<CesOrder1Goods1Mapper, CesOrder1Goods1> implements ICesOrder1Goods1Service {
	
	@Autowired
	private CesOrder1Goods1Mapper cesOrder1Goods1Mapper;
	
	@Override
	public List<CesOrder1Goods1> selectByMainId(String mainId) {
		return cesOrder1Goods1Mapper.selectByMainId(mainId);
	}
}
