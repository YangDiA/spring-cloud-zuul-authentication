package com.linrry.auth.zuul.reward.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.reward.api.entity.Funds;
import com.linrry.auth.zuul.reward.api.service.IFundsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 资金流水表 前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
@Controller
@RequestMapping("/reward/funds")
public class FundsController {

    @Autowired
    private IFundsService fundsService;

    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, String userId) {

        Result result = Result.ok();
        EntityWrapper<Funds> ew = new EntityWrapper<Funds>();
        if (StringUtils.isNotBlank(userId)) {
            ew.eq("user_id", userId);
        }
        Page<Funds> pageData = fundsService.selectPage(new Page<Funds>(page, limit), ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;

    }
}

