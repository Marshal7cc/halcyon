package com.marshal.halcyon.mail.controller;

import com.marshal.halcyon.core.component.ResponseData;
import com.marshal.halcyon.mail.entity.MailRequest;
import com.marshal.halcyon.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/1/3
 * @desc: 邮件发送controller
 */
@Controller
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/sendMail")
    public ResponseData sendMail(@RequestBody MailRequest mailRequest) {
        emailService.sendSimpleEmail(mailRequest.getReceiver(), mailRequest.getSubject(), mailRequest.getContent());
        return new ResponseData();
    }

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
        Map<String, String> params = new HashMap<>();
        params.put("username", "Marshal");

        emailService.sendTemplateEmail("475032739@qq.com", "主题：这是模板邮件", "registerTemplate", params);
    }

}
