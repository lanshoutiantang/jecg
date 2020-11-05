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
 * @Description: 阳历与阴历对应关系表
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("zn_calender")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_calender对象", description="阳历与阴历对应关系表")
public class ZnCalender implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**阳历日期*/
	@Excel(name = "阳历日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "阳历日期")
    private java.util.Date sundatedate;
	/**阴历日期*/
	@Excel(name = "阴历日期", width = 15)
    @ApiModelProperty(value = "阴历日期")
    private java.lang.String moondate;
	/**节日标识*/
	@Excel(name = "节日标识", width = 15)
    @ApiModelProperty(value = "节日标识")
    private java.lang.String holidayid;
	/**节日名称*/
	@Excel(name = "节日名称", width = 15)
    @ApiModelProperty(value = "节日名称")
    private java.lang.String holidayname;
	/**节日启动时间点*/
	@Excel(name = "节日启动时间点", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "节日启动时间点")
    private java.util.Date startdate;
	/**节日结束时间点*/
	@Excel(name = "节日结束时间点", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "节日结束时间点")
    private java.util.Date enddate;
}
