package com.marshal.halcyon.mail.service;

public interface EmailService {

    void sendSimpleEmail(String receiver, String subject, String content);

    void sendHtmlEmail(String receiver, String subject, String content);

    void sendAttachmentsEmail(String receiver, String subject, String content, String filePath);

    void sendInlineResourceEmail(String receiver, String subject, String content, String rscPath, String rscId);

}
