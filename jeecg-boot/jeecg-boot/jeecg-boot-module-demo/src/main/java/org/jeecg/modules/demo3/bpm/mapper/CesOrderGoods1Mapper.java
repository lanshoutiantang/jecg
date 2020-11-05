package org.jeecg.modules.demo3.bpm.mapper;

import java.util.List;
import org.jeecg.modules.demo3.bpm.entity.CesOrderGoods1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface CesOrderGoods1Mapper extends BaseMapper<CesOrderGoods1> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CesOrderGoods1> selectByMainId(@Param("mainId") String mainId);
}
