package org.jeecg.modules.demo3.origin.entity;

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
 * @Description: 商品资料表
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Data
@TableName("zv_goods")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zv_goods对象", description="商品资料表")
public class ZvGoods implements Serializable {
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
	/**goodsid*/
	@Excel(name = "goodsid", width = 15)
    @ApiModelProperty(value = "goodsid")
    private java.lang.String goodsid;
	/**barcodeid*/
	@Excel(name = "barcodeid", width = 15)
    @ApiModelProperty(value = "barcodeid")
    private java.lang.String barcodeid;
	/**deptid*/
	@Excel(name = "deptid", width = 15)
    @ApiModelProperty(value = "deptid")
    private java.lang.String deptid;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private java.lang.String name;
	/**flag*/
	@Excel(name = "flag", width = 15)
    @ApiModelProperty(value = "flag")
    private java.lang.Integer flag;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private java.lang.String status;
	/**brand*/
	@Excel(name = "brand", width = 15)
    @ApiModelProperty(value = "brand")
    private java.lang.String brand;
	/**spec*/
	@Excel(name = "spec", width = 15)
    @ApiModelProperty(value = "spec")
    private java.lang.String spec;
	/**indate*/
	@Excel(name = "indate", width = 15)
    @ApiModelProperty(value = "indate")
    private java.lang.String indate;
	/**deleted*/
	@Excel(name = "deleted", width = 15)
    @ApiModelProperty(value = "deleted")
    private java.lang.String deleted;
}
