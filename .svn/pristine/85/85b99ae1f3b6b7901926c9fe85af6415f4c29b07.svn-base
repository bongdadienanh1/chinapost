package com.ylife.wealth.model;

/**
 * Created by InThEnd on 2016/4/12.
 * <p/>
 * 申请状态枚举类型。
 */
public enum RequisitionStatus {

    //已申请
    APPLIED {
        @Override
        public String getName() {
            return "待审核";
        }
    },

    //已通过
    PASSED {
        @Override
        public String getName() {
            return "已审核";
        }
    },

    //已拒绝
    DENIED {
        @Override
        public String getName() {
            return "已拒绝";
        }
    },

    //已支付
    PAYED {
        @Override
        public String getName() {
            return "已支付";
        }
    };

    public abstract String getName();
}
