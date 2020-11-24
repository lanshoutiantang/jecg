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
 * @Description: 商品售价与日均销量
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
@Data
@TableName("zn_price_dms")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_price_dms对象", description="商品售价与日均销量")
public class ZnPriceDms implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**门店编码*/
	@Excel(name = "门店编码", width = 15)
    @ApiModelProperty(value = "门店编码")
    private java.lang.String shopid;
	/**商品类别（小类）*/
	@Excel(name = "商品类别（小类）", width = 15)
    @ApiModelProperty(value = "商品类别（小类）")
    private java.lang.String xlid;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
	/**定价*/
	@Excel(name = "定价", width = 15)
    @ApiModelProperty(value = "定价")
    private java.math.BigDecimal normalprice;
	/**价格段下限*/
	@Excel(name = "价格段下限", width = 15)
    @ApiModelProperty(value = "价格段下限")
    private java.math.BigDecimal band1;
	/**价格段上限*/
	@Excel(name = "价格段上限", width = 15)
    @ApiModelProperty(value = "价格段上限")
    private java.math.BigDecimal band2;
	/**折扣下限*/
	@Excel(name = "折扣下限", width = 15)
    @ApiModelProperty(value = "折扣下限")
    private java.lang.Integer disc1;
	/**折扣上限*/
	@Excel(name = "折扣上限", width = 15)
    @ApiModelProperty(value = "折扣上限")
    private java.lang.Integer disc2;
	/**实际售价*/
	@Excel(name = "实际售价", width = 15)
    @ApiModelProperty(value = "实际售价")
    private java.math.BigDecimal trueprice;
	/**实际售价对应的日均销量*/
	@Excel(name = "实际售价对应的日均销量", width = 15)
    @ApiModelProperty(value = "实际售价对应的日均销量")
    private java.math.BigDecimal dms;
	/**更新或新增日期*/
	@Excel(name = "更新或新增日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新或新增日期")
    private java.util.Date sdate;
}
