package org.jeecg.modules.demo3.chongzuo.service;

import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Goods1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单1商品
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
public interface ICesOrder1Goods1Service extends IService<CesOrder1Goods1> {

	public List<CesOrder1Goods1> selectByMainId(String mainId);
}
