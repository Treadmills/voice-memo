package com.ddz.domain.service;

import com.ddz.domain.entity.AccountEntity;

/**
 * Created by admin on 2020/5/18.
 */
public interface AccountService {

    AccountEntity findByUserName(String username);

    int update(AccountEntity accountEntity);

    int delete(AccountEntity accountEntity);
}
