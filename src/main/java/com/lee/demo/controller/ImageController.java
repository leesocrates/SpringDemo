package com.lee.demo.controller;

import com.lee.demo.utils.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
public class ImageController {

    @PostMapping(path = "/multipleImageUpload")
    public List<Map<String, Object>> multipleImageUpload(@RequestParam("uploadFiles") MultipartFile[] files) {
        System.out.println("上传的图片数：" + files.length);

        List<Map<String, Object>> resultList = new ArrayList<>();

        for (MultipartFile file : files) {    //循环保存文件

            Map<String, Object> resultMap = new HashMap<String, Object>();//一个文件上传的结果
            String resultMessage = "";//上传结果信息

            if (file.getSize() / 1000 > 500) {
                resultMessage = "图片大小不能超过100KB";
            } else {
                //判断上传文件格式
                String fileType = file.getContentType();
                if ("image/jpg".equals(fileType) || "image/png".equals(fileType) || "image/jpeg".equals(fileType)) {
                    // 要上传的目标文件存放的绝对路径
                    final String localPath = isLinux()? "/var/www/images" : "D:\\images";
                    //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                    //获取文件名
                    String fileName = file.getOriginalFilename();
                    resultMap.put("originFileName", fileName);
                    //获取文件后缀名
                    String suffixName = fileName!=null ? fileName.substring(fileName.lastIndexOf(".")): ".jpg";
                    //重新生成文件名
                    fileName = UUID.randomUUID() + suffixName;
                    if (FileUtils.upload(file, localPath, fileName)) {
                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
                        String relativePath = "images/" + fileName;
                        resultMap.put("relativePath", relativePath);//前端根据是否存在该字段来判断上传是否成功
                        resultMessage = "图片上传成功";
                    } else {
                        resultMessage = "图片上传失败";
                    }
                } else {
                    resultMessage = "图片格式不正确";
                }
            }
            resultMap.put("resultMessage", resultMessage);
            resultList.add(resultMap);
        }
        return resultList;
    }

    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }



}
