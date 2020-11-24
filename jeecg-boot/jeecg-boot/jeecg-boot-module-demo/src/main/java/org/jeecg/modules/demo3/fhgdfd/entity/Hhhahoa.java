package org.jeecg.modules.demo3.fhgdfd.entity;

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
 * @Description: 的撒法
 * @Author: jeecg-boot
 * @Date:   2020-11-23
 * @Version: V1.0
 */
@Data
@TableName("hhhahoa")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hhhahoa对象", description="的撒法")
public class Hhhahoa implements Serializable {
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
	/** 士大夫*/
	@Excel(name = " 士大夫", width = 15, dictTable = "tb_organ", dicText = "name", dicCode = "organ")
	@Dict(dictTable = "tb_organ", dicText = "name", dicCode = "organ")
    @ApiModelProperty(value = " 士大夫")
    private java.lang.String sadf;
	/**hgjg*/
	@Excel(name = "hgjg", width = 15)
    @ApiModelProperty(value = "hgjg")
    private java.lang.String hgjj;
}
