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
 * @Description: 季节性商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-22
 * @Version: V1.0
 */
@Data
@TableName("zn_season_xl")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_season_xl对象", description="季节性商品表")
public class ZnSeasonXl implements Serializable {
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
	/**业务区标识*/
	@Excel(name = "业务区标识", width = 15, dicCode = "city")
	@Dict(dicCode = "city")
    @ApiModelProperty(value = "业务区标识")
    private java.lang.String ywqid;
	/**小类编码*/
	@Excel(name = "小类编码", width = 15)
    @ApiModelProperty(value = "小类编码")
    private java.lang.String xlid;
	/**季节开始日期*/
	@Excel(name = "季节开始日期", width = 15)
    @ApiModelProperty(value = "季节开始日期")
    private java.lang.String begindate;
	/**季节结束日期*/
	@Excel(name = "季节结束日期", width = 15)
    @ApiModelProperty(value = "季节结束日期")
    private java.lang.String enddate;
	/**季节性商品起季是首次到货日期*/
	@Excel(name = "季节性商品起季是首次到货日期", width = 15)
    @ApiModelProperty(value = "季节性商品起季是首次到货日期")
    private java.lang.String arrdate;
	/**季节时间达到Period1，日均销量的折扣比例*/
	@Excel(name = "季节时间达到Period1，日均销量的折扣比例", width = 15)
    @ApiModelProperty(value = "季节时间达到Period1，日均销量的折扣比例")
    private java.math.BigDecimal rate1;
	/**季节时间达到Period2，日均销量的折扣比例*/
	@Excel(name = "季节时间达到Period2，日均销量的折扣比例", width = 15)
    @ApiModelProperty(value = "季节时间达到Period2，日均销量的折扣比例")
    private java.math.BigDecimal rate2;
	/**季节时间达到的比例2*/
	@Excel(name = "季节时间达到的比例2", width = 15)
    @ApiModelProperty(value = "季节时间达到的比例2")
    private java.math.BigDecimal period2;
	/**季节时间达到的比例1*/
	@Excel(name = "季节时间达到的比例1", width = 15)
    @ApiModelProperty(value = "季节时间达到的比例1")
    private java.math.BigDecimal period1;
	/**数据更新日期*/
	@Excel(name = "数据更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据更新日期")
    private java.util.Date sdate;
}
