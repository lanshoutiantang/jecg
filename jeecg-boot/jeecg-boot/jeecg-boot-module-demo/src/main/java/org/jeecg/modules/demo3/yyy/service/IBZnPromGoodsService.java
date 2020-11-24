package org.jeecg.modules.demo3.yyy.service;

import org.jeecg.modules.demo3.yyy.entity.BZnPromGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface IBZnPromGoodsService extends IService<BZnPromGoods> {

	public List<BZnPromGoods> selectByMainId(String mainId);
}
