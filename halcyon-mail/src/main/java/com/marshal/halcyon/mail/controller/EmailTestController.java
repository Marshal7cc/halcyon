package com.marshal.halcyon.mail.controller;

import com.marshal.halcyon.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

/**
 * @auth: Marshal
 * @date: 2019/1/3
 * @desc: 邮件发送测试controller
 */
@Controller
@RequestMapping("/email")
public class EmailTestController {

    @Autowired
    EmailService emailService;

    @Autowired
    TemplateEngine templateEngine;

    @RequestMapping("/simpleTest")
    public void test() {
        emailService.sendSimpleEmail("475032739@qq.com", "你好", "你哈哈哈");
    }

    @RequestMapping("/htmlTest")
    public void sendHtmlMail() throws Exception {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendHtmlEmail("475032739@qq.com", "this is html mail", content);
    }


    @RequestMapping("/attTest")
    public void sendAttachmentsMail() {
        String filePath = "F:" + File.separator + "timg.jpg";
        emailService.sendAttachmentsEmail("475032739@qq.com", "主题：带附件的邮件", "收到附件，请查收！", filePath);

    }

    @RequestMapping("/inTest")
    public void sendInlineResourceMail() {
        String rscId = "001";
        String content = "<html><body>这是有图片的邮件：<img src='cid:" + rscId + "' ></body></html>";
        String imgPath = "F:" + File.separator + "timg.jpg";

        emailService.sendInlineResourceEmail("475032739@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @RequestMapping("/temTest")
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("username", "Marshal");
        String emailContent = templateEngine.process("registerTemplate", context);

        System.out.println(emailContent);
        emailService.sendHtmlEmail("475032739@qq.com", "主题：这是模板邮件", emailContent);
    }

}
