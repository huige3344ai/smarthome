/**
 * 所有设备.js
 */	

add_sucess='0';//全局变量

  jQuery.extend({
	  
	  setValidate:function(id,str,url){
		    $(id).validate({
		    	/*onkeyup:false,*/
		    		rules: {
		    			"model.deviceNum":{
		    				rangelength:[1,10],
		    			},
		    			"model.deviceName":{
			    			required:true,
			    			rangelength:[1,10]
		    			},
		    			"model.userId":{
		    				min:1
		    			},
		    			"model.homeId":{
		    				min:1
		    			}
		    			
		    		},
		    		messages:{//messages
		    				"model.deviceNum":{
			    				rangelength:"设备编号不能超过十位",
			    			},
			    			"model.deviceName":{
				    			required:"设备名称为必填项",
				    			rangelength:"设备名称的字符不能超过十位。"
			    			},
			    			"model.userId":{
			    				min:"所有者必须勾选"
			    			},
			    			"model.homeId":{
			    				min:"房屋必须勾选"
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
								if(msg){
									alert(str+"成功，请关闭当前对话框。");
									add_sucess='1';
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
	setClodeModal:function(id){
	 $(id).on('hidden.bs.modal', function (e) {
		 $(this).removeData("modal");//每次重新加载数据
		  if(add_sucess=='1'){
			  self.location.reload();
		  }else
			  add_sucess=='0';
	  })
  }
  });


  
$(function() {
	
    	$("#hy_deviceNum").inputmask("[9999999999]");
    	//$("#hy_up_deviceNum").inputmask("[9999999999]");
    	
		
		//Initialize select2
		$('#hy_homeId').select2();
		
		//避免select2  自身遮挡 原生select选择后无法消除验证信息的问题
		$('#hy_userId').change(function(){
			 $(this).valid(); 
		});
		
		$('#hy_homeId').change(function(){
			$(this).valid(); 
		});
		
		
		$.setValidate("#hy_add_form","添加","devicesActionb_saveDevices.action");
		$.setValidate("#hy_update_form","更新","devicesActionb_updateDevices.action");
		
		
		$.setClodeModal("#addModal");
		$.setClodeModal("#updateModal");

	    	
	        //联动 触发
	        $('#hy_userId').change(function(){
	        	home("hy_homeId",this.value,"0");
	        });
	        
	        //Initialize modal
	        $('#hy_add_button').click(function(){
		        	$.ajax({
							  type:'POST',
							  url: "devicesActionb_getAllUserList.action",
							  async: false,
							  data: {},
							  success: function(json){
	        						
									if(json!=null&&json!='null'){
										$.each(json, function(index,element){
												var op = "<option value='"+element.id+"'>"+element.userName+"</option>";
												$('#hy_userId').append(op)
												});
												
									}		
							  },
							  dataType: 'json'
							});
							
				//Initialize Select2 Elements
		        $("#hy_userId").select2();	
		        
		        $("#addModal").modal();

					
			});	
	        
		});

	var home = function(id,data,selected){
		if(selected=='0'){
			$("#select2-"+id+"-container").text("请选择");
		}
		$.ajax({
			type:"post",
			url:"devicesActionb_getHomeList.action",
			async: true,
			data:{"query.uid":data},
			success: function(json){
				$("#"+id).empty();
				$("#"+id).append("<option value='0'>请选择</option>");
				if(json!=null&&json!='null'){
					$.each(JSON.parse(json),function(index,element){
						if(selected==element.id){
							var op = "<option value='"+element.id+"' selected='true'>"+element.country+"-"+element.address+"</option>";
								$("#select2-"+id+"-container").text(element.country+"-"+element.address);//根据系统生成的id手动赋值
						}else{
							var op = "<option value='"+element.id+"'>"+element.country+"-"+element.address+"</option>";
						}
						$("#"+id).append(op);
					}			
					);
				}
				
			},
			dateType:"json",
	});
	
		
};

var fillData = function(id){
	$.ajax({
	  type: 'POST',
	  url: "devicesActionb_getDevice.action",
	  data: {"query.id":id},
	  success: function(json){
	  	$('#hy_up_deviceNum').attr("value", json.deviceNum);
	  	$('#hy_up_deviceName').attr("value", json.deviceName);
	  	$('#hy_up_status').val(json.status);
	  	$('#hy_up_id').attr("value", id);
	  	getUser(json.userId,json.homeId);
	  },
	  dataType:"json", 
	  error:function(){
	  	alert('加载数据失败');
	  },
	});
	
//Initialize Select2 Elements
$("#hy_up_userId").select2();		
$("#hy_up_homeId").select2();		
var getUser = function(userId,homeId){
     $.ajax({
			  type:'POST',
			  url: "devicesActionb_getAllUserList.action",
			  async: false,
			  data: {},
			  success: function(json){
			  	$('#hy_up_userId').empty();
				$('#hy_up_userId').append("<option value='0'>请选择</option>");
					if(json!=null&&json!='null'){
						$.each(json, function(index,element){
								if(userId==element.id){
									var op = "<option value='"+element.id+"' selected='true'>"+element.userName+"</option>";
									$('#select2-hy_up_userId-container').text(element.userName);//根据系统生成的id手动赋值
									home("hy_up_homeId",element.id,homeId);
								}else{
									var op = "<option value='"+element.id+"'>"+element.userName+"</option>";
								}
								$('#hy_up_userId').append(op)
							});
								
					}		
			  },
			  dataType: 'json'
			});	
}	

	        //联动 触发
    $('#hy_up_userId').change(function(){
    	home("hy_up_homeId",this.value,"0");
    });

}	

