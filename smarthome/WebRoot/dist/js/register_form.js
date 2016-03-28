/**
 * 
 */
	$().ready(function() {
		
		
	    $("#hy_register_form").validate({
	    	onkeyup:false,
	    		rules: {
	    		 "model.userName":{
	    			 required:true,
	    			 minlength:4,
	    			 maxlength:20,
	    			 NoZzts:true,
	    			 remote: {
	    				    url: "page/loginAction_userNameIsExist.action",     //后台处理程序
	    				    type: "post",               //数据发送方式
	    				    dataType: "json",           //接受数据格式   
	    				    data: {                     //要传递的数据
	    				    	"model.userName": function() {
	    				            return $("#userName").val();
	    				        }
	    				    }
	    				}
	    		 }, 
	    		 "model.email":{
	    			 email:true,
	    			 remote: {
	    				    url: "page/loginAction_userNameIsExist.action",     //后台处理程序
	    				    type: "post",               //数据发送方式
	    				    dataType: "json",           //接受数据格式   
	    				    data: {                     //要传递的数据
	    				    	"model.email": function() {
	    				            return $("#email").val();
	    				        }
	    				    }
	    				}	    			 
	    		 },
	    		 "model.phone":{
	    			 rangelength:[11,11],
	    			 isRighZHPhoneNum:true,
	    			 remote: {
	    				    url: "page/loginAction_userNameIsExist.action",     //后台处理程序
	    				    type: "post",               //数据发送方式
	    				    dataType: "json",           //接受数据格式   
	    				    data: {                     //要传递的数据
	    				    	"model.phone": function() {
	    				            return $("#phone").val();
	    				        }
	    				    }
	    				}	    			 
	    		 },
	    		 "model.birthday":{
	    			 required:true,
	    			 dateISO:true,
	    			 compareDate:true
	    			 
	    		 },
	    		 "model.pwd":{
	    			 required:true,
	    			 minlength: 6,
	    			 maxlength:20
	    		 },
	    		 "query.confirmpwd":{
	    			 required:true,
	    			 equalTo:"#hy_pwd"
	    		 },
	    		 hy_agree:{
	    			 required:true
	    		 }
	    		 
	    	 },
	    	 messages:{
	    		 "model.userName":{
	    			 required:"用户名不能为空",
	    			 minlength:"用户名长度需要大于4呢",
	    			 maxlength:"用户名长度需要小于20呢",
	    			 remote:"用户名已经存在"
	    		 }, 
	    		 "model.email":{
	    			 email:'邮箱格式有误.例如:659174520@qq.com',
	    			 remote:"邮箱已经存在，请更换其他邮箱"
	    		 },
	    		 "model.phone":{
	    			 rangelength:'手机号码必须为11位',
	    			 remote:"手机号码已经存在"
	    		 },
	    		 "model.birthday":{
	    			 required:'出生日期不能为空',
	    			 dateISO:'日期有误请按照格式输入',
	    		 },
	    		 "model.pwd":{
	    			 required:'密码不能为空呢',
	    			 minlength:"密码长度需要大6呢",
	    			 maxlength:"密码长度需要小于20呢"
	    		 },
	    		 "query.confirmpwd":{
	    			 required:'确认密码不能为空',
	    			 equalTo:"两次密码不一致呢"
	    		 },
	    		 hy_agree:"必须勾选",
	    		 
	    		
	    	},
			errorPlacement: function(error, element) {
				   if (element.is(":checkbox")){
					   error.appendTo(element.parent().next());
				   }else{
	                    $(element).popover('destroy'); /*必需*/
	                    $(element).popover({content: $(error).text()}).popover('show');					   
					   //error.appendTo(element.parent());
				   }
				   
			},	
/*			success: function(label) {
				// set &nbsp; as text for IE
				label.html("&nbsp;").addClass("glyphicon glyphicon-ok");
				label.css('color','#00a65a');
				
			},
			highlight: function(element, errorClass) {

				$(element).parent().find("." + errorClass).removeClass("glyphicon glyphicon-ok");
				$(element).parent().find("." + errorClass).css('color','red');
			},*/
			submitHandler: function(form) {
				 form.submit();
			},
            unhighlight: function (element, errorClass, validClass) { //验证通过
                $(element).popover('destroy').removeClass(errorClass);
            }
	    	
	    });
	    
	});
