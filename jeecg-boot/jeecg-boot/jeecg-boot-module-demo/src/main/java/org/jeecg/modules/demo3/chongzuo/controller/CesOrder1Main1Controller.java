package org.jeecg.modules.demo3.chongzuo.controller;

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
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Goods1;
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Customer1;
import org.jeecg.modules.demo3.chongzuo.entity.CesOrder1Main1;
import org.jeecg.modules.demo3.chongzuo.vo.CesOrder1Main1Page;
import org.jeecg.modules.demo3.chongzuo.service.ICesOrder1Main1Service;
import org.jeecg.modules.demo3.chongzuo.service.ICesOrder1Goods1Service;
import org.jeecg.modules.demo3.chongzuo.service.ICesOrder1Customer1Service;
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
 * @Description: 商品1订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
@Api(tags="商品1订单表")
@RestController
@RequestMapping("/chongzuo/cesOrder1Main1")
@Slf4j
public class CesOrder1Main1Controller {
	@Autowired
	private ICesOrder1Main1Service cesOrder1Main1Service;
	@Autowired
	private ICesOrder1Goods1Service cesOrder1Goods1Service;
	@Autowired
	private ICesOrder1Customer1Service cesOrder1Customer1Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param cesOrder1Main1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商品1订单表-分页列表查询")
	@ApiOperation(value="商品1订单表-分页列表查询", notes="商品1订单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CesOrder1Main1 cesOrder1Main1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CesOrder1Main1> queryWrapper = QueryGenerator.initQueryWrapper(cesOrder1Main1, req.getParameterMap());
		Page<CesOrder1Main1> page = new Page<CesOrder1Main1>(pageNo, pageSize);
		IPage<CesOrder1Main1> pageList = cesOrder1Main1Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param cesOrder1Main1Page
	 * @return
	 */
	@AutoLog(value = "商品1订单表-添加")
	@ApiOperation(value="商品1订单表-添加", notes="商品1订单表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CesOrder1Main1Page cesOrder1Main1Page) {
		CesOrder1Main1 cesOrder1Main1 = new CesOrder1Main1();
		BeanUtils.copyProperties(cesOrder1Main1Page, cesOrder1Main1);
		cesOrder1Main1Service.saveMain(cesOrder1Main1, cesOrder1Main1Page.getCesOrder1Goods1List(),cesOrder1Main1Page.getCesOrder1Customer1List());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cesOrder1Main1Page
	 * @return
	 */
	@AutoLog(value = "商品1订单表-编辑")
	@ApiOperation(value="商品1订单表-编辑", notes="商品1订单表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CesOrder1Main1Page cesOrder1Main1Page) {
		CesOrder1Main1 cesOrder1Main1 = new CesOrder1Main1();
		BeanUtils.copyProperties(cesOrder1Main1Page, cesOrder1Main1);
		CesOrder1Main1 cesOrder1Main1Entity = cesOrder1Main1Service.getById(cesOrder1Main1.getId());
		if(cesOrder1Main1Entity==null) {
			return Result.error("未找到对应数据");
		}
		cesOrder1Main1Service.updateMain(cesOrder1Main1, cesOrder1Main1Page.getCesOrder1Goods1List(),cesOrder1Main1Page.getCesOrder1Customer1List());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品1订单表-通过id删除")
	@ApiOperation(value="商品1订单表-通过id删除", notes="商品1订单表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cesOrder1Main1Service.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品1订单表-批量删除")
	@ApiOperation(value="商品1订单表-批量删除", notes="商品1订单表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cesOrder1Main1Service.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品1订单表-通过id查询")
	@ApiOperation(value="商品1订单表-通过id查询", notes="商品1订单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CesOrder1Main1 cesOrder1Main1 = cesOrder1Main1Service.getById(id);
		if(cesOrder1Main1==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cesOrder1Main1);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单1商品通过主表ID查询")
	@ApiOperation(value="订单1商品主表ID查询", notes="订单1商品-通主表ID查询")
	@GetMapping(value = "/queryCesOrder1Goods1ByMainId")
	public Result<?> queryCesOrder1Goods1ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CesOrder1Goods1> cesOrder1Goods1List = cesOrder1Goods1Service.selectByMainId(id);
		return Result.OK(cesOrder1Goods1List);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单1客户通过主表ID查询")
	@ApiOperation(value="订单1客户主表ID查询", notes="订单1客户-通主表ID查询")
	@GetMapping(value = "/queryCesOrder1Customer1ByMainId")
	public Result<?> queryCesOrder1Customer1ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CesOrder1Customer1> cesOrder1Customer1List = cesOrder1Customer1Service.selectByMainId(id);
		return Result.OK(cesOrder1Customer1List);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cesOrder1Main1
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CesOrder1Main1 cesOrder1Main1) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<CesOrder1Main1> queryWrapper = QueryGenerator.initQueryWrapper(cesOrder1Main1, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<CesOrder1Main1> queryList = cesOrder1Main1Service.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<CesOrder1Main1> cesOrder1Main1List = new ArrayList<CesOrder1Main1>();
      if(oConvertUtils.isEmpty(selections)) {
          cesOrder1Main1List = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          cesOrder1Main1List = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<CesOrder1Main1Page> pageList = new ArrayList<CesOrder1Main1Page>();
      for (CesOrder1Main1 main : cesOrder1Main1List) {
          CesOrder1Main1Page vo = new CesOrder1Main1Page();
          BeanUtils.copyProperties(main, vo);
          List<CesOrder1Goods1> cesOrder1Goods1List = cesOrder1Goods1Service.selectByMainId(main.getId());
          vo.setCesOrder1Goods1List(cesOrder1Goods1List);
          List<CesOrder1Customer1> cesOrder1Customer1List = cesOrder1Customer1Service.selectByMainId(main.getId());
          vo.setCesOrder1Customer1List(cesOrder1Customer1List);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "商品1订单表列表");
      mv.addObject(NormalExcelConstants.CLASS, CesOrder1Main1Page.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商品1订单表数据", "导出人:"+sysUser.getRealname(), "商品1订单表"));
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
              List<CesOrder1Main1Page> list = ExcelImportUtil.importExcel(file.getInputStream(), CesOrder1Main1Page.class, params);
              for (CesOrder1Main1Page page : list) {
                  CesOrder1Main1 po = new CesOrder1Main1();
                  BeanUtils.copyProperties(page, po);
                  cesOrder1Main1Service.saveMain(po, page.getCesOrder1Goods1List(),page.getCesOrder1Customer1List());
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
