package org.jeecg.modules.demo3.ccc.service;

import org.jeecg.modules.demo3.ccc.entity.NZnPromShop;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
public interface INZnPromShopService extends IService<NZnPromShop> {

	public List<NZnPromShop> selectByMainId(String mainId);
}
