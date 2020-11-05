package org.jeecg.modules.demo3.aaa.entity;

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
 * @Description: online表单控件
 * @Author: jeecg-boot
 * @Date:   2020-10-31
 * @Version: V1.0
 */
@Data
@TableName("ces_filed__kongjian")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ces_filed__kongjian对象", description="online表单控件")
public class CesFiledKongjian implements Serializable {
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
	/**输入框*/
	@Excel(name = "输入框", width = 15)
    @ApiModelProperty(value = "输入框")
    private java.lang.String name;
	/**下拉框*/
	@Excel(name = "下拉框", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "下拉框")
    private java.lang.String sex;
	/**单选*/
	@Excel(name = "单选", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "单选")
    private java.lang.String radio;
	/**复选框*/
	@Excel(name = "复选框", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "复选框")
    private java.lang.String checkbox;
	/**下拉多选*/
	@Excel(name = "下拉多选", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "下拉多选")
    private java.lang.String selMul;
	/**下拉搜寻*/
	@Excel(name = "下拉搜寻", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "下拉搜寻")
    private java.lang.String selSearch;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private java.lang.String pic;
	/**文件*/
	@Excel(name = "文件", width = 15)
    @ApiModelProperty(value = "文件")
    private java.lang.String files;
	/**markdown*/
	@Excel(name = "markdown", width = 15)
    @ApiModelProperty(value = "markdown")
    private java.lang.String remark;
	/**富文本*/
	@Excel(name = "富文本", width = 15)
    @ApiModelProperty(value = "富文本")
    private java.lang.String fuwenben;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "日期")
    private java.util.Date birthday;
	/**用户*/
	@Excel(name = "用户", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "用户")
    private java.lang.String userSel;
	/**部门*/
	@Excel(name = "部门", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "部门")
    private java.lang.String depSel;
}
