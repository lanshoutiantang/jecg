package org.jeecg.modules.demo3.ttt.service;

import org.jeecg.modules.demo3.ttt.entity.AZnPromGoods;
import org.jeecg.modules.demo3.ttt.entity.AZnPromShop;
import org.jeecg.modules.demo3.ttt.entity.AZnPromPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: A促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface IAZnPromPlanService extends IService<AZnPromPlan> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(AZnPromPlan aZnPromPlan,List<AZnPromGoods> aZnPromGoodsList,List<AZnPromShop> aZnPromShopList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(AZnPromPlan aZnPromPlan,List<AZnPromGoods> aZnPromGoodsList,List<AZnPromShop> aZnPromShopList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
