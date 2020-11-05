package org.jeecg.modules.demo3.chongzuo.service;

import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Customer1;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单1客户
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
public interface ICesOrder1Customer1Service extends IService<CesOrder1Customer1> {

	public List<CesOrder1Customer1> selectByMainId(String mainId);
}
