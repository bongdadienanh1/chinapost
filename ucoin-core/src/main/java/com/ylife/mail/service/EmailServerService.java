package com.ylife.mail.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 邮件发送工具类
 * 
 * @author jiping
 * @since 2014年7月18日 上午10:43:40
 * @version 0.0.1
 */
public interface EmailServerService {

    /**
     * 发送邮件
     * @param toEmail 接收人
     * @param content 内容
     * @param is 附件字节流
     * @return
     * @throws IOException
     */
    int sendToStore(String[] toEmail, String content,ByteArrayInputStream is) throws IOException;
}
