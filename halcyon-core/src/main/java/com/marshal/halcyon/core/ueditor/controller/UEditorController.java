package com.marshal.halcyon.core.ueditor.controller;

import com.marshal.halcyon.core.ueditor.config.ConfigJson;
import com.marshal.halcyon.core.ueditor.entity.UEditor;
import com.marshal.halcyon.core.util.OSSClientUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auth: Marshal
 * @date: 2019/2/19
 * @desc:
 */
@RestController
@RequestMapping("/UEditor")
public class UEditorController {

    /**
     * 获取ConfigJson配置
     *
     * @return
     */
    @RequestMapping(value = "/config")
    public String getConfigJson() {
        return ConfigJson.CONFIG_JSON;
    }

    /**
     * 编辑器图片上传
     *
     * @param ueditor
     * @param upfile
     * @return
     */
    @RequestMapping(value = "/imgUpload")
    public UEditor imgUpload(UEditor ueditor, MultipartFile upfile) {
        try {

            if (!upfile.isEmpty()) {
                String fileName = upfile.getOriginalFilename();
                String imgName = OSSClientUtil.uploadImgToOss(upfile, "ueditor");

                ueditor.setState("SUCCESS");
                ueditor.setUrl(OSSClientUtil.getUEditorImgUrl(imgName));
                ueditor.setTitle(fileName);
                ueditor.setOriginal(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ueditor.setState("文件上传失败!");
        }
        return ueditor;
    }
}
