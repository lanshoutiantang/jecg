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
import org.jeecg.modules.demo3.intel.entity.ZnLog;
import org.jeecg.modules.demo3.intel.service.IZnLogService;

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
 * @Description: 日志表
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Api(tags="日志表")
@RestController
@RequestMapping("/intel/znLog")
@Slf4j
public class ZnLogController extends JeecgController<ZnLog, IZnLogService> {
	@Autowired
	private IZnLogService znLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "日志表-分页列表查询")
	@ApiOperation(value="日志表-分页列表查询", notes="日志表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnLog znLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnLog> queryWrapper = QueryGenerator.initQueryWrapper(znLog, req.getParameterMap());
		Page<ZnLog> page = new Page<ZnLog>(pageNo, pageSize);
		IPage<ZnLog> pageList = znLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znLog
	 * @return
	 */
	@AutoLog(value = "日志表-添加")
	@ApiOperation(value="日志表-添加", notes="日志表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnLog znLog) {
		znLogService.save(znLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znLog
	 * @return
	 */
	@AutoLog(value = "日志表-编辑")
	@ApiOperation(value="日志表-编辑", notes="日志表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnLog znLog) {
		znLogService.updateById(znLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "日志表-通过id删除")
	@ApiOperation(value="日志表-通过id删除", notes="日志表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "日志表-批量删除")
	@ApiOperation(value="日志表-批量删除", notes="日志表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "日志表-通过id查询")
	@ApiOperation(value="日志表-通过id查询", notes="日志表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnLog znLog = znLogService.getById(id);
		if(znLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znLog
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnLog znLog) {
        return super.exportXls(request, znLog, ZnLog.class, "日志表");
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
        return super.importExcel(request, response, ZnLog.class);
    }

}
