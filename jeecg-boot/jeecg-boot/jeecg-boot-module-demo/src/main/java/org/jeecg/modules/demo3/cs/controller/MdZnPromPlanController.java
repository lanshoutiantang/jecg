package org.jeecg.modules.demo3.cs.controller;

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
import org.jeecg.modules.demo3.cs.entity.MdZnPromShop;
import org.jeecg.modules.demo3.cs.entity.MdZnPromPlan;
import org.jeecg.modules.demo3.cs.vo.MdZnPromPlanPage;
import org.jeecg.modules.demo3.cs.service.IMdZnPromPlanService;
import org.jeecg.modules.demo3.cs.service.IMdZnPromShopService;
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
@RequestMapping("/cs/mdZnPromPlan")
@Slf4j
public class MdZnPromPlanController {
	@Autowired
	private IMdZnPromPlanService mdZnPromPlanService;
	@Autowired
	private IMdZnPromShopService mdZnPromShopService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mdZnPromPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "促销计划表-分页列表查询")
	@ApiOperation(value="促销计划表-分页列表查询", notes="促销计划表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MdZnPromPlan mdZnPromPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MdZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(mdZnPromPlan, req.getParameterMap());
		Page<MdZnPromPlan> page = new Page<MdZnPromPlan>(pageNo, pageSize);
		IPage<MdZnPromPlan> pageList = mdZnPromPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mdZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-添加")
	@ApiOperation(value="促销计划表-添加", notes="促销计划表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MdZnPromPlanPage mdZnPromPlanPage) {
		MdZnPromPlan mdZnPromPlan = new MdZnPromPlan();
		BeanUtils.copyProperties(mdZnPromPlanPage, mdZnPromPlan);
		mdZnPromPlanService.saveMain(mdZnPromPlan, mdZnPromPlanPage.getMdZnPromShopList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mdZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-编辑")
	@ApiOperation(value="促销计划表-编辑", notes="促销计划表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MdZnPromPlanPage mdZnPromPlanPage) {
		MdZnPromPlan mdZnPromPlan = new MdZnPromPlan();
		BeanUtils.copyProperties(mdZnPromPlanPage, mdZnPromPlan);
		MdZnPromPlan mdZnPromPlanEntity = mdZnPromPlanService.getById(mdZnPromPlan.getId());
		if(mdZnPromPlanEntity==null) {
			return Result.error("未找到对应数据");
		}
		mdZnPromPlanService.updateMain(mdZnPromPlan, mdZnPromPlanPage.getMdZnPromShopList());
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
		mdZnPromPlanService.delMain(id);
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
		this.mdZnPromPlanService.delBatchMain(Arrays.asList(ids.split(",")));
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
		MdZnPromPlan mdZnPromPlan = mdZnPromPlanService.getById(id);
		if(mdZnPromPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mdZnPromPlan);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销门店表通过主表ID查询")
	@ApiOperation(value="促销门店表主表ID查询", notes="促销门店表-通主表ID查询")
	@GetMapping(value = "/queryMdZnPromShopByMainId")
	public Result<?> queryMdZnPromShopListByMainId(@RequestParam(name="id",required=true) String id) {
		List<MdZnPromShop> mdZnPromShopList = mdZnPromShopService.selectByMainId(id);
		return Result.OK(mdZnPromShopList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mdZnPromPlan
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MdZnPromPlan mdZnPromPlan) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<MdZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(mdZnPromPlan, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<MdZnPromPlan> queryList = mdZnPromPlanService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<MdZnPromPlan> mdZnPromPlanList = new ArrayList<MdZnPromPlan>();
      if(oConvertUtils.isEmpty(selections)) {
          mdZnPromPlanList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          mdZnPromPlanList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<MdZnPromPlanPage> pageList = new ArrayList<MdZnPromPlanPage>();
      for (MdZnPromPlan main : mdZnPromPlanList) {
          MdZnPromPlanPage vo = new MdZnPromPlanPage();
          BeanUtils.copyProperties(main, vo);
          List<MdZnPromShop> mdZnPromShopList = mdZnPromShopService.selectByMainId(main.getId());
          vo.setMdZnPromShopList(mdZnPromShopList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "促销计划表列表");
      mv.addObject(NormalExcelConstants.CLASS, MdZnPromPlanPage.class);
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
              List<MdZnPromPlanPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MdZnPromPlanPage.class, params);
              for (MdZnPromPlanPage page : list) {
                  MdZnPromPlan po = new MdZnPromPlan();
                  BeanUtils.copyProperties(page, po);
                  mdZnPromPlanService.saveMain(po, page.getMdZnPromShopList());
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
