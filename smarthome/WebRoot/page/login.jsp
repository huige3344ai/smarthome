<%@page import="com.smarthome.util.OwnUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message = request.getParameter("message");
if(!OwnUtil.stringIsEmpty(message)){
	message = URLDecoder.decode(message,"UTF-8");
}
request.setAttribute("messages", message);
%>


<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    
    <title>智能家居控制系统登录</title>
	<link rel="shortcut icon" href="dist/img/favicon.ico">
	<link rel="Bookmark" href="dist/img/favicon.ico">    
    
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



	
  </head>
  
<body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
       <a href="page/login.jsp"><b>SMARTHOME</b>C</a> 
      </div><!-- /.login-logo -->
      <div class="login-box-body">
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
						请输入您的帐号和密码.
	            	</p>            	
            	</s:else>         	
        
        <form action="page/loginAction_login.action" method="post" >
          <div class="form-group has-feedback">
            <input  class="form-control" name="query.userName" placeholder="用户名" required> 
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" name="query.pwd" placeholder="密码" required>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox" name="query.autologin"> 两周内自动登录
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4" >
              <button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
            </div><!-- /.col -->
            
            <div class="col-xs-6" >
				<select class="form-control" name="query.isAdmin"> 
				  <option value="0" >普通用户</option>
				  <option value="1" >管理员</option>
				</select>             
            </div><!-- /.col -->
         
          </div>
        </form>
        <a href="page/returnpwd.jsp" style="color: red">找回密码</a><br>
        <a href="page/register.jsp" class="text-center">注册新用户</a>

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
