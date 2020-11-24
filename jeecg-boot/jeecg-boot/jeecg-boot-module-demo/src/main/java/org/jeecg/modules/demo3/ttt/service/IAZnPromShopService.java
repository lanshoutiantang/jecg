package org.jeecg.modules.demo3.ttt.service;

import org.jeecg.modules.demo3.ttt.entity.AZnPromShop;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: A促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface IAZnPromShopService extends IService<AZnPromShop> {

	public List<AZnPromShop> selectByMainId(String mainId);
}
