package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderMain1;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: Erp商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface IErpCesOrderMain1Service extends IService<ErpCesOrderMain1> {

	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);


}
