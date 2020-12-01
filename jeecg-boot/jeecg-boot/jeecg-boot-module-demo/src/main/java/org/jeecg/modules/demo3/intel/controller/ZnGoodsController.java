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
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.intel.entity.ZnGoods;
import org.jeecg.modules.demo3.intel.service.IZnGoodsService;

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
 * @Description: 门店补货商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Api(tags="门店补货商品表")
@RestController
@RequestMapping("/intel/znGoods")
@Slf4j
public class ZnGoodsController extends JeecgController<ZnGoods, IZnGoodsService> {
	@Autowired
	private IZnGoodsService znGoodsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-分页列表查询")
	@ApiOperation(value="门店补货商品表-分页列表查询", notes="门店补货商品表-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "oademo3/intel/ZnGoodsList")
	public Result<?> queryPageList(ZnGoods znGoods,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnGoods> queryWrapper = QueryGenerator.initQueryWrapper(znGoods, req.getParameterMap());
		Page<ZnGoods> page = new Page<ZnGoods>(pageNo, pageSize);
		IPage<ZnGoods> pageList = znGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znGoods
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-添加")
	@ApiOperation(value="门店补货商品表-添加", notes="门店补货商品表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnGoods znGoods) {
		znGoodsService.save(znGoods);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znGoods
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-编辑")
	@ApiOperation(value="门店补货商品表-编辑", notes="门店补货商品表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnGoods znGoods) {
		znGoodsService.updateById(znGoods);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-通过id删除")
	@ApiOperation(value="门店补货商品表-通过id删除", notes="门店补货商品表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-批量删除")
	@ApiOperation(value="门店补货商品表-批量删除", notes="门店补货商品表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "门店补货商品表-通过id查询")
	@ApiOperation(value="门店补货商品表-通过id查询", notes="门店补货商品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnGoods znGoods = znGoodsService.getById(id);
		if(znGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znGoods);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znGoods
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnGoods znGoods) {
        return super.exportXls(request, znGoods, ZnGoods.class, "门店补货商品表");
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
        return super.importExcel(request, response, ZnGoods.class);
    }

}
