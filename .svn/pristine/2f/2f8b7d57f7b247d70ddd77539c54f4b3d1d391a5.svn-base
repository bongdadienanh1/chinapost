package com.ylife.chinapost.controller;

import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.OperationLogService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Pageable;
import com.ylife.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/9.
 */
@Controller
@RequestMapping(value="/web/logManager", produces = "application/json;charset=utf-8")
public class OperationLogController {
    @Resource
    private OperationLogService operationLogService;

    /**
     * 根据登录帐号ID获取发布信息
     */
    @RequestMapping("/getOperationLog")
    @ResponseBody
    public String getOperationLog(@RequestParam("enterpriseId") Long enterpriseId,
                                  @RequestParam(value="otStartTime",required = false) String otStartTime,
                                  @RequestParam(value="otEndTime",required = false) String otEndTime,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date otStartT = null;
        Date otEndT = null;
        otStartTime= Constants.nullOrNotBlank(otStartTime);
        otEndTime=Constants.nullOrNotBlank(otEndTime);
        if (otStartTime != null) {
            otStartT = DateUtil.fromString(otStartTime, "yyyy-MM-dd HH:mm");
        }
        if (otEndTime != null) {
            otEndT = DateUtil.fromString(otEndTime, "yyyy-MM-dd HH:mm");
        }

        return new JsonResponseBean(operationLogService.getOperationLogList(enterpriseId,otStartT,otEndT,new Pageable(page, size))).toJson();
    }
}
