/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.CustomerAddressMapper;
import com.ylife.customer.mapper.CustomerInfoMapper;
import com.ylife.customer.mapper.CustomerMapper;
import com.ylife.customer.model.*;
import com.ylife.customer.service.CustomerService;
import com.ylife.data.page.PageBean;
import com.ylife.goods.mapper.GoodsProductMapper;
import com.ylife.goods.model.GoodsProductVo;
import com.ylife.order.model.BackOrder;
import com.ylife.order.model.CreditOrder;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 会员服务处理接口实现类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月14日 下午2:57:09
 * @version 1.0
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMERID = "customerId";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";
    private static final String UTYPE = "uType";

    // spring注解
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private CustomerAddressMapper customerAddressMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;

    /**
     * 查询单个会员信息 详细
     *
     * @param customerId
     *            用户编号
     * @see java.lang.Long {@link java.lang.Long}
     * @return @see com.ysh.customer.bean.Customer
     */
    @Override
    public CustomerAllInfo selectByPrimaryKey(Long customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    /**
     * <strong> 添加会员</strong> <br>
     * 注意事项：<br>
     * 1.可用于添加会员所有信息 所有信息参考 {@link CustomerAllInfo}<br>
     * 2.添加普通会员时,不用填充第三方字段属性<br>
     * 3.添加第三方会员或者店铺管理员时,需要填充会员信息中的第三方信息<br>
     *
     * @param allinfo
     *            {@link CustomerAllInfo} 查询、添加、修改等操作时用到的辅助类 包含会员的所有信息
     * @return 返回结果：int 0 添加失败 1 添加成功
     */
    @Override
    @Transactional
    public int addCustomer(CustomerAllInfo allinfo) {
        if (allinfo.getCustomerNickname() == null) {
            allinfo.setCustomerNickname(allinfo.getCustomerUsername());
        }
        if (allinfo.getPointLevelId() == null) {
//            allinfo.setPointLevelId(customerPointLevelMapper.selectDefaultCustomerLevel().getPointLelvelId());
        }
        // 通过邮箱注册用户
        if (allinfo.getCustomerUsername().indexOf("@") != -1) {
            allinfo.setInfoEmail(allinfo.getCustomerUsername());
            allinfo.setCustomerNickname(allinfo.getCustomerUsername().substring(0, allinfo.getCustomerUsername().indexOf("@")));
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$").matcher(allinfo.getCustomerUsername()).find()) {
            // 手机注册用户
            allinfo.setInfoMobile(allinfo.getCustomerUsername());
            allinfo.setCustomerNickname(allinfo.getCustomerUsername());
        }
        // 设置登录key
        UUID uuid = UUID.randomUUID();
        allinfo.setLoginKey(uuid.toString());
        return customerMapper.insertSelective(allinfo);
    }

    /**
     * 修改会员信息
     *
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    @Override
    @Transactional
    public int updateCustomer(CustomerAllInfo allinfo) {
        // 保存会员表
        int result = customerMapper.updateByPrimaryKeySelective(allinfo);
        if (result == 1) {
            // 保存会员拓展表
            result = customerInfoMapper.updateByPrimaryKeySelective(allinfo);
        }
        return result;
    }

    /**
     * 检查会员名是否存在
     *
     * @param customerName
     *            等级名称
     * @return true表示存在 false不存在
     * @see java.lang.Long {@link java.lang.Long}
     */
    @Override
    public boolean checkUsernameExitOrNot(String customerName) {
        if(selectCustomerByUserName(customerName)!=null){
            return true;
        }
        return false;
    }

    /**
     * 根据会员编号查找对应的默认收货地址
     *
     * @param customerId
     *            会员编号
     * @return 会员收货地址
     */
    @Override
    public CustomerAddress queryDefaultAddr(Long customerId) {
        return customerAddressMapper.selectDefaultAddr(customerId);
    }


    /**
     * 根据会员编号修改会员
     *
     * @param
     *
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerAllInfo record) {
        return customerMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public Customer selectCustomerByUserName(String userName) {
        return customerMapper.selectCustomerByUserName(userName);
    }

    /**
     * 验证邮箱存在性
     *
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    @Override
    public Long checkEmailExist(String email) {
        return customerMapper.checkEmailExist(email);
    }

    /**
     * 验证手机存在性
     *
     * @param mobile
     *            目标手机 {@link String}
     * @return 0不存在 1存在
     */
    @Override
    public Long checkMobileExist(String mobile) {
        return customerMapper.checkMobileExist(mobile);
    }

    /**
     * 查询所有订单
     *
     * @param paramMap
     *            查询订单条件
     * @param pb
     * @return
     */
    @Override
    public PageBean queryAllMyOrders(Map<String, Object> paramMap, PageBean pb) {
        Long count = customerMapper.searchTotalCount(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        pb.setPageSize(4);
        paramMap.put(STARTROWNUM, pb.getStartRowNum());
        paramMap.put(ENDROWNUM, pb.getEndRowNum());
        pb.setList(customerMapper.queryAllMyOrders(paramMap));
        return pb;
    }

    /**
     * 查询当前会员的退单信息
     *
     * @param paramMap
     *            查询订单条件
     * @param pb
     * @return
     */
    @Override
    public PageBean queryAllBackMyOrders(Map<String, Object> paramMap, PageBean pb) {
        // 退单的数据条数
        Long count = customerMapper.searchTotalCountBack(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        paramMap.put(STARTROWNUM, pb.getStartRowNum());
        paramMap.put(ENDROWNUM, pb.getEndRowNum());
        // 查询该会员下面的
        List<Object> backOrders = customerMapper.queryAllMyBackOrders(paramMap);
        if (null != backOrders && !backOrders.isEmpty()) {
//            for (int i = 0; i < backOrders.size(); i++) {
//                CreditOrder bo = (CreditOrder) backOrders.get(i);
//                if (!StringUtil.isBlank(bo.getBackGidGsum())) {
//                    // 获取退单对象 下面的退单的 商品Id
//                    String[] strs = bo.getBackGidGsum().split("-");
//                    // 处理数据
//                    if (strs.length > 0) {
//                        // 遍历退单对象下面的商品Id
//                        for (int j = 0; j < strs.length; j++) {
//                            String strss = strs[j];
//                            // 获取第J个商品的Id
//                            Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
//                            // 根据ID获取单个商品的详细信息
//                            GoodsProductVo orderProductVo = goodsProductMapper.queryDetailByProductId(goodsId);
//                            // 循环把查询获取到的商品信息放入到退单对象的商品集合中
//                            b
//                            bo.getOrderGoodslistVo().add(orderProductVo);
//                        }
//                    }
//                }
//            }
            pb.setList(backOrders);
        }
        return pb;
    }

    /**
     * 查询退单总数
     * @param map
     * @return
     */
    @Override
    public Long searchTotalCountBack(Map<String, Object> map) {
        return customerMapper.searchTotalCountBack(map);
    }

    /**
     * 查询退单总数
     * @param map
     * @return
     */
    @Override
    public Long searchTotalCount(Map<String, Object> map) {
        return customerMapper.searchTotalCount(map);
    }

    /**
     * 根据用户ID、订单Id查询订单详情
     * @param orderId
     * @param customerId
     * @return
     */
    @Override
    public OrderInfoBean queryOrderByParamMap(Long orderId, Long customerId) {
        //定义map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //用户id
        paramMap.put("customerId", customerId);
        //订单id
        paramMap.put("orderId", orderId);
        //执行查询订单操作
        return customerMapper.queryOrderByParamMap(paramMap);
    }
}
