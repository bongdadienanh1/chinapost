package com.ylife.security.captcha;

import com.ylife.security.event.filter.EventUsernamePasswordAuthenticaionFilter;
import com.ylife.utils.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by InThEnd on 2016/4/27.
 * 使用了验证码的验证过滤器。
 */
public class CaptchaAuthenticaionFilter extends EventUsernamePasswordAuthenticaionFilter {

    private static final String DEFAULT_CAPTCHA_PARAMETER_NAME = "k_captcha";

    /**
     * 验证码参数名称。
     */
    private String captchaKey = DEFAULT_CAPTCHA_PARAMETER_NAME;

    /**
     * 是否开启验证码。
     */
    private boolean useCaptcha = true;

    /**
     * 需要输入验证码的错误次数。
     */
    private int need_captcha_times = 3;

    private static final String ERROR_TIMES_SESSION_KEY = "k_captcha_error_times";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (useCaptcha) {
            int errorTimes = obtainErrorTimes(request);
            if (errorTimes >= need_captcha_times - 1) {
                checkCaptcha(request);
            }
        }
        return super.attemptAuthentication(request, response);

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        cleanAllAttributes(request);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        request.getSession().setAttribute(ERROR_TIMES_SESSION_KEY, obtainErrorTimes(request) + 1);
        cleanCaptchaAttributes(request);
        super.unsuccessfulAuthentication(request, response, failed);
    }

    private int obtainErrorTimes(HttpServletRequest request) {
        Object object = request.getSession().getAttribute(ERROR_TIMES_SESSION_KEY);
        return object == null ? 0 : (int) object;
    }

    private void checkCaptcha(HttpServletRequest request) {
        String captchaText = (String) request.getSession().getAttribute(CaptchaConstants.CAPTCHA_TEXT_SESSION_KEY);
        String captchaValue = request.getParameter(captchaKey);
        if (StringUtil.isBlank(captchaText)) {
            throw new CaptchaException("需要输入验证码。");
        }
        if (StringUtil.isBlank(captchaValue)) {
            throw new CaptchaException("验证码不能为空。");
        }
        if (!captchaText.equals(captchaValue)) {
            throw new CaptchaException("验证码错误。");
        }
    }

    private void cleanAllAttributes(HttpServletRequest request) {
        request.getSession().removeAttribute(CaptchaConstants.CAPTCHA_TEXT_SESSION_KEY);
        request.getSession().removeAttribute(CaptchaConstants.CAPTCHA_TIME_SESSION_KEY);
        request.getSession().removeAttribute(ERROR_TIMES_SESSION_KEY);
    }

    private void cleanCaptchaAttributes(HttpServletRequest request) {
        request.getSession().removeAttribute(CaptchaConstants.CAPTCHA_TEXT_SESSION_KEY);
        request.getSession().removeAttribute(CaptchaConstants.CAPTCHA_TIME_SESSION_KEY);
    }

    /**
     * 设置验证码的参数名，默认为"k_captcha"。
     */
    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    /**
     * 设置是否开启验证码，默认开启。
     */
    public void setUseCaptcha(boolean useCaptcha) {
        this.useCaptcha = useCaptcha;
    }

    /**
     * 设置登录失败几次需要使用验证码。默认为3次。
     */
    public void setNeed_captcha_times(int need_captcha_times) {
        this.need_captcha_times = need_captcha_times;
    }
}
