package org.jeecg.modules.demo3.ccc.controller;

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
import org.jeecg.modules.demo3.ccc.entity.NZnPromShop;
import org.jeecg.modules.demo3.ccc.entity.NZnPromGoods;
import org.jeecg.modules.demo3.ccc.entity.NZnPromPlan;
import org.jeecg.modules.demo3.ccc.vo.NZnPromPlanPage;
import org.jeecg.modules.demo3.ccc.service.INZnPromPlanService;
import org.jeecg.modules.demo3.ccc.service.INZnPromShopService;
import org.jeecg.modules.demo3.ccc.service.INZnPromGoodsService;
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
 * @Date:   2020-11-17
 * @Version: V1.0
 */
@Api(tags="促销计划表")
@RestController
@RequestMapping("/ccc/nZnPromPlan")
@Slf4j
public class NZnPromPlanController {
	@Autowired
	private INZnPromPlanService nZnPromPlanService;
	@Autowired
	private INZnPromShopService nZnPromShopService;
	@Autowired
	private INZnPromGoodsService nZnPromGoodsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nZnPromPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "促销计划表-分页列表查询")
	@ApiOperation(value="促销计划表-分页列表查询", notes="促销计划表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NZnPromPlan nZnPromPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(nZnPromPlan, req.getParameterMap());
		Page<NZnPromPlan> page = new Page<NZnPromPlan>(pageNo, pageSize);
		IPage<NZnPromPlan> pageList = nZnPromPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-添加")
	@ApiOperation(value="促销计划表-添加", notes="促销计划表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NZnPromPlanPage nZnPromPlanPage) {
		NZnPromPlan nZnPromPlan = new NZnPromPlan();
		BeanUtils.copyProperties(nZnPromPlanPage, nZnPromPlan);
		nZnPromPlanService.saveMain(nZnPromPlan, nZnPromPlanPage.getNZnPromShopList(),nZnPromPlanPage.getNZnPromGoodsList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-编辑")
	@ApiOperation(value="促销计划表-编辑", notes="促销计划表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NZnPromPlanPage nZnPromPlanPage) {
		NZnPromPlan nZnPromPlan = new NZnPromPlan();
		BeanUtils.copyProperties(nZnPromPlanPage, nZnPromPlan);
		NZnPromPlan nZnPromPlanEntity = nZnPromPlanService.getById(nZnPromPlan.getId());
		if(nZnPromPlanEntity==null) {
			return Result.error("未找到对应数据");
		}
		nZnPromPlanService.updateMain(nZnPromPlan, nZnPromPlanPage.getNZnPromShopList(),nZnPromPlanPage.getNZnPromGoodsList());
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
		nZnPromPlanService.delMain(id);
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
		this.nZnPromPlanService.delBatchMain(Arrays.asList(ids.split(",")));
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
		NZnPromPlan nZnPromPlan = nZnPromPlanService.getById(id);
		if(nZnPromPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nZnPromPlan);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销门店表通过主表ID查询")
	@ApiOperation(value="促销门店表主表ID查询", notes="促销门店表-通主表ID查询")
	@GetMapping(value = "/queryNZnPromShopByMainId")
	public Result<?> queryNZnPromShopListByMainId(@RequestParam(name="id",required=true) String id) {
		List<NZnPromShop> nZnPromShopList = nZnPromShopService.selectByMainId(id);
		return Result.OK(nZnPromShopList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销商品表通过主表ID查询")
	@ApiOperation(value="促销商品表主表ID查询", notes="促销商品表-通主表ID查询")
	@GetMapping(value = "/queryNZnPromGoodsByMainId")
	public Result<?> queryNZnPromGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<NZnPromGoods> nZnPromGoodsList = nZnPromGoodsService.selectByMainId(id);
		return Result.OK(nZnPromGoodsList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nZnPromPlan
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NZnPromPlan nZnPromPlan) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<NZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(nZnPromPlan, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<NZnPromPlan> queryList = nZnPromPlanService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<NZnPromPlan> nZnPromPlanList = new ArrayList<NZnPromPlan>();
      if(oConvertUtils.isEmpty(selections)) {
          nZnPromPlanList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          nZnPromPlanList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<NZnPromPlanPage> pageList = new ArrayList<NZnPromPlanPage>();
      for (NZnPromPlan main : nZnPromPlanList) {
          NZnPromPlanPage vo = new NZnPromPlanPage();
          BeanUtils.copyProperties(main, vo);
          List<NZnPromShop> nZnPromShopList = nZnPromShopService.selectByMainId(main.getId());
          vo.setNZnPromShopList(nZnPromShopList);
          List<NZnPromGoods> nZnPromGoodsList = nZnPromGoodsService.selectByMainId(main.getId());
          vo.setNZnPromGoodsList(nZnPromGoodsList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "促销计划表列表");
      mv.addObject(NormalExcelConstants.CLASS, NZnPromPlanPage.class);
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
              List<NZnPromPlanPage> list = ExcelImportUtil.importExcel(file.getInputStream(), NZnPromPlanPage.class, params);
              for (NZnPromPlanPage page : list) {
                  NZnPromPlan po = new NZnPromPlan();
                  BeanUtils.copyProperties(page, po);
                  nZnPromPlanService.saveMain(po, page.getNZnPromShopList(),page.getNZnPromGoodsList());
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
