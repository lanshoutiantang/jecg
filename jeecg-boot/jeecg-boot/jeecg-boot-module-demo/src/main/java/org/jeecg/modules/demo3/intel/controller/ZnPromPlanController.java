package org.jeecg.modules.demo3.intel.controller;

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
import org.jeecg.modules.demo3.intel.entity.ZnPromShop;
import org.jeecg.modules.demo3.intel.entity.ZnPromGoods;
import org.jeecg.modules.demo3.intel.entity.ZnPromPlan;
import org.jeecg.modules.demo3.intel.vo.ZnPromPlanPage;
import org.jeecg.modules.demo3.intel.service.IZnPromPlanService;
import org.jeecg.modules.demo3.intel.service.IZnPromShopService;
import org.jeecg.modules.demo3.intel.service.IZnPromGoodsService;
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
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="促销计划表")
@RestController
@RequestMapping("/intel/znPromPlan")
@Slf4j
public class ZnPromPlanController {
	@Autowired
	private IZnPromPlanService znPromPlanService;
	@Autowired
	private IZnPromShopService znPromShopService;
	@Autowired
	private IZnPromGoodsService znPromGoodsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znPromPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "促销计划表-分页列表查询")
	@ApiOperation(value="促销计划表-分页列表查询", notes="促销计划表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnPromPlan znPromPlan,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(znPromPlan, req.getParameterMap());
		Page<ZnPromPlan> page = new Page<ZnPromPlan>(pageNo, pageSize);
		IPage<ZnPromPlan> pageList = znPromPlanService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-添加")
	@ApiOperation(value="促销计划表-添加", notes="促销计划表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnPromPlanPage znPromPlanPage) {
		ZnPromPlan znPromPlan = new ZnPromPlan();
		BeanUtils.copyProperties(znPromPlanPage, znPromPlan);
		znPromPlanService.saveMain(znPromPlan, znPromPlanPage.getZnPromShopList(),znPromPlanPage.getZnPromGoodsList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znPromPlanPage
	 * @return
	 */
	@AutoLog(value = "促销计划表-编辑")
	@ApiOperation(value="促销计划表-编辑", notes="促销计划表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnPromPlanPage znPromPlanPage) {
		ZnPromPlan znPromPlan = new ZnPromPlan();
		BeanUtils.copyProperties(znPromPlanPage, znPromPlan);
		ZnPromPlan znPromPlanEntity = znPromPlanService.getById(znPromPlan.getId());
		if(znPromPlanEntity==null) {
			return Result.error("未找到对应数据");
		}
		znPromPlanService.updateMain(znPromPlan, znPromPlanPage.getZnPromShopList(),znPromPlanPage.getZnPromGoodsList());
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
		znPromPlanService.delMain(id);
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
		this.znPromPlanService.delBatchMain(Arrays.asList(ids.split(",")));
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
		ZnPromPlan znPromPlan = znPromPlanService.getById(id);
		if(znPromPlan==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znPromPlan);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销门店表通过主表ID查询")
	@ApiOperation(value="促销门店表主表ID查询", notes="促销门店表-通主表ID查询")
	@GetMapping(value = "/queryZnPromShopByMainId")
	public Result<?> queryZnPromShopListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ZnPromShop> znPromShopList = znPromShopService.selectByMainId(id);
		return Result.OK(znPromShopList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "促销商品表通过主表ID查询")
	@ApiOperation(value="促销商品表主表ID查询", notes="促销商品表-通主表ID查询")
	@GetMapping(value = "/queryZnPromGoodsByMainId")
	public Result<?> queryZnPromGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ZnPromGoods> znPromGoodsList = znPromGoodsService.selectByMainId(id);
		return Result.OK(znPromGoodsList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znPromPlan
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnPromPlan znPromPlan) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<ZnPromPlan> queryWrapper = QueryGenerator.initQueryWrapper(znPromPlan, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ZnPromPlan> queryList = znPromPlanService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<ZnPromPlan> znPromPlanList = new ArrayList<ZnPromPlan>();
      if(oConvertUtils.isEmpty(selections)) {
          znPromPlanList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          znPromPlanList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<ZnPromPlanPage> pageList = new ArrayList<ZnPromPlanPage>();
      for (ZnPromPlan main : znPromPlanList) {
          ZnPromPlanPage vo = new ZnPromPlanPage();
          BeanUtils.copyProperties(main, vo);
          List<ZnPromShop> znPromShopList = znPromShopService.selectByMainId(main.getId());
          vo.setZnPromShopList(znPromShopList);
          List<ZnPromGoods> znPromGoodsList = znPromGoodsService.selectByMainId(main.getId());
          vo.setZnPromGoodsList(znPromGoodsList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "促销计划表列表");
      mv.addObject(NormalExcelConstants.CLASS, ZnPromPlanPage.class);
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
              List<ZnPromPlanPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ZnPromPlanPage.class, params);
              for (ZnPromPlanPage page : list) {
                  ZnPromPlan po = new ZnPromPlan();
                  BeanUtils.copyProperties(page, po);
                  znPromPlanService.saveMain(po, page.getZnPromShopList(),page.getZnPromGoodsList());
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
