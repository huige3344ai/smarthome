/**
 * 
 */


add_sucess='0';//全局变量  标示是否正常运行ajax

  jQuery.extend({
	  
	  setValidate:function(id,str,url,ad_id,d_id,p_id,c_id,a_id){
			    $(id).validate({
			    	onkeyup:false,
		    		rules: {
		    			"model.ownerId":{
		    				min:1
		    			},
		    			
		    		},
		    		messages:{//messages
			    			"model.ownerId":{
			    				min:"所有者必须勾选"
			    			},
		    		},
				errorPlacement: function(error, element) {
		                    $(element).popover('destroy'); /*必需*/
		                    $(element).popover({content: $(error).text()}).popover('show');					   
				},	
				submitHandler: function(form) {
					var address = '';
					address+=$(p_id).val()+"-";
					address+=$(c_id).val()+"-";
					if($(d_id).val()!=''){
						address+=$(a_id).val()+"-";
						address+=$(d_id).val();
					}else
						address+=$(a_id).val();
					
					if(confirm('是否确认提交当前的数据？')&&address!=''){
						$(ad_id).attr("value", address);
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
								alert("网络异常，"+str+"失败");
							},
							dataType: "json"
						});
					}else
						alert('住所信息不完整，请重新尝试')
					
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
				  add_sucess='0';
		  })
	  }		
  });
$(function(){
	
	new PCAS('location_p', 'location_c', 'location_a', '广东省', '', '');
	$.setValidate("#hy_add_form","添加","page/homeAction_addHome.action",'#hy_address',"#hy_detail_address","#location_p","#location_c","#location_a");
	
	$.setValidate("#hy_update_form","更新","page/homeAction_updateHome.action",'#hy_up_address',"#hy_up_detail_address","#location_up_p","#location_up_c","#location_up_a");
	
	$.setClodeModal('#addModal');
	$.setClodeModal('#updateModal');
	
	//避免select2  自身遮挡 原生select选择后无法消除验证信息的问题
	$('#hy_userId').change(function(){
		 $(this).valid(); 
	});
	
    //Initialize modal
    $('#hy_add_button').click(function(){
        	$.ajax({
					  type:'POST',
					  url: "devicesActionb_getAllUserList.action",
					  async: false,
					  data: {},
					  success: function(json){
						  if(!jQuery.isEmptyObject(json)){
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


var fillData = function(id){
	$.ajax({
	  type: 'POST',
	  url: "page/homeAction_getHome.action",
	  data: {"query.id":id},
	  success: function(json){
		$('#hy_up_id').attr("value", id);
		
	  	$('#hy_up_country').attr("value", json.country);
		var address = json.address
	  	if(address!=''){
	  		var detail = address.split("-");
	  		if(detail.length==4){
	  			new PCAS('location_up_p', 'location_up_c', 'location_up_a',detail[0] , detail[1], detail[2]);
	  			$('#hy_up_detail_address').attr("value", detail[3]);	
	  		}else if(detail.length==3){
	  			new PCAS('location_up_p', 'location_up_c', 'location_up_a',detail[0] , detail[1], detail[2]);
	  		}else{
	  			aleret('加载数据失败');
	  			$('hy_save_homes').val("disabled", "true");
	  		}
	  	}
	  	getUser(json.ownerId);//设置所有者
	  	
	  },
	  dataType:"json", 
	  error:function(){
	  	alert('加载数据失败');
	  },
	});

	
$("#hy_up_userId").select2();		
var getUser = function(userId){
    $.ajax({
			  type:'POST',
			  url: "devicesActionb_getAllUserList.action",
			  async: false,
			  data: {},
			  success: function(json){
				if(!jQuery.isEmptyObject(json)){
						$('#hy_up_userId').empty();
						$('#hy_up_userId').append("<option value='0'>请选择</option>");
						$.each(json, function(index,element){
								if(userId==element.id){
									var op = "<option value='"+element.id+"' selected='true'>"+element.userName+"</option>";
									$('#select2-hy_up_userId-container').text(element.userName);//根据系统生成的id手动赋值
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


};