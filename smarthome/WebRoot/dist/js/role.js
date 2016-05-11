/**
 * 
 */
var status='0';


jQuery.extend({
	  
	  setValidate:function(id,str,url){
		    $(id).validate({
		    	onkeyup:false,
		    		rules: {
		    			"model.roleName":{
		    				required: true
		    			},
		    			"model.role":{
			    			required:true,
			    			rangelength:[1,10]
		    			}
		    			
		    		},
		    		messages:{//messages
		    				"model.roleName":{
		    					required:"角色名称不能为空",
			    			},
			    			"model.role":{
				    			required:"角色代号为必填项",
				    			rangelength:"角色代号字符不能超过十位。"
			    			}

		    		},
				errorPlacement: function(error, element) {
		                    $(element).popover('destroy'); /*必需*/
		                    $(element).popover({content: $(error).text()}).popover('show');					   
				},	
				submitHandler: function(form) {
					
					if(confirm('是否确认提交当前的数据？')){
						var data = $(id).serializeArray();
						$.ajax({
							type: 'POST',
							url: url,
							data: data,
							success: function(msg){
								if(msg=='1'){
									alert(str+"成功，请关闭当前对话框。");
									status='1';
								}else{
									alert("网络异常，"+str+"失败");
								}
							},
							erorr:function(){
								alert("网络异常，"+str+"失败");
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
	setClosedModal:function(id){
	 $(id).on('hidden.bs.modal', function (e) {
		 $(this).removeData("modal");//每次重新加载数据
		  if(status=='1'){
			  self.location.reload();
		  }else
			  status='0';
	  })
}
});

$(function(){
	$('#hy_add_button').click(function(){
		$('.permission').remove();
		$.ajax({
			  type: 'POST',
			  url: 'page/rolesAction_getPermisson.action',
			  async:true,
			  success: function(json){
				  	if(!jQuery.isEmptyObject(json)){
				  		$.each(json, function(index,element){
				  			if(element.module=='user'){
				  				var check = "<div class='col-xs-6 col-sm-4 col-md-4 permission' >";
				  				check +="<label class='checkbox-inline'>";
				  				check+="<input type='checkbox' name='query.permission' value='"+element.id+"'>"+element.permissionName;
				  				check+="</label></div>";
				  				$('#hy_role_user').append(check);
				  			}else if(element.module=='device'){
				  				var check = "<div class='col-xs-6 col-sm-4 col-md-4 permission'>";
				  				check +="<label class='checkbox-inline'>";
				  				check+="<input type='checkbox' name='query.permission' value='"+element.id+"'>"+element.permissionName;
				  				check+="</label></div>";				  				
				  				$('#hy_role_device').append(check);			  				
				  			}else if(element.module=='home'){
				  				var check = "<div class='col-xs-6 col-sm-4 col-md-4 permission'>";
				  				check +="<label class='checkbox-inline'>";
				  				check+="<input type='checkbox' name='query.permission' value='"+element.id+"'>"+element.permissionName;
				  				check+="</label></div>";					  				
				  				$('#hy_role_home').append(check);			  				
				  			}else if(element.module=='role'){
				  				var check = "<div class='col-xs-6 col-sm-4 col-md-4 permission'>";
				  				check +="<label class='checkbox-inline'>";
				  				check+="<input type='checkbox' name='query.permission' value='"+element.id+"'>"+element.permissionName;
				  				check+="</label></div>";					  				
				  				$('#hy_role_power').append(check);			  				
				  			}
				  		});
				  	}else
						  alert('数据加载失败');
			  },
			  dataType:"json", 
			  error:function(){
				  alert('数据加载失败');
			  },
		});

		$('#addModal').modal();
		
	});
	
	$.setValidate("#hy_add_form","添加角色",'page/rolesAction_saveRoles.action');
	$.setValidate("#hy_update_form","修改角色",'page/rolesAction_updateRoles.action');
	$.setClosedModal('#addModal');
	$.setClosedModal('#updateModal');

});

var fillCheck = function(id,name,checked){
		var check = "<div class='col-xs-6 col-sm-4 col-md-4 update_permission' >";
			check +="<label class='checkbox-inline'>";
			if(checked=='true'){
				check+="<input type='checkbox' name='query.permission' value='"+id+"' checked='"+checked+"'>"+name;
			}else
			check+="<input type='checkbox' name='query.permission' value='"+id+"'>"+name;
			
			check+="</label></div>";
		return check;
}
var fillData = function(id){
	object={}
	$('.update_permission').remove();
	$.ajax({
		  type: 'POST',
		  url: 'page/rolesAction_getUpdatePermisson.action',
		  data: {"query.id":id},
		  success: function(json){
					if(!jQuery.isEmptyObject(json)){
						$.each(json, function(index,element){
				  			if(element.module=='user'){
				  				if(element.selected=='1'){
				  					$('#hy_update_role_user').append(fillCheck(element.id,element.permissionName,'true'));
				  				}else
				  					$('#hy_update_role_user').append(fillCheck(element.id,element.permissionName,''));
				  			}else if(element.module=='device'){
				  				if(element.selected=='1'){
				  					$('#hy_update_role_device').append(fillCheck(element.id,element.permissionName,'true'));
				  				}else
				  					$('#hy_update_role_device').append(fillCheck(element.id,element.permissionName,''));		  				
				  			}else if(element.module=='home'){
				  				if(element.selected=='1'){
				  					$('#hy_update_role_home').append(fillCheck(element.id,element.permissionName,'true'));
				  				}else
				  					$('#hy_update_role_home').append(fillCheck(element.id,element.permissionName,''));
				  			}else if(element.module=='role'){
				  				if(element.selected=='1'){
				  					$('#hy_update_role_power').append(fillCheck(element.id,element.permissionName,'true'));
				  				}else
				  					$('#hy_update_role_power').append(fillCheck(element.id,element.permissionName,''));
				  			}							
							
						});
					}  
		  },
		  dataType:"json", 
		  error:function(){
		  },
		});
	
	$.ajax({
		type: 'POST',
		url: 'page/rolesAction_getUpdateRoles.action',
		data: {"query.id":id},
		success: function(json){
			$('#hy_update_roleName').val('');
			$('#hy_update_role').val('');
			$('#hy_update_id').val('');
			if(!jQuery.isEmptyObject(json)){
						object=json;
						$('#hy_update_roleName').val(object.roleName);
						$('#hy_update_role').val(object.role);
						$('#hy_update_id').val(id);
			}else{
				alert('加载数据失败');
			}  
		},
		dataType:"json", 
		error:function(){
			alert('加载数据失败');
		},
	});
	
	
}