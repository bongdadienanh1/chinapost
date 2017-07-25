function getCurrentEnterpriseInfo(){
    if( window.sessionStorage ){
        var _enterpriseId = getItem("enterpriseId");
        var _enterpriseName = getItem("enterpriseName");
        var _accountName = getItem("accountName");
        var _isTop = getItem("isTop");
        var _isEnd = getItem("isEnd");
        var data =  {
            enterpriseId:_enterpriseId,
            enterpriseName:_enterpriseName,
            accountName:_accountName,
            isTop:_isTop,
            isEnd:_isEnd
        }
        var enterprise = new Enterprise(data);
        return enterprise;
    }
}

function getItem(key){
    return sessionStorage.getItem(key);
}


function Enterprise(data){
    var enterpriseId = data.enterpriseId;
    var enterpriseName = data.enterpriseName;
    var accountName = data.accountName;
    var isTop = data.isTop;
    var isEnd = data.isEnd;
    var _enterprise = {
        //setEnterpriseId:function(id){
        //    enterpriseId = id;
        //},
        getEnterpriseId:function(){
            return enterpriseId;
        },

        //setEnterpriseName:function(name){
        //    enterpriseName = name;
        //},
        getEnterpriseName:function(){
            return enterpriseName;
        },

        //setAccountName:function(name){
        //    accountName = name;
        //},
        getAccountName:function(){
            return accountName;
        },

        //setIsTop:function(b){
        //    isTop = b;
        //},
        getIsTop:function(){
            return isTop;
        },

        //setIsEnd:function(b){
        //    isEnd = b;
        //},
        getIsEnd:function(){
            return isEnd;
        }
    };
    return _enterprise;
}