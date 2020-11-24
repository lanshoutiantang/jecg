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
 * @Description: 商品类别表
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Data
@TableName("zv_cg_dl_zl_xl")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zv_cg_dl_zl_xl对象", description="商品类别表")
public class ZvCgDlZlXl implements Serializable {
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
	/**cgid*/
	@Excel(name = "cgid", width = 15)
    @ApiModelProperty(value = "采购类编码")
    private java.lang.String cgid;
	/**cgname*/
	@Excel(name = "cgname", width = 15)
    @ApiModelProperty(value = "采购类名称")
    private java.lang.String cgname;
	/**cg*/
	@Excel(name = "cg", width = 15)
    @ApiModelProperty(value = "采购类")
    private java.lang.String cg;
	/**dlid*/
	@Excel(name = "dlid", width = 15)
    @ApiModelProperty(value = "大类编码")
    private java.lang.String dlid;
	/**dlname*/
	@Excel(name = "dlname", width = 15)
    @ApiModelProperty(value = "大类名称")
    private java.lang.String dlname;
	/**dl*/
	@Excel(name = "dl", width = 15)
    @ApiModelProperty(value = "大类")
    private java.lang.String dl;
	/**zlid*/
	@Excel(name = "zlid", width = 15)
    @ApiModelProperty(value = "中类编码")
    private java.lang.String zlid;
	/**zlname*/
	@Excel(name = "zlname", width = 15)
    @ApiModelProperty(value = "中类名称")
    private java.lang.String zlname;
	/**zl*/
	@Excel(name = "zl", width = 15)
    @ApiModelProperty(value = "中类")
    private java.lang.String zl;
	/**xlid*/
	@Excel(name = "xlid", width = 15)
    @ApiModelProperty(value = "小类编码")
    private java.lang.String xlid;
	/**xlname*/
	@Excel(name = "xlname", width = 15)
    @ApiModelProperty(value = "小类名称")
    private java.lang.String xlname;
	/**xl*/
	@Excel(name = "xl", width = 15)
    @ApiModelProperty(value = "小类")
    private java.lang.String xl;
	/**pdtjflag*/
	@Excel(name = "pdtjflag", width = 15)
    @ApiModelProperty(value = "pdtjflag")
    private java.lang.Integer pdtjflag;
	/**qdhtj*/
	@Excel(name = "qdhtj", width = 15)
    @ApiModelProperty(value = "qdhtj")
    private java.lang.Integer qdhtj;
}
