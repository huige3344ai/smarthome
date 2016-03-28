<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  


<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    
    <title>智能家居控制系统找回密码</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link href="//cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">
    <!-- Ionicons -->
	<link href="//cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


    <style type="text/css">
    .popover-content{
    	font-size:12px;
    	color: red;  
    }
    </style>
	
  </head>
  
<body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
       <a href="page/login.jsp"><b>SMARTHOME</b>C</a> 
      </div><!-- /.login-logo -->
      <div class="login-box-body">
            	<p class="login-box-msg" style="color: red">
					
            	</p>            	
        
        <form action="page/loginAction_updatePwd.action" method="post" id="hy_reset_form" >
        	<input type="hidden" name="query.userName" value="<s:property value="query.userName"/>"/>    
        	<input type="hidden" name="query.emailVer" value="<s:property value="query.emailVer"/>"/>    
        	    	
          <div class="form-group has-feedback">
            <input  type="email" class="form-control"  name="query.email" value="<s:property value="query.email"/>" readonly="readonly"> 
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="新密码" id="hy_pwd" name="query.pwd" data-placement="bottom"> 
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control"  placeholder="确认密码" id="hy_c_pwd"  name="query.confirmpwd"  data-placement="bottom">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>

          <div class="row">
            <div class="col-xs-8">
            </div><!-- /.col -->
            <div class="col-xs-4" >
              <button type="submit"  class="btn btn-primary btn-block btn-flat">确认</button>
            </div><!-- /.col -->

          </div>
        </form>


      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="<%=basePath%>plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="<%=basePath%>plugins/iCheck/icheck.min.js"></script>
     <!-- jQueryValidate -->
    <script src="plugins/jQueryValidate/jquery.validate.min.js"></script>  
    <script src="plugins/jQueryValidate/validate.expand.js"></script>     
    <script type="text/javascript">
$().ready(function() {
		
		
	    $("#hy_reset_form").validate({
	    	onkeyup:false,
	    		rules: {
		    		 "query.pwd":{
		    			 required:true,
		    			 minlength: 6,
		    			 maxlength:20
		    		 },
		    		 "query.confirmpwd":{
		    			 required:true,
		    			 equalTo:"#hy_pwd"
		    		 }	
	    		},
	    		messages:{
		    		 "query.pwd":{
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
		                    $(element).popover('destroy'); 
		                    $(element).popover({content: $(error).text()}).popover('show');					   
					},	
		
					submitHandler: function(form) {
						 form.submit();
					},
		            unhighlight: function (element, errorClass, validClass) { //验证通过
		                $(element).popover('destroy').removeClass(errorClass);
		            }	    		
    
			    });
			    
			});  
    </script>

  </body>
</html>
