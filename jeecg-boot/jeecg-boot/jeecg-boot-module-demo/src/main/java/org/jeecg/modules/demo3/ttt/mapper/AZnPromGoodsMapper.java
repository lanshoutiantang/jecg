package org.jeecg.modules.demo3.ttt.mapper;

import java.util.List;
import org.jeecg.modules.demo3.ttt.entity.AZnPromGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: A促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface AZnPromGoodsMapper extends BaseMapper<AZnPromGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<AZnPromGoods> selectByMainId(@Param("mainId") String mainId);
}
