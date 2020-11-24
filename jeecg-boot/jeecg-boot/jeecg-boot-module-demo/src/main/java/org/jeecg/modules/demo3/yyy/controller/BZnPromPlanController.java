package org.jeecg.modules.demo3.yyy.controller;

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
import org.jeecg.modules.demo3.yyy.entity.BZnPromGoods;
import org.jeecg.modules.demo3.yyy.entity.BZnPromShop;
import org.jeecg.modules.demo3.yyy.entity.BZnPromPlan;
import org.jeecg.modules.demo3.yyy.vo.BZnPromPlanPage;
import org.jeecg.modules.demo3.yyy.service.IBZnPromPlanService;
import org.jeecg.modules.demo3.yyy.service.IBZnPromGoodsService;
import org.jeecg.modules.demo3.yyy.service.IBZnPromShopService;
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
@RequestMapping("/yyy/bZnPromPlan")
@Slf4j
public class BZnPromPlanController {
	@Autowired
	private IBZnPromPlanService bZnPromPlanService;
	@Autowired
	private IBZnPromGoodsService bZnPromGoodsService;
	@Autowired
	private IBZnPromShopService bZnPromShopService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bZnPromPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "促销计划表-分页列表查询")
	@ApiOperation(value="促销计划表-分页列表查询", notes="促销计划表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BZnPromPlan bZnPromPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(bZnPromPlan, req.getParameterMap());
		Page<BZnPromPlan> page = new Page<BZnPromPlan>(pageNo, pageSize);
		IPage<BZnPromPlan> pageList = bZnPromPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-添加")
	@ApiOperation(value="促销计划表-添加", notes="促销计划表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BZnPromPlanPage bZnPromPlanPage) {
		BZnPromPlan bZnPromPlan = new BZnPromPlan();
		BeanUtils.copyProperties(bZnPromPlanPage, bZnPromPlan);
		bZnPromPlanService.saveMain(bZnPromPlan, bZnPromPlanPage.getBZnPromGoodsList(),bZnPromPlanPage.getBZnPromShopList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bZnPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-编辑")
	@ApiOperation(value="促销计划表-编辑", notes="促销计划表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BZnPromPlanPage bZnPromPlanPage) {
		BZnPromPlan bZnPromPlan = new BZnPromPlan();
		BeanUtils.copyProperties(bZnPromPlanPage, bZnPromPlan);
		BZnPromPlan bZnPromPlanEntity = bZnPromPlanService.getById(bZnPromPlan.getId());
		if(bZnPromPlanEntity==null) {
			return Result.error("未找到对应数据");
		}
		bZnPromPlanService.updateMain(bZnPromPlan, bZnPromPlanPage.getBZnPromGoodsList(),bZnPromPlanPage.getBZnPromShopList());
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
		bZnPromPlanService.delMain(id);
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
		this.bZnPromPlanService.delBatchMain(Arrays.asList(ids.split(",")));
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
		BZnPromPlan bZnPromPlan = bZnPromPlanService.getById(id);
		if(bZnPromPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bZnPromPlan);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销商品表通过主表ID查询")
	@ApiOperation(value="促销商品表主表ID查询", notes="促销商品表-通主表ID查询")
	@GetMapping(value = "/queryBZnPromGoodsByMainId")
	public Result<?> queryBZnPromGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BZnPromGoods> bZnPromGoodsList = bZnPromGoodsService.selectByMainId(id);
		return Result.OK(bZnPromGoodsList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销门店表通过主表ID查询")
	@ApiOperation(value="促销门店表主表ID查询", notes="促销门店表-通主表ID查询")
	@GetMapping(value = "/queryBZnPromShopByMainId")
	public Result<?> queryBZnPromShopListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BZnPromShop> bZnPromShopList = bZnPromShopService.selectByMainId(id);
		return Result.OK(bZnPromShopList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bZnPromPlan
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BZnPromPlan bZnPromPlan) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<BZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(bZnPromPlan, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<BZnPromPlan> queryList = bZnPromPlanService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<BZnPromPlan> bZnPromPlanList = new ArrayList<BZnPromPlan>();
      if(oConvertUtils.isEmpty(selections)) {
          bZnPromPlanList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          bZnPromPlanList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<BZnPromPlanPage> pageList = new ArrayList<BZnPromPlanPage>();
      for (BZnPromPlan main : bZnPromPlanList) {
          BZnPromPlanPage vo = new BZnPromPlanPage();
          BeanUtils.copyProperties(main, vo);
          List<BZnPromGoods> bZnPromGoodsList = bZnPromGoodsService.selectByMainId(main.getId());
          vo.setBZnPromGoodsList(bZnPromGoodsList);
          List<BZnPromShop> bZnPromShopList = bZnPromShopService.selectByMainId(main.getId());
          vo.setBZnPromShopList(bZnPromShopList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "促销计划表列表");
      mv.addObject(NormalExcelConstants.CLASS, BZnPromPlanPage.class);
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
              List<BZnPromPlanPage> list = ExcelImportUtil.importExcel(file.getInputStream(), BZnPromPlanPage.class, params);
              for (BZnPromPlanPage page : list) {
                  BZnPromPlan po = new BZnPromPlan();
                  BeanUtils.copyProperties(page, po);
                  bZnPromPlanService.saveMain(po, page.getBZnPromGoodsList(),page.getBZnPromShopList());
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
