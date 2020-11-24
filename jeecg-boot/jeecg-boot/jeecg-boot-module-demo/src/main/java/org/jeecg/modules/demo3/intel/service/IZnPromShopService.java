package org.jeecg.modules.demo3.intel.service;

import org.jeecg.modules.demo3.intel.entity.ZnPromShop;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
public interface IZnPromShopService extends IService<ZnPromShop> {

	public List<ZnPromShop> selectByMainId(String mainId);
}
