package org.jeecg.modules.demo3.ccc.mapper;

import java.util.List;
import org.jeecg.modules.demo3.ccc.entity.NZnPromGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
public interface NZnPromGoodsMapper extends BaseMapper<NZnPromGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<NZnPromGoods> selectByMainId(@Param("mainId") String mainId);
}
