package com.ddz.utils.http;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
/**
 * Http 简单封装
 *
 * @author xuyw
 * @email xyw10000@163.com
 * @date 2012-06-14
 */
public class HttpUtil {
    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 6000;
    //读取超时时间
    private static final int READ_TIMEOUT = 5000;
    // 参数编码
    private static final String ENCODE_CHARSET = "utf-8";

    /**
     * 发送HTTP_POST请求
     *
     * @param reqURL 请求地址
     * @param params 发送到远程主机的正文数据[a:1,b:2]
     * @return String
     */
    public static String postRequest(String reqURL, String... params) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            url = new URL(reqURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);// 设置输入流采用字节流
                urlConn.setDoOutput(true);// 设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false); // POST请求不能使用缓存
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接超时
                urlConn.setReadTimeout(READ_TIMEOUT); // 设置读取超时
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                urlConn.setRequestProperty("Charset", ENCODE_CHARSET);//
                String param = sendPostParams(params);
                urlConn.setRequestProperty("Content-Length",
                        param.getBytes().length + "");//
                // urlConn.setRequestProperty("Connection", "Keep-Alive");
                // //设置长连接
                urlConn.connect();// 连接服务器发送消息
                if (!"".equals(param)) {
                    out = new DataOutputStream(urlConn.getOutputStream());
                    // 将要上传的内容写入流中
                    out.writeBytes(param);
                    // 刷新、关闭
                    out.flush();
                    out.close();

                }
                in = new InputStreamReader(urlConn.getInputStream(),
                        HttpUtil.ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData.toString();
    }

    /**
     * 发送HTTP_GET请求
     *
     * @param httpUrl 请求地址
     * @return String
     */
    public static String getRequest(String httpUrl, String... params) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            String paramurl = sendGetParams(httpUrl, params);
            url = new URL(paramurl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
                in = new InputStreamReader(urlConn.getInputStream(),
                        HttpUtil.ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultData.toString();
    }

    /**
     * Post追加参数
     *
     * @param params 发送到远程主机的正文数据[a:1,b:2]
     * @return
     * @see <code>params</code>
     */
    private static String sendPostParams(String... params) {
        StringBuilder sbd = new StringBuilder("");
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                String[] temp = params[i].split(":");
                sbd.append(temp[0]);
                sbd.append("=");
                sbd.append(urlEncode(temp[1]));
                sbd.append("&");

            }
            sbd.setLength(sbd.length() - 1);// 删掉最后一个
        }
        return sbd.toString();
    }

    /**
     * Get追加参数
     *
     * @param reqURL 请求地址
     * @param params 发送到远程主机的正文数据[a:1,b:2]
     * @return
     * @see <code>params</code>
     */
    private static String sendGetParams(String reqURL, String... params) {
        StringBuilder sbd = new StringBuilder(reqURL);
        if (params != null && params.length > 0) {
            if (isexist(reqURL, "\\?")) {// 存在?
                sbd.append("&");
            } else {
                sbd.append("?");
            }
            for (int i = 0; i < params.length; i++) {
                String[] temp = params[i].split(":");
                sbd.append(temp[0]);
                sbd.append("=");
                sbd.append(urlEncode(temp[1]));
                sbd.append("&");

            }
            sbd.setLength(sbd.length() - 1);// 删掉最后一个
        }
        return sbd.toString();
    }

    /**
     * 发送HTTP_POST请求
     *
     * @param reqURL 请求地址
     * @param param  发送到远程主机的正文数据Json字符串格式
     * @return String
     */
    public static byte[] postJsonBuffer(String reqURL, String param) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            url = new URL(reqURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        byte[] data = null;
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);// 设置输入流采用字节流
                urlConn.setDoOutput(true);// 设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false); // POST请求不能使用缓存
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接超时
                urlConn.setReadTimeout(READ_TIMEOUT); // 设置读取超时
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type",
                        "Application/json;charset=UTF-8");
                urlConn.setRequestProperty("Charset", ENCODE_CHARSET);//
                //    String param = sendPostParams(params);
                urlConn.setRequestProperty("Content-Length",
                        param.getBytes().length + "");//
                // urlConn.setRequestProperty("Connection", "Keep-Alive");
                // //设置长连接
                urlConn.connect();// 连接服务器发送消息
                if (!"".equals(param)) {
                    out = new DataOutputStream(urlConn.getOutputStream());
                    // 将要上传的内容写入流中
                    out.write(param.getBytes("UTF-8"));
                    // 刷新、关闭
                    out.flush();
                    out.close();

                }
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1000];
                int rc = 0;
                while ((rc = urlConn.getInputStream().read(buff, 0, 500)) > 0) {
                    swapStream.write(buff, 0, rc);
                }

                data = swapStream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }


    // 查找某个字符串是否存在
    private static boolean isexist(String str, String fstr) {
        return str.indexOf(fstr) == -1 ? false : true;
    }

    /**
     * 编码
     *
     * @param source
     * @return
     */
    public static String urlEncode(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder
                    .encode(source, HttpUtil.ENCODE_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getPostXML(HttpServletRequest request) {

        String xmlData = "";
        Map<String, String> macMap = new HashMap<String, String>();
        ServletInputStream sis = null;
        try {
            //编码格式改成UTF-8
            request.setCharacterEncoding("UTF-8");

            sis = request.getInputStream();
            // 取HTTP请求流长度
            int size = request.getContentLength();
            // 用于缓存每次读取的数据
            byte[] buffer = new byte[size];
            // 用于存放结果的数组
            byte[] xmldataByte = new byte[size];
            int count = 0;
            int rbyte = 0;
            // 循环读取
            while (count < size) {
                // 每次实际读取长度存于rbyte中
                rbyte = sis.read(buffer);
                for (int i = 0; i < rbyte; i++) {
                    xmldataByte[count + i] = buffer[i];
                }
                count += rbyte;
            }

            xmlData = new String(xmldataByte, "UTF-8");
            System.out.println("查询xmlData为：" + xmlData);
            return xmlData;
        } catch (Exception e) {
            System.out.println("getPostXML error ：" + e);
        }
        return null;
    }

    /**
     * 发送HTTP_POST请求
     *
     * @param reqURL 请求地址
     * @param param  发送到远程主机的正文数据Json字符串格式
     * @return String
     */
    public static String postRequestJson(String reqURL, String param) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            url = new URL(reqURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);// 设置输入流采用字节流
                urlConn.setDoOutput(true);// 设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false); // POST请求不能使用缓存
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接超时
                urlConn.setReadTimeout(READ_TIMEOUT); // 设置读取超时
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                urlConn.setRequestProperty("Charset", ENCODE_CHARSET);//
                //    String param = sendPostParams(params);
                urlConn.setRequestProperty("Content-Length",
                        param.getBytes().length + "");//
                // urlConn.setRequestProperty("Connection", "Keep-Alive");
                // //设置长连接
                urlConn.connect();// 连接服务器发送消息
                if (!"".equals(param)) {
                    out = new DataOutputStream(urlConn.getOutputStream());
                    // 将要上传的内容写入流中
                    out.write(param.getBytes("UTF-8"));
                    // 刷新、关闭
                    out.flush();
                    out.close();

                }
                in = new InputStreamReader(urlConn.getInputStream(),
                        HttpUtil.ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData.toString();
    }

    /**
     * 发送HTTP_POST请求
     *
     * @param reqURL 请求地址
     * @param param  发送到远程主机的正文数据Json字符串格式
     * @return String
     */
    public static String postJson(String reqURL, String param) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            url = new URL(reqURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);// 设置输入流采用字节流
                urlConn.setDoOutput(true);// 设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false); // POST请求不能使用缓存
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接超时
                urlConn.setReadTimeout(READ_TIMEOUT); // 设置读取超时
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type",
                        "Application/json;charset=UTF-8");
                urlConn.setRequestProperty("Charset", ENCODE_CHARSET);//
                //    String param = sendPostParams(params);
                urlConn.setRequestProperty("Content-Length",
                        param.getBytes().length + "");//
                // urlConn.setRequestProperty("Connection", "Keep-Alive");
                // //设置长连接
                urlConn.connect();// 连接服务器发送消息
                if (!"".equals(param)) {
                    out = new DataOutputStream(urlConn.getOutputStream());
                    // 将要上传的内容写入流中
                    out.write(param.getBytes("UTF-8"));
                    // 刷新、关闭
                    out.flush();
                    out.close();

                }
                in = new InputStreamReader(urlConn.getInputStream(),
                        HttpUtil.ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData.toString();
    }


    /**
     * 发送HTTP_POST请求
     *
     * @param reqURL 请求地址
     * @param param  发送到远程主机的正文数据Json字符串格式
     * @return String
     */
    public static String postJsonAndHeader(String reqURL, String param,Map<String,String> headerMap) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {
            url = new URL(reqURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);// 设置输入流采用字节流
                urlConn.setDoOutput(true);// 设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false); // POST请求不能使用缓存
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接超时
                urlConn.setReadTimeout(READ_TIMEOUT); // 设置读取超时
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type",
                        "Application/json;charset=UTF-8");
                urlConn.setRequestProperty("Charset", ENCODE_CHARSET);//
                //    String param = sendPostParams(params);
                urlConn.setRequestProperty("Content-Length",
                        param.getBytes().length + "");//
                //需要设置的header
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    urlConn.setRequestProperty(entry.getKey(),entry.getValue());
                }
                System.out.println(JSONObject.toJSONString(headerMap));
                // urlConn.setRequestProperty("Connection", "Keep-Alive");
                // //设置长连接
                urlConn.connect();// 连接服务器发送消息
                if (!"".equals(param)) {
                    out = new DataOutputStream(urlConn.getOutputStream());
                    // 将要上传的内容写入流中
                    out.write(param.getBytes("UTF-8"));
                    // 刷新、关闭
                    out.flush();
                    out.close();

                }
                in = new InputStreamReader(urlConn.getInputStream(),
                        HttpUtil.ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData.toString();
    }

}
