package com.ddz.common.wx;

import com.ddz.controller.LoginController;
import com.ddz.utils.base.HexStringUtils;
import com.ddz.utils.config.WxConfig;
import com.ddz.utils.base.Json2Utils;
import com.ddz.utils.http.HttpUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2019/11/19.
 */
public class WxApiHelper {

    public static final Logger logger = LoggerFactory.getLogger(WxApiHelper.class);

    public static Map<String,Object> code2Session(String code){
        Map<String, Object> retMap = new HashMap<>();

        try {
            if (StringUtils.isBlank(code)) {
                retMap.put("errcode", "40029");
                retMap.put("errmsg", "code无效");
            } else {
                String[] params = new String[4];
                params[0] = "appid:" + WxConfig.APP_ID;
                params[1] = "secret:" + WxConfig.APP_SECRET;
                params[2] = "js_code:" + code;
                params[3] = "grant_type:authorization_code";
                String url = WxConfig.codeSessionUrl;

                String result = HttpUtil.getRequest(url, params);
                retMap = Json2Utils.toMap(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            retMap.put("errcode", "40029");
            retMap.put("errmsg", ex.getMessage());
        }
        return retMap;
    }

    /**
     * 作用：获取access_token是调用微信API接口的第一步，
     * 相当于创建了一个登录凭证，其它的业务API接口，都需要依赖于access_token来鉴权调用者身份
     */
    public static String getWxToken() {
        GeneralToken generalToken = WeixinToken.getInstance().getToken();
        try {
            long currentTime = System.currentTimeMillis();
            if ((generalToken == null) || (generalToken.getExpires_time() <= currentTime)) {
                String tokenUrl = WxConfig.tokenUrl+"?grant_type=client_credential&" +
                        "appid=" + WxConfig.APP_ID + "&secret=" + WxConfig.APP_SECRET;;
                String res = HttpUtil.getRequest(tokenUrl, null);
                System.out.println(res);
                JSONObject jsSon = new JSONObject(res); //将字符串{“id”：1}
                if (jsSon.has("access_token")) {
                    String accessToken = (String) jsSon.get("access_token");
                    int expires_in = jsSon.getInt("expires_in");
                    if (generalToken == null) {
                        WeixinToken.getInstance().setToken(new GeneralToken());
                        generalToken = WeixinToken.getInstance().getToken();
                    }
                    generalToken.setAccess_token(accessToken);
                    generalToken.setExpires_time(currentTime + expires_in - 200);
                    return accessToken;
                }
            } else {
                return generalToken.getAccess_token();
            }
        } catch (Exception ex) {
            logger.error("获取accessToken出错：" + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> getUserInfo(String openid){
        Map<String, Object> retMap = new HashMap<>();
        //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        try {
            if (StringUtils.isBlank(openid)) {
                retMap.put("errcode", "40029");
                retMap.put("errmsg", "openid无效");
            } else {
                String[] params = new String[3];
                params[0] = "access_token:" + getWxToken();
                params[1] = "openid:" + openid;
                params[2] = "lang:zh_CN";
                String url = WxConfig.userInfoUrl;

                String result = HttpUtil.getRequest(url, params);
                retMap = Json2Utils.toMap(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            retMap.put("errcode", "40029");
            retMap.put("errmsg", ex.getMessage());
        }
        return retMap;
    }


    /**
     * 获取小程序码
     * @param scene
     * @return
     */
    public static String getUnlimit(String scene){
        String data = null;
        try {
            if (StringUtils.isBlank(scene)) {
                return data;
            } else {
                Map<String,Object> params = new HashedMap(2);
                params.put("scene",HttpUtil.urlEncode(scene));
                byte[] bf= HttpUtil.postJsonBuffer(WxConfig.getUnlimit+"?access_token="+getWxToken(), com.alibaba.fastjson.JSONObject.toJSONString(params));
                data = HexStringUtils.toHexString(bf);

                data = Base64.getEncoder().encodeToString(bf);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
       // System.out.println(getUnlimit("111111"));
        System.out.println(getUserInfo("oNzhL5HSxOeKK7RHyfGqyhkqYaCg"));
    }
}
