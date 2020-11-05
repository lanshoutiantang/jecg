package org.jeecg.modules.demo3.bpm.mapper;

import java.util.List;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderGoods1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: Erp订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface ErpCesOrderGoods1Mapper extends BaseMapper<ErpCesOrderGoods1> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ErpCesOrderGoods1> selectByMainId(@Param("mainId") String mainId);

}
