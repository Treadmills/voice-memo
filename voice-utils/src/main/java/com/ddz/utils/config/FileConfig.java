package com.ddz.utils.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目配置
 * Created by admin on 2019/11/26.
 */
@Component
@ConfigurationProperties(prefix = "voice.file", ignoreInvalidFields = true)
public class FileConfig {

    private static String path;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        FileConfig.path = path;
    }
}
