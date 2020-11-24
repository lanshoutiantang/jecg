package org.jeecg.modules.demo3.intel.service;

import org.jeecg.modules.demo3.intel.entity.ZnPromGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
public interface IZnPromGoodsService extends IService<ZnPromGoods> {

	public List<ZnPromGoods> selectByMainId(String mainId);
}
