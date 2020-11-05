package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.CesOrderCustomer1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface ICesOrderCustomer1Service extends IService<CesOrderCustomer1> {

	public List<CesOrderCustomer1> selectByMainId(String mainId);
}
