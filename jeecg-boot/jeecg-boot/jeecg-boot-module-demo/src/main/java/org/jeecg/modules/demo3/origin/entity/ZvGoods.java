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
 * @Date:   2020-11-16
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
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
	/**商品分类*/
	@Excel(name = "商品分类", width = 15)
    @ApiModelProperty(value = "商品分类")
    private java.lang.String barcodeid;
	/**商品分类（小类）*/
	@Excel(name = "商品分类（小类）", width = 15)
    @ApiModelProperty(value = "商品分类（小类）")
    private java.lang.String deptid;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
    @ApiModelProperty(value = "商品名称")
    private java.lang.String name;
	/**商品状态*/
	@Excel(name = "商品状态", width = 15)
    @ApiModelProperty(value = "商品状态")
    private java.lang.Integer flag;
	/**商品状态名称*/
	@Excel(name = "商品状态名称", width = 15)
    @ApiModelProperty(value = "商品状态名称")
    private java.lang.String status;
	/**品牌*/
	@Excel(name = "品牌", width = 15)
    @ApiModelProperty(value = "品牌")
    private java.lang.String brand;
	/**规格*/
	@Excel(name = "规格", width = 15)
    @ApiModelProperty(value = "规格")
    private java.lang.String spec;
	/**建档日期*/
	@Excel(name = "建档日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "建档日期")
    private java.util.Date indate;
	/**删除状态*/
	@Excel(name = "删除状态", width = 15)
    @ApiModelProperty(value = "删除状态")
    private java.lang.String deleted;
}
