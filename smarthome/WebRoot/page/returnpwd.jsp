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



	
  </head>
  
<body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
       <a href="page/login.jsp"><b>SMARTHOME</b>C</a> 
      </div><!-- /.login-logo -->
      <div class="login-box-body">
      			<s:if test="message!=null">
	            	<p class="login-box-msg" style="color: red">
						<s:property value="message"/>
	            	</p>            	
      			</s:if>
      			<s:else>
	            	<p class="login-box-msg" style="color: red">
						请输入需要找回密码的邮箱后，点击发送.
	            	</p>            	
      			</s:else>
        
        <form action="page/loginAction_checkCode.action" id="hy_returnpwd" method="post" >
          <div class="form-group has-feedback">
            <input  type="email" id="email" class="form-control" name="query.email" placeholder="邮箱" required> 
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
          	<div class="input-group input-group-sm">
          		<input type="text" class="form-control" name="query.emailVer" placeholder="验证码" data-inputmask="'mask': 'AAAAAA'" >
	            <span class="input-group-btn"><button type="button" id="send_email" class="btn btn-info btn-flat">发送</button></span>
          	</div>
          </div>

          <div class="row">
            <div class="col-xs-8">
            </div><!-- /.col -->
            <div class="col-xs-4" >
              <button type="submit" class="btn btn-primary btn-block btn-flat">确认</button>
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
    <script>
      $(function () {
 
        $('#send_email').click(function(){
        	if($('#email').val()!=''){
        		$('.login-box-msg').text('请输入需要找回密码的邮箱后，点击发送.');
	        	$.ajax({
	        	url: "page/loginAction_sendValidate.action",
	        	async : false,
	        	data:{"query.email":$("#email").val()},
	        	tyep:"post",
	        	dataType:"json",
	        	success:function(msg){
	        		if(msg){
	        			$('.login-box-msg').text('您的验证码已经发送到你绑定你的邮箱,请你在30分钟内尝试。');
	        			$(':submit').removeAttr('disabled');
	        		}else{
	        			$('.login-box-msg').text('您输入的邮箱不存在，请核实之后再继续尝试。');
	        			$(':submit').attr('disabled','disabled');
	        		}
	        	},
	        	error:function(){
	        		alret("网络异常发送失败，请重试。");
	        	},
	        	
	        	});
	        	
        	}else
        	$('.login-box-msg').text('邮箱不能为空呢。');
        
        });
        
        
      });
    </script>
  </body>
</html>
