package org.jeecg.modules.demo3.bpm.service;

import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderCustomer1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: Erp订单客户
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
public interface IErpCesOrderCustomer1Service extends IService<ErpCesOrderCustomer1> {

	public List<ErpCesOrderCustomer1> selectByMainId(String mainId);
}
