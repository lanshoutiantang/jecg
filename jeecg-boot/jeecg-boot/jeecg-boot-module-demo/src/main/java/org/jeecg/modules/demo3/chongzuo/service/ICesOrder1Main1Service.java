package org.jeecg.modules.demo3.chongzuo.service;

import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Goods1;
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Customer1;
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Main1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 商品1订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
public interface ICesOrder1Main1Service extends IService<CesOrder1Main1> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(CesOrder1Main1 cesOrder1Main1,List<CesOrder1Goods1> cesOrder1Goods1List,List<CesOrder1Customer1> cesOrder1Customer1List) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(CesOrder1Main1 cesOrder1Main1,List<CesOrder1Goods1> cesOrder1Goods1List,List<CesOrder1Customer1> cesOrder1Customer1List);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
