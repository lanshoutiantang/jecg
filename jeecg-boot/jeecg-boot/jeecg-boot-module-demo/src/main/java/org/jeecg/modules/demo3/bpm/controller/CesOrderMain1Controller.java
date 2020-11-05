package org.jeecg.modules.demo3.bpm.controller;

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
import org.jeecg.modules.demo3.bpm.entity.CesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.CesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.entity.CesOrderMain1;
import org.jeecg.modules.demo3.bpm.vo.CesOrderMain1Page;
import org.jeecg.modules.demo3.bpm.service.ICesOrderMain1Service;
import org.jeecg.modules.demo3.bpm.service.ICesOrderGoods1Service;
import org.jeecg.modules.demo3.bpm.service.ICesOrderCustomer1Service;
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
 * @Description: 商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Api(tags="商品订单表")
@RestController
@RequestMapping("/bpm/cesOrderMain1")
@Slf4j
public class CesOrderMain1Controller {
	@Autowired
	private ICesOrderMain1Service cesOrderMain1Service;
	@Autowired
	private ICesOrderGoods1Service cesOrderGoods1Service;
	@Autowired
	private ICesOrderCustomer1Service cesOrderCustomer1Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param cesOrderMain1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商品订单表-分页列表查询")
	@ApiOperation(value="商品订单表-分页列表查询", notes="商品订单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CesOrderMain1 cesOrderMain1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CesOrderMain1> queryWrapper = QueryGenerator.initQueryWrapper(cesOrderMain1, req.getParameterMap());
		Page<CesOrderMain1> page = new Page<CesOrderMain1>(pageNo, pageSize);
		IPage<CesOrderMain1> pageList = cesOrderMain1Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param cesOrderMain1Page
	 * @return
	 */
	@AutoLog(value = "商品订单表-添加")
	@ApiOperation(value="商品订单表-添加", notes="商品订单表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CesOrderMain1Page cesOrderMain1Page) {
		CesOrderMain1 cesOrderMain1 = new CesOrderMain1();
		BeanUtils.copyProperties(cesOrderMain1Page, cesOrderMain1);
		cesOrderMain1Service.saveMain(cesOrderMain1, cesOrderMain1Page.getCesOrderGoods1List(),cesOrderMain1Page.getCesOrderCustomer1List());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cesOrderMain1Page
	 * @return
	 */
	@AutoLog(value = "商品订单表-编辑")
	@ApiOperation(value="商品订单表-编辑", notes="商品订单表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CesOrderMain1Page cesOrderMain1Page) {
		CesOrderMain1 cesOrderMain1 = new CesOrderMain1();
		BeanUtils.copyProperties(cesOrderMain1Page, cesOrderMain1);
		CesOrderMain1 cesOrderMain1Entity = cesOrderMain1Service.getById(cesOrderMain1.getId());
		if(cesOrderMain1Entity==null) {
			return Result.error("未找到对应数据");
		}
		cesOrderMain1Service.updateMain(cesOrderMain1, cesOrderMain1Page.getCesOrderGoods1List(),cesOrderMain1Page.getCesOrderCustomer1List());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品订单表-通过id删除")
	@ApiOperation(value="商品订单表-通过id删除", notes="商品订单表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cesOrderMain1Service.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品订单表-批量删除")
	@ApiOperation(value="商品订单表-批量删除", notes="商品订单表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cesOrderMain1Service.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品订单表-通过id查询")
	@ApiOperation(value="商品订单表-通过id查询", notes="商品订单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CesOrderMain1 cesOrderMain1 = cesOrderMain1Service.getById(id);
		if(cesOrderMain1==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cesOrderMain1);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单商品通过主表ID查询")
	@ApiOperation(value="订单商品主表ID查询", notes="订单商品-通主表ID查询")
	@GetMapping(value = "/queryCesOrderGoods1ByMainId")
	public Result<?> queryCesOrderGoods1ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CesOrderGoods1> cesOrderGoods1List = cesOrderGoods1Service.selectByMainId(id);
		return Result.OK(cesOrderGoods1List);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单客户通过主表ID查询")
	@ApiOperation(value="订单客户主表ID查询", notes="订单客户-通主表ID查询")
	@GetMapping(value = "/queryCesOrderCustomer1ByMainId")
	public Result<?> queryCesOrderCustomer1ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CesOrderCustomer1> cesOrderCustomer1List = cesOrderCustomer1Service.selectByMainId(id);
		return Result.OK(cesOrderCustomer1List);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cesOrderMain1
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CesOrderMain1 cesOrderMain1) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<CesOrderMain1> queryWrapper = QueryGenerator.initQueryWrapper(cesOrderMain1, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<CesOrderMain1> queryList = cesOrderMain1Service.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<CesOrderMain1> cesOrderMain1List = new ArrayList<CesOrderMain1>();
      if(oConvertUtils.isEmpty(selections)) {
          cesOrderMain1List = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          cesOrderMain1List = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<CesOrderMain1Page> pageList = new ArrayList<CesOrderMain1Page>();
      for (CesOrderMain1 main : cesOrderMain1List) {
          CesOrderMain1Page vo = new CesOrderMain1Page();
          BeanUtils.copyProperties(main, vo);
          List<CesOrderGoods1> cesOrderGoods1List = cesOrderGoods1Service.selectByMainId(main.getId());
          vo.setCesOrderGoods1List(cesOrderGoods1List);
          List<CesOrderCustomer1> cesOrderCustomer1List = cesOrderCustomer1Service.selectByMainId(main.getId());
          vo.setCesOrderCustomer1List(cesOrderCustomer1List);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "商品订单表列表");
      mv.addObject(NormalExcelConstants.CLASS, CesOrderMain1Page.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商品订单表数据", "导出人:"+sysUser.getRealname(), "商品订单表"));
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
              List<CesOrderMain1Page> list = ExcelImportUtil.importExcel(file.getInputStream(), CesOrderMain1Page.class, params);
              for (CesOrderMain1Page page : list) {
                  CesOrderMain1 po = new CesOrderMain1();
                  BeanUtils.copyProperties(page, po);
                  cesOrderMain1Service.saveMain(po, page.getCesOrderGoods1List(),page.getCesOrderCustomer1List());
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
