package org.jeecg.modules.demo3.yyy.service.impl;

import org.jeecg.modules.demo3.yyy.entity.BZnPromGoods;
import org.jeecg.modules.demo3.yyy.mapper.BZnPromGoodsMapper;
import org.jeecg.modules.demo3.yyy.service.IBZnPromGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Service
public class BZnPromGoodsServiceImpl extends ServiceImpl<BZnPromGoodsMapper, BZnPromGoods> implements IBZnPromGoodsService {
	
	@Autowired
	private BZnPromGoodsMapper bZnPromGoodsMapper;
	
	@Override
	public List<BZnPromGoods> selectByMainId(String mainId) {
		return bZnPromGoodsMapper.selectByMainId(mainId);
	}
}
