package org.jeecg.modules.demo3.intel.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 商品日销量表
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("zn_dayly_sales")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_dayly_sales对象", description="商品日销量表")
public class ZnDaylySales implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**date*/
	@Excel(name = "date", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "date")
    private java.util.Date sdate;
	/**门店编码*/
	@Excel(name = "门店编码", width = 15)
    @ApiModelProperty(value = "门店编码")
    private java.lang.String shopid;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
	/**实际销售额*/
	@Excel(name = "实际销售额", width = 15)
    @ApiModelProperty(value = "实际销售额")
    private java.math.BigDecimal salevalue;
	/**折扣额*/
	@Excel(name = "折扣额", width = 15)
    @ApiModelProperty(value = "折扣额")
    private java.math.BigDecimal disvalue;
	/**销售数量*/
	@Excel(name = "销售数量", width = 15)
    @ApiModelProperty(value = "销售数量")
    private java.math.BigDecimal qty;
	/**实际售价*/
	@Excel(name = "实际售价", width = 15)
    @ApiModelProperty(value = "实际售价")
    private java.math.BigDecimal disc;
}
