package org.jeecg.modules.demo3.yyy.mapper;

import java.util.List;
import org.jeecg.modules.demo3.yyy.entity.BZnPromShop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface BZnPromShopMapper extends BaseMapper<BZnPromShop> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BZnPromShop> selectByMainId(@Param("mainId") String mainId);
}
