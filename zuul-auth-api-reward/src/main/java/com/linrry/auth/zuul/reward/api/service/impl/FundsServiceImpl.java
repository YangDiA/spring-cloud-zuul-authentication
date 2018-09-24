package com.linrry.auth.zuul.reward.api.service.impl;

import com.linrry.auth.zuul.reward.api.entity.Funds;
import com.linrry.auth.zuul.reward.api.mapper.FundsMapper;
import com.linrry.auth.zuul.reward.api.service.IFundsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资金流水表 服务实现类
 * </p>
 *
 * @author linrry
 * @since 2018-09-24
 */
@Service
public class FundsServiceImpl extends ServiceImpl<FundsMapper, Funds> implements IFundsService {

}
