package com.ddz.controller;

import com.baidu.aip.speech.AipSpeech;
import com.ddz.domain.entity.UserInfo;
import com.ddz.utils.base.DateUtils;
import com.ddz.utils.base.R;
import com.ddz.utils.config.FileConfig;
import com.ddz.utils.voice.AudioUtils;
import com.ddz.utils.voice.VoiceAipSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by admin on 2019/11/26.
 */
@RestController
@RequestMapping(value = "/file", produces = "application/json; charset=utf-8")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${voice.file.path}")
    private String filePath;

    @RequestMapping(value = "discern",method = RequestMethod.POST)
    public R discern(HttpServletRequest request, @RequestParam( value="file",required=true)MultipartFile multipartFile) throws IllegalStateException, IOException {

        //这里一定要写required=false 不然前端不传文件一定报错。到不了后台。
        String realpath = "";
        //获取文件名
        String name = "";
        if (multipartFile != null) {
            long size = multipartFile.getSize();
            logger.debug("size:[{}]",size);
            if (size > 5242880) {//文件设置大小，我这里设置5M。
                return R.isFail("语音太长啦！");
            }
            name = multipartFile.getOriginalFilename();//直接返回文件的名字
            String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());//我这里取得文件后缀
            String fileName = DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS) + getRadomValue() +"";//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
            String basePath = filePath + "/voice/";
            String filepath = basePath + "/mp3/"+DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD);//获取项目路径到webapp
            File file = new File(filepath);
            if (!file.exists()) {//目录不存在就创建
                file.mkdirs();
            }
            String pcmpath = basePath + "/pcm/"+DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD);//获取项目路径到webapp
            File pcm = new File(pcmpath);
            if (!pcm.exists()) {//目录不存在就创建
                pcm.mkdirs();
            }
            multipartFile.transferTo(new File(file + "/test-" + fileName + "." + subffix));//保存文件
            realpath = file + "/test-" + fileName + "." + subffix;
            String pcmFilePath = pcmpath + "/test-"+fileName+".pcm";
            long ss = System.currentTimeMillis();
            logger.debug(ss+"");
            logger.debug("realpath:[{}],pcmFilePath:[{}]",realpath,pcmFilePath);
            boolean isDiscern = AudioUtils.convertMP32Pcm(realpath,pcmFilePath);
            logger.debug(System.currentTimeMillis()-ss+"ms--"+isDiscern);

            if(isDiscern){
                VoiceAipSpeech voiceAipSpeech = VoiceAipSpeech.getInstance();
                AipSpeech client = voiceAipSpeech.getClient();
                //开始识别语音信息
                org.json.JSONObject retVoiceStr = client.asr(pcmFilePath,"pcm", 16000, null);
                logger.debug("retVoiceStr:{}",retVoiceStr);
                Map<String,Object> map = new HashMap(2);

                map.put("voicePath",realpath);
                if(retVoiceStr.getInt("err_no") != 0 ){
                    map.put("voiceStr" ,"");
                }else{
                    map.put("voiceStr" ,retVoiceStr.getJSONArray("result"));
                }

                return R.isOk().data(map);
            }
        }
        return R.isFail("识别失败，请重试！");
    }

    public int getRadomValue(){
        return new Random(1).nextInt(1000);
    }
}
