package com.linrry.auth.zuul.sys.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.sys.api.entity.Cate;
import com.linrry.auth.zuul.sys.api.service.ICateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-05-17
 */
@RestController
@RequestMapping("/sys/cate")
public class CateController extends CrudController<Cate,ICateService> {

    @Autowired
    private  ICateService cateService;

    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, String cateName) {

        Result result = Result.ok();
        EntityWrapper<Cate> ew = new EntityWrapper<Cate>();
        ew.orderBy("code", true);
        if (StringUtils.isNotBlank(cateName)) {
            ew.like("cateName", cateName);
        }
        Page<Cate> pageData = cateService.selectPage(new Page<Cate>(page, limit), ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;

    }
}

