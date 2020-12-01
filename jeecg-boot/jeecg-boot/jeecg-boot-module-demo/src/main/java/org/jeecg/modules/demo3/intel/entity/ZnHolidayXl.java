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
 * @Description: 节日专属商品类别表
 * @Author: jeecg-boot
 * @Date:   2020-11-22
 * @Version: V1.0
 */
@Data
@TableName("zn_holiday_xl")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_holiday_xl对象", description="节日专属商品类别表")
public class ZnHolidayXl implements Serializable {
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
	@Excel(name = "业务区标识", width = 15)
    @Dict(dicCode = "city")
    @ApiModelProperty(value = "业务区标识")
    private java.lang.String ywqid;
	/**节日代码*/
	@Excel(name = "节日代码", width = 15)
    @ApiModelProperty(value = "节日代码")
    private java.lang.String holidayid;
    /**节日名称*/
    @Excel(name = "节日名称", width = 15)
    @ApiModelProperty(value = "节日名称")
    private java.lang.String holidayname;
	/**小类编码*/
    @Excel(name = "小类编码", width = 15)
    @ApiModelProperty(value = "小类编码")
    private java.lang.String xlid;
    /**小类名称*/
    @Excel(name = "小类名称", width = 15)
    @ApiModelProperty(value = "小类名称")
    private java.lang.String xlname;
    /**去年小类销售额*/
	@Excel(name = "去年小类销售额", width = 15)
    @ApiModelProperty(value = "去年小类销售额")
    private java.math.BigDecimal salevalue;
	/**春节指数*/
	@Excel(name = "春节指数", width = 15)
    @ApiModelProperty(value = "春节指数")
    private java.math.BigDecimal zs;
	/**节日安全系数*/
	@Excel(name = "节日安全系数", width = 15)
    @ApiModelProperty(value = "节日安全系数")
    private java.math.BigDecimal rate;
}
