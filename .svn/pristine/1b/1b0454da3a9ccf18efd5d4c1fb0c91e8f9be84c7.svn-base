package com.ylife.security.captcha;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by InThEnd on 2016/4/28.
 * 验证码输出器。
 */
@Component
public class CaptchaOutputter {

    private Producer kaptchaProducer = null;

    private String sessionKeyValue = null;

    private String sessionKeyDateValue = null;


    {        // Switch off disk based caching.
        ImageIO.setUseCache(false);
        Config config = new Config(new Properties());
        this.kaptchaProducer = config.getProducerImpl();
        this.sessionKeyValue = CaptchaConstants.CAPTCHA_TEXT_SESSION_KEY;
        this.sessionKeyDateValue = CaptchaConstants.CAPTCHA_TIME_SESSION_KEY;
    }


    public void outPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = kaptchaProducer.createText();

        // create the image with the text
        BufferedImage bi = kaptchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);

        // fixes issue #69: set the attributes after we write the image in case the image writing fails.

        // store the text in the session
        session.setAttribute(sessionKeyValue, capText);

        // store the date in the session so that it can be compared
        // against to make sure someone hasn't taken too long to enter
        // their kaptcha
        session.setAttribute(sessionKeyDateValue, new Date());
    }

}
