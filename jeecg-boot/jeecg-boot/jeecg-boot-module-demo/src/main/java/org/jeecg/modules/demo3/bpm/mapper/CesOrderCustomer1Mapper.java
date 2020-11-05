package org.jeecg.modules.demo3.bpm.mapper;

import java.util.List;
import org.jeecg.modules.demo3.bpm.entity.CesOrderCustomer1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface CesOrderCustomer1Mapper extends BaseMapper<CesOrderCustomer1> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CesOrderCustomer1> selectByMainId(@Param("mainId") String mainId);
}
