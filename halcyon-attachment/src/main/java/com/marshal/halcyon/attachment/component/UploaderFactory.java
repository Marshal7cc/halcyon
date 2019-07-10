package com.marshal.halcyon.attachment.component;

import com.marshal.halcyon.attachment.component.impl.DefaultUploader;
import com.marshal.halcyon.core.util.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/7/10
 * @desc: 获取uploader de 工厂,默认返回DefaultUploader
 */
@Component
public class UploaderFactory {

    //注入默认uploader
    @Autowired
    @Qualifier("defaultUploader")
    Uploader uploader;

    public Uploader getUploader() {
        return uploader;
    }

    /**
     * 支持自定义Uploader
     */
    @PostConstruct
    private void init() {

        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();

        Map<String, Uploader> beans = applicationContext.getBeansOfType(Uploader.class);

        if (beans.size() > 1) {
            beans.forEach((k, v) -> {
                if (!(v instanceof DefaultUploader)) {
                    this.uploader = v;
                }
            });
        }

    }

}
