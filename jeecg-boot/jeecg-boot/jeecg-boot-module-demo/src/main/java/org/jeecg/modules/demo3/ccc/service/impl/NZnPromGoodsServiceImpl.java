package org.jeecg.modules.demo3.ccc.service.impl;

import org.jeecg.modules.demo3.ccc.entity.NZnPromGoods;
import org.jeecg.modules.demo3.ccc.mapper.NZnPromGoodsMapper;
import org.jeecg.modules.demo3.ccc.service.INZnPromGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
@Service
public class NZnPromGoodsServiceImpl extends ServiceImpl<NZnPromGoodsMapper, NZnPromGoods> implements INZnPromGoodsService {
	
	@Autowired
	private NZnPromGoodsMapper nZnPromGoodsMapper;
	
	@Override
	public List<NZnPromGoods> selectByMainId(String mainId) {
		return nZnPromGoodsMapper.selectByMainId(mainId);
	}
}
