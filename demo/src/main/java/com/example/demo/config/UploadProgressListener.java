package com.example.demo.config;

import com.example.demo.entity.ProgressEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/29
 */
@Component
@Slf4j
public class UploadProgressListener implements ProgressListener {
    public static final String STATUS = "status";
    private HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
        ProgressEntity status = new ProgressEntity();
        session.setAttribute(STATUS, status);
    }

    /* pBytesRead  到目前为止读取文件的比特数
     * pContentLength 文件总大小
     * pItems 目前正在读取第几个文件
     */
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        ProgressEntity status = (ProgressEntity) session.getAttribute(STATUS);
        status.setPBytesRead(pBytesRead);
        status.setPContentLength(pContentLength);
        status.setPItems(pItems);
        log.info("上传进度: {} ", status.toString());
    }

}
