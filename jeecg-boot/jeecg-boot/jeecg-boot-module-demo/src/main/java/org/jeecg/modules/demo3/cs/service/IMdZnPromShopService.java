package org.jeecg.modules.demo3.cs.service;

import org.jeecg.modules.demo3.cs.entity.MdZnPromShop;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface IMdZnPromShopService extends IService<MdZnPromShop> {

	public List<MdZnPromShop> selectByMainId(String mainId);
}
