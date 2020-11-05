package org.jeecg.modules.demo3.bbb.controller;

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
import org.jeecg.modules.demo3.bbb.entity.Dddd;
import org.jeecg.modules.demo3.bbb.service.IDdddService;

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
 * @Description: dddd
 * @Author: jeecg-boot
 * @Date:   2020-11-02
 * @Version: V1.0
 */
@Api(tags="dddd")
@RestController
@RequestMapping("/bbb/dddd")
@Slf4j
public class DdddController extends JeecgController<Dddd, IDdddService> {
	@Autowired
	private IDdddService ddddService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dddd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "dddd-分页列表查询")
	@ApiOperation(value="dddd-分页列表查询", notes="dddd-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dddd dddd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dddd> queryWrapper = QueryGenerator.initQueryWrapper(dddd, req.getParameterMap());
		Page<Dddd> page = new Page<Dddd>(pageNo, pageSize);
		IPage<Dddd> pageList = ddddService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param dddd
	 * @return
	 */
	@AutoLog(value = "dddd-添加")
	@ApiOperation(value="dddd-添加", notes="dddd-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dddd dddd) {
		ddddService.save(dddd);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param dddd
	 * @return
	 */
	@AutoLog(value = "dddd-编辑")
	@ApiOperation(value="dddd-编辑", notes="dddd-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dddd dddd) {
		ddddService.updateById(dddd);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "dddd-通过id删除")
	@ApiOperation(value="dddd-通过id删除", notes="dddd-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ddddService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "dddd-批量删除")
	@ApiOperation(value="dddd-批量删除", notes="dddd-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ddddService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "dddd-通过id查询")
	@ApiOperation(value="dddd-通过id查询", notes="dddd-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dddd dddd = ddddService.getById(id);
		if(dddd==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(dddd);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param dddd
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dddd dddd) {
        return super.exportXls(request, dddd, Dddd.class, "dddd");
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
        return super.importExcel(request, response, Dddd.class);
    }

}
