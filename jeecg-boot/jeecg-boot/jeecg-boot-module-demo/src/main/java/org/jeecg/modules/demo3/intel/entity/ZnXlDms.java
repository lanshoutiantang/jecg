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
 * @Description: 小类折扣与销量
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("zn_xl_dms")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_xl_dms对象", description="小类折扣与销量")
public class ZnXlDms implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**门店编码*/
	@Excel(name = "门店编码", width = 15)
    @ApiModelProperty(value = "门店编码")
    private java.lang.String shopid;
	/**小类编码*/
	@Excel(name = "小类编码", width = 15)
    @ApiModelProperty(value = "小类编码")
    private java.lang.String xlid;
	/**折扣比例下限*/
	@Excel(name = "折扣比例下限", width = 15)
    @ApiModelProperty(value = "折扣比例下限")
    private java.lang.Integer disc1;
	/**折扣比例上限*/
	@Excel(name = "折扣比例上限", width = 15)
    @ApiModelProperty(value = "折扣比例上限")
    private java.lang.Integer disc2;
	/**小类在disc1—disc2 折扣范围内的单品的日均销量*/
	@Excel(name = "小类在disc1—disc2 折扣范围内的单品的日均销量", width = 15)
    @ApiModelProperty(value = "小类在disc1—disc2 折扣范围内的单品的日均销量")
    private java.math.BigDecimal dmsDisc;
	/**小类在原价销售时单品的日均销量*/
	@Excel(name = "小类在原价销售时单品的日均销量", width = 15)
    @ApiModelProperty(value = "小类在原价销售时单品的日均销量")
    private java.math.BigDecimal dmsOri;
	/**本条记录更新或新增日期*/
	@Excel(name = "本条记录更新或新增日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "本条记录更新或新增日期")
    private java.util.Date sdate;
}
