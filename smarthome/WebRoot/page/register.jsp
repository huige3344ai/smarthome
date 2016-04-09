<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户注册</title>
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
    <!-- base.css -->
    <link rel="stylesheet" href="dist/css/base.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style type="text/css">
    .error{
		color: red;    
    }
    .popover-content{
    	font-size:12px;
    	color: red;  
    }
    </style>
  </head>
  <body class="hold-transition register-page">
    <div class="register-box">
      <div class="register-logo">
        <a href="page/login.jsp"><b>SMARTHOME</b>C</a>
      </div>
			
      <div class="register-box-body">
            	<s:if test="#request.message!=null&&#request.message!=''">
	            	<p class="login-box-msg" style="color: red;">
            			<s:property value="#request.message"/>
	            	</p>	            		
            	</s:if> 
            	<s:elseif test="#request.messages&&#request.messages!=''"> 
            		<p class="login-box-msg" style="color: red;">
            			<s:property value="#request.messages"/>
            		</p>		
            	</s:elseif>  	
            	<s:else>
	            	<p class="login-box-msg">
						注册新用户.
	            	</p>            	
            	</s:else>       
        <form action="page/loginAction_registerUser.action" id="hy_register_form" method="post">
          <div class="form-group has-feedback">
            <input  class="form-control" id="userName" name="model.userName" placeholder="用户名" data-placement="bottom">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="email" class="form-control" id="email" name="model.email" placeholder="邮箱" data-placement="bottom">
            <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
          </div>
          
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="phone" name="model.phone"  placeholder="手机号码" data-placement="bottom">
         	<span class="glyphicon glyphicon-earphone  form-control-feedback"></span>
         </div>
         
          <div class="form-group has-feedback">
            <input type="text" id="datemask" class="form-control" name="model.birthday" placeholder="出生年月日" data-placement="bottom">
         	<span class="glyphicon glyphicon-gift  form-control-feedback" ></span>
         </div>
                  
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="密码" id="hy_pwd" name="model.pwd" data-placement="bottom"> 
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="确认密码"  name="query.confirmpwd" data-placement="bottom">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
				<input id="hy_agree" type="checkbox" value="1" name="hy_agree" class="{required:true}" />
				<label id="lterms" for="hy_agree">同意我们的声明</label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">注册</button>
            </div><!-- /.col -->
          </div>
        </form>

        <a href="page/login.jsp" class="text-center">我已经拥有帐号。</a><br/>
          		
        
      </div><!-- /.form-box -->

    </div><!-- /.register-box -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
 	<script src="dist/js/jquery.cookie.js"></script>		
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="plugins/iCheck/icheck.min.js"></script>
  	<!--base.js  -->
    <script src="dist/js/base.js"></script>  
	<!--Moment.js-->
	<script src="//cdn.bootcss.com/moment.js/2.10.2/moment.min.js"></script>    
    <!-- InputMask -->
    <script src="plugins/input-mask/jquery.inputmask.js"></script>
    <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <!-- jQueryValidate -->
    <script src="plugins/jQueryValidate/jquery.validate.min.js"></script>  
    <script src="plugins/jQueryValidate/validate.expand.js"></script>  
    <script src="dist/js/register_form.js"></script>   

     
    
    <script type="text/javascript">
    $(function(){
       	//date	
        $("#datemask").inputmask("yyyy-mm-dd");
        //phone Euro	
        $("#phone").inputmask("[99999999999]");

        
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        }); 
        
        
    });
	        
    </script>
  </body>
</html>