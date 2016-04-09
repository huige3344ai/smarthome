/**
 * 
 */
add_sucess='0';//全局变量
object={};
	 jQuery.extend({		
		  setValidate:function(id,str,url,pwdId,u_id){
			  $(id).validate({
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
									  return $("#"+u_id+"userName").val();
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
						  
					  },
					  "model.pwd":{
						  required:true,
						  minlength: 6,
						  maxlength:20
					  },
					  "query.confirmpwd":{
						  required:true,
						  equalTo:pwdId
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
								success: function(msg){
									if(msg){
										alert(str+"成功，请关闭当前对话框。");
										add_sucess='1';
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
		  },
		  
		/*关闭模态框后刷新页面*/
		setCloseModal:function(id){
		 $(id).on('hidden.bs.modal', function (e) {
			 $(this).removeData("modal");//每次重新加载数据
			 $('#up_userName').attr("value", "");
			 $('#up_email').attr("value", "");
			 $('#up_phone').attr("value", "");
			 $('#up_datemask').attr("value", "");
			 $('#user_ud').attr("value", "");
			  if(add_sucess=='1'){
				  self.location.reload();
			  }else
				  add_sucess='0';
		  })
	  }
	    
	});
	 
	 
	 var fillData = function(id){
		 
		$.setValidate("#hy_update_form","更新","page/userAction_updateBUser.action","#hy_up_pwd","up_");
		 
		 object={};
			$.ajax({
			  type: 'POST',
			  url: "page/userAction_getUser.action",
			  data: {"query.id":id},
			  success: function(json){
				object=json;
				
				$('#user_ud').attr("value", json.id);
				
			  	$('#up_userName').attr("value", json.userName);
			  	$('#up_userName').rules("remove");
			  	
			  	$('#up_email').attr("value", json.email);
			  	$('#up_email').rules("remove");
			  	
			  	
			  	
			  	if(json.phone!=''){
			  		$('#up_phone').attr("value", json.phone);
			  		$('#up_phone').rules("remove");
			  	}
			  	
			  	if(json.birthday!=''){
			  		$('#up_datemask').attr("value", json.birthday);
			  		$('#up_datemask').rules("remove");
			  	}
			  	validateBirthday("#up_datemask",object);
			  	
			  	if(json.isAdmin=='0'||json.isAdmin=='1'){
			  		$('#isAdmin').val(json.isAdmin);
			  	}else
			  		$('#isAdmin').val("0");
			  	
			  	$('#hy_up_pwd').rules("remove");//移除密码校验
			  	$('#hy_up_confirm_pwd').rules("remove");


			  },
			  dataType:"json", 
			  error:function(){
			  	alert('加载数据失败');
			  }
			});
	 }
	 
	$(function(){
		$.setValidate("#hy_register_form","添加","page/userAction_registerBUser.action","#hy_pwd","add_");
		$.setCloseModal("#addModal");
		
		$.setCloseModal("#updateModal");		 

		
		
		$('#up_email').change( function() { 
			if($('#up_email').val()!=object.email){
				$('#up_email').rules("add",{
						required:true,
						  email:true,
						  remote: {
							  url: "page/loginAction_userNameIsExist.action",     //后台处理程序
							  type: "post",               //数据发送方式
							  dataType: "json",           //接受数据格式   
							  data: {                     //要传递的数据
								  "model.email": function() {
									  return $("#up_email").val();
								  }
							  }
						  }	    			 
					  ,
					  messages:{
						  "model.email":{
							  required:"邮箱不能为空了。",
							  email:'邮箱格式有误.例如:659174520@qq.com',
							  remote:"邮箱已经存在，请更换其他邮箱"
						  }
					  }
					
				});
			}else
			  	$('#email').rules("remove");
		});
		
	  	$('#up_phone').change(function(){
	  		
			if($('#up_phone').val()!=object.phone){
				$('#up_phone').rules("add",{
						  required:true,
						  rangelength:[11,11],
						  isRighZHPhoneNum:true,
						  remote: {
							  url: "page/loginAction_userNameIsExist.action",     //后台处理程序
							  type: "post",               //数据发送方式
							  dataType: "json",           //接受数据格式   
							  data: {                     //要传递的数据
								  "model.phone": function() {
									  return $("#up_phone").val();
								  }
							  }
						  },
						  messages:{
							  required:"手机不能为空了。",							  
							  rangelength:'手机号码必须为11位',
							  remote:"手机号码已经存在"
						  }
					  					
				});
			}else
			  	$('#up_phone').rules("remove");
	  	});
	  	
	  	
	  	$('#hy_up_pwd').change(function(){
	  		if($('#hy_up_pwd').val()!=''){
	  			$('#hy_up_pwd').rules("add",{
					  required:true,
					  minlength: 6,
					  maxlength:20,
					  messages:{
						  required:'密码不能为空呢',
						  minlength:"密码长度需要大6呢",
						  maxlength:"密码长度需要小于20呢"	  
					  }
	  				
	  			});
	  			$('hy_up_confirm_pwd').rules("add",{
					  required:true,
					  equalTo:"#hy_up_pwd",
					  messages:{
						  required:'确认密码不能为空',
						  equalTo:"两次密码不一致呢"						  
					  }
	  				
	  			});
	  		}
	  	});
	  	

	  	
		
		
	});
