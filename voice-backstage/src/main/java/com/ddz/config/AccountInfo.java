package com.ddz.config;

import com.ddz.domain.entity.AccountEntity;
import com.ddz.domain.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2020/5/18.
 */
public class AccountInfo extends AccountEntity{
    private Set<RoleInfo> roles = new HashSet<RoleInfo>();

    public Set<RoleInfo> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleInfo> roles) {
        this.roles = roles;
    }
}
