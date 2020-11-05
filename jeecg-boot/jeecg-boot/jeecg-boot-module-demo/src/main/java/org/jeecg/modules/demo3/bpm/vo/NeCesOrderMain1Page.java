package org.jeecg.modules.demo3.bpm.vo;

import java.util.List;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderMain1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderCustomer1;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: Ne商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Data
@ApiModel(value="ces_order_main1Page对象", description="Ne商品订单表")
public class NeCesOrderMain1Page {

	/**主键*/
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**订单编码*/
	@Excel(name = "订单编码", width = 15)
	@ApiModelProperty(value = "订单编码")
	private java.lang.String orderCode;
	/**下单时间*/
	@Excel(name = "下单时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "下单时间")
	private java.util.Date xdDate;
	/**订单金额*/
	@Excel(name = "订单金额", width = 15)
	@ApiModelProperty(value = "订单金额")
	private java.math.BigDecimal money;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;
	
	@ExcelCollection(name="Ne订单商品")
	@ApiModelProperty(value = "Ne订单商品")
	private List<NeCesOrderGoods1> neCesOrderGoods1List;
	@ExcelCollection(name="Ne订单客户")
	@ApiModelProperty(value = "Ne订单客户")
	private List<NeCesOrderCustomer1> neCesOrderCustomer1List;
	
}
