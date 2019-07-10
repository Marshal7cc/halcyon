package com.marshal.halcyon.attachment.component;

import com.marshal.halcyon.attachment.exception.AttachmentException;

import javax.servlet.http.HttpServletRequest;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc: 文件上传器
 */
public interface Uploader {

    /**
     * 初始化上传组件，解析参数，分离文件类型，初始化相关子组件.<br>
     * 使用的时候必须先初始化.
     *
     * @param request HttpServletRequest
     */
    void init(HttpServletRequest request) throws AttachmentException;

    /**
     * 上传接口.
     *
     * @return 返回FileInfo类型list
     * @throws AttachmentException 文件上传异常
     */
    void upload() throws AttachmentException;

    /**
     * 设置 文件过滤器.
     *
     * @param filter 文件过滤器
     */
    void addFilter(FileFilter filter);

    /**
     * 添加文件处理器.
     *
     * @param processor 文件后续处理器
     */
    void addFileProcessor(FileProcessor processor);


    /**
     * 单个可上传文件大小.
     *
     * @param singleFileSize 文件大小
     */
    void setSingleFileSize(long singleFileSize);

    /**
     * 可上传总文件大小.
     *
     * @param allFileSize 一次性总文件大小
     */
    void setAllFileSize(long allFileSize);

    /**
     * 一次上传最大文件数量.
     *
     * @param maxFileNum 一次性文件上传数量
     */
    void setMaxFileNum(int maxFileNum);


}
