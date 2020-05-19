package com.ddz.utils.voice;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

/**
 * 创建百度API语音识别
 * Created by admin on 2019/11/25.
 */
public class VoiceAipSpeech {
    //设置APPID/AK/SK
    public static final String APP_ID = "16506066";
    public static final String API_KEY = "XkGyw0ikjgGSPa15teVEz15v";
    public static final String SECRET_KEY = "GO2k8eX9XESKDvthV2GtI4Ijpbmc11SG";

    private static VoiceAipSpeech voiceAipSpeech;
    private static AipSpeech client;
    private VoiceAipSpeech(AipSpeech client){
        this.client = client;
    }

    public AipSpeech getClient() {
        return client;
    }

    public static VoiceAipSpeech getInstance(){
        try {
            if (null == voiceAipSpeech) {
                // 模拟在创建对象之前做一些准备工作
                Thread.sleep(1000);
                synchronized (VoiceAipSpeech.class) {
                    if(null == voiceAipSpeech) {

                        // 初始化一个AipSpeech
                        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

                        // 可选：设置网络连接参数
                        client.setConnectionTimeoutInMillis(2000);
                        client.setSocketTimeoutInMillis(60000);

                        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
                        // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
                        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

                        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
                        // 也可以直接通过jvm启动参数设置此环境变量
                        //  System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
                        voiceAipSpeech = new VoiceAipSpeech(client);
                    }
                }
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
        return voiceAipSpeech;
    }

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        VoiceAipSpeech voiceAipSpeech = VoiceAipSpeech.getInstance();

        AipSpeech client = voiceAipSpeech.getClient();

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
       // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
       // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
      //  System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.asr("C:\\Users\\admin\\AppData\\Local\\Temp\\tomcat-docbase.8708936049061607055.8762\\files\\pcm\\test-20191125130615.pcm", "pcm", 16000, null);
        System.out.println(res.toString(2));

    }
}
