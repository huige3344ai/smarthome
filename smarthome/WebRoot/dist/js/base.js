/**
 * (base on jq) base.js
 */


//1.js的分层(功能) : jquery(tools)  组件(ui)  应用(app), mvc(backboneJs)
//2.js的规划(管理) : 避免全局变量和方法(命名空间，闭包，面向对象) , 模块化(seaJs,requireJs)
window.onload = function(){

};



var status='0';//设置全局变量
var mv = {};//命名空间

/**
 * 全局的ajax访问，处理ajax清求时sesion超时
 */
$.ajaxSetup({ 
    contentType:"application/x-www-form-urlencoded;charset=utf-8", 
    complete:function(XMLHttpRequest,textStatus){ 
            var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
            if(sessionstatus=="timeout"){ 
            			alert('你没有相应权限，请登录相应具有权限的用户操作！！！')
                        
		                } else if(sessionstatus=="nopermisson"){
		                	//如果超时就处理 ，指定要跳转的页面
		                	window.location.replace("page/login.jsp"); 
		                }
             } 
    });

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


/**
 * 获取当前日期  yyyy/mm/dd
 */
function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    //var hh = now.getHours();            //时
    //var mm = now.getMinutes();          //分
   
    var clock = year + "/";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "/";
   
    if(day < 10)
        clock += "0";
       
      clock += day ;
//    clock += day + " ";
   
//    if(hh < 10)
//        clock += "0";
       
//    clock += hh + ":";
//    if (mm < 10) clock += '0'; 
//    clock += mm; 
    
    return(clock); 
} 



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
	if(confirm(msg+" 删除后，用户相关联的数据也会丢失，是否继续")){
		$.post(url,{'query.id':id},function(json){
			if(!jQuery.isEmptyObject(json)){
				alert('删除成功');
				self.location.reload();//刷新
			}else{
				alert('删除失败');
			}
			
		},'json');
	}
}	

var updateOp = function(url,id,status,msg){
	if(confirm(msg)){
		$.post(url,{'query.id':id,'query.status':status},function(json){
			if(!jQuery.isEmptyObject(json)){
				alert('更新状态成功');
				self.location.reload();//刷新
			}else{
				alert('更新状态失败');
			}
			
		},'json');
	}
}	

/**
 * 重置form数据
 */
var resetForm = function(formID){
	$(':input',formID)
	.not(':button, :submit, :reset, :hidden')
	.val('')
	.removeAttr('checked')
	.removeAttr('selected');
}

/*关闭模态框后刷新页面*/
var setClosedModal=function(id){
 $(id).on('hidden.bs.modal', function (e) {
	 $(this).removeData("modal");//每次重新加载数据
	  if(status=='1'){
		  self.location.reload();
	  }else
		  status='0';
  })
}

var judePicType = function(id){
	
	$(id).change(function(){
			  var filepath=$("input[name='query.file']").val();
			  var extStart=filepath.lastIndexOf(".");
			  var ext=filepath.substring(extStart,filepath.length).toUpperCase();
			  if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			  alert("图片限于bmp,png,gif,jpeg,jpg格式");
			  return false;
			  }else{
				  alert(ext+'可以上传');
			  }
			  var file_size = 0;
			  if (/msie/.test(navigator.userAgent.toLowerCase())) {
			  var img=new Image();
			  img.src=filepath;
				  while(true){
					  if(img.fileSize > 0){
						  if(img.fileSize>10240){
						  alert("图片不大于10MB。");
						  }else{
						  var num03 = img.fileSize/1024;
						  num04 = num03.toFixed(2)
						  }
					  break;
					  }
				  }
			  } else {
			  file_size = this.files[0].size;
			  console.log(file_size/1024/1024 + " MB");
			  var size = file_size / 1024;
				  if(size > 10240){
				  alert("上传的文件大小不能超过10M！");
				  }else{
				  var num01 = file_size/1024;
				  num02 = num01.toFixed(2)
				  }
			  }
			  return true;
		  });
	
	
}



/**
 * 不能为空动态添加校验
 */
var validateNotEmpty= function(id,str){
	$(id).change( function() { 
		if($(id).val()!=''){
			$(id).rules("add",{
				required:true,
				messages:{
					required:str+"不能为空"
				}
			});
		}
	});
}

/**
 * 校验邮箱通用方法
 */
var validateEmail= function(id,object,url){
	$(id).change( function() {
		alert(object.email)
		if($(id).val()!=object.email){
			$(id).rules("add",{
				required: true,
				email:true,
				remote: {
					url: url,     //后台处理程序
					type: "post",               //数据发送方式
					data: {                     //要传递的数据
						"model.email": function() {
							return $(id).val();
						}
					}
				},
			messages:{
					required:"邮箱不能为空",
					email:'邮箱格式有误.例如:659174520@qq.com',
					remote:"邮箱已经存在，请更换其他邮箱"
			}
			
			});
		}else
			$(id).rules("remove");
	});
	
}

/**
 * 校验手机通用方法
 */
var validatePhone = function(id,object,url){
	
	$(id).change(function(){
		if($(id).val()!=object.phone){
		$(id).rules("add",{
				  required:true,
				  rangelength:[11,11],
				  isRighZHPhoneNum:true,
				  remote: {
					  url: url,     //后台处理程序
					  type: "post",               //数据发送方式
					  data: {                     //要传递的数据
						  "model.phone": function() {
							  return $(id).val();
						  }
					  }
				  },
				  messages:{
					  required:"手机号码不能为空",
					  rangelength:'手机号码必须为11位',
					  remote:"手机号码已经存在"
				  }
			  					
		});
	}else
	  	$(id).rules("remove");
});
	
}	

/**
 * 校验两次密码通用方法
 */		
var validatePwd = function(id,confirmId,object){
	$(id).change(function(){
		if($(id).val()!=''){
			$(id).rules("add",{
			  required:true,
			  minlength: 6,
			  maxlength:20,
			  messages:{
				  required:'密码不能为空呢',
				  minlength:"密码长度需要大6呢",
				  maxlength:"密码长度需要小于20呢"	  
			  }
				
			});
			$(confirmId).add("rules",{
			  required:true,
			  equalTo:id,
			  messages:{
				  required:'确认密码不能为空',
				  equalTo:"两次密码不一致呢"						  
			  }
				
			});
		}else
		  	$(id).rules("remove");
	});	
	
}	


/**
 * 生日 添加校验添加
 */
var validateBirthday = function(id,object){
	$(id).change(function(){
		if($(id).val()!=object.birthday){
			$(id).rules("add",{
				required:true,
				dateISO:true,
				compareDate:true,
				messages:{
					required:'出生日期不能为空',
					dateISO:'日期有误请按照格式输入'
				}
			
			});
		}
	});
	
}


$(document).ready(function(){
    var status=  $.cookie("sidebar_toggle");
    if(status!=null&&status!=''){
    	//$(document.body).removeClass('skin-blue sidebar-mini');
    	$(document.body).addClass('sidebar-collapse');
    }
});




/**
 * cookie 保存菜单操作情况
 */

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
	


      	
	




