package com.ylife.form.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.model.InventoryChangeHistoryForm;

import java.util.Date;

/**
 * Created by XiaoBiaoGe on 2016/11/23.
 */
public interface InventoryChangeFormService {



    Page<InventoryChangeHistoryForm> getHistoryChangeForm(Long maxCatalog,
                                                    Long minCatalog,
                                                    Date start,
                                                    Date end,
                                                    String goodsInfoName,
                                                    String goodsInfoItemNo,
                                                    Pageable pageable);
}
