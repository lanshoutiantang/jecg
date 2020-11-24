package org.jeecg.modules.demo3.cs.mapper;

import java.util.List;
import org.jeecg.modules.demo3.cs.entity.MdZnPromShop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销门店表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface MdZnPromShopMapper extends BaseMapper<MdZnPromShop> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MdZnPromShop> selectByMainId(@Param("mainId") String mainId);
}
