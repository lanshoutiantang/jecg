package org.jeecg.modules.demo3.bpm.controller;

import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderGoods1;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderCustomer1;
import org.jeecg.modules.demo3.bpm.entity.ErpCesOrderMain1;
import org.jeecg.modules.demo3.bpm.service.IErpCesOrderMain1Service;
import org.jeecg.modules.demo3.bpm.service.IErpCesOrderGoods1Service;
import org.jeecg.modules.demo3.bpm.service.IErpCesOrderCustomer1Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: Erp商品订单表
 * @Author: jeecg-boot
 * @Date:   2020-10-18
 * @Version: V1.0
 */
@Api(tags="Erp商品订单表")
@RestController
@RequestMapping("/bpm/erpCesOrderMain1")
@Slf4j
public class ErpCesOrderMain1Controller extends JeecgController<ErpCesOrderMain1, IErpCesOrderMain1Service> {

	@Autowired
	private IErpCesOrderMain1Service erpCesOrderMain1Service;

	@Autowired
	private IErpCesOrderGoods1Service erpCesOrderGoods1Service;

	@Autowired
	private IErpCesOrderCustomer1Service erpCesOrderCustomer1Service;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param erpCesOrderMain1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "Erp商品订单表-分页列表查询")
	@ApiOperation(value="Erp商品订单表-分页列表查询", notes="Erp商品订单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpCesOrderMain1 erpCesOrderMain1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpCesOrderMain1> queryWrapper = QueryGenerator.initQueryWrapper(erpCesOrderMain1, req.getParameterMap());
		Page<ErpCesOrderMain1> page = new Page<ErpCesOrderMain1>(pageNo, pageSize);
		IPage<ErpCesOrderMain1> pageList = erpCesOrderMain1Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
     *   添加
     * @param erpCesOrderMain1
     * @return
     */
    @AutoLog(value = "Erp商品订单表-添加")
    @ApiOperation(value="Erp商品订单表-添加", notes="Erp商品订单表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ErpCesOrderMain1 erpCesOrderMain1) {
        erpCesOrderMain1Service.save(erpCesOrderMain1);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param erpCesOrderMain1
     * @return
     */
    @AutoLog(value = "Erp商品订单表-编辑")
    @ApiOperation(value="Erp商品订单表-编辑", notes="Erp商品订单表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ErpCesOrderMain1 erpCesOrderMain1) {
        erpCesOrderMain1Service.updateById(erpCesOrderMain1);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "Erp商品订单表-通过id删除")
    @ApiOperation(value="Erp商品订单表-通过id删除", notes="Erp商品订单表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        erpCesOrderMain1Service.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "Erp商品订单表-批量删除")
    @ApiOperation(value="Erp商品订单表-批量删除", notes="Erp商品订单表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.erpCesOrderMain1Service.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpCesOrderMain1 erpCesOrderMain1) {
        return super.exportXls(request, erpCesOrderMain1, ErpCesOrderMain1.class, "Erp商品订单表");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ErpCesOrderMain1.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-Erp订单商品-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "Erp订单商品-通过主表ID查询")
	@ApiOperation(value="Erp订单商品-通过主表ID查询", notes="Erp订单商品-通过主表ID查询")
	@GetMapping(value = "/listErpCesOrderGoods1ByMainId")
    public Result<?> listErpCesOrderGoods1ByMainId(ErpCesOrderGoods1 erpCesOrderGoods1,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<ErpCesOrderGoods1> queryWrapper = QueryGenerator.initQueryWrapper(erpCesOrderGoods1, req.getParameterMap());
        Page<ErpCesOrderGoods1> page = new Page<ErpCesOrderGoods1>(pageNo, pageSize);
        IPage<ErpCesOrderGoods1> pageList = erpCesOrderGoods1Service.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param erpCesOrderGoods1
	 * @return
	 */
	@AutoLog(value = "Erp订单商品-添加")
	@ApiOperation(value="Erp订单商品-添加", notes="Erp订单商品-添加")
	@PostMapping(value = "/addErpCesOrderGoods1")
	public Result<?> addErpCesOrderGoods1(@RequestBody ErpCesOrderGoods1 erpCesOrderGoods1) {
		erpCesOrderGoods1Service.save(erpCesOrderGoods1);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param erpCesOrderGoods1
	 * @return
	 */
	@AutoLog(value = "Erp订单商品-编辑")
	@ApiOperation(value="Erp订单商品-编辑", notes="Erp订单商品-编辑")
	@PutMapping(value = "/editErpCesOrderGoods1")
	public Result<?> editErpCesOrderGoods1(@RequestBody ErpCesOrderGoods1 erpCesOrderGoods1) {
		erpCesOrderGoods1Service.updateById(erpCesOrderGoods1);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Erp订单商品-通过id删除")
	@ApiOperation(value="Erp订单商品-通过id删除", notes="Erp订单商品-通过id删除")
	@DeleteMapping(value = "/deleteErpCesOrderGoods1")
	public Result<?> deleteErpCesOrderGoods1(@RequestParam(name="id",required=true) String id) {
		erpCesOrderGoods1Service.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "Erp订单商品-批量删除")
	@ApiOperation(value="Erp订单商品-批量删除", notes="Erp订单商品-批量删除")
	@DeleteMapping(value = "/deleteBatchErpCesOrderGoods1")
	public Result<?> deleteBatchErpCesOrderGoods1(@RequestParam(name="ids",required=true) String ids) {
	    this.erpCesOrderGoods1Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportErpCesOrderGoods1")
    public ModelAndView exportErpCesOrderGoods1(HttpServletRequest request, ErpCesOrderGoods1 erpCesOrderGoods1) {
		 // Step.1 组装查询条件
		 QueryWrapper<ErpCesOrderGoods1> queryWrapper = QueryGenerator.initQueryWrapper(erpCesOrderGoods1, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<ErpCesOrderGoods1> pageList = erpCesOrderGoods1Service.list(queryWrapper);
		 List<ErpCesOrderGoods1> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "Erp订单商品"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ErpCesOrderGoods1.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("Erp订单商品报表", "导出人:" + sysUser.getRealname(), "Erp订单商品"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importErpCesOrderGoods1/{mainId}")
    public Result<?> importErpCesOrderGoods1(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<ErpCesOrderGoods1> list = ExcelImportUtil.importExcel(file.getInputStream(), ErpCesOrderGoods1.class, params);
				 for (ErpCesOrderGoods1 temp : list) {
                    temp.setOrderMainId1(mainId);
				 }
				 long start = System.currentTimeMillis();
				 erpCesOrderGoods1Service.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-Erp订单商品-end----------------------------------------------*/

    /*--------------------------------子表处理-Erp订单客户-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "Erp订单客户-通过主表ID查询")
	@ApiOperation(value="Erp订单客户-通过主表ID查询", notes="Erp订单客户-通过主表ID查询")
	@GetMapping(value = "/listErpCesOrderCustomer1ByMainId")
    public Result<?> listErpCesOrderCustomer1ByMainId(ErpCesOrderCustomer1 erpCesOrderCustomer1,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<ErpCesOrderCustomer1> queryWrapper = QueryGenerator.initQueryWrapper(erpCesOrderCustomer1, req.getParameterMap());
        Page<ErpCesOrderCustomer1> page = new Page<ErpCesOrderCustomer1>(pageNo, pageSize);
        IPage<ErpCesOrderCustomer1> pageList = erpCesOrderCustomer1Service.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param erpCesOrderCustomer1
	 * @return
	 */
	@AutoLog(value = "Erp订单客户-添加")
	@ApiOperation(value="Erp订单客户-添加", notes="Erp订单客户-添加")
	@PostMapping(value = "/addErpCesOrderCustomer1")
	public Result<?> addErpCesOrderCustomer1(@RequestBody ErpCesOrderCustomer1 erpCesOrderCustomer1) {
		erpCesOrderCustomer1Service.save(erpCesOrderCustomer1);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param erpCesOrderCustomer1
	 * @return
	 */
	@AutoLog(value = "Erp订单客户-编辑")
	@ApiOperation(value="Erp订单客户-编辑", notes="Erp订单客户-编辑")
	@PutMapping(value = "/editErpCesOrderCustomer1")
	public Result<?> editErpCesOrderCustomer1(@RequestBody ErpCesOrderCustomer1 erpCesOrderCustomer1) {
		erpCesOrderCustomer1Service.updateById(erpCesOrderCustomer1);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Erp订单客户-通过id删除")
	@ApiOperation(value="Erp订单客户-通过id删除", notes="Erp订单客户-通过id删除")
	@DeleteMapping(value = "/deleteErpCesOrderCustomer1")
	public Result<?> deleteErpCesOrderCustomer1(@RequestParam(name="id",required=true) String id) {
		erpCesOrderCustomer1Service.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "Erp订单客户-批量删除")
	@ApiOperation(value="Erp订单客户-批量删除", notes="Erp订单客户-批量删除")
	@DeleteMapping(value = "/deleteBatchErpCesOrderCustomer1")
	public Result<?> deleteBatchErpCesOrderCustomer1(@RequestParam(name="ids",required=true) String ids) {
	    this.erpCesOrderCustomer1Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportErpCesOrderCustomer1")
    public ModelAndView exportErpCesOrderCustomer1(HttpServletRequest request, ErpCesOrderCustomer1 erpCesOrderCustomer1) {
		 // Step.1 组装查询条件
		 QueryWrapper<ErpCesOrderCustomer1> queryWrapper = QueryGenerator.initQueryWrapper(erpCesOrderCustomer1, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<ErpCesOrderCustomer1> pageList = erpCesOrderCustomer1Service.list(queryWrapper);
		 List<ErpCesOrderCustomer1> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "Erp订单客户"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ErpCesOrderCustomer1.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("Erp订单客户报表", "导出人:" + sysUser.getRealname(), "Erp订单客户"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importErpCesOrderCustomer1/{mainId}")
    public Result<?> importErpCesOrderCustomer1(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<ErpCesOrderCustomer1> list = ExcelImportUtil.importExcel(file.getInputStream(), ErpCesOrderCustomer1.class, params);
				 for (ErpCesOrderCustomer1 temp : list) {
                    temp.setOrderMainId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 erpCesOrderCustomer1Service.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-Erp订单客户-end----------------------------------------------*/




}
