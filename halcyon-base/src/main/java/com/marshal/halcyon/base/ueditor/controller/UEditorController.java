package com.marshal.halcyon.base.ueditor.controller;

import com.marshal.halcyon.base.ueditor.config.ConfigJson;
import com.marshal.halcyon.base.ueditor.entity.UEditor;
import com.marshal.halcyon.base.ueditor.util.OSSClientUtil;
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

    //图片上传文件夹
    public static final String UEDITOR_UPLOAD_FOLDER = "ueditor";

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
                String originalFilename = upfile.getOriginalFilename();
                String newFileName = OSSClientUtil.uploadImgToOSS(upfile, UEDITOR_UPLOAD_FOLDER);

                ueditor.setState("SUCCESS");
                ueditor.setUrl(OSSClientUtil.getUEditorImgUrl(newFileName));
                ueditor.setTitle(originalFilename);
                ueditor.setOriginal(originalFilename);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ueditor.setState("文件上传失败!");
        }
        return ueditor;
    }
}
