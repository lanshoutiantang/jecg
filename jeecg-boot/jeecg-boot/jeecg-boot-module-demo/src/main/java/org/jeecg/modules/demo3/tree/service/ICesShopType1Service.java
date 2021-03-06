package org.jeecg.modules.demo3.tree.service;

import org.jeecg.modules.demo3.tree.entity.CesShopType1;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface ICesShopType1Service extends IService<CesShopType1> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addCesShopType1(CesShopType1 cesShopType1);
	
	/**修改节点*/
	void updateCesShopType1(CesShopType1 cesShopType1) throws JeecgBootException;
	
	/**删除节点*/
	void deleteCesShopType1(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<CesShopType1> queryTreeListNoPage(QueryWrapper<CesShopType1> queryWrapper);

}
