package org.jeecg.modules.demo3.ttt.service.impl;

import org.jeecg.modules.demo3.ttt.entity.AZnPromShop;
import org.jeecg.modules.demo3.ttt.mapper.AZnPromShopMapper;
import org.jeecg.modules.demo3.ttt.service.IAZnPromShopService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: A促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Service
public class AZnPromShopServiceImpl extends ServiceImpl<AZnPromShopMapper, AZnPromShop> implements IAZnPromShopService {
	
	@Autowired
	private AZnPromShopMapper aZnPromShopMapper;
	
	@Override
	public List<AZnPromShop> selectByMainId(String mainId) {
		return aZnPromShopMapper.selectByMainId(mainId);
	}
}
