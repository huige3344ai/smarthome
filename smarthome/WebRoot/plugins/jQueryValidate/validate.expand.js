/**
 * 
 */
$(function(){
	//手机号码验证   
	jQuery.validator.addMethod("isRighZHPhoneNum", function(value, element) {   
	    var tel = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	    return this.optional(element) || (tel.test(value));
	}, "请正确填写中国手机号码！");


	//比较时间
	$.validator.addMethod("compareDate",function(value,element){
		var startDate = CurentTime();
		var born=$(element).val();
	    return new Date(Date.parse(startDate)) >= new Date(Date.parse(born.replace("-", "/"))); 
	},"出生时间必须小于等于当前日期");
	
	/**
	 * 不能有特殊符号
	 */
	$.validator.addMethod("NoZzts",function(value,element){
		var u =/^([\u4E00-\u9FA5]|\w)*$/;
		return this.optional(element) || (u.test(value));
	},"用户名不能包含特殊符号");
	
    jQuery.validator.addMethod("sameToOldPwd", function(value, element) {  
        return this.optional(element) || same(value);  
    }, "新密码不能与老密码重复");  	
	
	var same=function(pwd) {  
		alert($.md5(pwd));
		var old_pwd =object.pwd;
	    if (old_pwd == pwd)  
	        return false;  
	    else  
	        return true;  
	}  

});
