package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderGoods1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: Erp订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface IErpCesOrderGoods1Service extends IService<ErpCesOrderGoods1> {

	public List<ErpCesOrderGoods1> selectByMainId(String mainId);
}
