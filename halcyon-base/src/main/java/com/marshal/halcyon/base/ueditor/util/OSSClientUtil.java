package com.marshal.halcyon.base.ueditor.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.marshal.halcyon.base.ueditor.controller.UEditorController;
import com.marshal.halcyon.base.ueditor.mapper.SysConfigMapper;
import com.marshal.halcyon.core.listener.ContextRefreshedListener;

import com.marshal.halcyon.core.util.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/2/20
 * @desc: 阿里云oss工具类
 */
@Component
public class OSSClientUtil implements ContextRefreshedListener {

    private static Logger log = LoggerFactory.getLogger(OSSClientUtil.class);


    private static OSSClient ossClient;

    private static Map<String, String> OSSConfigMap;

    private static SysConfigMapper sysConfigMapper;

    private OSSClientUtil() {

    }

    public synchronized static OSSClient getOSSClient() throws Exception {
        if (ossClient == null) {
            OSSConfigMap = sysConfigMapper.getOSSConfig();
            if (OSSConfigMap == null) {
                throw new Exception("error occured while getting ossClient");
            }

            String endPoint = OSSConfigMap.get("endpoint");
            String accessKeyId = OSSConfigMap.get("accessKeyId");
            String accessKeySecret = OSSConfigMap.get("accessKeySecret");

            if (StringUtils.isAnyBlank(endPoint, accessKeyId, accessKeySecret)) {
                throw new Exception("error occured while getting ossClient");
            }

            return new OSSClient(endPoint, accessKeyId, accessKeySecret);
        } else {
            return ossClient;
        }
    }


    /**
     * 上传指定路径图片
     *
     * @param url
     * @throws Exception
     */
    public void uploadImgToOSS(String url) throws Exception {
        if (StringUtils.isEmpty(url)) {
            throw new Exception("file path error!");
        }
        File fileOnServer = new File(url);
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            String fileName = split[split.length - 1];
            this.uploadFileToOSS(fileInputStream, fileName);
        } catch (FileNotFoundException e) {
            throw new Exception("图片上传失败!");
        }
    }

    public static String uploadImgToOSS(MultipartFile file) throws Exception {
        return uploadImgToOSS(file, null);
    }

    public static String uploadImgToOSS(MultipartFile file, String folderName) throws Exception {
        if (file.getSize() > 1024 * 1024) {
            throw new Exception("上传图片大小不能超过1M！");
        }
        String originalFilename = file.getOriginalFilename();
        String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String newFileName = name + System.currentTimeMillis() + suffix;
        InputStream inputStream = file.getInputStream();
        if (StringUtils.isEmpty(folderName)) {
            uploadFileToOSS(inputStream, newFileName);
        } else {
            uploadFileToOSS(inputStream, folderName, newFileName);
        }

        return newFileName;
    }

    /**
     * 上传文件到oss
     *
     * @param instream
     * @param fileName
     */
    public static void uploadFileToOSS(InputStream instream, String fileName) {
        uploadFileToOSS(instream, null, fileName);
    }

    public static void uploadFileToOSS(InputStream instream, String foldName, String fileName) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            OSSClient ossClient = getOSSClient();
            String bucketName = OSSConfigMap.get("bucketName");
            if (StringUtils.isEmpty(foldName)) {
                ossClient.putObject(bucketName, fileName, instream, objectMetadata);
            } else {
                ossClient.putObject(bucketName, foldName + "/" + fileName, instream, objectMetadata);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getUEditorImgUrl(String fileName) {
        return getImgUrl(fileName, UEditorController.UEDITOR_UPLOAD_FOLDER);
    }

    public static String getAvatorImgUrl(String fileName) {
        return getImgUrl(fileName, "avatar");
    }

    public static String getImgUrl(String fileName, String folderName) {
        if (OSSConfigMap == null) {
            return "";
        }

        String endPoint = OSSConfigMap.get("endpoint");
        String accessKeyId = OSSConfigMap.get("accessKeyId");
        String accessKeySecret = OSSConfigMap.get("accessKeySecret");
        String bucketName = OSSConfigMap.get("bucketName");

        if (StringUtils.isAnyBlank(endPoint, accessKeyId, accessKeySecret, bucketName)) {
            return "";
        }
        return "https://" + bucketName + "." + endPoint.substring(7) + "/" + folderName + "/" + fileName;
    }

    /**
     * 获取Content-Type
     *
     * @param FilenameExtension
     * @return
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        sysConfigMapper = ApplicationContextHolder.getBean(SysConfigMapper.class);
    }
}
