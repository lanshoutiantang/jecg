package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.NeCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderMain1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: Ne商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface INeCesOrderMain1Service extends IService<NeCesOrderMain1> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(NeCesOrderMain1 neCesOrderMain1,List<NeCesOrderGoods1> neCesOrderGoods1List,List<NeCesOrderCustomer1> neCesOrderCustomer1List) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(NeCesOrderMain1 neCesOrderMain1,List<NeCesOrderGoods1> neCesOrderGoods1List,List<NeCesOrderCustomer1> neCesOrderCustomer1List);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
