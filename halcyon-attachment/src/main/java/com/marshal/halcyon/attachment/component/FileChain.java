package com.marshal.halcyon.attachment.component;

import com.marshal.halcyon.attachment.exception.AttachmentException;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc: 文件上传处理链
 */
public interface FileChain {

    /**
     * 执行处理过程
     *
     * @throws AttachmentException
     */
    void doProcess() throws AttachmentException;

    /**
     * 添加处理器.
     *
     * @param fileProcessor FileProcessor
     */
    void addProcessor(FileProcessor fileProcessor);
}
