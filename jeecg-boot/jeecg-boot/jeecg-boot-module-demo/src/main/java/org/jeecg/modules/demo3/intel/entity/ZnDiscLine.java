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
 * @Description: 折扣比例分界线
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("zn_disc_line")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_disc_line对象", description="折扣比例分界线")
public class ZnDiscLine implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**商品分类的级别*/
	@Excel(name = "商品分类的级别", width = 15)
    @ApiModelProperty(value = "商品分类的级别")
    private java.lang.String level;
	/**部类或采购线id*/
	@Excel(name = "部类或采购线id", width = 15)
    @ApiModelProperty(value = "部类或采购线id")
    private java.lang.String lbid;
	/**折扣线*/
	@Excel(name = "折扣线", width = 15)
    @ApiModelProperty(value = "折扣线")
    private java.math.BigDecimal discLine;
}
