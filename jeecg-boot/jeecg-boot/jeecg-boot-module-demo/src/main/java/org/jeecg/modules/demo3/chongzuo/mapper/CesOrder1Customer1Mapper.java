package org.jeecg.modules.demo3.chongzuo.mapper;

import java.util.List;
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Customer1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单1客户
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
public interface CesOrder1Customer1Mapper extends BaseMapper<CesOrder1Customer1> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CesOrder1Customer1> selectByMainId(@Param("mainId") String mainId);
}
