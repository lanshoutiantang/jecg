package org.jeecg.modules.demo3.oooo.service.impl;

import org.jeecg.modules.demo3.oooo.entity.SpZnPromGoods;
import org.jeecg.modules.demo3.oooo.mapper.SpZnPromGoodsMapper;
import org.jeecg.modules.demo3.oooo.service.ISpZnPromGoodsService;
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
public class SpZnPromGoodsServiceImpl extends ServiceImpl<SpZnPromGoodsMapper, SpZnPromGoods> implements ISpZnPromGoodsService {
	
	@Autowired
	private SpZnPromGoodsMapper spZnPromGoodsMapper;
	
	@Override
	public List<SpZnPromGoods> selectByMainId(String mainId) {
		return spZnPromGoodsMapper.selectByMainId(mainId);
	}
}
