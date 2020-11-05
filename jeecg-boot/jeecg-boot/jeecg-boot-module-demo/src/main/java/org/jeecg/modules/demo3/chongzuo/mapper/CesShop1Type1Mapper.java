package org.jeecg.modules.demo3.chongzuo.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo3.chongzuo.entity.CesShop1Type1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
public interface CesShop1Type1Mapper extends BaseMapper<CesShop1Type1> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
