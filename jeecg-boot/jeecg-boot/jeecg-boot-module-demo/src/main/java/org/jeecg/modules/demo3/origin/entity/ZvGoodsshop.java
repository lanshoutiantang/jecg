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
 * @Description: 门店商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Data
@TableName("zv_goodsshop")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zv_goodsshop对象", description="门店商品表")
public class ZvGoodsshop implements Serializable {
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
	/**flag*/
	@Excel(name = "flag", width = 15)
    @ApiModelProperty(value = "商品状态编码")
    private java.lang.Integer flag;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "商品状态名称")
    private java.lang.String status;
	/**stocktype*/
	@Excel(name = "stocktype", width = 15)
    @ApiModelProperty(value = "库存类型编码")
    private java.lang.Integer stocktype;
	/**stocktypename*/
	@Excel(name = "stocktypename", width = 15)
    @ApiModelProperty(value = "库存类型名称")
    private java.lang.String stocktypename;
	/**logistics*/
	@Excel(name = "logistics", width = 15)
    @ApiModelProperty(value = "logistics")
    private java.lang.String logistics;
	/**normalprice*/
	@Excel(name = "normalprice", width = 15)
    @ApiModelProperty(value = "正常价格")
    private java.lang.Double normalprice;
	/**goodsdate*/
	@Excel(name = "goodsdate", width = 15)
    @ApiModelProperty(value = "建档时间")
    private java.lang.String goodsdate;
	/**deleted*/
	@Excel(name = "deleted", width = 15)
    @ApiModelProperty(value = "删除状态")
    private java.lang.String deleted;
}
