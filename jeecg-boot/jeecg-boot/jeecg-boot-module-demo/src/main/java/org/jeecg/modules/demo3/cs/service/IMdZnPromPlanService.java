package org.jeecg.modules.demo3.cs.service;

import org.jeecg.modules.demo3.cs.entity.MdZnPromShop;
import org.jeecg.modules.demo3.cs.entity.MdZnPromPlan;
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
public interface IMdZnPromPlanService extends IService<MdZnPromPlan> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(MdZnPromPlan mdZnPromPlan,List<MdZnPromShop> mdZnPromShopList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MdZnPromPlan mdZnPromPlan,List<MdZnPromShop> mdZnPromShopList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
