package org.jeecg.modules.demo3.ccc.controller;

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
import org.jeecg.modules.demo3.ccc.entity.Fffff;
import org.jeecg.modules.demo3.ccc.service.IFffffService;

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
 * @Description: fffff
 * @Author: jeecg-boot
 * @Date:   2020-11-02
 * @Version: V1.0
 */
@Api(tags="fffff")
@RestController
@RequestMapping("/ccc/fffff")
@Slf4j
public class FffffController extends JeecgController<Fffff, IFffffService> {
	@Autowired
	private IFffffService fffffService;
	
	/**
	 * 分页列表查询
	 *
	 * @param fffff
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "fffff-分页列表查询")
	@ApiOperation(value="fffff-分页列表查询", notes="fffff-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Fffff fffff,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Fffff> queryWrapper = QueryGenerator.initQueryWrapper(fffff, req.getParameterMap());
		Page<Fffff> page = new Page<Fffff>(pageNo, pageSize);
		IPage<Fffff> pageList = fffffService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param fffff
	 * @return
	 */
	@AutoLog(value = "fffff-添加")
	@ApiOperation(value="fffff-添加", notes="fffff-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Fffff fffff) {
		fffffService.save(fffff);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param fffff
	 * @return
	 */
	@AutoLog(value = "fffff-编辑")
	@ApiOperation(value="fffff-编辑", notes="fffff-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Fffff fffff) {
		fffffService.updateById(fffff);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "fffff-通过id删除")
	@ApiOperation(value="fffff-通过id删除", notes="fffff-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fffffService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "fffff-批量删除")
	@ApiOperation(value="fffff-批量删除", notes="fffff-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fffffService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "fffff-通过id查询")
	@ApiOperation(value="fffff-通过id查询", notes="fffff-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Fffff fffff = fffffService.getById(id);
		if(fffff==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(fffff);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param fffff
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Fffff fffff) {
        return super.exportXls(request, fffff, Fffff.class, "fffff");
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
        return super.importExcel(request, response, Fffff.class);
    }

}
