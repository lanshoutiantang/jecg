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
 * @Description: 最小库存与最小订货量表
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Data
@TableName("zv_minorder")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zv_minorder对象", description="最小库存与最小订货量表")
public class ZvMinorder implements Serializable {
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
	/**shopid*/
	@Excel(name = "shopid", width = 15)
    @ApiModelProperty(value = "商店编码")
    private java.lang.String shopid;
	/**goodsid*/
	@Excel(name = "goodsid", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
	/**minorderqty*/
	@Excel(name = "minorderqty", width = 15)
    @ApiModelProperty(value = "最小订货量")
    private java.lang.Double minorderqty;
	/**minstockqty*/
	@Excel(name = "minstockqty", width = 15)
    @ApiModelProperty(value = "最小库存量")
    private java.lang.Double minstockqty;
}
