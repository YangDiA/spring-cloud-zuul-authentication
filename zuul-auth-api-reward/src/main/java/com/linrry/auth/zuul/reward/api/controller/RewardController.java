package com.linrry.auth.zuul.reward.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.linrry.auth.zuul.common.Result;
import com.linrry.auth.zuul.reward.api.entity.Reward;
import com.linrry.auth.zuul.reward.api.service.IRewardService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 团队建设月奖表 前端控制器
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
@Controller
@RequestMapping("/reward/reward")
public class RewardController {

    @Autowired
    private IRewardService rewardService;

    @ResponseBody
    @RequestMapping("/page")
    public Result page(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit, String userId) {

        Result result = Result.ok();
        EntityWrapper<Reward> ew = new EntityWrapper<Reward>();
        if (StringUtils.isNotBlank(userId)) {
            ew.like("userId", userId);
        }
        Page<Reward> pageData = rewardService.selectPage(new Page<Reward>(page, limit), ew);
        result.setData(pageData.getRecords()).setCount(pageData.getTotal());
        return  result;

    }

}

