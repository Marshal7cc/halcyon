package com.marshal.halcyon.attachment.component.impl;

import com.marshal.halcyon.attachment.component.*;
import com.marshal.halcyon.attachment.constant.AttachmentConstant;
import com.marshal.halcyon.attachment.entity.SysAttachment;
import com.marshal.halcyon.attachment.entity.SysAttachmentCategory;
import com.marshal.halcyon.attachment.entity.SysFile;
import com.marshal.halcyon.attachment.exception.AttachmentException;
import com.marshal.halcyon.attachment.mapper.SysAttachmentCategoryMapper;
import com.marshal.halcyon.attachment.mapper.SysAttachmentMapper;
import com.marshal.halcyon.attachment.mapper.SysFileMapper;
import com.marshal.halcyon.core.util.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc: 默认uploader
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class DefaultUploader implements Uploader, AttachmentConstant {

    @Autowired
    SysAttachmentMapper sysAttachmentMapper;

    @Autowired
    SysAttachmentCategoryMapper sysAttachmentCategoryMapper;

    @Autowired
    SysFileMapper sysFileMapper;

    //允许单个文件上传的大小.
    private long singleFileSize = SINGLE_FILE_SIZE_MAX;

    //允许总共文件上传的大小.
    private long allFileSize = ALL_FILE_SIZE;

    //允许文件上传个数.
    private int maxFileNum = MAX_FILE_NUM;

    private SysAttachmentCategory attachmentCategory;

    //附件类型
    private String sourceKey;

    //附件业务主键
    private String sourceType;

    private List<FileFilter> filters;

    private FileChain fileChain;

    private List<MultipartFile> fileItems;

    @Override
    public void addFilter(FileFilter filter) {
        filters.add(filter);
    }

    @Override
    public void addFileProcessor(FileProcessor processor) {
        fileChain.addProcessor(processor);
    }

    @Override
    public void init(HttpServletRequest request) throws AttachmentException {
        this.sourceKey = null;
        this.sourceType = null;
        this.filters = null;
        this.fileChain = null;
        this.fileItems = new ArrayList<>();
        this.attachmentCategory = null;

        if (!(request instanceof MultipartHttpServletRequest)) {
            throw new AttachmentException();
        }

        //初始化FileFilter过滤器
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        if (filters == null) {
            filters = new ArrayList<>();
        }
        Map<String, FileFilter> beans = applicationContext.getBeansOfType(FileFilter.class);
        beans.forEach((k, v) -> {
            filters.add(v);
        });

        FileChain defaultFileChain = new DefaultFileChain();
        this.fileChain = defaultFileChain;

        MultipartFile multipartFile = ((MultipartHttpServletRequest) request).getFile("files");
        this.fileItems.add(multipartFile);

        this.sourceType = request.getParameter("sourceType");
        this.sourceKey = request.getParameter("sourceKey");
        this.attachmentCategory = sysAttachmentCategoryMapper.selectCategoryBySourceType(sourceType);

    }

    @Override
    public void upload() throws AttachmentException {
        //过滤器过滤
        for (FileFilter filter : filters) {
            filter.isAccept(fileItems);
        }

        if (fileItems != null) {
            for (MultipartFile file : fileItems) {
                doUploadFile(file);
            }
        }

        //后置处理器
        fileChain.doProcess();
    }

    private void doUploadFile(MultipartFile file) {

        try {
            File dir = new File((attachmentCategory.getCategoryPath()));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //存储
            File f = new File(attachmentCategory.getCategoryPath() + File.separator + file.getOriginalFilename());
            file.transferTo(f);

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("文件上传错误！", e);
            }
        }

        SysAttachment attachment = new SysAttachment();
        attachment.setCategoryId(attachmentCategory.getCategoryId());
        attachment.setSourceKey(sourceKey);
        attachment.setSourceType(sourceType);
        attachment.setName(sourceType);

        sysAttachmentMapper.insertSelective(attachment);

        SysFile sysFile = new SysFile();
        sysFile.setAttachmentId(attachment.getAttachmentId());
        sysFile.setFileName(file.getOriginalFilename());
        sysFile.setFilePath(attachmentCategory.getCategoryPath() + File.separator + file.getOriginalFilename());
        sysFile.setFileSize(file.getSize());
        sysFile.setFileType(file.getContentType());
        sysFile.setUploadTime(new Date());
        sysFileMapper.insert(sysFile);
    }

    @Override
    public void setSingleFileSize(long singleFileSize) {
        this.singleFileSize = singleFileSize;
    }

    @Override
    public void setAllFileSize(long allFileSize) {
        this.allFileSize = allFileSize;
    }

    @Override
    public void setMaxFileNum(int maxFileNum) {
        this.maxFileNum = maxFileNum;
    }
}
