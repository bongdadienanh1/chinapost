<#if (pageBean.getContent()?size >0)>
<div class="table_pagenav ops-right">
    <nav>
        <ul class="pagination">
            <#if ((pageBean.getNumber()-2)>0)>
                <#assign pageNo="${pageBean.getNumber()-2}" />
            <#else>
                <#assign pageNo="1" />
            </#if>
            <#if ((pageBean.getTotalPages()-1)>0)>
                <#assign endNo="${pageBean.getTotalPages()-2}" />
            <#else>
                <#assign endNo="1" />
            </#if>
            <#if (pageBean.getNumber()==1)>
                <li>
                    <a href="javascript:;" class="disabled">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            <#else>
                <li>
                    <a href="javascript:;"  <#if (pageBean.getNumber()>1)>onClick="searchData('${pageBean.previousPageable().pageNumber}');"</#if>>
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <#if (pageBean.getNumber() >= 4)>
                    <li>
                        <a href="javascript:void(0);" class="num" onClick="searchData('1');">1</a>
                    </li>
                </#if>
            </#if>
            <#if ((pageBean.getNumber()-3)>1) >
                <li>
                    <span class="ellipsis">...</span>
                </li>
            </#if>
            <#list pageNo?number .. pageBean.getTotalPages() as item>
                <#if (item_index<=4)>
                    <#if (item=pageBean.getNumber())>
                        <li class="active">
                            <span class="current">${item}</span>
                        </li>
                    <#else>
                        <li>
                            <a href="javascript:;" onClick="searchData('${item}')">
                                ${item}
                            </a>
                        </li>
                        <#--<a class="num" href="javascript:void(0);" onClick="searchData('${item}');">${item}</a>-->
                    </#if>
                </#if>
            </#list>
            <#if pageBean.getNumber()!=pageBean.getTotalPages()>
                <#if ((pageBean.getTotalPages()-pageBean.getNumber())>3) >
                    <#if (pageBean.getTotalPages()>5)>
                        <li>
                            <span class="ellipsis">...</span>
                        </li>
                    </#if>
                </#if>
            </#if>

            <#if (pageBean.getNumber() == pageBean.getTotalPages() || pageBean.getTotalPages() <= 1)>
                <#if (pageBean.getTotalPages() > pageBean.getNumber())>
                    <li>
                        <a class="num_cur disabled" href="javascript:void(0);">>下一页</a>
                    </li>
                </#if>
            <#else>
                <#if ((pageBean.getTotalPages() - pageBean.getNumber())>=3)>
                    <#if (pageBean.getTotalPages()>5)>
                        <li>
                            <a href="javascript:;" onClick="searchData('${pageBean.getTotalPages()}');">${pageBean.getTotalPages()}</a>
                        </li>
                    </#if>
                </#if>
                <li>
                    <a href="javascript:;" aria-label="Next" onClick="searchData('${pageBean.nextPageable().pageNumber}');">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <#--<a class="pg_next" href="javascript:void(0);" <#if (pageBean.getTotalPages()>pageBean.getNumber())>onClick="searchData('${pageBean.nextPageable().pageNumber}');"</#if>>下一页</a>-->
            </#if>
        </ul>
    </nav>
</div>
</#if>