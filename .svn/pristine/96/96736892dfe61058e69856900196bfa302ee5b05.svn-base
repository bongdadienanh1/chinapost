package com.ylife.mail.service.impl;

import com.ylife.mail.bean.EmailServer;
import com.ylife.mail.mapper.EmailServerMapper;
import com.ylife.mail.service.EmailServerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/15.
 */
@Service
public class EmailServerServiceImpl implements EmailServerService {


    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(EmailServerServiceImpl.class);

    private static EmailServer emailServerSite;
    // spring注入
    @Resource
    private EmailServerMapper essm;

    /**
     * 用户反馈发送邮件
     */
    @Override
    public int sendToStore(String[] toEmail, String content,ByteArrayInputStream is) throws IOException {
        Session session = getSession();
        try {
            //创建邮件
            Message msg = new MimeMessage(session);
            //设置邮件的基本信息
            // 发件人地址
            msg.setFrom(new InternetAddress(emailServerSite.getSmtpaccount()));

            for (int i = 0; i < toEmail.length; i++) {
                InternetAddress toAddr = new InternetAddress(toEmail[i]);
                msg.addRecipient(javax.mail.Message.RecipientType.TO,toAddr);
            }

            msg.setSubject(content);
            msg.setSentDate(new Date());
            //正文
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(content,"text/html;charset=UTF-8");
            //添加附件必须设置邮件类型
            MimeBodyPart attach = new MimeBodyPart();
            DataHandler dh = new DataHandler(new ByteArrayDataSource(is,"application/vnd.ms-excel"));
            attach.setDataHandler(dh);
            attach.setFileName(MimeUtility.encodeText(content+".xls"));

            //创建容器描述数据关系
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(text);
            mp.addBodyPart(attach);
            mp.setSubType("mixed");
            msg.setContent(mp);
            msg.saveChanges();

            // Send the message
            Transport.send(msg);
            return 1;
        } catch (Exception mex) {
            LOGGER.error("用户反馈发送邮件失败，请查看原因：", mex);
            return 0;
        }
    }

    /**
     * 获取session
     * */
    public Session getSession() {
        // 读取数据库数据
        emailServerSite = essm.selectEmailServer();
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        // SMTP服务器
        props.setProperty("mail.smtp.host", emailServerSite.getSmtpserver());
        // SMTP端口号
        props.setProperty("mail.smtp.port", emailServerSite.getSmtpport());
        props.setProperty("mail.smtp.auth", "true");
        // 新添加的
        props.put("mail.smtp.socketFactory.fallback", "true");
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // SMTP账号、密码
                return new PasswordAuthentication(emailServerSite.getSmtpaccount(), emailServerSite.getSmtppass());
            }

        });
    }
}
