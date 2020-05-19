package com.ddz.utils.config;

/**
 * Created by admin on 2019/11/19.
 */
public class WxConfig {

    public static final String APP_ID = "wx7deecbcb0ce3513b";

    public static final String APP_SECRET = "b38311bdb64615988b03f796942d92c9";

    public static final String codeSessionUrl= "https://api.weixin.qq.com/sns/jscode2session";

    public static final String getUnlimit= "https://api.weixin.qq.com/wxa/getwxacodeunlimit";

    public static final String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

    //https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    public static final String userInfoUrl= "https://api.weixin.qq.com/cgi-bin/user/info";
}
