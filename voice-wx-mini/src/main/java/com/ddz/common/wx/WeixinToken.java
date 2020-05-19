package com.ddz.common.wx;

/**
 * Created by dl on 2019/4/10.
 */
public class WeixinToken {
    private WeixinToken() {
    }

    private GeneralToken token;
    private static WeixinToken instance = new WeixinToken();

    public static WeixinToken getInstance() {
        return instance;
    }

    public GeneralToken getToken() {
        return token;
    }

    public void setToken(GeneralToken token) {
        this.token = token;
    }
}
