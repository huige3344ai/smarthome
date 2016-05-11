<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>智能家居控制系统</title>
<link rel="shortcut icon" href="dist/img/favicon.ico">
<link rel="Bookmark" href="dist/img/favicon.ico"> 
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link href="//cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">
<!-- Ionicons -->
<link href="//cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"> 
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet" href="plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
<!-- Daterange picker -->
<link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<!-- base css -->
<link rel="stylesheet" href="dist/css/base.css">
<link rel="stylesheet" href="dist/css/style.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		
		<jsp:include page="/page/foreground/nav/topnav.jsp"></jsp:include>

		<jsp:include page="/page/foreground/nav/leftnav.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					首页 <small>个人中心</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="page/foreground/device/myDevices.jsp"><i
							class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">个人中心</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
			
				<!-- Main row -->
			<div class="row">
			
		<div class="col-md-3">
			
              <!-- Profile Image -->
              <div class="box box-primary">
                <div class="box-body box-profile">
                  <img class="profile-user-img img-responsive img-circle" src="${session.user.logoImage}" alt="User profile picture">
                  <h3 class="profile-username text-center">${session.user.userName}</h3>
                  <p class="text-muted text-center">
	                  <s:if test="#session.user.isAdmin==1">
	                  	我是管理员
	                  </s:if>
	                  <s:else>
	                  	我是普通人
	                  </s:else>
                  </p>
                  <button class="btn btn-primary btn-block" data-target="#picModal" data-toggle="modal"><b>上传我的头像</b></button>
                </div><!-- /.box-body -->
              </div><!-- /.box -->

              <!-- About Me Box -->
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">个人信息</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <strong><i class="fa fa-book margin-r-5"></i>出生日期</strong>
                  <p class="text-muted">
                    ${session.user.birthday}
                  </p>

                  <hr>

                  <strong><i class="fa fa-plug margin-r-5"></i>拥有的设备</strong>
                  <p class="text-muted">灯光设备</p>

                  <hr>
                  <strong><i class="fa  fa-moon-o margin-r-5"></i>最佳作息推荐</strong>
                  <p class="text-muted"></p>
                  <p class="text-muted">休息时间：${session.user.recommendRestTime}</p>
                  <p class="text-muted">休息时长：${session.user.recommendWakeTime}</p>

                  <hr>
                  <strong><i class="fa fa-file-text-o margin-r-5"></i> 个人说明</strong>
                  <p>${session.user.note}</p>
                  
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
            <div class="col-md-9">
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#settings" data-toggle="tab">信息设置</a></li>
                </ul>
                
               <div class="tab-content">
                <div class="active tab-pane">
                  <div class="tab-pane" id="settings">
                    <form class="form-horizontal" id="hy_user_from">
					 <input type="hidden" name="query.id" id="user_ud" value="${session.user.id}">
                      <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">我的邮箱</label>
                        <div class="col-sm-10">
            				<input type="email" class="form-control" value="${session.user.email}" id="up_email"  name="model.email" placeholder="邮箱" data-placement="bottom">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">出生日期</label>
                        <div class="col-sm-10">
            				<input type="text" id="up_datemask" class="form-control" name="model.birthday" value="${session.user.birthday }"   placeholder="出生年月日" data-placement="bottom">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">手机号码</label>
                        <div class="col-sm-10">
            				<input type="text" class="form-control" id="up_phone" value="${session.user.phone}"  name="model.phone"  placeholder="手机号码" data-placement="bottom">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">旧密码</label>
                        <div class="col-sm-10">
            				<input type="password" class="form-control" placeholder="旧密码" id="hy_pwd" name="model.pwd" data-placement="bottom"> 
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10">
            				<input type="password" class="form-control" placeholder="新密码" id="hy_new_pwd" name="query.pwd" data-placement="bottom"> 
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10">
           					 <input type="password" class="form-control" id="hy_confirm_pwd" placeholder="确认密码"  name="query.confirmpwd" data-placement="bottom"/>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputExperience" class="col-sm-2 control-label">个人说明</label>
                        <div class="col-sm-10">
                          <textarea class="form-control" name="model.note"  style="height: 180px" id="inputExperience" placeholder="个人说明">${session.user.note}</textarea>
                        </div>
                      </div>

                      <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                          <button type="submit" id="hy_update" class="btn btn-danger">确认修改</button>
                        </div>
                      </div>
                    </form>
                  </div><!-- /.tab-pane -->
                </div><!-- /.tab-content -->
              </div><!-- /.nav-tabs-custom -->
            </div><!-- /.col -->
            
			</div><!-- /row -->	
			
		</div><!-- /.content -->
		</section>
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 2.3.0
			</div>
			<strong>Copyright &copy; 2014-2015 <a>Almsaeed Studio</a>.
			</strong> All rights reserved.
		</footer>
		
		<div class="modal" id="picModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">上传我的头像</h4>
                  </div>
                  <div class="modal-body">
                  
                     <div class="imageBox">
					    <div class="thumbBox"></div>
					    <div class="spinner" style="display: none">Loading...</div>
					  </div>
					 
					  <div class="action"> 
					  	<form  id="hy_pic_form" action="" enctype="multipart/form-data">
					  	
						    <div class="new-contentarea tc" id="select_pic"> <a href="javascript:void(0)" class="upload-img">
						      <label for="upload-file">选择</label>
						      </a>
						      <input type="file"  id="upload-file" />      
						    </div> 
						    
						    <input type="hidden" name="query.base64" id="base64_pic">
					   
					    </form> 
					                   
					    <input type="button" id="btnCrop"  class="Btnsty_peyton" value="裁切"/>
					    <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  />
					    <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" />
					    
					    </div> 
					  <div class="cropped">
					  </div>
                  
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="hy_save_pic" class="btn btn-primary">保存</button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.picModal -->
			


	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.1.4 -->
	<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<!-- jQuery cookie.js -->
	<script src="dist/js/jquery.cookie.js"></script>		
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.5 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- Morris.js charts -->
	<script src="dist/js/raphael-min.js"></script>
	<script src="plugins/morris/morris.min.js"></script>
	<!--Moment.js-->
	<script src="//cdn.bootcss.com/moment.js/2.10.2/moment.min.js"></script>
	<!-- Sparkline -->
	<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
	<!-- jvectormap -->
	<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<!-- jQuery Knob Chart -->
	<script src="plugins/knob/jquery.knob.js"></script>
	<!-- daterangepicker -->
	<script src="plugins/daterangepicker/daterangepicker.js"></script>
	<script src="dist/js/zh_CN.datepicker.js"></script>
	<!-- datepicker -->
	<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>

    <!-- InputMask -->
    <script src="plugins/input-mask/jquery.inputmask.js"></script>
    <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<!-- jQueryValidate -->
    <script src="plugins/jQueryValidate/jquery.validate.min.js"></script>    
    <script src="plugins/jQueryValidate/validate.expand.js"></script>    
	<!-- base.js -->
	<script src="dist/js/base.js"></script>
	<script src="dist/js/cropbox.js"></script>
	<script src="dist/js/personcenter.js"></script>
	<script type="text/javascript">

        
        
		$(window).load(function() {
		
			$.setValidate("#hy_user_from","更新","page/userAction_updateUser.action");

		
			var options =
			{
				thumbBox: '.thumbBox',
				spinner: '.spinner',
				imgSrc: 'dist/img/user4-128x128.jpg'
			}
			var cropper = $('.imageBox').cropbox(options);
			$('#upload-file').on('change', function(){
				var reader = new FileReader();
				reader.onload = function(e) {
					options.imgSrc = e.target.result;
					cropper = $('.imageBox').cropbox(options);
				}
				reader.readAsDataURL(this.files[0]);
				this.files = [];
			})
			$('#btnCrop').on('click', function(){
				var img = cropper.getDataURL();
				$('.cropped').html('');			
				$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
				$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
				$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
				$("#base64_pic").val(img);
				
			})
			$('#btnZoomIn').on('click', function(){
				cropper.zoomIn();
			})
			$('#btnZoomOut').on('click', function(){
				cropper.zoomOut();
			})
			
			judePicType('#hy_pic_form');
			setClosedModal('#picModal');
			
			$('#hy_save_pic').click(function(){
				 var form = new FormData($('#hy_pic_form')[0])
				$.ajax({
				  type: 'POST',
				  url: "page/userAction_uploadPic.action",
				  data: form,
				  success: function(json){
				  	status='1';
				  },
                  processData:false,
                  contentType:false,				  
				  error:function(json){
				  	alert(json)
				  	status='0';
				  },
				});
			
			});
			
			
		});       
	
	</script>
	</body>
</html>