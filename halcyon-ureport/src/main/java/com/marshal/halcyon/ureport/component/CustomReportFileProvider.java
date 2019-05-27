package com.marshal.halcyon.ureport.component;

import com.bstek.ureport.exception.ReportException;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.marshal.halcyon.ureport.mapper.SysReportMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.*;

@Component
public class CustomReportFileProvider implements ReportProvider, ApplicationContextAware {
    private String prefix = "file:";

    @Value("${ureport.fileStorePath}")
    private String fileStoreDir;

    private boolean disabled;

    @Autowired
    private SysReportMapper sysReportMapper;

    public CustomReportFileProvider() {
    }

    public InputStream loadReport(String file) {
        if (file.startsWith(this.prefix)) {
            file = file.substring(this.prefix.length(), file.length());
        }

        String fullPath = this.fileStoreDir + "/" + file;

        try {
            return new FileInputStream(fullPath);
        } catch (FileNotFoundException var4) {
            throw new ReportException(var4);
        }
    }

    public void deleteReport(String file) {
        if (file.startsWith(this.prefix)) {
            file = file.substring(this.prefix.length(), file.length());
        }

        String fullPath = this.fileStoreDir + "/" + file;
        File f = new File(fullPath);
        if (f.exists()) {
            f.delete();
        }

    }

    public List<ReportFile> getReportFiles() {
        File file = new File(this.fileStoreDir);
        List<ReportFile> list = new ArrayList();
        File[] var3 = file.listFiles();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            File f = var3[var5];
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(f.lastModified());
            list.add(new ReportFile(f.getName(), calendar.getTime()));
        }

        Collections.sort(list, new Comparator<ReportFile>() {
            public int compare(ReportFile f1, ReportFile f2) {
                return f2.getUpdateDate().compareTo(f1.getUpdateDate());
            }
        });
        return list;
    }

    public String getName() {
        return "web服务器";
    }

    public void saveReport(String file, String content) {
        if (file.startsWith(this.prefix)) {
            file = file.substring(this.prefix.length(), file.length());
        }

        String fullPath = this.fileStoreDir + "/" + file;
        FileOutputStream outStream = null;

        try {
            outStream = new FileOutputStream(new File(fullPath));
            IOUtils.write(content, outStream, "utf-8");
        } catch (Exception var13) {
            throw new ReportException(var13);
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

    }

    public boolean disabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setFileStoreDir(String fileStoreDir) {
        this.fileStoreDir = fileStoreDir;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        File file = new File(this.fileStoreDir);
        if (!file.exists()) {
            if (applicationContext instanceof WebApplicationContext) {
                WebApplicationContext context = (WebApplicationContext) applicationContext;
                ServletContext servletContext = context.getServletContext();
                String basePath = servletContext.getRealPath("/");
                this.fileStoreDir = basePath + this.fileStoreDir;
                file = new File(this.fileStoreDir);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }

        }
    }

    public String getPrefix() {
        return this.prefix;
    }
}
