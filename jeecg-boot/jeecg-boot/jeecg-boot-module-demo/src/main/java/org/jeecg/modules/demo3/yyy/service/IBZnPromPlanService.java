package org.jeecg.modules.demo3.yyy.service;

import org.jeecg.modules.demo3.yyy.entity.BZnPromGoods;
import org.jeecg.modules.demo3.yyy.entity.BZnPromShop;
import org.jeecg.modules.demo3.yyy.entity.BZnPromPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface IBZnPromPlanService extends IService<BZnPromPlan> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(BZnPromPlan bZnPromPlan,List<BZnPromGoods> bZnPromGoodsList,List<BZnPromShop> bZnPromShopList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(BZnPromPlan bZnPromPlan,List<BZnPromGoods> bZnPromGoodsList,List<BZnPromShop> bZnPromShopList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
