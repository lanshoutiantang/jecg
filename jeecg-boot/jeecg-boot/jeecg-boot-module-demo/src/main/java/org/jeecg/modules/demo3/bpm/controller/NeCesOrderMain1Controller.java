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
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.entity.NeCesOrderMain1;
import org.jeecg.modules.demo3.bpm.vo.NeCesOrderMain1Page;
import org.jeecg.modules.demo3.bpm.service.INeCesOrderMain1Service;
import org.jeecg.modules.demo3.bpm.service.INeCesOrderGoods1Service;
import org.jeecg.modules.demo3.bpm.service.INeCesOrderCustomer1Service;
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
 * @Description: Ne商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Api(tags="Ne商品订单表")
@RestController
@RequestMapping("/bpm/neCesOrderMain1")
@Slf4j
public class NeCesOrderMain1Controller {
	@Autowired
	private INeCesOrderMain1Service neCesOrderMain1Service;
	@Autowired
	private INeCesOrderGoods1Service neCesOrderGoods1Service;
	@Autowired
	private INeCesOrderCustomer1Service neCesOrderCustomer1Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param neCesOrderMain1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "Ne商品订单表-分页列表查询")
	@ApiOperation(value="Ne商品订单表-分页列表查询", notes="Ne商品订单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NeCesOrderMain1 neCesOrderMain1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NeCesOrderMain1> queryWrapper = QueryGenerator.initQueryWrapper(neCesOrderMain1, req.getParameterMap());
		Page<NeCesOrderMain1> page = new Page<NeCesOrderMain1>(pageNo, pageSize);
		IPage<NeCesOrderMain1> pageList = neCesOrderMain1Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param neCesOrderMain1Page
	 * @return
	 */
	@AutoLog(value = "Ne商品订单表-添加")
	@ApiOperation(value="Ne商品订单表-添加", notes="Ne商品订单表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NeCesOrderMain1Page neCesOrderMain1Page) {
		NeCesOrderMain1 neCesOrderMain1 = new NeCesOrderMain1();
		BeanUtils.copyProperties(neCesOrderMain1Page, neCesOrderMain1);
		neCesOrderMain1Service.saveMain(neCesOrderMain1, neCesOrderMain1Page.getNeCesOrderGoods1List(),neCesOrderMain1Page.getNeCesOrderCustomer1List());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param neCesOrderMain1Page
	 * @return
	 */
	@AutoLog(value = "Ne商品订单表-编辑")
	@ApiOperation(value="Ne商品订单表-编辑", notes="Ne商品订单表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NeCesOrderMain1Page neCesOrderMain1Page) {
		NeCesOrderMain1 neCesOrderMain1 = new NeCesOrderMain1();
		BeanUtils.copyProperties(neCesOrderMain1Page, neCesOrderMain1);
		NeCesOrderMain1 neCesOrderMain1Entity = neCesOrderMain1Service.getById(neCesOrderMain1.getId());
		if(neCesOrderMain1Entity==null) {
			return Result.error("未找到对应数据");
		}
		neCesOrderMain1Service.updateMain(neCesOrderMain1, neCesOrderMain1Page.getNeCesOrderGoods1List(),neCesOrderMain1Page.getNeCesOrderCustomer1List());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Ne商品订单表-通过id删除")
	@ApiOperation(value="Ne商品订单表-通过id删除", notes="Ne商品订单表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		neCesOrderMain1Service.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "Ne商品订单表-批量删除")
	@ApiOperation(value="Ne商品订单表-批量删除", notes="Ne商品订单表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.neCesOrderMain1Service.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Ne商品订单表-通过id查询")
	@ApiOperation(value="Ne商品订单表-通过id查询", notes="Ne商品订单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		NeCesOrderMain1 neCesOrderMain1 = neCesOrderMain1Service.getById(id);
		if(neCesOrderMain1==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(neCesOrderMain1);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Ne订单商品-通过主表ID查询")
	@ApiOperation(value="Ne订单商品-通过主表ID查询", notes="Ne订单商品-通过主表ID查询")
	@GetMapping(value = "/queryNeCesOrderGoods1ByMainId")
	public Result<?> queryNeCesOrderGoods1ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<NeCesOrderGoods1> neCesOrderGoods1List = neCesOrderGoods1Service.selectByMainId(id);
		IPage <NeCesOrderGoods1> page = new Page<>();
		page.setRecords(neCesOrderGoods1List);
		page.setTotal(neCesOrderGoods1List.size());
		return Result.OK(page);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Ne订单客户-通过主表ID查询")
	@ApiOperation(value="Ne订单客户-通过主表ID查询", notes="Ne订单客户-通过主表ID查询")
	@GetMapping(value = "/queryNeCesOrderCustomer1ByMainId")
	public Result<?> queryNeCesOrderCustomer1ListByMainId(@RequestParam(name="id",required=true) String id) {
		List<NeCesOrderCustomer1> neCesOrderCustomer1List = neCesOrderCustomer1Service.selectByMainId(id);
		IPage <NeCesOrderCustomer1> page = new Page<>();
		page.setRecords(neCesOrderCustomer1List);
		page.setTotal(neCesOrderCustomer1List.size());
		return Result.OK(page);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param neCesOrderMain1
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NeCesOrderMain1 neCesOrderMain1) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<NeCesOrderMain1> queryWrapper = QueryGenerator.initQueryWrapper(neCesOrderMain1, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<NeCesOrderMain1> queryList = neCesOrderMain1Service.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<NeCesOrderMain1> neCesOrderMain1List = new ArrayList<NeCesOrderMain1>();
      if(oConvertUtils.isEmpty(selections)) {
          neCesOrderMain1List = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          neCesOrderMain1List = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<NeCesOrderMain1Page> pageList = new ArrayList<NeCesOrderMain1Page>();
      for (NeCesOrderMain1 main : neCesOrderMain1List) {
          NeCesOrderMain1Page vo = new NeCesOrderMain1Page();
          BeanUtils.copyProperties(main, vo);
          List<NeCesOrderGoods1> neCesOrderGoods1List = neCesOrderGoods1Service.selectByMainId(main.getId());
          vo.setNeCesOrderGoods1List(neCesOrderGoods1List);
          List<NeCesOrderCustomer1> neCesOrderCustomer1List = neCesOrderCustomer1Service.selectByMainId(main.getId());
          vo.setNeCesOrderCustomer1List(neCesOrderCustomer1List);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "Ne商品订单表列表");
      mv.addObject(NormalExcelConstants.CLASS, NeCesOrderMain1Page.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("Ne商品订单表数据", "导出人:"+sysUser.getRealname(), "Ne商品订单表"));
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
              List<NeCesOrderMain1Page> list = ExcelImportUtil.importExcel(file.getInputStream(), NeCesOrderMain1Page.class, params);
              for (NeCesOrderMain1Page page : list) {
                  NeCesOrderMain1 po = new NeCesOrderMain1();
                  BeanUtils.copyProperties(page, po);
                  neCesOrderMain1Service.saveMain(po, page.getNeCesOrderGoods1List(),page.getNeCesOrderCustomer1List());
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
