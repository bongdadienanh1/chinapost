<#assign bath = request.contextPath>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
  <link href="${bath}/static/bootstrap/css/bootstrap.min.css?version=${VERSION}" rel="stylesheet">
    <link rel="stylesheet" href="${bath}/static/css/font-awesome.min.css?version=${VERSION}">
    <link href="${bath}/static/iconfont/iconfont.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/zTreeStyle/zTreeStyle.css?version=${VERSION}" rel="stylesheet">
    <link href="${bath}/static/css/style.css?version=${VERSION}" rel="stylesheet">

	<style type="text/css">
	body{background:none}
	div.meneame a{
	border: #ddd 1px solid;
		padding: 10px 10px 10px 15px; 
		background-position: 50% bottom;
		color: #555;
		margin-right: 3px;
		text-decoration: none;
	}
	</style>

</head>
<body>
	
            <div class="common_data p20">
               <div class="filter_area">
              <form role="form" class="form-inline" action="queryMobProductForGoods.htm" method="post" id="searchForm">

                  <input type="hidden" id="page" name="page"/>
                  <input type="hidden" id="enterpriseId" name="enterpriseId" value="1"/>
                  <input type="hidden" id="size" name="size" value="10"/>
              <#--<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">-->
                <#--<div class="form-group">-->
                  <#--<div class="input-group">-->
                    <#--<span class="input-group-addon">选择分类</span>-->
                    <#--<input type="text" class="form-control" id="cateChooseInput" data-toggle="dropdown">-->
                    <#--<div class="dropdown-menu" role="menu" id="cateChoose">-->
                        <#--<ul id="treeDemo" class="ztree"></ul>-->
                    <#--</div>-->
                     <#--<input type="hidden" name="cateId"-->
						<#--id="searchCatId" value="${map.cateId }" /> -->
                  <#--</div>-->
                <#--</div>-->
				  <div class="form-group">
					  <div class="input-group">
						  <span class="input-group-addon">货品编号</span>
						  <input type="text" class="form-control" name="goodsNumber" value="${map.goodsNumber!''}" >
					  </div>
				  </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">货品关键字</span>
                    <input type="text" class="form-control" name="goodsInfoName" value="${map.goodsInfoName!''}" >
                  </div>
                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">搜索</button>
                </div>
              </form>
            </div>
     <div>
      <form id="batchdel_ware" action="batchDelWare.htm?CSRFToken=${token }" method="post">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th>序号</th>
              <th>选择</th>
              <th>图片</th>
              <th>货品编号</th>
              <th>货品名称</th>
              <th>供应商</th>
              <th>优惠价</th>
            </tr>
            </thead>
            <tbody>

           	<#list pageBean.content as product>
	            <tr>
	              <td>${product_index+1}</td>
	              <td><input type="checkbox" name="productId" value="${product.goodsInfoId }" /></td>
	              <td><input type="hidden" value="${product.goodsInfoImgId}" id="productImg_${product.goodsInfoId }" />
	               <img src="${product.goodsInfoImgId}" width="131px" height="60px" /></td>
	              <td><span id="productNo_${product.goodsInfoId }">${product.goodsInfoItemNo}</span></td>
	              <td><span
								id="productName_${product.goodsInfoId }"
								title="${product.goodsInfoName}">
					  			<#if product.goodsInfoName?? && product.goodsInfoName?length gt 10>
									${product.goodsInfoName?substring(0,10)}...
								<#else >
									${product.goodsInfoName!''}
								</#if>
							</span></td>
	               <td>
	               <span>
						<span id="goodsMerchants_${product.goodsInfoId }">${product.thirdName}</span>
					</span>
	              </td>
	              <td>
	              <span
							id="productPrice_${product.goodsInfoId }">${product.goodsInfoPreferPrice}</span>
	              </td>
	            </tr>
             </#list>
            </tbody>
            </table>
		</form>
            <div class="table_foot">
				<#include "page/searchPag.ftl"/>
                    <#--<c:import url="../jsp/page/searchPag.jsp">-->
				     <#--<c:param name="pageBean" value="${map.pb }"/>-->
				     <#--<c:param name="path" value="../"></c:param>-->
				<#--</c:import>-->
            </div>

            </div>
		</div>
     
    <script src="${bath}/static/js/jquery.min.js?version=${VERSION}"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${bath}/static/bootstrap/js/bootstrap.min.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jquery.ztree.core-3.5.min.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jquery.ztree.excheck-3.5.min.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
	<link href="${bath}/static/js/artDialog4.1.7/skins/default.css?version=${VERSION}" rel="stylesheet">
	<script type="text/javascript" src="${bath}/static/js/artDialog4.1.7/artDialog.source.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/artDialog4.1.7/plugins/iframeTools.js?version=${VERSION}"></script>
     <script type="application/javascript">
      $(function(){
        //点击出现下拉内容,光标移开下拉内容消失
//        $('#cateChooseInput').click(function(){
//          $('#cateChoose').show().css({
//            'left' : $(this).prev('.input-group-addon').width() + 'px',
//            'minWidth' : '200px'
//          });
//          $('#cateChoose').mouseleave(function(){
//            $(this).hide();
//          });
//        });

        /* 下面是关于树形菜单 */
//        var setting = {
//          data: {
//            simpleData: {
//              enable: true
//            }
//          },
//		    callback : {
//		        onClick : onClick3
//		    }
//        };
//
//        function onClick3(event, treeId, treeNode, clickFlag)
//        {
//            $("#cateChooseInput").val(treeNode.name);
//            $("#searchCatId").val(treeNode.cateId);
//        }
        
        /*获取所有移动版分类，填充高级搜索的Tree*/
        <#--$.get("ajaxQueryMobCateBarForChoose.htm?CSRFToken=${token}", function (data)-->
        <#--{-->
            <#--var zNodes = new Array();-->
            <#--var node = {-->
                    <#--id : 0, cateId : -1,-->
                    <#--pId : 0, name : "所有", open : true -->
                <#--};-->
            <#--zNodes.push(node);-->
            <#--for (var i = 0; i < data.length; i++)-->
            <#--{-->
                <#--if (data[i].cateId == $("#searchCatId").val()) {-->
                    <#--$("#cateChooseInput").val(data[i].name);-->
                <#--}-->
                <#--node = {-->
                    <#--id : data[i].cateBarId, cateId : data[i].cateId,-->
                    <#--pId : data[i].parentId, name : data[i].name, open : true -->
                <#--};-->
                <#--zNodes.push(node);-->
            <#--}-->
            <#--$.fn.zTree.init($("#treeDemo"), setting, zNodes);-->
        <#--});-->
       

        /* 下面是关于树形菜单 END */
        
        
        var parent = art.dialog.parent,				// 父页面window对象
		  api = art.dialog.open.api;	// 			art.dialog.open扩展方法
	if (!api) return;
	// 操作对话框
	api.title('选择货品')
		// 自定义按钮
		.button(
			{
				name: '保存',
				callback: function () {
					var win = art.dialog.open.origin;//来源页面
					//获取可选择的长度
					var size = $("#size").val()>0?$("#size").val():1;
					var flag = checkSelected('productId',size);
					
					if(flag==1){
						//获取productId集合
						var products = $("input[name='productId']:checked");
						//获取图片集合
						var ids = new Array();
						var srcs = new Array();
						var names = new Array();
						var prices = new Array();
						for(var i=0;i<products.length;i++){
							var id = $(products[i]).val();
							ids.push(id);
							srcs.push($("#productImg_" + id).val());
							names.push($("#productName_" + id).attr("title"));
							prices.push($("#productPrice_" + id).text());
						}
						win.window.saveChoooseProduct(ids, srcs, "",
								names, "", prices);
						art.dialog.close();
					}else if(flag==0){
						art.dialog.alert('选择的货品大于'+size+'个！');
						return false;
					}else{
						art.dialog.alert('请选择货品！');
						return false;
					}
				},
				focus: true
			},
			{
				name: '取消',
				callback: function () {
					art.dialog.close();
				}
			}
		);
      });

      /**
      * 检查是否选中一行
      * @param _objId      checkbox节点name属性名
      * @param _modifyFlag 标识符值
      * NINGPAI_xiaomm
      * 2014-03-04 14:22
      * */
      function checkSelected(_objId,size){
      	checkedList = new Array();
        	$("input[name='"+_objId+"']:checked").each(function() {
        		checkedList.push($(this).val());
        	});
        	if(checkedList.length > 0){
      		if(checkedList.length <= size){
      			return 1;
      		}else{
      			return 0;
      		}
      	}else{
        	  	return -1;
        	}
      }

      //查询
      function searchData(pageNumber){
          $("#page").val(pageNumber);
          $("#searchForm").submit();
      }
    </script>
    
</body>
</html>