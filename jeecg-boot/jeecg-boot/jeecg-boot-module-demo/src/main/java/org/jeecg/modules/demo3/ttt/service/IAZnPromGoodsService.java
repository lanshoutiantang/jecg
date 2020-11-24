package org.jeecg.modules.demo3.ttt.service;

import org.jeecg.modules.demo3.ttt.entity.AZnPromGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: A促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface IAZnPromGoodsService extends IService<AZnPromGoods> {

	public List<AZnPromGoods> selectByMainId(String mainId);
}
