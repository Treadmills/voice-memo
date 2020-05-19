package com.ddz.domain.entity;

/**
 * Created by admin on 2019/11/20.
 */
public class UserInfo extends UserEntity{

    private String jseessionKey;

    public String getJseessionKey() {
        return jseessionKey;
    }

    public void setJseessionKey(String jseessionKey) {
        this.jseessionKey = jseessionKey;
    }
}
