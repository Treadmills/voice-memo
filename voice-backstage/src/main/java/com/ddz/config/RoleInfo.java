package com.ddz.config;

import com.ddz.domain.entity.MenuEntity;
import com.ddz.domain.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2020/5/18.
 */
public class RoleInfo extends RoleEntity{
    private Set<MenuEntity> menus = new HashSet<MenuEntity>();

    public Set<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuEntity> menus) {
        this.menus = menus;
    }
}
