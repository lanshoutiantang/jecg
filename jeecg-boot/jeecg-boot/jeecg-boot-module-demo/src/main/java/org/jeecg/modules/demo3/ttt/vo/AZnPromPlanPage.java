package org.jeecg.modules.demo3.ttt.vo;

import java.util.List;
import org.jeecg.modules.demo3.ttt.entity.AZnPromPlan;
import org.jeecg.modules.demo3.ttt.entity.AZnPromGoods;
import org.jeecg.modules.demo3.ttt.entity.AZnPromShop;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: A促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Data
@ApiModel(value="zn_prom_planPage对象", description="A促销计划表")
public class AZnPromPlanPage {

	/**主键*/
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
	/**促销计划编号*/
	@Excel(name = "促销计划编号", width = 15)
	@ApiModelProperty(value = "促销计划编号")
	private java.lang.String planid;
	/**促销计划名称*/
	@Excel(name = "促销计划名称", width = 15)
	@ApiModelProperty(value = "促销计划名称")
	private java.lang.String planname;

	@ExcelCollection(name="A促销商品表")
	@ApiModelProperty(value = "A促销商品表")
	private List<AZnPromGoods> aZnPromGoodsList;
	@ExcelCollection(name="A促销门店表")
	@ApiModelProperty(value = "A促销门店表")
	private List<AZnPromShop> aZnPromShopList;

}
