package com.marshal.halcyon.attachment.mapper;


import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import com.marshal.halcyon.attachment.entity.SysAttachmentCategory;

public interface SysAttachmentCategoryMapper extends Mapper<SysAttachmentCategory> {

    SysAttachmentCategory selectCategoryBySourceType(@Param("sourceType") String sourceType);

}