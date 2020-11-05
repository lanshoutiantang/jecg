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
 * @Description: 促销明细历史表
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("zn_prom_detail_his")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_prom_detail_his对象", description="促销明细历史表")
public class ZnPromDetailHis implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**促销计划编号*/
	@Excel(name = "促销计划编号", width = 15)
    @ApiModelProperty(value = "促销计划编号")
    private java.lang.String planid;
	/**促销计划名称*/
	@Excel(name = "促销计划名称", width = 15)
    @ApiModelProperty(value = "促销计划名称")
    private java.lang.String planname;
	/**促销开始日期时间*/
	@Excel(name = "促销开始日期时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "促销开始日期时间")
    private java.util.Date begindate;
	/**促销结束日期时间*/
	@Excel(name = "促销结束日期时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "促销结束日期时间")
    private java.util.Date enddate;
	/**门店编码*/
	@Excel(name = "门店编码", width = 15)
    @ApiModelProperty(value = "门店编码")
    private java.lang.String shopid;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
	/**促销售价*/
	@Excel(name = "促销售价", width = 15)
    @ApiModelProperty(value = "促销售价")
    private java.math.BigDecimal price;
	/**采购分货数量*/
	@Excel(name = "采购分货数量", width = 15)
    @ApiModelProperty(value = "采购分货数量")
    private java.math.BigDecimal planqty;
	/**补货标识*/
	@Excel(name = "补货标识", width = 15)
    @ApiModelProperty(value = "补货标识")
    private java.lang.String flag;
	/**备份日期*/
	@Excel(name = "备份日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "备份日期")
    private java.util.Date sdate;
}
