package org.jeecg.modules.demo3.intel.service.impl;

import org.jeecg.modules.demo3.intel.entity.ZnPromShop;
import org.jeecg.modules.demo3.intel.mapper.ZnPromShopMapper;
import org.jeecg.modules.demo3.intel.service.IZnPromShopService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Service
public class ZnPromShopServiceImpl extends ServiceImpl<ZnPromShopMapper, ZnPromShop> implements IZnPromShopService {
	
	@Autowired
	private ZnPromShopMapper znPromShopMapper;
	
	@Override
	public List<ZnPromShop> selectByMainId(String mainId) {
		return znPromShopMapper.selectByMainId(mainId);
	}
}
