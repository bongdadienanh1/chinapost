package com.ylife.chinapost.third.controller;

import com.ylife.chinapost.third.callable.OrderStatusCallable;
import com.ylife.chinapost.third.service.OpenInterfaceService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * OpenInterfaceController.
 *
 * @Author henry .
 * @Create 2017-07-19 14:29.
 */
@Controller
@RequestMapping(value = "/interface")
public class OpenInterfaceController {

    @Resource
    private OpenInterfaceService openInterfaceService;

    /** 配置的线程池 **/
    @Resource(name = "threadPool")
    private ThreadPoolTaskExecutor taskExecutor;

    @RequestMapping(value = "/deliveryOrderStatus")
    @ResponseBody
    public String deliveryOrderStatus(HttpServletRequest request) throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        Future f = taskExecutor.submit(new OrderStatusCallable(request,openInterfaceService));
        return URLEncoder.encode(f.get().toString(),"UTF-8");
        //return f.get().toString();
    }
}
