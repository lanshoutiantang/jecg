package org.jeecg.modules.demo3.yyy.service.impl;

import org.jeecg.modules.demo3.yyy.entity.BZnPromShop;
import org.jeecg.modules.demo3.yyy.mapper.BZnPromShopMapper;
import org.jeecg.modules.demo3.yyy.service.IBZnPromShopService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Service
public class BZnPromShopServiceImpl extends ServiceImpl<BZnPromShopMapper, BZnPromShop> implements IBZnPromShopService {
	
	@Autowired
	private BZnPromShopMapper bZnPromShopMapper;
	
	@Override
	public List<BZnPromShop> selectByMainId(String mainId) {
		return bZnPromShopMapper.selectByMainId(mainId);
	}
}
