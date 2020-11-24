package org.jeecg.modules.demo3.ttt.service.impl;

import org.jeecg.modules.demo3.ttt.entity.AZnPromGoods;
import org.jeecg.modules.demo3.ttt.mapper.AZnPromGoodsMapper;
import org.jeecg.modules.demo3.ttt.service.IAZnPromGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: A促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Service
public class AZnPromGoodsServiceImpl extends ServiceImpl<AZnPromGoodsMapper, AZnPromGoods> implements IAZnPromGoodsService {
	
	@Autowired
	private AZnPromGoodsMapper aZnPromGoodsMapper;
	
	@Override
	public List<AZnPromGoods> selectByMainId(String mainId) {
		return aZnPromGoodsMapper.selectByMainId(mainId);
	}
}
