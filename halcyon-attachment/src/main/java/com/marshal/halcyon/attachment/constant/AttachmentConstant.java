package com.marshal.halcyon.attachment.constant;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc:
 */
public interface AttachmentConstant {

    //文件上传总 单位byte 默认10G ALL_FILE_SIZE = 10737418240.
    long ALL_FILE_SIZE = 10737418240L;

    //单个文件上传 单位byte默认10M SINGLE_FILE_SIZE_MAX = 10485760.

    long SINGLE_FILE_SIZE_MAX = 10485760;

    // 默认一次可上传的最大文件数量 MAX_FILE_NUM = 9999.
    int MAX_FILE_NUM = 9999;

}
