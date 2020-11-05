package org.jeecg.modules.demo3.aaa.controller;

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
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.aaa.entity.CesFiledKongjian;
import org.jeecg.modules.demo3.aaa.service.ICesFiledKongjianService;

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
 * @Date:   2020-10-31
 * @Version: V1.0
 */
@Api(tags="online表单控件")
@RestController
@RequestMapping("/aaa/cesFiledKongjian")
@Slf4j
public class CesFiledKongjianController extends JeecgController<CesFiledKongjian, ICesFiledKongjianService> {
	@Autowired
	private ICesFiledKongjianService cesFiledKongjianService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cesFiledKongjian
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@PermissionData(pageComponent = "system/UserList")
	@AutoLog(value = "online表单控件-分页列表查询")
	@ApiOperation(value="online表单控件-分页列表查询", notes="online表单控件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CesFiledKongjian cesFiledKongjian,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CesFiledKongjian> queryWrapper = QueryGenerator.initQueryWrapper(cesFiledKongjian, req.getParameterMap());
		Page<CesFiledKongjian> page = new Page<CesFiledKongjian>(pageNo, pageSize);
		IPage<CesFiledKongjian> pageList = cesFiledKongjianService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param cesFiledKongjian
	 * @return
	 */
	@AutoLog(value = "online表单控件-添加")
	@ApiOperation(value="online表单控件-添加", notes="online表单控件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CesFiledKongjian cesFiledKongjian) {
		cesFiledKongjianService.save(cesFiledKongjian);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cesFiledKongjian
	 * @return
	 */
	@AutoLog(value = "online表单控件-编辑")
	@ApiOperation(value="online表单控件-编辑", notes="online表单控件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CesFiledKongjian cesFiledKongjian) {
		cesFiledKongjianService.updateById(cesFiledKongjian);
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
		cesFiledKongjianService.removeById(id);
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
		this.cesFiledKongjianService.removeByIds(Arrays.asList(ids.split(",")));
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
		CesFiledKongjian cesFiledKongjian = cesFiledKongjianService.getById(id);
		if(cesFiledKongjian==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cesFiledKongjian);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cesFiledKongjian
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CesFiledKongjian cesFiledKongjian) {
        return super.exportXls(request, cesFiledKongjian, CesFiledKongjian.class, "online表单控件");
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
        return super.importExcel(request, response, CesFiledKongjian.class);
    }

}
