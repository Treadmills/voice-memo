package com.ddz.domain.service.impl;

import com.ddz.domain.entity.AccountEntity;
import com.ddz.domain.entity.AccountEntityExample;
import com.ddz.domain.mapper.AccountEntityMapper;
import com.ddz.domain.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2020/5/18.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountEntityMapper accountEntityMapper;

    @Override
    public AccountEntity findByUserName(String username) {
        AccountEntityExample example = new AccountEntityExample();
        example.createCriteria().andAccountUsernameEqualTo(username);
        List<AccountEntity> list = accountEntityMapper.selectByExample(example);
        return list.size()>0?list.get(0): null;
    }

    @Override
    public int update(AccountEntity accountEntity) {
        return 0;
    }

    @Override
    public int delete(AccountEntity accountEntity) {
        return 0;
    }
}
