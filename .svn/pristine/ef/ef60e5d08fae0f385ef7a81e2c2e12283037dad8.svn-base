package com.ylife.chinapost.service.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/12.
 * <p/>
 * 子节点财富统计结果封装。
 */
public class SonsWealthResult {

    private BigDecimal totalWealth;

    private List<SonWealth> wealthList;

    public BigDecimal getTotalWealth() {
        return totalWealth;
    }

    public void setTotalWealth(BigDecimal totalWealth) {
        this.totalWealth = totalWealth;
    }

    public List<SonWealth> getWealthList() {
        return wealthList;
    }

    public void setWealthList(List<SonWealth> wealthList) {
        this.wealthList = wealthList;
    }

    private static class SonWealth {

        private Long id;

        private String name;

        private BigDecimal wealth;

        public SonWealth(Long id, String name, BigDecimal wealth) {
            this.id = id;
            this.name = name;
            this.wealth = wealth;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getWealth() {
            return wealth;
        }

        public void setWealth(BigDecimal wealth) {
            this.wealth = wealth;
        }
    }
}
