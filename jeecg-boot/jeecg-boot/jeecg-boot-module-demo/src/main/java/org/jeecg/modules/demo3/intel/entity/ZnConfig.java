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
 * @Description: 补货参数
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
@Data
@TableName("zn_config")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_config对象", description="补货参数")
public class ZnConfig implements Serializable {
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
	/**补货类别*/
	@Excel(name = "补货类别", width = 15)
    @ApiModelProperty(value = "补货类别")
    private java.lang.String classtype;
	/**补货日*/
	@Excel(name = "补货日", width = 15)
    @ApiModelProperty(value = "补货日")
    private java.lang.String orderdate;
	/**补货周期标识*/
	@Excel(name = "补货周期标识", width = 15)
    @ApiModelProperty(value = "补货周期标识")
    private java.lang.String weekflag;
	/**安全库存天数*/
	@Excel(name = "安全库存天数", width = 15)
    @ApiModelProperty(value = "安全库存天数")
    private java.lang.Integer safedays;
	/**到货周期*/
	@Excel(name = "到货周期", width = 15)
    @ApiModelProperty(value = "到货周期")
    private java.lang.Integer arrivaldays;
	/**最小起订量可销天数*/
	@Excel(name = "最小起订量可销天数", width = 15)
    @ApiModelProperty(value = "最小起订量可销天数")
    private java.math.BigDecimal dmsMinorder;
}
