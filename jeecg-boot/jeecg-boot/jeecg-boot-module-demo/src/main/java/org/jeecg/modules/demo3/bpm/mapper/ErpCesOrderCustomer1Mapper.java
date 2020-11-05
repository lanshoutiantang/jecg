package org.jeecg.modules.demo3.bpm.mapper;

import java.util.List;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderCustomer1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: Erp订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface ErpCesOrderCustomer1Mapper extends BaseMapper<ErpCesOrderCustomer1> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ErpCesOrderCustomer1> selectByMainId(@Param("mainId") String mainId);

}
