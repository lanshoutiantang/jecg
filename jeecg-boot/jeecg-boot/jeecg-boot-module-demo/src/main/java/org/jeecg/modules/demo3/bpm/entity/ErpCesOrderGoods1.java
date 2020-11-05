package org.jeecg.modules.demo3.bpm.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.common.aspect.annotation.Dict;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: Erp订单商品
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Data
@TableName("ces_order_goods1")
@ApiModel(value="ces_order_main1对象", description="Erp商品订单表")
public class ErpCesOrderGoods1 implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
	@ApiModelProperty(value = "商品名称")
	private java.lang.String goodsName;
	/**价格*/
	@Excel(name = "价格", width = 15)
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal price;
	/**数量*/
	@Excel(name = "数量", width = 15)
	@ApiModelProperty(value = "数量")
	private java.lang.Integer num;
	/**单品总价*/
	@Excel(name = "单品总价", width = 15)
	@ApiModelProperty(value = "单品总价")
	private java.math.BigDecimal zongPrice;
	/**订单id*/
	@ApiModelProperty(value = "订单id")
	private java.lang.String orderMainId1;
}
