package org.jeecg.modules.demo3.intel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.ZnGoodsHis;
import org.jeecg.modules.demo3.intel.service.IZnGoodsHisService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 门店补货商品历史表
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Api(tags="门店补货商品历史表")
@RestController
@RequestMapping("/intel/znGoodsHis")
@Slf4j
public class ZnGoodsHisController extends JeecgController<ZnGoodsHis, IZnGoodsHisService> {
	@Autowired
	private IZnGoodsHisService znGoodsHisService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znGoodsHis
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "门店补货商品历史表-分页列表查询")
	@ApiOperation(value="门店补货商品历史表-分页列表查询", notes="门店补货商品历史表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnGoodsHis znGoodsHis,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnGoodsHis> queryWrapper = QueryGenerator.initQueryWrapper(znGoodsHis, req.getParameterMap());
		Page<ZnGoodsHis> page = new Page<ZnGoodsHis>(pageNo, pageSize);
		IPage<ZnGoodsHis> pageList = znGoodsHisService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znGoodsHis
	 * @return
	 */
	@AutoLog(value = "门店补货商品历史表-添加")
	@ApiOperation(value="门店补货商品历史表-添加", notes="门店补货商品历史表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnGoodsHis znGoodsHis) {
		znGoodsHisService.save(znGoodsHis);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znGoodsHis
	 * @return
	 */
	@AutoLog(value = "门店补货商品历史表-编辑")
	@ApiOperation(value="门店补货商品历史表-编辑", notes="门店补货商品历史表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnGoodsHis znGoodsHis) {
		znGoodsHisService.updateById(znGoodsHis);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "门店补货商品历史表-通过id删除")
	@ApiOperation(value="门店补货商品历史表-通过id删除", notes="门店补货商品历史表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znGoodsHisService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "门店补货商品历史表-批量删除")
	@ApiOperation(value="门店补货商品历史表-批量删除", notes="门店补货商品历史表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znGoodsHisService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "门店补货商品历史表-通过id查询")
	@ApiOperation(value="门店补货商品历史表-通过id查询", notes="门店补货商品历史表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnGoodsHis znGoodsHis = znGoodsHisService.getById(id);
		if(znGoodsHis==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znGoodsHis);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znGoodsHis
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnGoodsHis znGoodsHis) {
        return super.exportXls(request, znGoodsHis, ZnGoodsHis.class, "门店补货商品历史表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZnGoodsHis.class);
    }

}
