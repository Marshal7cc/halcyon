package com.marshal.halcyon.mail.entity;

/**
 * @auth: Marshal
 * @date: 2019/1/5
 * @desc: 邮件发送请求
 */
public class MailRequest {

    private String subject;

    private String content;

    private String receiver;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
