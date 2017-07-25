package com.ylife.chinapost.controller.api;

import com.ylife.chinapost.service.UcoinQueryService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/4/16.
 * U宝查询API接口。
 */
@Controller
@RequestMapping(value = "/web/api/ucoinquery", produces = "application/json;charset=utf-8")
public class UcoinQueryAPIController {

    @Resource
    private UcoinQueryService ucoinQueryService;

    /**
     * 根据用户ID获取用户的历史记录分页
     *
     * @param customerId 用户ID
     * @param pageNumber 页码
     * @param pageSize   页大小
     */
    @RequestMapping("/ucoinhistories")
    @ResponseBody
    public String getHistories(@RequestParam("customerId") Long customerId,
                               @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "1") int pageSize) {
        Page<CustomerUcoinHistory> page = ucoinQueryService.getCustomerUcoinHistories(customerId, new Pageable(pageNumber, pageSize));
        return new JsonResponseBean(page).toJson();
    }
}
