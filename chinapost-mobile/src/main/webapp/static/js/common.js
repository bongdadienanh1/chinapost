	   function isWeiXin(){
		    var ua = window.navigator.userAgent.toLowerCase();
		    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
		        return true;
		    }else{
		        return false;
		    }
	 }

       function HTMLEncode(html)
       {
           var temp = document.createElement ("div");
           (temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html);
           var output = temp.innerHTML;
           temp = null;
           return output;
       }

       function HTMLDecode(text)
       {
           var temp = document.createElement("div");
           temp.innerHTML = text;
           var output = temp.innerText || temp.textContent;
           temp = null;
           return output;
       }


       function alertStr(str){
           $('body').append('<div class="tip-box" style="z-index:99999"><div class="tip-body"><h3>'+str+'</h3></div></div>');
           setTimeout(function(){
               $('.tip-box').remove();
           },1000);
       }


       function alertOk(str,fun){
           var yuePay2 = dialog({
               width: 260,
               content: '<p class="tc">支付密码</p>',
               okValue: '确定',
               ok: function () {
                   //确认离开，跳转到下一页面
                   return true;
               }
           });
           yuePay2.showModal();
       }



       function decimal(num){
           var fx = Math.round(num*100)/100;
           var sx = fx.toString();
           var pos_decimal = sx.indexOf(".");
           if(pos_decimal < 0){
               pos_decimal = sx.length;
               sx += '.';
           }
           while (sx.length <= pos_decimal + 2)
           {
               sx += '0';
           }
           return sx;
       }

