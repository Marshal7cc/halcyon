package com.marshal.halcyon.mail.service.impl;

import com.marshal.halcyon.mail.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @auth: Marshal
 * @date: 2019/1/3
 * @desc: 邮件发送Service
 */
@Component
@Slf4j
public class EmailServiceImpl implements EmailService {

    /**
     * 邮件发送者
     */
    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    JavaMailSender mailSender;

    /**
     * 发送简单邮件
     *
     * @param receiver
     * @param subject
     * @param content
     */
    @Override
    public void sendSimpleEmail(String receiver, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);

        /* String[] adds = {"xxx@qq.com","yyy@qq.com"};
        message.setTo(adds);*/

        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);//执行发送邮件
            log.info("简单邮件已经发送。");
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常！", e);
        }
    }

    /**
     * 发送html格式的邮件
     *
     * @param receiver
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlEmail(String receiver, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送html邮件时发生异常！", e);
        }

    }

    /**
     * 发送带附件的邮件
     *
     * @param receiver
     * @param subject
     * @param content
     * @param filePath
     */
    @Override
    public void sendAttachmentsEmail(String receiver, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);// true表示这个邮件是有附件的

            FileSystemResource file = new FileSystemResource(new File(filePath));//创建文件系统资源
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);//添加附件

            mailSender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件时发生异常！", e);
        }

    }

    /**
     * 发送带内联资源的邮件
     *
     * @param receiver
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    @Override
    public void sendInlineResourceEmail(String receiver, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));

            //添加内联资源，一个id对应一个资源，最终通过id来找到该资源
            helper.addInline(rscId, res);//添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现

            mailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }

    }
}
