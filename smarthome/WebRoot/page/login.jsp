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
    
    <title>智能家居控制系统登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>智能家居控制系统登录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
	<link rel="stylesheet"  href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="dist/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="dist/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">



	
  </head>
  
<body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href=""><b>SMARTHOME</b>C</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        		
            	<s:if test="#request.message!=null">
            	<p class="login-box-msg" style="color: red;">
            		<s:property value="#request.message"/>
            	</p>	            		
            	</s:if>      		
            	<s:else>
            	<p class="login-box-msg">
					请输入您的帐号和密码.
            	</p>            	
            	</s:else>         	
        
        <form action="page/loginAction_login.action" method="post" >
          <div class="form-group has-feedback">
            <input  class="form-control" name="query.userName" placeholder="用户名">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" name="query.pwd" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox" name="query.autologin" value="#request.query.autologin"> 两周内自动登录
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4" >
              <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div><!-- /.col -->
            
            <div class="col-xs-6" >
				<select class="form-control" name="query.isAdmin"> 
				  <option value="1" >管理员</option>
				  <option value="0" >普通用户</option>
				</select>             
            </div><!-- /.col -->
         
          </div>
        </form>
        <a href="#">I forgot my password</a><br>
        <a href="register.html" class="text-center">Register a new membership</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="<%=basePath%>plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="<%=basePath%>plugins/iCheck/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>
