package com.ttice.controller;


import com.alibaba.fastjson.JSONObject;
import com.ttice.Util.TencentCOS;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@io.swagger.annotations.Api(tags = "工具类Api")
@RestController
@RequestMapping("/Api")
public class Api {

    @ApiOperation(value = "上传图片")
    @ApiImplicitParam(name = "editormd-image-file",value = "图片Formate",required = true)
    @PostMapping("/updateimage")
    public JSONObject imageUpload(@RequestParam("editormd-image-file") MultipartFile image) throws IOException {
        JSONObject jsonObject = new JSONObject();
        // 获取文件名
        String fileName = image.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid做为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-"+System.currentTimeMillis(), prefix);
        // 将MultipartFile转为File
        image.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String fileNames = TencentCOS.uploadfile(excelFile);

        //程序结束时，删除临时文件
        TencentCOS.deletefile(String.valueOf(excelFile));
        //存入图片名称
        String path = fileNames;
        String imageName = TencentCOS.intage.concat("/").concat(path);
        System.out.println(imageName);
        jsonObject.put("url", imageName);
        //返回图片名称
        return jsonObject;

//        方法二:本地存储图片，需要配置资源映射为虚拟路径
//        JSONObject jsonObject = new JSONObject();
//        if(image != null) {
//            String path = FileUtils.uploadFile(image,"/Users/macbook/");
//            System.out.println(path);
//            jsonObject.put("url", path);
//            jsonObject.put("success", 1);
//            jsonObject.put("message", "upload success!");
//            return jsonObject;
//        }
//        jsonObject.put("success", 0);
//        jsonObject.put("message", "upload error!");
//        return jsonObject;
    }
}


