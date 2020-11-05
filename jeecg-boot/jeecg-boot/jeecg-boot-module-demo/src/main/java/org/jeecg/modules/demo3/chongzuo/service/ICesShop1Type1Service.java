package org.jeecg.modules.demo3.chongzuo.service;

import org.jeecg.modules.demo3.chongzuo.entity.CesShop1Type1;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
public interface ICesShop1Type1Service extends IService<CesShop1Type1> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addCesShop1Type1(CesShop1Type1 cesShop1Type1);
	
	/**修改节点*/
	void updateCesShop1Type1(CesShop1Type1 cesShop1Type1) throws JeecgBootException;
	
	/**删除节点*/
	void deleteCesShop1Type1(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<CesShop1Type1> queryTreeListNoPage(QueryWrapper<CesShop1Type1> queryWrapper);

}
