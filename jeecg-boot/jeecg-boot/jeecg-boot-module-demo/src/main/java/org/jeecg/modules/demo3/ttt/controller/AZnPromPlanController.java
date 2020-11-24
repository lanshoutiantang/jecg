package org.jeecg.modules.demo3.ttt.controller;

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
import org.jeecg.modules.demo3.ttt.entity.AZnPromGoods;
import org.jeecg.modules.demo3.ttt.entity.AZnPromShop;
import org.jeecg.modules.demo3.ttt.entity.AZnPromPlan;
import org.jeecg.modules.demo3.ttt.vo.AZnPromPlanPage;
import org.jeecg.modules.demo3.ttt.service.IAZnPromPlanService;
import org.jeecg.modules.demo3.ttt.service.IAZnPromGoodsService;
import org.jeecg.modules.demo3.ttt.service.IAZnPromShopService;
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
 * @Description: A促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
@Api(tags="A促销计划表")
@RestController
@RequestMapping("/ttt/aZnPromPlan")
@Slf4j
public class AZnPromPlanController {
	@Autowired
	private IAZnPromPlanService aZnPromPlanService;
	@Autowired
	private IAZnPromGoodsService aZnPromGoodsService;
	@Autowired
	private IAZnPromShopService aZnPromShopService;
	
	/**
	 * 分页列表查询
	 *
	 * @param aZnPromPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "A促销计划表-分页列表查询")
	@ApiOperation(value="A促销计划表-分页列表查询", notes="A促销计划表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AZnPromPlan aZnPromPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(aZnPromPlan, req.getParameterMap());
		Page<AZnPromPlan> page = new Page<AZnPromPlan>(pageNo, pageSize);
		IPage<AZnPromPlan> pageList = aZnPromPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param aZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "A促销计划表-添加")
	@ApiOperation(value="A促销计划表-添加", notes="A促销计划表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AZnPromPlanPage aZnPromPlanPage) {
		AZnPromPlan aZnPromPlan = new AZnPromPlan();
		BeanUtils.copyProperties(aZnPromPlanPage, aZnPromPlan);
		aZnPromPlanService.saveMain(aZnPromPlan, aZnPromPlanPage.getAZnPromGoodsList(),aZnPromPlanPage.getAZnPromShopList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param aZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "A促销计划表-编辑")
	@ApiOperation(value="A促销计划表-编辑", notes="A促销计划表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AZnPromPlanPage aZnPromPlanPage) {
		AZnPromPlan aZnPromPlan = new AZnPromPlan();
		BeanUtils.copyProperties(aZnPromPlanPage, aZnPromPlan);
		AZnPromPlan aZnPromPlanEntity = aZnPromPlanService.getById(aZnPromPlan.getId());
		if(aZnPromPlanEntity==null) {
			return Result.error("未找到对应数据");
		}
		aZnPromPlanService.updateMain(aZnPromPlan, aZnPromPlanPage.getAZnPromGoodsList(),aZnPromPlanPage.getAZnPromShopList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "A促销计划表-通过id删除")
	@ApiOperation(value="A促销计划表-通过id删除", notes="A促销计划表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		aZnPromPlanService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "A促销计划表-批量删除")
	@ApiOperation(value="A促销计划表-批量删除", notes="A促销计划表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.aZnPromPlanService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "A促销计划表-通过id查询")
	@ApiOperation(value="A促销计划表-通过id查询", notes="A促销计划表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AZnPromPlan aZnPromPlan = aZnPromPlanService.getById(id);
		if(aZnPromPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(aZnPromPlan);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "A促销商品表通过主表ID查询")
	@ApiOperation(value="A促销商品表主表ID查询", notes="A促销商品表-通主表ID查询")
	@GetMapping(value = "/queryAZnPromGoodsByMainId")
	public Result<?> queryAZnPromGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<AZnPromGoods> aZnPromGoodsList = aZnPromGoodsService.selectByMainId(id);
		return Result.OK(aZnPromGoodsList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "A促销门店表通过主表ID查询")
	@ApiOperation(value="A促销门店表主表ID查询", notes="A促销门店表-通主表ID查询")
	@GetMapping(value = "/queryAZnPromShopByMainId")
	public Result<?> queryAZnPromShopListByMainId(@RequestParam(name="id",required=true) String id) {
		List<AZnPromShop> aZnPromShopList = aZnPromShopService.selectByMainId(id);
		return Result.OK(aZnPromShopList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param aZnPromPlan
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AZnPromPlan aZnPromPlan) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<AZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(aZnPromPlan, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<AZnPromPlan> queryList = aZnPromPlanService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<AZnPromPlan> aZnPromPlanList = new ArrayList<AZnPromPlan>();
      if(oConvertUtils.isEmpty(selections)) {
          aZnPromPlanList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          aZnPromPlanList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<AZnPromPlanPage> pageList = new ArrayList<AZnPromPlanPage>();
      for (AZnPromPlan main : aZnPromPlanList) {
          AZnPromPlanPage vo = new AZnPromPlanPage();
          BeanUtils.copyProperties(main, vo);
          List<AZnPromGoods> aZnPromGoodsList = aZnPromGoodsService.selectByMainId(main.getId());
          vo.setAZnPromGoodsList(aZnPromGoodsList);
          List<AZnPromShop> aZnPromShopList = aZnPromShopService.selectByMainId(main.getId());
          vo.setAZnPromShopList(aZnPromShopList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "A促销计划表列表");
      mv.addObject(NormalExcelConstants.CLASS, AZnPromPlanPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("A促销计划表数据", "导出人:"+sysUser.getRealname(), "A促销计划表"));
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
              List<AZnPromPlanPage> list = ExcelImportUtil.importExcel(file.getInputStream(), AZnPromPlanPage.class, params);
              for (AZnPromPlanPage page : list) {
                  AZnPromPlan po = new AZnPromPlan();
                  BeanUtils.copyProperties(page, po);
                  aZnPromPlanService.saveMain(po, page.getAZnPromGoodsList(),page.getAZnPromShopList());
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
