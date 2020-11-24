package org.jeecg.modules.demo3.ccc.mapper;

import java.util.List;
import org.jeecg.modules.demo3.ccc.entity.NZnPromShop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
public interface NZnPromShopMapper extends BaseMapper<NZnPromShop> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<NZnPromShop> selectByMainId(@Param("mainId") String mainId);
}
