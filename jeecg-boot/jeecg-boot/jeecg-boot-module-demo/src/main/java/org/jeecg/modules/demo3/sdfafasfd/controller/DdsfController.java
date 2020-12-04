package org.jeecg.modules.demo3.sdfafasfd.controller;

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
import org.jeecg.modules.demo3.sdfafasfd.entity.Ddsf;
import org.jeecg.modules.demo3.sdfafasfd.service.IDdsfService;

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
 * @Description: dsfd
 * @Author: jeecg-boot
 * @Date:   2020-12-03
 * @Version: V1.0
 */
@Api(tags="dsfd")
@RestController
@RequestMapping("/sdfafasfd/ddsf")
@Slf4j
public class DdsfController extends JeecgController<Ddsf, IDdsfService> {
	@Autowired
	private IDdsfService ddsfService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ddsf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "dsfd-分页列表查询")
	@ApiOperation(value="dsfd-分页列表查询", notes="dsfd-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ddsf ddsf,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ddsf> queryWrapper = QueryGenerator.initQueryWrapper(ddsf, req.getParameterMap());
		Page<Ddsf> page = new Page<Ddsf>(pageNo, pageSize);
		IPage<Ddsf> pageList = ddsfService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ddsf
	 * @return
	 */
	@AutoLog(value = "dsfd-添加")
	@ApiOperation(value="dsfd-添加", notes="dsfd-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ddsf ddsf) {
		ddsfService.save(ddsf);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ddsf
	 * @return
	 */
	@AutoLog(value = "dsfd-编辑")
	@ApiOperation(value="dsfd-编辑", notes="dsfd-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ddsf ddsf) {
		ddsfService.updateById(ddsf);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "dsfd-通过id删除")
	@ApiOperation(value="dsfd-通过id删除", notes="dsfd-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ddsfService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "dsfd-批量删除")
	@ApiOperation(value="dsfd-批量删除", notes="dsfd-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ddsfService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "dsfd-通过id查询")
	@ApiOperation(value="dsfd-通过id查询", notes="dsfd-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ddsf ddsf = ddsfService.getById(id);
		if(ddsf==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ddsf);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ddsf
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Ddsf ddsf) {
        return super.exportXls(request, ddsf, Ddsf.class, "dsfd");
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
        return super.importExcel(request, response, Ddsf.class);
    }

}
