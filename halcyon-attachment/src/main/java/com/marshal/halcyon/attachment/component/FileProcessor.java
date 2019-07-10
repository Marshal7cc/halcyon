package com.marshal.halcyon.attachment.component;

import com.marshal.halcyon.attachment.exception.AttachmentException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc: 文件处理链中的处理器
 */
public interface FileProcessor {

    /**
     * 处理逻辑.
     *
     * @param files 文件上传结果信息
     * @throws Exception 异常
     */
    void process(List<MultipartFile> files) throws AttachmentException;

}
