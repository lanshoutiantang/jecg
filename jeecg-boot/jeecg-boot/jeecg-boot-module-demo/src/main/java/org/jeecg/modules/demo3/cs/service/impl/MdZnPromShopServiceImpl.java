package org.jeecg.modules.demo3.cs.service.impl;

import org.jeecg.modules.demo3.cs.entity.MdZnPromShop;
import org.jeecg.modules.demo3.cs.mapper.MdZnPromShopMapper;
import org.jeecg.modules.demo3.cs.service.IMdZnPromShopService;
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
public class MdZnPromShopServiceImpl extends ServiceImpl<MdZnPromShopMapper, MdZnPromShop> implements IMdZnPromShopService {
	
	@Autowired
	private MdZnPromShopMapper mdZnPromShopMapper;
	
	@Override
	public List<MdZnPromShop> selectByMainId(String mainId) {
		return mdZnPromShopMapper.selectByMainId(mainId);
	}
}
