package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.CesOrderGoods1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface ICesOrderGoods1Service extends IService<CesOrderGoods1> {

	public List<CesOrderGoods1> selectByMainId(String mainId);
}
