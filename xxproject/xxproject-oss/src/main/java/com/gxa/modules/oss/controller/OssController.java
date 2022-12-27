package com.gxa.modules.oss.controller;

import com.gxa.common.exception.ResultException;
import com.gxa.common.utils.Result;
import com.gxa.modules.oss.factory.OSSFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys/oss")
public class OssController {

    /**
     * 上传文件
     */
    @PostMapping("/upload")
//    @RequiresPermissions("sys:oss:all")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new ResultException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

        Map map = new HashMap();
        map.put("url",url);
        return new Result().ok(map);
    }
}