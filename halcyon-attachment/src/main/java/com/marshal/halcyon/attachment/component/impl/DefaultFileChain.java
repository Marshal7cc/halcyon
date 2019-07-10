package com.marshal.halcyon.attachment.component.impl;

import com.marshal.halcyon.attachment.component.FileChain;
import com.marshal.halcyon.attachment.component.FileProcessor;
import com.marshal.halcyon.attachment.exception.AttachmentException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc:
 */
public class DefaultFileChain implements FileChain {

    private List<FileProcessor> processors;

    private List<MultipartFile> files;

    private Integer processorIndex = 0;

    @Override
    public void doProcess() throws AttachmentException {
        if (processors != null && !processors.isEmpty() && processorIndex < processors.size()) {
            processors.get(processorIndex++).process(files);
        }
    }

    @Override
    public void addProcessor(FileProcessor fileProcessor) {
        processors.add(fileProcessor);
    }
}
