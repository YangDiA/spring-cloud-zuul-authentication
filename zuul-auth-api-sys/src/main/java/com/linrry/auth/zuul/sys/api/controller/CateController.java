package com.linrry.auth.zuul.sys.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.ArrayUtils;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.common.controller.CrudController;
import com.linrry.auth.zuul.sys.api.entity.Apirule;
import com.linrry.auth.zuul.sys.api.entity.Cate;
import com.linrry.auth.zuul.sys.api.service.IApiruleService;
import com.linrry.auth.zuul.sys.api.service.ICateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

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
    @Autowired
    private IApiruleService apiruleService;

    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, String cateName) {

        Result result = Result.ok();
       /* EntityWrapper<Cate> ew = new EntityWrapper<Cate>();
        ew.orderBy("code", true);
        if (StringUtils.isNotBlank(cateName)) {
            ew.like("cateName", cateName);
        }

        Page<Cate> pageData = cateService.selectPage(new Page<Cate>(page, limit), ew);*/
        EntityWrapper<Apirule>ew =  new EntityWrapper<Apirule>();
        ew.orderBy("cateId", true);
        if (StringUtils.isNotBlank(cateName)) {
            ew.like("cateName", cateName);
        }
        ew.eq("type",1);
        Page<Apirule> pageData = apiruleService.selectPage(new Page<>(page,limit),ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid Cate t, BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        //cateService.insert(t);
        Apirule apirule = new Apirule();
        apirule.setRuleName(t.getCateName());
        apirule.setCateId(0);
        apirule.setType(1);
        apirule.setCateName(t.getCateName());
        apirule.setCode(t.getCode());
        apiruleService.insert(apirule);
        apirule.setCateId(apirule.getId());
        EntityWrapper<Apirule>ew =  new EntityWrapper<Apirule>();
        ew.eq("id",apirule.getId());
        apiruleService.update(apirule, ew );
        return Result.ok("新增成功");
    }


    @ResponseBody
    @RequestMapping("/selectById")
    public Result selectById(String id){
        if(com.baomidou.mybatisplus.toolkit.StringUtils.isEmpty(id)){
            return Result.failure("参数{id}不能为空");
        }
        Apirule t =  apiruleService.selectById(id);
        return Result.ok("获取成功").setData(t);
    }



    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid Cate t,BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        Apirule apirule = new Apirule();
        apirule.setId(t.getId());
        apirule.setCode(t.getCode());
        apirule.setCateName(t.getCateName());
        apirule.setRuleName(t.getCateName());
        apiruleService.updateById(apirule);
        return Result.ok("更新成功");
    }


    /**
     * 删除对象,可批量删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(String[] id){

        boolean b = true;

        if(ArrayUtils.isEmpty(id)){
            return Result.failure("参数{id}不能为空");
        }else{
            b = apiruleService.deleteBatchIds(Arrays.asList(id));
        }
        return b ? Result.ok("删除成功") : Result.failure("糟糕,删除失败");
    }
}

