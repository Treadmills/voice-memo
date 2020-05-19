package com.ddz.utils.base;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by admin on 2019/11/19.
 */
public class R<T> implements Serializable
{
    private Logger logger = LoggerFactory.getLogger(R.class);

    private static final long serialVersionUID = -4577255781088498763L;
    private static final int OK = 0;
    private static final int FAIL = 1;
    private static final int UNJURISDICTION = 998;//没有访问权限
    private static final int UNAUTHORIZED = 999;//未登录，登陆过期

    private T data; //服务端数据
    private int status = OK; //状态码
    private String msg = ""; //描述信息

    //APIS
    public static R isOk() {
        return new R();
    }

    public static R isFail() {
        return new R().status(FAIL);
    }

    public static R isUnjurisdiction() {
        return new R().status(UNJURISDICTION);
    }

    public static R isUnauthorized() {
        return new R().status(UNAUTHORIZED);
    }

    public static R isUnauthorized(String info) {
        return new R().status(UNAUTHORIZED).msg(info);
    }

    public static R isFail(String info) {
        return isFail().msg(info);
    }

    public R msg(String info) {
        this.setMsg(info);
        return this;
    }

    public R data(T data) {
        this.setData(data);
        logger.info("-->> "+JSONObject.toJSONString(this));
        System.out.println(JSONObject.toJSONString(this));
        return this;
    }

    public R status(int status) {
        this.setStatus(status);
        return this;
    }


    //Constructors
    public R() {

    }

    //Getter&Setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
