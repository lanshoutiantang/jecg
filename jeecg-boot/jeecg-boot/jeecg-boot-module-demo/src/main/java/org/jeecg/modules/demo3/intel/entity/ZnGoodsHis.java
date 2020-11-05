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
 * @Description: 门店补货商品历史表
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("zn_goods_his")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_goods_his对象", description="门店补货商品历史表")
public class ZnGoodsHis implements Serializable {
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
	/**小类编码*/
	@Excel(name = "小类编码", width = 15)
    @ApiModelProperty(value = "小类编码")
    private java.lang.String xlid;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
	/**商品类别*/
	@Excel(name = "商品类别", width = 15)
    @ApiModelProperty(value = "商品类别")
    private java.lang.String classtype;
	/**定价*/
	@Excel(name = "定价", width = 15)
    @ApiModelProperty(value = "定价")
    private java.math.BigDecimal normalprice;
	/**最小库存数量*/
	@Excel(name = "最小库存数量", width = 15)
    @ApiModelProperty(value = "最小库存数量")
    private java.math.BigDecimal minstock;
	/**最小起订量*/
	@Excel(name = "最小起订量", width = 15)
    @ApiModelProperty(value = "最小起订量")
    private java.math.BigDecimal minorder;
	/**昨日日终库存数量*/
	@Excel(name = "昨日日终库存数量", width = 15)
    @ApiModelProperty(value = "昨日日终库存数量")
    private java.math.BigDecimal closeqty;
	/**计算生成的补货数量*/
	@Excel(name = "计算生成的补货数量", width = 15)
    @ApiModelProperty(value = "计算生成的补货数量")
    private java.math.BigDecimal qty;
	/**补货标识*/
	@Excel(name = "补货标识", width = 15)
    @ApiModelProperty(value = "补货标识")
    private java.lang.String flag;
	/**更新日期时间*/
	@Excel(name = "更新日期时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期时间")
    private java.util.Date sdate;
	/**季节开始日期*/
	@Excel(name = "季节开始日期", width = 15)
    @ApiModelProperty(value = "季节开始日期")
    private java.lang.String seasonBegindate;
	/**节日开始日期*/
	@Excel(name = "节日开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "节日开始日期")
    private java.util.Date holidayBegindate;
	/**季节属性*/
	@Excel(name = "季节属性", width = 15)
    @ApiModelProperty(value = "季节属性")
    private java.lang.String seasontype;
	/**节日属性*/
	@Excel(name = "节日属性", width = 15)
    @ApiModelProperty(value = "节日属性")
    private java.lang.String holidaytype;
	/**节日结束日期*/
	@Excel(name = "节日结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "节日结束日期")
    private java.util.Date holidayEnddate;
}
