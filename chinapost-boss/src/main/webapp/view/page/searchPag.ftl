<#if (pageBean.getContent()?size >0)>
<div class="table_pagenav pull-right">
    <div class="meneame">
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
        <span class="disabled">上一页</span>
    <#else>
        <a <#if (pageBean.getNumber()>1)>onClick="searchData('${pageBean.previousPageable().pageNumber}');"</#if> href="javascript:;">上一页</a>
        <#if (pageBean.getNumber() >= 4)>
            <a class="num" href="javascript:void(0)" onClick="searchData('1');">1</a>
        </#if>
    </#if>
    <#if ((pageBean.getNumber()-3)>1) >
        <span class="ellipsis">...</span>
    </#if>
    <#list pageNo?number .. pageBean.getTotalPages() as item>
        <#if (item_index<=4)>
            <#if (item=pageBean.getNumber())>
                <span class="current">${item}</span>
            <#else>
                <a class="num" href="javascript:void(0);" onClick="searchData('${item}');">${item}</a>
            </#if>
        </#if>
    </#list>
    <#if pageBean.getNumber()!=pageBean.getTotalPages()>
        <#if ((pageBean.getTotalPages()-pageBean.getNumber())>3) >
            <#if (pageBean.getTotalPages()>5)>
                <span class="ellipsis">...</span>
            </#if>
        </#if>
    </#if>

    <#if (pageBean.getNumber() == pageBean.getTotalPages() || pageBean.getTotalPages() <= 1)>
        <#if (pageBean.getTotalPages() > pageBean.getNumber())>
            <a class="num_cur" href="javascript:void(0);">${pageBean.getTotalPages()}</a>
        </#if>
        <a class="no_pages pg_next" href="javascript:void(0);">下一页</a>
    <#else>
        <#if ((pageBean.getTotalPages() - pageBean.getNumber())>=3)>
            <#if (pageBean.getTotalPages()>5)>
                <a class="num" href="javascript:void(0);" onClick="searchData('${pageBean.getTotalPages()}');" >${pageBean.getTotalPages()}</a>
            </#if>
        </#if>
        <a class="pg_next" href="javascript:void(0);" <#if (pageBean.getTotalPages()>pageBean.getNumber())>onClick="searchData('${pageBean.nextPageable().pageNumber}');"</#if>>下一页</a>
    </#if>
</div>
</div>
<#else>
<center>没有符合条件的记录</center>
</#if>