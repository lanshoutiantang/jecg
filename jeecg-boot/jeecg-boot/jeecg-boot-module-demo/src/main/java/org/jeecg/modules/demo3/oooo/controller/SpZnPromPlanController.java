package org.jeecg.modules.demo3.oooo.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.oooo.entity.SpZnPromGoods;
import org.jeecg.modules.demo3.oooo.entity.SpZnPromPlan;
import org.jeecg.modules.demo3.oooo.vo.SpZnPromPlanPage;
import org.jeecg.modules.demo3.oooo.service.ISpZnPromPlanService;
import org.jeecg.modules.demo3.oooo.service.ISpZnPromGoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Api(tags="促销计划表")
@RestController
@RequestMapping("/oooo/spZnPromPlan")
@Slf4j
public class SpZnPromPlanController {
	@Autowired
	private ISpZnPromPlanService spZnPromPlanService;
	@Autowired
	private ISpZnPromGoodsService spZnPromGoodsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param spZnPromPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "促销计划表-分页列表查询")
	@ApiOperation(value="促销计划表-分页列表查询", notes="促销计划表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SpZnPromPlan spZnPromPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SpZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(spZnPromPlan, req.getParameterMap());
		Page<SpZnPromPlan> page = new Page<SpZnPromPlan>(pageNo, pageSize);
		IPage<SpZnPromPlan> pageList = spZnPromPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param spZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-添加")
	@ApiOperation(value="促销计划表-添加", notes="促销计划表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SpZnPromPlanPage spZnPromPlanPage) {
		SpZnPromPlan spZnPromPlan = new SpZnPromPlan();
		BeanUtils.copyProperties(spZnPromPlanPage, spZnPromPlan);
		spZnPromPlanService.saveMain(spZnPromPlan, spZnPromPlanPage.getSpZnPromGoodsList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param spZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-编辑")
	@ApiOperation(value="促销计划表-编辑", notes="促销计划表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SpZnPromPlanPage spZnPromPlanPage) {
		SpZnPromPlan spZnPromPlan = new SpZnPromPlan();
		BeanUtils.copyProperties(spZnPromPlanPage, spZnPromPlan);
		SpZnPromPlan spZnPromPlanEntity = spZnPromPlanService.getById(spZnPromPlan.getId());
		if(spZnPromPlanEntity==null) {
			return Result.error("未找到对应数据");
		}
		spZnPromPlanService.updateMain(spZnPromPlan, spZnPromPlanPage.getSpZnPromGoodsList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销计划表-通过id删除")
	@ApiOperation(value="促销计划表-通过id删除", notes="促销计划表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		spZnPromPlanService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "促销计划表-批量删除")
	@ApiOperation(value="促销计划表-批量删除", notes="促销计划表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.spZnPromPlanService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销计划表-通过id查询")
	@ApiOperation(value="促销计划表-通过id查询", notes="促销计划表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SpZnPromPlan spZnPromPlan = spZnPromPlanService.getById(id);
		if(spZnPromPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(spZnPromPlan);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销商品表通过主表ID查询")
	@ApiOperation(value="促销商品表主表ID查询", notes="促销商品表-通主表ID查询")
	@GetMapping(value = "/querySpZnPromGoodsByMainId")
	public Result<?> querySpZnPromGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<SpZnPromGoods> spZnPromGoodsList = spZnPromGoodsService.selectByMainId(id);
		return Result.OK(spZnPromGoodsList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param spZnPromPlan
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SpZnPromPlan spZnPromPlan) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<SpZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(spZnPromPlan, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<SpZnPromPlan> queryList = spZnPromPlanService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<SpZnPromPlan> spZnPromPlanList = new ArrayList<SpZnPromPlan>();
      if(oConvertUtils.isEmpty(selections)) {
          spZnPromPlanList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          spZnPromPlanList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<SpZnPromPlanPage> pageList = new ArrayList<SpZnPromPlanPage>();
      for (SpZnPromPlan main : spZnPromPlanList) {
          SpZnPromPlanPage vo = new SpZnPromPlanPage();
          BeanUtils.copyProperties(main, vo);
          List<SpZnPromGoods> spZnPromGoodsList = spZnPromGoodsService.selectByMainId(main.getId());
          vo.setSpZnPromGoodsList(spZnPromGoodsList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "促销计划表列表");
      mv.addObject(NormalExcelConstants.CLASS, SpZnPromPlanPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("促销计划表数据", "导出人:"+sysUser.getRealname(), "促销计划表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<SpZnPromPlanPage> list = ExcelImportUtil.importExcel(file.getInputStream(), SpZnPromPlanPage.class, params);
              for (SpZnPromPlanPage page : list) {
                  SpZnPromPlan po = new SpZnPromPlan();
                  BeanUtils.copyProperties(page, po);
                  spZnPromPlanService.saveMain(po, page.getSpZnPromGoodsList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
