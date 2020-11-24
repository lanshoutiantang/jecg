package org.jeecg.modules.demo3.ccc.service;

import org.jeecg.modules.demo3.ccc.entity.NZnPromShop;
import org.jeecg.modules.demo3.ccc.entity.NZnPromGoods;
import org.jeecg.modules.demo3.ccc.entity.NZnPromPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
public interface INZnPromPlanService extends IService<NZnPromPlan> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(NZnPromPlan nZnPromPlan,List<NZnPromShop> nZnPromShopList,List<NZnPromGoods> nZnPromGoodsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(NZnPromPlan nZnPromPlan,List<NZnPromShop> nZnPromShopList,List<NZnPromGoods> nZnPromGoodsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
