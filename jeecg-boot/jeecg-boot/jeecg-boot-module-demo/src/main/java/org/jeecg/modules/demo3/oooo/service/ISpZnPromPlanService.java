package org.jeecg.modules.demo3.oooo.service;

import org.jeecg.modules.demo3.oooo.entity.SpZnPromGoods;
import org.jeecg.modules.demo3.oooo.entity.SpZnPromPlan;
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
public interface ISpZnPromPlanService extends IService<SpZnPromPlan> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(SpZnPromPlan spZnPromPlan,List<SpZnPromGoods> spZnPromGoodsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(SpZnPromPlan spZnPromPlan,List<SpZnPromGoods> spZnPromGoodsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
