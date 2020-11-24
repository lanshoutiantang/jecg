package org.jeecg.modules.demo3.intel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.ZnSeasonGoods;
import org.jeecg.modules.demo3.intel.service.IZnSeasonGoodsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 季节性商品在季节期间的周销售平均值
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
@Api(tags="季节性商品在季节期间的周销售平均值")
@RestController
@RequestMapping("/intel/znSeasonGoods")
@Slf4j
public class ZnSeasonGoodsController extends JeecgController<ZnSeasonGoods, IZnSeasonGoodsService> {
	@Autowired
	private IZnSeasonGoodsService znSeasonGoodsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znSeasonGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "季节性商品在季节期间的周销售平均值-分页列表查询")
	@ApiOperation(value="季节性商品在季节期间的周销售平均值-分页列表查询", notes="季节性商品在季节期间的周销售平均值-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnSeasonGoods znSeasonGoods,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnSeasonGoods> queryWrapper = QueryGenerator.initQueryWrapper(znSeasonGoods, req.getParameterMap());
		Page<ZnSeasonGoods> page = new Page<ZnSeasonGoods>(pageNo, pageSize);
		IPage<ZnSeasonGoods> pageList = znSeasonGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znSeasonGoods
	 * @return
	 */
	@AutoLog(value = "季节性商品在季节期间的周销售平均值-添加")
	@ApiOperation(value="季节性商品在季节期间的周销售平均值-添加", notes="季节性商品在季节期间的周销售平均值-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnSeasonGoods znSeasonGoods) {
		znSeasonGoodsService.save(znSeasonGoods);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znSeasonGoods
	 * @return
	 */
	@AutoLog(value = "季节性商品在季节期间的周销售平均值-编辑")
	@ApiOperation(value="季节性商品在季节期间的周销售平均值-编辑", notes="季节性商品在季节期间的周销售平均值-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnSeasonGoods znSeasonGoods) {
		znSeasonGoodsService.updateById(znSeasonGoods);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "季节性商品在季节期间的周销售平均值-通过id删除")
	@ApiOperation(value="季节性商品在季节期间的周销售平均值-通过id删除", notes="季节性商品在季节期间的周销售平均值-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znSeasonGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "季节性商品在季节期间的周销售平均值-批量删除")
	@ApiOperation(value="季节性商品在季节期间的周销售平均值-批量删除", notes="季节性商品在季节期间的周销售平均值-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znSeasonGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "季节性商品在季节期间的周销售平均值-通过id查询")
	@ApiOperation(value="季节性商品在季节期间的周销售平均值-通过id查询", notes="季节性商品在季节期间的周销售平均值-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnSeasonGoods znSeasonGoods = znSeasonGoodsService.getById(id);
		if(znSeasonGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znSeasonGoods);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znSeasonGoods
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnSeasonGoods znSeasonGoods) {
        return super.exportXls(request, znSeasonGoods, ZnSeasonGoods.class, "季节性商品在季节期间的周销售平均值");
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
        return super.importExcel(request, response, ZnSeasonGoods.class);
    }

}
