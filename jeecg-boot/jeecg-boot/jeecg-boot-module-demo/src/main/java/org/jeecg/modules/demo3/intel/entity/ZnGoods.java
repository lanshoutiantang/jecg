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
 * @Description: 门店补货商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Data
@TableName("zn_goods")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_goods对象", description="门店补货商品表")
public class ZnGoods implements Serializable {
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
	/**门店编码*/
    @Excel(name = " 门店编码", width = 15, dictTable = "tb_organ", dicText = "name", dicCode = "organ")
    @Dict(dictTable = "tb_organ", dicText = "name", dicCode = "organ")
    @ApiModelProperty(value = "门店编码")
    private java.lang.String shopid;
    /**部类编码*/
    @Excel(name = " 部类编码", width = 15, dictTable = "blcode_name", dicText = "blname", dicCode = "blid")
    @Dict(dictTable = "blcode_name", dicText = "blname", dicCode = "blid")
    @ApiModelProperty(value = "部类编码")
    private java.lang.String blid;
    /**课类编码*/
    @Excel(name = " 部类编码", width = 15, dictTable = "klcode_name", dicText = "klname", dicCode = "klid")
    @Dict(dictTable = "klcode_name", dicText = "klname", dicCode = "klid")
    @ApiModelProperty(value = "科类编码")
    private java.lang.String klid;
	/**小类编码*/
	@Excel(name = "小类编码", width = 15)
    @ApiModelProperty(value = "小类编码")
    private java.lang.String xlid;
    /**小类名称*/
    @Excel(name = "小类名称", width = 15)
    @ApiModelProperty(value = "小类名称")
    private java.lang.String xlname;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
    /**商品名称*/
    @Excel(name = "商品名称", width = 15)
    @ApiModelProperty(value = "商品名称")
    private java.lang.String goodsname;
	/**补货类型*/
	@Excel(name = "补货类型", width = 15)
    @ApiModelProperty(value = "补货类型")
    private java.lang.String classtype;
	/**季节属性*/
	@Excel(name = "季节属性", width = 15)
    @ApiModelProperty(value = "季节属性")
    private java.lang.String seasontype;
	/**季节性商品起季时首次到货日期*/
	@Excel(name = "季节性商品起季时首次到货日期", width = 15)
    @ApiModelProperty(value = "季节性商品起季时首次到货日期")
    private java.lang.String arrdate;
	/**季节下降点*/
	@Excel(name = "季节下降点", width = 15)
    @ApiModelProperty(value = "季节下降点")
    private java.lang.Integer aweek;
	/**季节下降点之后日均销量的折扣比例*/
	@Excel(name = "季节下降点之后日均销量的折扣比例", width = 15)
    @ApiModelProperty(value = "季节下降点之后日均销量的折扣比例")
    private java.lang.String rate;
	/**节日属性*/
	@Excel(name = "节日属性", width = 15)
    @ApiModelProperty(value = "节日属性")
    private java.lang.String holidaytype;
	/**节日开始日期*/
	@Excel(name = "节日开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "节日开始日期")
    private java.util.Date holidayBegindate;
	/**节日结束日期*/
	@Excel(name = "节日结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "节日结束日期")
    private java.util.Date holidayEnddate;
	/**春节指数*/
	@Excel(name = "春节指数", width = 15)
    @ApiModelProperty(value = "春节指数")
    private java.math.BigDecimal zs;
	/**节日安全系数*/
	@Excel(name = "节日安全系数", width = 15)
    @ApiModelProperty(value = "节日安全系数")
    private java.math.BigDecimal hoRate;
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
    @Excel(name = " 补货标识", width = 15, dicCode = "flag")
    @Dict(dicCode = "flag")
//	@Excel(name = "补货标识", width = 15)
    @ApiModelProperty(value = "补货标识")
    private java.lang.String flag;
	/**更新日期时间*/
	@Excel(name = "更新日期时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期时间")
    private java.util.Date sdate;
    /**业务区机构编码*/
    @Excel(name = "业务区机构编码", width = 15)
    @ApiModelProperty(value = "业务区机构编码")
    private java.lang.String ywqOrgan;
    /**门店机构编码*/
    @Excel(name = "门店机构编码", width = 15)
    @ApiModelProperty(value = "门店机构编码")
    private java.lang.String shopOrgan;
    /**部类机构编码*/
    @Excel(name = "部类机构编码", width = 15)
    @ApiModelProperty(value = "部类机构编码")
    private java.lang.String blOrgan;
    /**课类机构编码*/
    @Excel(name = "课类机构编码", width = 15)
    @ApiModelProperty(value = "课类机构编码")
    private java.lang.String klOrgan;
    /**部类全部机构编码*/
    @Excel(name = "部类全部机构编码", width = 15)
    @ApiModelProperty(value = "部类全部机构编码")
    private java.lang.String blOrganAll;
    /**课类全部机构编码*/
    @Excel(name = "课类全部机构编码", width = 15)
    @ApiModelProperty(value = "课类全部机构编码")
    private java.lang.String klOrganAll;
}
