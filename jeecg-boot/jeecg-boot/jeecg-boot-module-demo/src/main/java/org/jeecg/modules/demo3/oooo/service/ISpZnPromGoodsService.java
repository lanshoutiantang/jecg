package org.jeecg.modules.demo3.oooo.service;

import org.jeecg.modules.demo3.oooo.entity.SpZnPromGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface ISpZnPromGoodsService extends IService<SpZnPromGoods> {

	public List<SpZnPromGoods> selectByMainId(String mainId);
}
