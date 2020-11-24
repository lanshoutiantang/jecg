package org.jeecg.modules.demo3.intel.mapper;

import java.util.List;
import org.jeecg.modules.demo3.intel.entity.ZnPromShop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
public interface ZnPromShopMapper extends BaseMapper<ZnPromShop> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ZnPromShop> selectByMainId(@Param("mainId") String mainId);
}
