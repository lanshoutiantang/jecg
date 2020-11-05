package org.jeecg.modules.demo3.bpm.mapper;

import java.util.List;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderGoods1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: Ne订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface NeCesOrderGoods1Mapper extends BaseMapper<NeCesOrderGoods1> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<NeCesOrderGoods1> selectByMainId(@Param("mainId") String mainId);
}
