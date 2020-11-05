package org.jeecg.modules.demo3.demo3.controller;

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
import org.jeecg.modules.demo3.demo3.entity.CesFiled_Kongjian;
import org.jeecg.modules.demo3.demo3.service.ICesFiled_KongjianService;

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
 * @Description: online表单控件
 * @Author: jeecg-boot
 * @Date:   2020-10-17
 * @Version: V1.0
 */
@Api(tags="online表单控件")
@RestController
@RequestMapping("/demo3/cesFiled_Kongjian")
@Slf4j
public class CesFiled_KongjianController extends JeecgController<CesFiled_Kongjian, ICesFiled_KongjianService> {
	@Autowired
	private ICesFiled_KongjianService cesFiled_KongjianService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cesFiled_Kongjian
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "online表单控件-分页列表查询")
	@ApiOperation(value="online表单控件-分页列表查询", notes="online表单控件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CesFiled_Kongjian cesFiled_Kongjian,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CesFiled_Kongjian> queryWrapper = QueryGenerator.initQueryWrapper(cesFiled_Kongjian, req.getParameterMap());
		Page<CesFiled_Kongjian> page = new Page<CesFiled_Kongjian>(pageNo, pageSize);
		IPage<CesFiled_Kongjian> pageList = cesFiled_KongjianService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param cesFiled_Kongjian
	 * @return
	 */
	@AutoLog(value = "online表单控件-添加")
	@ApiOperation(value="online表单控件-添加", notes="online表单控件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CesFiled_Kongjian cesFiled_Kongjian) {
		cesFiled_KongjianService.save(cesFiled_Kongjian);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cesFiled_Kongjian
	 * @return
	 */
	@AutoLog(value = "online表单控件-编辑")
	@ApiOperation(value="online表单控件-编辑", notes="online表单控件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CesFiled_Kongjian cesFiled_Kongjian) {
		cesFiled_KongjianService.updateById(cesFiled_Kongjian);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "online表单控件-通过id删除")
	@ApiOperation(value="online表单控件-通过id删除", notes="online表单控件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cesFiled_KongjianService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "online表单控件-批量删除")
	@ApiOperation(value="online表单控件-批量删除", notes="online表单控件-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cesFiled_KongjianService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "online表单控件-通过id查询")
	@ApiOperation(value="online表单控件-通过id查询", notes="online表单控件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CesFiled_Kongjian cesFiled_Kongjian = cesFiled_KongjianService.getById(id);
		if(cesFiled_Kongjian==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cesFiled_Kongjian);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cesFiled_Kongjian
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CesFiled_Kongjian cesFiled_Kongjian) {
        return super.exportXls(request, cesFiled_Kongjian, CesFiled_Kongjian.class, "online表单控件");
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
        return super.importExcel(request, response, CesFiled_Kongjian.class);
    }

}
