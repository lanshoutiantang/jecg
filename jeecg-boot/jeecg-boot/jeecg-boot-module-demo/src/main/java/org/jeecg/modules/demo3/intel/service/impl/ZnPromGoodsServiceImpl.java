package org.jeecg.modules.demo3.intel.service.impl;

import org.jeecg.modules.demo3.intel.entity.ZnPromGoods;
import org.jeecg.modules.demo3.intel.mapper.ZnPromGoodsMapper;
import org.jeecg.modules.demo3.intel.service.IZnPromGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Service
public class ZnPromGoodsServiceImpl extends ServiceImpl<ZnPromGoodsMapper, ZnPromGoods> implements IZnPromGoodsService {
	
	@Autowired
	private ZnPromGoodsMapper znPromGoodsMapper;
	
	@Override
	public List<ZnPromGoods> selectByMainId(String mainId) {
		return znPromGoodsMapper.selectByMainId(mainId);
	}
}
