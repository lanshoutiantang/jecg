package org.jeecg.modules.demo3.intel.service;

import org.jeecg.modules.demo3.intel.entity.ZnPromShop;
import org.jeecg.modules.demo3.intel.entity.ZnPromGoods;
import org.jeecg.modules.demo3.intel.entity.ZnPromPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
public interface IZnPromPlanService extends IService<ZnPromPlan> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ZnPromPlan znPromPlan,List<ZnPromShop> znPromShopList,List<ZnPromGoods> znPromGoodsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ZnPromPlan znPromPlan,List<ZnPromShop> znPromShopList,List<ZnPromGoods> znPromGoodsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
