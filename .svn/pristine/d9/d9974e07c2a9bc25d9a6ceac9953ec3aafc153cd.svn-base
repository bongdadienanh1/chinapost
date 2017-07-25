package com.ylife.chinapost.third.callable;

import com.ylife.chinapost.third.service.OpenInterfaceService;
import com.ylife.data.json.message.JsonResponseBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * OrderStatusCallable.
 *
 * @Author henry .
 * @Create 2017-07-19 15:24.
 */
public class OrderStatusCallable implements Callable<Object> {

    private HttpServletRequest request;

    private OpenInterfaceService openInterfaceService;

    public OrderStatusCallable(HttpServletRequest request, OpenInterfaceService openInterfaceService) {
        this.request = request;
        this.openInterfaceService = openInterfaceService;
    }

    public Object call() throws Exception {
        return new JsonResponseBean(openInterfaceService.deliveryOrderStatus(getRequestValues())).toJson();
    }

    /**
     * 从request中获取所有的参数组成Map
     * @return
     */
    private Map<String,Object> getRequestValues(){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        Iterator<Map.Entry<Object, Object>> it = request.getParameterMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            paramMap.put(entry.getKey().toString(),((String[])entry.getValue())[0]);
        }
        return paramMap;
    }
}
