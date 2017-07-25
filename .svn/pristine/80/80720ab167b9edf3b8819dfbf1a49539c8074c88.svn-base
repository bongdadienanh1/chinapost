<head>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style>
        .accountSearch{
            width:1600px;
            height:150px;
            margin-left:20px;
            margin-top:20px;
        }
        .accountSearch li{
            width:250px;
            height:36px;
            line-height:40px;
            float:left;
        }
        .accountSearch li input,select,option{
            width:120px;
            height:38px;
            margin-left: -3px;;
            border:1px solid #ccc;
            background:#FFF;
            margin-top: -3px;;
        }
        option{
            font-size: 15px;
        }
        .accountSearch li span{
            display:inline-block;
            width:90px;
            height:36px;
            text-align:center;
            font-size:15px;
            background:#e4e4e4;
            line-height:36px;
            border:1px solid #ccc;

        }
        #accSearch{
            width:70px;
            height:37px;
            background:#24b383;
            color:#FFF;
            border:1px solid transparent;
            font-size:15px;
            margin-left: 10px;
        }
        #accSearch:hover{
        }
        #accSearch:active{
            background: #009966;
        }
        #accExport{
            width:110px;
            height:37px;
            background:#33cccc;
            color:#FFF;
            font-size: 15px;
            border:1px solid transparent;
            margin-left:30px;
        }
        #accExport:hover{
            border:1px solid #f0aa39;
            color:#f0aa39;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    sessionStorage.setItem("ShoppingCart",'');
</script>
<div style="width:1600px; height:100px; border-bottom:2px solid #dcdcdc; margin-left:20px;">
	<span style="font:24px '黑体'; color:#000; display:inline-block; margin-top:15px;margin-bottom:20px;">会员管理
    </span>
    <br  />
    会员列表
    |
    <a style="display:inline-block; margin:0px 10px 0px 10px;" href="piliangdaoru">会员消费统计</a>
</div>
<div class="accountSearch">
    <ul>
        <li>
            <span>身份证号</span>
            <input type="text" name="accountNum" />
        </li>
        <li>
            <span>姓名</span>
            <input type="text" name="accountCust" />
        </li>
        <li>
            <span>联系电话</span>
            <input type="text" name="accountCust" />
        </li>
        <li>
            <span>是否激活</span>
            <select>
                <option selected>全部</option>
                <option>是</option>
                <option>否</option>
            </select>
        </li>
        <li>
            <span>客户经理号</span>
            <input type="text" name="accountUserName" />
        </li>
        <li>
            <span>手机绑定</span>
            <select>
                <option selected>全部</option>
                <option>是</option>
                <option>否</option>
            </select>
        </li>
        <li style="margin-top: 10px;">
            <span>标签</span>
            <select>

            </select>
        </li>
        <li style="width:300px;margin-top: 10px;">
            <input type="button" value="搜索" id="accSearch" />
            <input type="button" id="accExport" value="开通新会员" />
        </li>
    </ul>
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>