package org.jeecg.modules.demo3.tree.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo3.tree.entity.CesShopType1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface CesShopType1Mapper extends BaseMapper<CesShopType1> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
