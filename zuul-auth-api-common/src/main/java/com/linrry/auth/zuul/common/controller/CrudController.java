package com.linrry.auth.zuul.common.controller;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.linrry.auth.zuul.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Arrays;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-05-09 下午 15:02
 */
public abstract class CrudController <T extends Serializable,S extends IService<T>>{

    /**
     * 注入服务层
     */
    @Autowired(required = false) private S s;


    @ResponseBody
    @RequestMapping("/selectById")
    public Result selectById(String id){
        if(StringUtils.isEmpty(id)){
            return Result.failure("参数{id}不能为空");
        }
        T t =  s.selectById(id);
        return Result.ok("获取成功").setData(t);
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid T t,BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        s.insert(t);
        return Result.ok("新增成功");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid T t,BindingResult result){
        if(result.hasErrors()){
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return Result.failure(firstError);
        }
        s.updateById(t);
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
            b = s.deleteBatchIds(Arrays.asList(id));
        }
        return b ? Result.ok("删除成功") : Result.failure("糟糕,删除失败");
    }

}
