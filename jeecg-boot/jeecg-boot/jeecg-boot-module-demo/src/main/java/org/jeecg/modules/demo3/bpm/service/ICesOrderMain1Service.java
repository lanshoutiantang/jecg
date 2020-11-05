package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.CesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.CesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.entity.CesOrderMain1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface ICesOrderMain1Service extends IService<CesOrderMain1> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(CesOrderMain1 cesOrderMain1,List<CesOrderGoods1> cesOrderGoods1List,List<CesOrderCustomer1> cesOrderCustomer1List) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(CesOrderMain1 cesOrderMain1,List<CesOrderGoods1> cesOrderGoods1List,List<CesOrderCustomer1> cesOrderCustomer1List);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
