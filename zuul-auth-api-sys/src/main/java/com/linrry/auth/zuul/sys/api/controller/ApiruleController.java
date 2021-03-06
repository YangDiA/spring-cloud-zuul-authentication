package com.linrry.auth.zuul.sys.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.sys.api.entity.Apirule;
import com.linrry.auth.zuul.sys.api.entity.Cate;
import com.linrry.auth.zuul.sys.api.service.IApiruleService;
import com.linrry.auth.zuul.sys.api.service.ICateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-05-31
 */
@RestController
@RequestMapping("/sys/apirule")
public class ApiruleController extends CrudController<Apirule,IApiruleService> {

    @Autowired
    private IApiruleService apiruleService;

    @Autowired
    private ICateService cateService;

    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, Integer cateId) {

        Result result = Result.ok();
        EntityWrapper<Apirule> ew = new EntityWrapper<Apirule>();
        ew.orderBy("cateId", true);
        if (cateId != null && cateId !=0) {
            ew.eq("cateId",cateId);
        }
        ew.eq("type",0);
        Page<Apirule> pageData = apiruleService.selectPage(new Page<Apirule>(page, limit), ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;

    }
    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid Apirule t, BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        Cate cate =  cateService.selectById(t.getCateId());
        t.setCateName(cate.getCateName());

        apiruleService.insert(t);
        return Result.ok("新增成功");
    }
}

