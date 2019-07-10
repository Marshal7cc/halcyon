package com.marshal.halcyon.attachment.component;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc: 文件上传过滤器，一般是一些前置校验工作
 */
public interface FileFilter {

    /**
     * 是否通过过滤
     *
     * @param files
     * @return 1.true通过  2.false 拒绝
     */
    boolean isAccept(List<MultipartFile> files);

}
