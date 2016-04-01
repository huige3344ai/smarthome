/**
 * (base on jq) base.js
 */


//1.js的分层(功能) : jquery(tools)  组件(ui)  应用(app), mvc(backboneJs)
//2.js的规划(管理) : 避免全局变量和方法(命名空间，闭包，面向对象) , 模块化(seaJs,requireJs)
window.onload = function(){

};
var mv = {};//命名空间

$.isNotBlank =function(str,ob){
	var val=ob.val();
	if(val==null||val.length<=0){
		alert(str+'不能为空!!!');
		return false;
	}
	return true;
}

$.isErrorDate =function(start,end){
	 var startTime = new Date(start.replace(/\-/g, "\/"));  
	 var endTime = new Date(end.replace(/\-/g, "\/"));  
	 if(start!=null&&end!=null&&startTime>=endTime){
		 alert("开始时间不能大于等于结束时间的呢~");
		 return false;
	 }
	 return true;
}


//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}
//调用： var time1 = new Date().Format("yyyy-MM-dd");
//var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");

/**      
 * 对Date的扩展，将 Date 转化为指定格式的String      
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
 * eg:      
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
 */        
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}     
     
//var date = new Date();      
//window.alert(date.pattern("yyyy-MM-dd hh:mm:ss"));



Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(), //day 
        "h+": this.getHours(), //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter 
        "S": this.getMilliseconds() //millisecond 
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


function judeTime(value1,value2){
	  var getDate=function(str){
	   var tempDate=new Date();
	   var list=str.split("-");
	   tempDate.setFullYear(list[0]);
	   tempDate.setMonth(list[1]-1);
	   tempDate.setDate(list[2]);
	   return tempDate;
	  }
	  var date1=getDate(value1);
	  var date2=getDate(value2);
	  if(date1>date2){
		  return true;
	  }else
		  return false;
}

/**
 * app 应用组
 */
mv.app = {};

mv.app.pageSort = function(){
	var sort = $("#pageSort").val();
	$("#sort").val(sort);
    $("#pagerSortForm").submit();
}


mv.app.pageSumSet = function(){
    var perSum = $("#perNum").val();
    $("#numPerPage").val(perSum);
    $("#pagerForm").submit();
}


mv.app.pageFormForward = function(pageNum) {
    $("#pageNum").val(pageNum+1);
    $("#pagerForm").submit();
};


mv.app.pagerFormBack = function(pageNum){
    $("#pageNum").val(pageNum-1);
    $("#pagerForm").submit();
};

mv.app.pageFormSelect = function(page){
    $("#pageNum").val(page);
    $("#pagerForm").submit();
}

mv.app.loadPage = function(Page,MaxPage){

	if(MaxPage != "" && MaxPage > 1){
        document.getElementById("page").innerHTML = Pagehtml()
	}
	
	function Pagehtml(){//分页代码
	    var PageStr = "";
	    Page = parseInt(Page);
	    var xPage = Page - 2,dPage = Page + 2
	    if(xPage < 1){
	            xPage = 1
	            dPage = 5
	    }
	    if(dPage > MaxPage){
	            dPage = MaxPage
	            xPage = (dPage - 4)
	    }
	    if(xPage < 1){
	            xPage = 1
	    }
	    

	    if (Page == 1){
	            PageStr += "<li class='paginate_button previous disabled' ><a >上一页</a></li>"
	    }else{
	            PageStr += "<li class='paginate_button previous' ><a  href='javascript:mv.app.pagerFormBack("+ (Page) +")'>上一页</a> </li>"
	    }
	    if(xPage > 1){
	            PageStr += "<li class='paginate_button active'><a href='javascript:mv.app.pagerFormSelect(1)'>1</a></li>"
	    }
	    if(xPage > 2){
	            PageStr += " ..."
	    }
	    for(var j = xPage;j <= dPage;j++) {
	            PageStr += (Page == j) ? "<li class='paginate_button active'> <a>" + j + "</a></li>" : " <li class='paginate_button'> <a href='javascript:mv.app.pageFormSelect("+ j +")'>" + j + "</a><li/>";
	}
	    if(dPage < MaxPage - 1){
	            PageStr += " ..."
	    }
	    if(dPage < MaxPage){
            PageStr += "<li class='paginate_button'> <a href=' javascript:mv.app.pageFormSelect("+ MaxPage +")'>" + MaxPage + "</a> </li>"
	    }
	    if (Page == MaxPage){
	            PageStr += "<li class='paginate_button next disabled'> <a>下一页</a> </li>"
	    }else{
	            PageStr += "<li class='paginate_button next'><a href='javascript:mv.app.pageFormForward("+ (Page ) +")'>下一页</a></li>"
	    }
	    return PageStr;
	}	
	
}


/**
 * 删除通用方法
 */
var deleteOp = function(url,id,msg){
	if(confirm(msg)){
		$.post(url,{'query.id':id},function(json){
			if(json){
				alert('删除成功');
				self.location.reload();//刷新
			}
			
		},'json');
	}
}	

$(document).ready(function(){
    var status=  $.cookie("sidebar_toggle");
    if(status!=null&&status!=''){
    	//$(document.body).removeClass('skin-blue sidebar-mini');
    	$(document.body).addClass('sidebar-collapse');
    }
});


	$(function(){
		//首页链接  点击之后  清空导航保存cookie
		$(".main-header a").click(function(){
			$.cookie("navstation",null,{ path: "/" })
		});
		//右边 首页导航 点击之后     清空导航保存cookie
		$(".breadcrumb li a").click(function(){
			$.cookie("navstation",null,{ path: "/" })
		});
		
		
		$("#hy_sidebar-toggle").click(function(){
			$.cookie("sidebar_toggle",null , { path: "/" });
			var status="";
			if($(document.body).hasClass('sidebar-collapse')){
				$(document.body).addClass('sidebar-collapse');
				var status="sidebar";
			}else{
				$(document.body).removeClass('sidebar-collapse');
			}
			$.cookie("sidebar_toggle",status , { path: "/" });
		});
		//一级菜单
		$(".sidebar-menu li a").click(function(){
			$.cookie("navstation", $(this).html(), { path: "/" });
		});
		//二级菜单
	    $(".sidebar-menu li ul li a").click(function(){
	        $.cookie("navstation", $(this).html(), { path: "/" });
	    });
	    
	    
	    
	    var navstation = $.cookie("navstation");
	    if(navstation != null){
	    	
	    	$(".sidebar-menu li a").each(function(){
	    		if($(this).html() == navstation){
	    			//$(this).parent().parent().parent().addClass(" active");
	    			$(this).parent().addClass(" active");
	    		}
	    	});
	    	
	    	$(".sidebar-menu li ul li a").each(function(){
	    		if($(this).html() == navstation){
	    			$(this).parent().parent().parent().addClass(" active");
	    			$(this).parent().addClass("active");
	    		}
	    	});
	    	$.cookie("navstation", null, { path: "/" });
	    	
	    }
	    
	    
	});
	

	
	
	
	




