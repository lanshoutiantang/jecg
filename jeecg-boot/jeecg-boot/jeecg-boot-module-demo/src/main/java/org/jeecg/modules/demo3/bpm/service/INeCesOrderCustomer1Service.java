package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.NeCesOrderCustomer1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: Ne订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface INeCesOrderCustomer1Service extends IService<NeCesOrderCustomer1> {

	public List<NeCesOrderCustomer1> selectByMainId(String mainId);
}
