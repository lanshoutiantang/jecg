package org.jeecg.modules.demo3.ccc.service.impl;

import org.jeecg.modules.demo3.ccc.entity.NZnPromShop;
import org.jeecg.modules.demo3.ccc.mapper.NZnPromShopMapper;
import org.jeecg.modules.demo3.ccc.service.INZnPromShopService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
@Service
public class NZnPromShopServiceImpl extends ServiceImpl<NZnPromShopMapper, NZnPromShop> implements INZnPromShopService {
	
	@Autowired
	private NZnPromShopMapper nZnPromShopMapper;
	
	@Override
	public List<NZnPromShop> selectByMainId(String mainId) {
		return nZnPromShopMapper.selectByMainId(mainId);
	}
}
