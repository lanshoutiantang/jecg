package org.jeecg.modules.demo3.yyy.mapper;

import java.util.List;
import org.jeecg.modules.demo3.yyy.entity.BZnPromGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface BZnPromGoodsMapper extends BaseMapper<BZnPromGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BZnPromGoods> selectByMainId(@Param("mainId") String mainId);
}
