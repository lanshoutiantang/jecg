package org.jeecg.modules.demo3.intel.mapper;

import java.util.List;
import org.jeecg.modules.demo3.intel.entity.ZnPromGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
public interface ZnPromGoodsMapper extends BaseMapper<ZnPromGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ZnPromGoods> selectByMainId(@Param("mainId") String mainId);
}
