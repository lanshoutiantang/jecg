package org.jeecg.modules.demo3.chongzuo.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.chongzuo.entity.CesShop1Type1;
import org.jeecg.modules.demo3.chongzuo.service.ICesShop1Type1Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2020-10-23
 * @Version: V1.0
 */
@Api(tags="商品分类")
@RestController
@RequestMapping("/chhongzuo/cesShop1Type1")
@Slf4j
public class CesShop1Type1Controller extends JeecgController<CesShop1Type1, ICesShop1Type1Service>{
	@Autowired
	private ICesShop1Type1Service cesShop1Type1Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param cesShop1Type1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商品分类-分页列表查询")
	@ApiOperation(value="商品分类-分页列表查询", notes="商品分类-分页列表查询")
	@GetMapping(value = "/rootList")
	public Result<?> queryPageList(CesShop1Type1 cesShop1Type1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String hasQuery = req.getParameter("hasQuery");
        if(hasQuery != null && "true".equals(hasQuery)){
            QueryWrapper<CesShop1Type1> queryWrapper =  QueryGenerator.initQueryWrapper(cesShop1Type1, req.getParameterMap());
            List<CesShop1Type1> list = cesShop1Type1Service.queryTreeListNoPage(queryWrapper);
            IPage<CesShop1Type1> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        }else{
            String parentId = cesShop1Type1.getPid();
            if (oConvertUtils.isEmpty(parentId)) {
                parentId = "0";
            }
            cesShop1Type1.setPid(null);
            QueryWrapper<CesShop1Type1> queryWrapper = QueryGenerator.initQueryWrapper(cesShop1Type1, req.getParameterMap());
            // 使用 eq 防止模糊查询
            queryWrapper.eq("pid", parentId);
            Page<CesShop1Type1> page = new Page<CesShop1Type1>(pageNo, pageSize);
            IPage<CesShop1Type1> pageList = cesShop1Type1Service.page(page, queryWrapper);
            return Result.OK(pageList);
        }
	}

	 /**
      * 获取子数据
      * @param cesShop1Type1
      * @param req
      * @return
      */
	@AutoLog(value = "商品分类-获取子数据")
	@ApiOperation(value="商品分类-获取子数据", notes="商品分类-获取子数据")
	@GetMapping(value = "/childList")
	public Result<?> queryPageList(CesShop1Type1 cesShop1Type1,HttpServletRequest req) {
		QueryWrapper<CesShop1Type1> queryWrapper = QueryGenerator.initQueryWrapper(cesShop1Type1, req.getParameterMap());
		List<CesShop1Type1> list = cesShop1Type1Service.list(queryWrapper);
		IPage<CesShop1Type1> pageList = new Page<>(1, 10, list.size());
        pageList.setRecords(list);
		return Result.OK(pageList);
	}

    /**
      * 批量查询子节点
      * @param parentIds 父ID（多个采用半角逗号分割）
      * @return 返回 IPage
      * @param parentIds
      * @return
      */
	@AutoLog(value = "商品分类-批量获取子数据")
    @ApiOperation(value="商品分类-批量获取子数据", notes="商品分类-批量获取子数据")
    @GetMapping("/getChildListBatch")
    public Result getChildListBatch(@RequestParam("parentIds") String parentIds) {
        try {
            QueryWrapper<CesShop1Type1> queryWrapper = new QueryWrapper<>();
            List<String> parentIdList = Arrays.asList(parentIds.split(","));
            queryWrapper.in("pid", parentIdList);
            List<CesShop1Type1> list = cesShop1Type1Service.list(queryWrapper);
            IPage<CesShop1Type1> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("批量查询子节点失败：" + e.getMessage());
        }
    }
	
	/**
	 *   添加
	 *
	 * @param cesShop1Type1
	 * @return
	 */
	@AutoLog(value = "商品分类-添加")
	@ApiOperation(value="商品分类-添加", notes="商品分类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CesShop1Type1 cesShop1Type1) {
		cesShop1Type1Service.addCesShop1Type1(cesShop1Type1);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cesShop1Type1
	 * @return
	 */
	@AutoLog(value = "商品分类-编辑")
	@ApiOperation(value="商品分类-编辑", notes="商品分类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CesShop1Type1 cesShop1Type1) {
		cesShop1Type1Service.updateCesShop1Type1(cesShop1Type1);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品分类-通过id删除")
	@ApiOperation(value="商品分类-通过id删除", notes="商品分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cesShop1Type1Service.deleteCesShop1Type1(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品分类-批量删除")
	@ApiOperation(value="商品分类-批量删除", notes="商品分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cesShop1Type1Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品分类-通过id查询")
	@ApiOperation(value="商品分类-通过id查询", notes="商品分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CesShop1Type1 cesShop1Type1 = cesShop1Type1Service.getById(id);
		if(cesShop1Type1==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cesShop1Type1);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cesShop1Type1
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CesShop1Type1 cesShop1Type1) {
		return super.exportXls(request, cesShop1Type1, CesShop1Type1.class, "商品分类");
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
		return super.importExcel(request, response, CesShop1Type1.class);
    }

}
