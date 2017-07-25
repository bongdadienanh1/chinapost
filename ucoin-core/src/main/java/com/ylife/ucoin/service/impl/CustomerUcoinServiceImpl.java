package com.ylife.ucoin.service.impl;

import com.ylife.exception.UserOperationException;
import com.ylife.ucoin.mapper.CustomerUcoinMapper;
import com.ylife.ucoin.model.CustomerUcoin;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by InThEnd on 2016/4/8.
 * CustomerUcoinServiceImpl
 */
@Service
public class CustomerUcoinServiceImpl implements CustomerUcoinService {

    @Resource
    private CustomerUcoinMapper customerUcoinMapper;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;

    @Override
    public List<CustomerUcoin> getUcoins(long customerId) {
        return customerUcoinMapper.selectByCustomerId(customerId);
    }

    @Override
    public BigDecimal getUcoinBalanceByEnterpriseIdAndEnterpriseId(Long customerId, Long enterpriseId) {
        return customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(customerId,enterpriseId);
    }

    @Override
    public BigDecimal getUcoinBalance(long customerId) {
        return customerUcoinMapper.sumResePriceByCustomerId(customerId);
    }

    @Override
    public void grandUcoin(long customerId, long enterpriseId, BigDecimal count, Date startTime, Date endTime, String sendType, String remark) {
        Assert.notNull(count);
        Date createDate = new Date();
        if (startTime == null) {
            startTime = createDate;
        }
        CustomerUcoin ucoin = new CustomerUcoin();
        ucoin.setCustomerId(customerId);
        ucoin.setEnterpriseId(enterpriseId);
        ucoin.setCreateTime(createDate);
        ucoin.setStartTime(startTime);
        ucoin.setEndTime(endTime);
        ucoin.setResePrice(count);
        customerUcoinMapper.insertSelective(ucoin);
    }

    @Override
    public void redeemUcoinCode(long customerId, String code) {

    }

    @Override
    @Transactional
    public void deductUcoin(long customerId, long enterpriseId, BigDecimal amount, String remark) {
        Assert.notNull(amount);
        cutUcoin(customerId, amount, "UCOIN_DEDUCT");
        customerUcoinHistoryService.addHistory(customerId, enterpriseId, null, HistoryType.UCOIN_DEDUCT, null, amount, null, null, null, null, remark, null, null,null);
    }

    /**
     * 使用U宝付款。
     *
     * @param customerId 用户ID
     * @param amount     付款额
     * @param remark     备注
     * @param orderId    消费购物时关联的订单ID
     */
    @Override
    public List<CustomerUcoin> ucoinPay(long customerId, BigDecimal amount, String remark, long orderId) {
        Assert.notNull(amount);
        //customerUcoinHistoryService.addHistory(customerId, null, null, HistoryType.UCOIN_CONSUME,null, amount, null, null, null, null, remark, orderId, null);
        return cutUcoin(customerId, amount,"UCOIN_CONSUME");
    }

    @Override
    public List<CustomerUcoin> ucoinPay(long customerId, BigDecimal amount, String remark, long orderId, long enterpriseId,BigDecimal totalSettlePrice) {
        List<CustomerUcoin> ucoinHistoryList = new ArrayList<>();
        Assert.notNull(amount);
        if (amount.compareTo(BigDecimal.ZERO) != 0) {
            ucoinHistoryList = changeUcoin(customerId, enterpriseId, amount.negate(), null, null, false);
        }
        BigDecimal balancePrice=customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(customerId,enterpriseId);

        customerUcoinHistoryService.addHistory(customerId, enterpriseId, null, HistoryType.UCOIN_CONSUME, null,amount, null, null,balancePrice,null, remark, orderId, null,totalSettlePrice);

        return ucoinHistoryList;
    }

    /**
     * 判断两个数是否为相同符号
     *
     * @param a
     * @param b
     * @return 两个为正、两个为负返回true,一正一负返回false
     */
    private boolean isEqualSymbol(BigDecimal a, BigDecimal b) {
        if ((a.compareTo(BigDecimal.ZERO) > 0 && b.compareTo(BigDecimal.ZERO) < 0) || (a.compareTo(BigDecimal.ZERO) < 0 && b.compareTo(BigDecimal.ZERO) > 0)) {
            return false;
        }
        return true;
    }


    /**
     * U宝变化
     *
     * @param customerId    用户ID
     * @param enterpriseId  企业ID
     * @param ucoinPrice    U宝金额(扣减为负，发放为正)
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param allowNegative 是否允许负数
     */
    @Override
    @Transactional
    public List<CustomerUcoin> changeUcoin(long customerId, long enterpriseId, BigDecimal ucoinPrice, Date startTime, Date endTime, boolean allowNegative) {
        List<CustomerUcoin> ucoinHistoryList = new ArrayList<>();


        Assert.notNull(ucoinPrice);
        /*if (ucoinPrice.compareTo(BigDecimal.ZERO) == 0) {
            throw new UserOperationException("参数有误。");
        }*/
        BigDecimal remain = ucoinPrice;
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", customerId);
        map.put("enterpriseId", enterpriseId);
        BigDecimal price = BigDecimal.ZERO;//抵扣累计
        int i = 0;
        while (true) {
            CustomerUcoin ucoin = customerUcoinMapper.selectEarliestUcoinByCustomerIdAndEnterpriseIdForUpdate(map);
            CustomerUcoin ucoinHistory = new CustomerUcoin();
            if (ucoin == null) {
                //不存在，即为发放
                if (!allowNegative && remain.compareTo(BigDecimal.ZERO) < 0) {
                    throw new UserOperationException("会员邮豆余额不足。");
                } else {
                    grandUcoin(customerId, enterpriseId, remain, startTime, endTime, "", "");
                    break;
                }
            }
            BigDecimal resePrice = ucoin.getResePrice();
            if (i == 0) {//符号相同，不抵扣
                if (isEqualSymbol(remain, resePrice)) {
                    if (!allowNegative && remain.compareTo(BigDecimal.ZERO) < 0) {
                        throw new UserOperationException("会员邮豆余额不足。");
                    } else {
                        grandUcoin(customerId, enterpriseId, remain, startTime, endTime, "", "");
                        break;
                    }
                }
            }

            //符号不同，进行抵扣
            if (remain.abs().doubleValue() >= resePrice.abs().doubleValue()) {
                ucoinHistory.setResePrice(resePrice);
                ucoinHistory.setCustomerId(customerId);
                ucoinHistory.setEnterpriseId(enterpriseId);
                ucoinHistory.setStartTime(ucoin.getStartTime());
                ucoinHistoryList.add(ucoinHistory);
                customerUcoinMapper.deleteByPrimaryKey(ucoin.getId());
            } else {
                ucoinHistory.setResePrice(remain.abs());
                ucoinHistory.setCustomerId(customerId);
                ucoinHistory.setEnterpriseId(enterpriseId);
                ucoinHistory.setStartTime(ucoin.getStartTime());
                ucoinHistoryList.add(ucoinHistory);
                customerUcoinMapper.updatePriceByPrimaryKey(ucoin.getId(), resePrice.add(remain));
            }

            //还需要抵扣多少
            remain = resePrice.add(remain);
            //抵扣了多少
            price = price.add(resePrice.abs());
            //抵扣大于等于邮豆金额，跳出
            if (price.compareTo(ucoinPrice.abs()) >= 0) {
                if (remain.compareTo(BigDecimal.ZERO) < 0 && !allowNegative) {
                    throw new UserOperationException("会员邮豆余额不足，不能为负。");
                } else {
                    break;
                }
            }
            i++;
        }
        return ucoinHistoryList;
    }

    /**
     * 减少U宝。
     */
    @Transactional
    private List<CustomerUcoin> cutUcoin(long customerId, BigDecimal amount,String type) {
        Assert.notNull(amount);
        List<CustomerUcoin> ucoinHistoryList = new ArrayList<>();
        BigDecimal remain = amount;
        while (true) {
            CustomerUcoin ucoin = customerUcoinMapper.selectEarliestUcoinByCustomerIdForUpdate(customerId);//B端扣减
            if(type.equals("UCOIN_CONSUME")){//C端下单
                ucoin = customerUcoinMapper.selectEarliestMoreZeroUcoinByCustomerIdForUpdate(customerId);
            }
            CustomerUcoin ucoinHistory = new CustomerUcoin();
            if (ucoin == null) {
                throw new UserOperationException("邮豆余额不足。");
            }
            BigDecimal resePrice = ucoin.getResePrice();
            int result = resePrice.compareTo(remain);
            if (result > 0) {
                ucoinHistory.setResePrice(remain);
                ucoinHistory.setCustomerId(customerId);
                ucoinHistory.setEnterpriseId(ucoin.getEnterpriseId());
                ucoinHistory.setStartTime(ucoin.getStartTime());
                ucoinHistoryList.add(ucoinHistory);
                customerUcoinMapper.updatePriceByPrimaryKey(ucoin.getId(), resePrice.subtract(remain));
                break;
            } else if (result == 0) {
                ucoinHistory.setResePrice(remain);
                ucoinHistory.setCustomerId(customerId);
                ucoinHistory.setEnterpriseId(ucoin.getEnterpriseId());
                ucoinHistory.setStartTime(ucoin.getStartTime());
                ucoinHistoryList.add(ucoinHistory);
                customerUcoinMapper.deleteByPrimaryKey(ucoin.getId());
                break;
            } else {
                ucoinHistory.setResePrice(resePrice);
                ucoinHistory.setCustomerId(customerId);
                ucoinHistory.setEnterpriseId(ucoin.getEnterpriseId());
                ucoinHistory.setStartTime(ucoin.getStartTime());
                ucoinHistoryList.add(ucoinHistory);
                remain = remain.subtract(resePrice);
                customerUcoinMapper.deleteByPrimaryKey(ucoin.getId());
            }
        }
        return ucoinHistoryList;
    }

    @Override
    public CustomerUcoin getEarliestUcoin(long customerId) {
        return null;
    }

    @Override
    @Transactional
    public void deductUcoinNew(long customerId, long enterpriseId,Long batchId, BigDecimal amount, Date businessTime,String remark) {
        Assert.notNull(amount);
        cutUcoinNew(customerId, enterpriseId, amount);
        BigDecimal balancePrice=customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(customerId, enterpriseId);
        customerUcoinHistoryService.addHistory(customerId, enterpriseId, batchId, HistoryType.UCOIN_DEDUCT, businessTime,amount.negate(), null, null, balancePrice, null, remark, null, null,null);
    }

    /**
     * 减少u宝新
     */
    @Transactional
    private void cutUcoinNew(long customerId, long enterpriseId, BigDecimal amount) {
        Assert.notNull(amount);
        List<CustomerUcoin> ucoinList = customerUcoinMapper.selectEarliestUcoinByCustomerIdForUpdateList(customerId, enterpriseId);
        CustomerUcoin ucoin = new CustomerUcoin();
        if (ucoinList.isEmpty()) {
            ucoin.setCustomerId(customerId);
            ucoin.setEnterpriseId(enterpriseId);
            ucoin.setResePrice(amount.negate());
            ucoin.setStartTime(new Date());
            customerUcoinMapper.insertSelective(ucoin);
        }
        for (int i = 0; i < ucoinList.size(); i++) {
            BigDecimal balance = ucoinList.get(i).getResePrice();//获得第一个余额
            if (balance.compareTo(amount) < 0) {
                if (i != (ucoinList.size() - 1)) {
                    amount = amount.subtract(balance);
                    customerUcoinMapper.deleteByPrimaryKey(ucoinList.get(i).getId());
                } else if (i == (ucoinList.size() - 1)) {
                    ucoin.setCustomerId(customerId);
                    ucoin.setEnterpriseId(enterpriseId);
                    amount = amount.subtract(balance);
                    ucoin.setResePrice(amount.negate());
                    ucoin.setStartTime(ucoinList.get(i).getStartTime());
                    customerUcoinMapper.deleteByPrimaryKey(ucoinList.get(i).getId());
                    customerUcoinMapper.insertSelective(ucoin);
                    break;
                }

            } else if (balance.compareTo(amount) == 0) {
                customerUcoinMapper.deleteByPrimaryKey(ucoinList.get(i).getId());
                break;
            } else {
                BigDecimal statment = balance.subtract(amount);
                customerUcoinMapper.updatePriceByPrimaryKey(ucoinList.get(i).getId(), statment);
                break;
            }
        }
    }

}
