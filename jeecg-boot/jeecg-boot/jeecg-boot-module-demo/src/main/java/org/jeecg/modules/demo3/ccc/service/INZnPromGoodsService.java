package org.jeecg.modules.demo3.ccc.service;

import org.jeecg.modules.demo3.ccc.entity.NZnPromGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
public interface INZnPromGoodsService extends IService<NZnPromGoods> {

	public List<NZnPromGoods> selectByMainId(String mainId);
}
