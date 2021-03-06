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
 * @Description: 季节性商品
 * @Author: jeecg-boot
 * @Date:   2020-11-12
 * @Version: V1.0
 */
@Data
@TableName("zh_season_xl")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zh_season_xl对象", description="季节性商品")
public class ZhSeasonXl implements Serializable {
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
	/**季节开始与结束的中间周或日期具*/
	@Excel(name = "季节开始与结束的中间周或日期具", width = 15)
    @ApiModelProperty(value = "季节开始与结束的中间周或日期具")
    private java.lang.Integer mweek;
	/**季节内三年销量平均值*/
	@Excel(name = "季节内三年销量平均值", width = 15)
    @ApiModelProperty(value = "季节内三年销量平均值")
    private java.math.BigDecimal mqty;
	/**季节下降点*/
	@Excel(name = "季节下降点", width = 15)
    @ApiModelProperty(value = "季节下降点")
    private java.lang.Integer aweek;
	/**季节下降点之后日均销量的折扣比例*/
	@Excel(name = "季节下降点之后日均销量的折扣比例", width = 15)
    @ApiModelProperty(value = "季节下降点之后日均销量的折扣比例")
    private java.lang.String rate;
}
