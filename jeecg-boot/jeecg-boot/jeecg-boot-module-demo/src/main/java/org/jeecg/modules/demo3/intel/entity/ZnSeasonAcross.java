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
 * @Description: 纯季节性商品越期补货明细表
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("zn_season_across")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_season_across对象", description="纯季节性商品越期补货明细表")
public class ZnSeasonAcross implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String shopid;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private java.lang.String goodsid;
	/**补货类型*/
	@Excel(name = "补货类型", width = 15)
    @ApiModelProperty(value = "补货类型")
    private java.lang.String flag;
	/**下次补货日期*/
	@Excel(name = "下次补货日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "下次补货日期")
    private java.util.Date nextdate;
	/**数据生成日期*/
	@Excel(name = "数据生成日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据生成日期")
    private java.util.Date sdate;
}
