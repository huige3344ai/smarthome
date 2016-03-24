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
<title>智能家居控制后台系统</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
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
<link rel="stylesheet"
	href="plugins/daterangepicker/daterangepicker-bs3.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<!-- fullCalendar 2.2.5-->
<link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.min.css">
<link rel="stylesheet"
	href="plugins/fullcalendar/fullcalendar.print.css" media="print">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<s:include value="nav/topnav.jsp"></s:include>

		<s:include value="nav/leftnav.jsp"></s:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					首页 <small>控制面板</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="page/background/index.jsp"><i
							class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">首页控制面板</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->
				<div class="row">

					<div class="col-lg-4 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-yellow">
							<div class="inner">
								<h3>44</h3>
								<p>User Registrations</p>
							</div>
							<div class="icon">
								<i class="ion ion-person-add"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->

					<div class="col-lg-4 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-green">
							<div class="inner">
								<h3>
									53<sup style="font-size: 20px">%</sup>
								</h3>
								<p>Bounce Rate</p>
							</div>
							<div class="icon">
								<i class="ion ion-stats-bars"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->

					<div class="col-lg-4 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner">
								<h3>65</h3>
								<p>Unique Visitors</p>
							</div>
							<div class="icon">
								<i class="ion ion-pie-graph"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
				</div>
				<!-- /.row -->

				<!-- Main row -->

				<div class="row">

					<!-- calendar  .col -->
					<div class="col-md-10">
						<div class="box box-primary">
							<div class="box-body no-padding">
								
						              <div class="box box-danger">
						                <div class="box-header">
						                  <h3 class="box-title">正在读取数据......</h3>
						                </div>
						                <div class="box-body">
						                
							                <!-- THE CALENDAR -->
							                <div id="calendar"></div>
						                 
						                </div><!-- /.box-body -->
						                <!-- Loading (remove the following to stop the loading)-->
						                <div class="overlay">
						                  <i class="fa fa-refresh fa-spin"></i>
						                </div>
						                <!-- end loading -->
						              </div><!-- /.box -->
								
								
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /. box -->
					</div>
					<!-- /.col -->

					<div class="col-md-2">
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-primary btn-lg"
							data-toggle="modal" data-target="#add_task_modal">添加任务</button>

						<!-- add Modal -->
						<div class="modal fade" id="add_task_modal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">添加任务</h4>
									</div>
									<div class="modal-body">
										<form action="" id="addtaskForm">

											<table class="table">
												<tr>
													<td>
														<div class="form-group">	
														 <label>主题和颜色</label>
										                  <div class="btn-group" style="width: 100%; margin-bottom: 10px;">
										                    <ul class="fc-color-picker" id="color-chooser">
										                      <li><a class="text-aqua" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-blue" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-light-blue" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-teal" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-yellow" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-orange" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-green" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-lime" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-red" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-purple" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-fuchsia" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-muted" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-navy" href="#"><i class="fa fa-square"></i></a></li>
										                    </ul>
										                  </div><!-- /btn-group -->
										                    <input id="hy_task_theme"  type="text"  name="model.theme" class="form-control" placeholder="输入...">
										                  <div class="input-group">
										                    <div class="input-group-btn">
										                      	<input type="hidden" id="hy_task_color" name="model.color"  />
										                    </div><!-- /btn-group -->
										                  </div><!-- /form-group -->
										                </div>		
													</td>
												</tr>
												<tr>
													<td>
														<div class="form-group">
															<label>任务内容</label>
															<textarea class="form-control" name="model.contents" id="hy_task_contents" style="height: 140px" rows="4" placeholder="输入 ..."></textarea>
														</div>
													</td>
												</tr>

												<tr>
													<td>
														<!-- Date and time range -->
														<div class="form-group">
															<label>开始时间-结束时间</label>
															<div class="input-group">
																<div class="input-group-addon">
																	<i class="fa fa-clock-o"></i>
																</div>
																<input type="text" id="reservationtime" name="query.startTimeAndEndtime" class="form-control pull-right" id="reservationtime">
															</div>
															<!-- /.input group -->
														</div> <!-- /.form group -->
													</td>
												</tr>

											</table>

										</form>

									</div>
									<div class="modal-footer">
										<button type="button" id="hy_task_save" class="btn btn-primary">保存</button>
										<button type="button" id="hy_task_cancel" class="btn btn-default" data-dismiss="modal">关闭</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					
						<!-- update Modal -->
						<div class="modal fade" id="update_task_modal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">更新任务</h4>
									</div>
									<div class="modal-body">
										<form action="" id="updatetaskForm">

											<table class="table">
												<tr>
													<td>
														<div class="form-group">	
														 <label>主题和颜色</label>
										                  <div class="btn-group" style="width: 100%; margin-bottom: 10px;">
										                    <ul class="fc-color-picker" id="color-chooser">
										                      <li><a class="text-aqua" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-blue" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-light-blue" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-teal" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-yellow" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-orange" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-green" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-lime" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-red" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-purple" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-fuchsia" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-muted" href="#"><i class="fa fa-square"></i></a></li>
										                      <li><a class="text-navy" href="#"><i class="fa fa-square"></i></a></li>
										                    </ul>
										                  </div><!-- /btn-group -->
										                    <input id="hy_updatetask_theme"  type="text"  name="query.theme" class="form-control" placeholder="输入...">
										                  <div class="input-group">
										                    <div class="input-group-btn">
										                      	<input type="hidden" id="hy_updatetask_color" name="query.color"  />
										                      	<input type="hidden" id="hy_task_id" name="query.id"  />
										                    </div><!-- /btn-group -->
										                  </div><!-- /form-group -->
										                </div>		
													</td>
												</tr>
												<tr>
													<td>
														<div class="form-group">
															<label>任务内容</label>
															<textarea class="form-control" name="query.content" id="hy_updatetask_contents" style="height: 140px" rows="4" placeholder="输入 ..."></textarea>
														</div>
													</td>
												</tr>

												<tr>
													<td>
														<!-- Date and time range -->
														<div class="form-group">
															<label>开始时间-结束时间</label>
															<div class="input-group">
																<div class="input-group-addon">
																	<i class="fa fa-clock-o"></i>
																</div>
																<input type="text" id="update_reservationtime" name="query.startTimeAndEndtime" class="form-control pull-right" >
															</div>
															<!-- /.input group -->
														</div> <!-- /.form group -->
													</td>
												</tr>

											</table>

										</form>

									</div>
									<div class="modal-footer">
										<button type="button" id="hy_task_update" class="btn btn-primary">保存</button>
										<button type="button" id="hy_task_delete" class="btn btn-danger  glyphicon glyphicon-trash"> 删除</button>
										<button type="button"  class="btn btn-default" data-dismiss="modal">关闭</button>
									</div>
								</div>
							</div>
						</div>
					




				</div>
				<!-- /.row -->

			</section>
			<!-- /.content -->


		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 2.3.0
			</div>
			<strong>Copyright &copy; 2014-2015 <a>Almsaeed Studio</a>.
			</strong> All rights reserved.
		</footer>



	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.1.4 -->
	<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
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
	<script
		src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>
	<!-- fullCalendar 2.2.5 -->
	<script src="plugins/fullcalendar/fullcalendar.min.js"></script>
	<!-- base.js -->
	<script src="dist/js/base.js"></script>
	<script src="dist/js/back_index.js"></script>
	</body>
</html>
												