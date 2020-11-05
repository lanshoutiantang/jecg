package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.NeCesOrderGoods1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: Ne订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface INeCesOrderGoods1Service extends IService<NeCesOrderGoods1> {

	public List<NeCesOrderGoods1> selectByMainId(String mainId);
}
