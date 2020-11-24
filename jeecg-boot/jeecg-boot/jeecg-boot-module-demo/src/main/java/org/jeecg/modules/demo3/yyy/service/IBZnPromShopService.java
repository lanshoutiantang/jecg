package org.jeecg.modules.demo3.yyy.service;

import org.jeecg.modules.demo3.yyy.entity.BZnPromShop;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface IBZnPromShopService extends IService<BZnPromShop> {

	public List<BZnPromShop> selectByMainId(String mainId);
}
