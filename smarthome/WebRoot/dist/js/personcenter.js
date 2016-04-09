/**
 * 
 */

object={};

jQuery.extend({		
	  setValidate:function(id,str,url,u_id){
		  $(id).validate({
			  onkeyup:false,
			  rules: {
				  "model.email":{
					  email:true,
					  remote: {
						  url: "page/loginAction_userNameIsExist.action",     //后台处理程序
						  type: "post",               //数据发送方式
						  dataType: "json",           //接受数据格式   
						  data: {                     //要传递的数据
							  "model.email": function() {
								  return $("#"+u_id+"email").val();
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
								  return $("#"+u_id+"phone").val();
							  }
						  }
					  }	    			 
				  },
				  "model.birthday":{
					  required:true,
					  dateISO:true,
					  compareDate:true
					  
				  }

				  
			  },
			  messages:{
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
				  }
				  
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
			  submitHandler: function(form) {
				  
					if(confirm('是否确认提交当前的数据？')){
						var data = $(id).serialize();
						$.ajax({
							type: 'POST',
							url: url,
							data: data,
							anysc:false,
							success: function(msg){
								if(msg==0){
									alert(str+"成功");
									self.location.reload();
									add_sucess='1';
								}else if(msg==1){
									alert('更新密码成功，将跳转登录页面');
									self.location='page/login.jsp';
								}else{
									alert(str+"失败，失败原因："+msg);
									
								}
							},
							erorr:function(){
								alert('网络异常，添加失败');
							},
							dataType: "json"
						});
					}
					
			  },
			  unhighlight: function (element, errorClass, validClass) { //验证通过
				  $(element).popover('destroy').removeClass(errorClass);
			  }
			  
		  });
	  }
	  
});

$(function(){
	
	$("#up_phone").inputmask("[99999999999]");//phone Euro	
	$("#up_datemask").inputmask("yyyy-mm-dd");	//date	

	

 	
	$.ajax({
		  type: 'POST',
		  url: "page/userAction_getUser.action",
		  data: {"query.id":$('#user_ud').val()},
		  success: function(json){
			object=json;
			
			if(json.length==0){
				$('#hy_update').attr("disabled", "true");
			}else{
				
				if(json.email!=''){
					$('#up_email').rules("remove");
				}
				
				
				if(json.email!=''){
					$('#up_datemask').rules("remove");
				}
				
				
				
				if(json.phone!=''){
					$('#up_phone').rules("remove");
				}
				
				
				//(id,object,url)
				validateEmail("#up_email",object,"page/loginAction_userNameIsExist.action","up_");
				validatePhone("#up_phone",object,"page/loginAction_userNameIsExist.action","up_");
			}
			
		  	
		  },
		  dataType:"json", 
		  error:function(){
		  	alert('加载数据失败');
		  }
		});	
	
	$('#hy_new_pwd').change(function(){
		if($('#hy_new_pwd').val()!=''){
			$('#hy_new_pwd').rules("add",{
			  required:true,
			  minlength: 6,
			  maxlength:20,
			  messages:{
				  required:'密码不能为空呢',
				  sameToOldPwd:true,
				  minlength:"密码长度需要大6呢",
				  maxlength:"密码长度需要小于20呢"	  
			  }
				
			});
			$("#hy_confirm_pwd").rules("add",{
			  required:true,
			  equalTo:"#hy_new_pwd",
			  messages:{
				  required:'确认密码不能为空',
				  equalTo:"两次密码不一致呢"						  
			  }
				
			});
			$("#hy_pwd").rules("add",{
				required:true,				
				messages:{
					required:'旧密码不能为空',
				}
			});			
			
		}else{
			$('#hy_pwd').rules("remove");
			$('#hy_new_pwd').rules("remove");
			$('#hy_confirm_pwd').rules("remove");
		}
	
		
		
	});	

	
});

