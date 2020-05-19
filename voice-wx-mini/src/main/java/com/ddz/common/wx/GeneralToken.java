package com.ddz.common.wx;

/**
 * Created by dl on 2019/4/10.
 */
public class GeneralToken {
    private Long expires_time; //成功有效时间
    private String access_token;  // 普通Token

    //get set 忽略
    public Long getExpires_time() {
        return expires_time;
    }

    public void setExpires_time(Long expires_time) {
        this.expires_time = expires_time;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
