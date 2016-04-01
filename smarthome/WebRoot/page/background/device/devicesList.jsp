<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

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
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<!-- Font Awesome -->
<link href="//cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">
<!-- Ionicons -->
<link href="//cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"> 
<!-- Select2 -->
<link rel="stylesheet" href="plugins/select2/select2.min.css">
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
<link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.print.css" media="print">

<style type="text/css">
  .error{
	color: red;    
  }
  .popover-content{
  	font-size:12px;
  	color: red;  
  }
  </style>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="/page/background/nav/topnav.jsp"></jsp:include>

		<jsp:include page="/page/background/nav/leftnav.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					首页 <small>所有设备</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="page/background/index.jsp"><i
							class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">所有设备</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
			
				<!-- Main row -->
				<div class="row">
			 		<!-- 查询表单 -->
			         <form id="pagerForm" name="pagerForm"
			            action="devicesActionb_devicesList.action" method="post">
			            <input type="hidden" name="pageNum" id="pageNum" value="1" />
			            <input type="hidden" name="parentName" id="parentName"
			                value="<s:property value='parentName'/>" />
			            <input type="hidden" name="query.userName" value="${query.userName}">
			            <input type="hidden" name="query.deviceName" value="${query.deviceName}">
			            <input type="hidden" name="numPerPage" id="numPerPage"
			                value="<s:property value='page.numPerPage'/>" />
			            <!-- 可选，每页显示多少行 -->
			        </form>
				    <div class="box">
						    <div class="box-header">
						      <h3 class="box-title">所有设备</h3>
						    </div><!-- /.box-header -->
					    <div class="box-body">
						    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
							    <div class="row">
							    <div class="col-md-6">
							    <div id="DataTables_Table_0_length" class="dataTables_length">
							    <label>
								    <select size="1" name="perNum" id="perNum" onchange="javascript:mv.app.pageSumSet();">
									      <option value="10">
									          10
									      </option>
									      <s:if test="page.numPerPage == 30">
									          <option value="30" selected="selected">
									              30
									          </option>
									          <option value="50">
									              50
									          </option>
									      </s:if>
									      <s:else>
									          <option value="30">
									              30
									          </option>
									          <s:if test="page.numPerPage == 50">
									              <option value="50" selected="selected">
									                  50
									              </option>
									          </s:if>
									          <s:else>
									              <option value="50">
									                  50
									              </option>
									          </s:else>
									      </s:else>
								    </select> 每页
							    </label>
							    <label style="margin-left: 40px;">    
								    <button  type="button" class="btn btn-success" data-toggle="modal" id="hy_add_button">
									                <i class="glyphicon glyphicon-edit icon-white"></i>添加
									</button>
							    </label>
								      
							    </div>
							    </div>
							    
							    <div class="col-md-6">
								    <div class="dataTables_filter" id="DataTables_Table_0_filter">
								        <form action="devicesActionb_devicesList.action" method="post">
								             <input type="hidden" name="pageNum" id="pageNum" value="1"/>
									    	 <label>所有者用户名: <input type="text"  name="query.userName" value="${query.userName}"></label>
									    	 <label>设备名称: <input type="text"  name="query.deviceName" value="${query.deviceName}"></label>
									   		 <input type="submit" value="搜索" id="search" class="btn btn-default" style="margin: 4px;"/>
								        </form>
								    </div>
							    </div>
							    
							    </div>
							    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
							    <thead>
							    <tr role="row">
							    	<th>序号</th>
							    	<th>设备名称</th>
							    	<th>设备状态</th>
							    	<th>设备所有者</th>
							    	<th>设备添加时间</th>
							    	<th>操作</th>
							    	
							    </tr>
							    </thead>
							    
							    <tbody role="alert" aria-live="polite" aria-relevant="all">
								<s:iterator value="page" var="p" status="s">
								 <tr role="row" class="even">
								        <td class="sorting_1">
									        <s:if test="#p.id != ''">${p.id}</s:if>
									        <s:else >未知</s:else>
								        </td>
								        <td class="center">
									        <s:if test="#p.deviceName != ''">${p.deviceName}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.status != ''">${p.status}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.userName != ''">${p.userName}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.recordTime != ''">${p.recordTime}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
											
								            <button  type="button" class="btn btn-info"  onclick="return fillData(${p.id})" data-toggle="modal" data-target="#updateModal">
								                <i class="glyphicon glyphicon-edit icon-white"></i>编辑
								            </button>            	            
								            <button class="btn btn-danger" onclick="deleteOp('devicesActionb_deleteDevices.action',${p.id},'是否需要删除该设备');">
								                <i class="glyphicon glyphicon-trash icon-white"></i>删除
								            </button>            
								        </td>
								 </tr>		
								 
								</s:iterator>    
							   
							    </tbody>
							    </table>
								    <div class="row">
									    <div class="col-md-5">
									  	  <div class="dataTables_info" id="DataTables_Table_0_info">
										  	  <s:if test="page!=null">
							                   	现在是第<span class="STYLE3"><s:property value="page.pageNum" />
							                    </span>页，一共有<span class="STYLE4"><s:property value="page.totalPage" />
							                    </span>页 共有 <s:property value="page.totalCount" />条信息
								              </s:if>      		  	  
										  </div>
									    </div>
									    <div class="col-md-7">
										    <div class="dataTables_paginate paging_simple_numbers">
												<ul id="page" class="pagination">
												</ul>			    
										    </div>
									    </div>
									</div><!-- /.row -->
									
						    </div><!-- /.dataTables_wrapper -->
					    </div><!-- /.box-body -->
				    </div><!-- /.box -->
				   
				
				</div><!-- Main row -->
			
			</section><!-- /.content -->



		</div>
		<!-- /.content-wrapper -->

		<!-- add modal -->
		<div class="modal" id="addModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">添加设备</h4>
                  </div>
                  <form  class="form-horizontal" id="hy_add_form"><!-- 表单式样 -->
	                  <div class="modal-body">
	                    	<div class="form-group">
	                    		<label for="hy_deviceNum" class="col-sm-3 control-label">设备编号</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.deviceNum" id="hy_deviceNum" data-placement="top" placeholder="设备编号">
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_deviceName" class="col-sm-3 control-label">*设备名称</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.deviceName" id="hy_deviceName" data-placement="top" placeholder="设备名称" >
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_userId" class="col-sm-3 control-label">*设备所有人</label>
	                    		<div class="col-sm-9">
					               <select class="form-control select2" id="hy_userId" name="model.userId"  data-placement="top" style="width: 100%;">
				                  	<option value="0">请选择</option>
				                    </select>
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_homeId"  class="col-sm-3 control-label">*设备所在地</label>
	                    		<div class="col-sm-9">
					               <select class="form-control select2" id="hy_homeId"  name="model.homeId"  data-placement="top" style="width: 100%;">
				                    </select>	                    		
	                    		</div>
	                    	</div>
	                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" id="hy_save_devices"  class="btn btn-primary">保存</button>
                  </div>
                  </form>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
           </div>

		<!-- ./add modal -->
		
		<!-- update modal -->
		<div class="modal" id="updateModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">更新设备</h4>
                  </div>
                  <form  class="form-horizontal" id="hy_update_form"><!-- 表单式样 -->
                  		<input hidden="hidden" name="query.id" id="hy_up_id">
	                  <div class="modal-body">
	                    	<div class="form-group">
	                    		<label for="hy_deviceNum" class="col-sm-3 control-label">设备编号</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.deviceNum" id="hy_up_deviceNum" data-placement="top" placeholder="设备编号">
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_deviceName" class="col-sm-3 control-label">*设备名称</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.deviceName" id="hy_up_deviceName" data-placement="top" placeholder="设备名称" >
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_deviceName" class="col-sm-3 control-label">*设备状态</label>
	                    		<div class="col-sm-9">
	                    			<select class="form-control" id="hy_up_status" name="model.status" style="width: 100%;">
	                    				<option value="1">启动：1</option>
	                    				<option value="0">关闭：0</option>
	                    			</select>
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_userId" class="col-sm-3 control-label">*设备所有人</label>
	                    		<div class="col-sm-9">
					               <select class="form-control select2" id="hy_up_userId" data-placement="top" name="model.userId" style="width: 100%;">
				                  	<option value="0">请选择</option>
				                    </select>
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_homeId"  class="col-sm-3 control-label">*设备所在地</label>
	                    		<div class="col-sm-9">
					               <select class="form-control select2" id="hy_up_homeId"  data-placement="top" name="model.homeId" style="width: 100%;">
				                    </select>	                    		
	                    		</div>
	                    	</div>
	                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" id="hy_update_devices"  class="btn btn-primary">保存</button>
                  </div>
                  </form>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
           </div>

		<!-- ./update modal -->



		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 2.3.0
			</div>
			<strong>Copyright &copy; 2014-2015 <a>Almsaeed Studio</a>.
			</strong> All rights reserved.
		</footer>
		
		



	</div><!-- ./wrapper -->
	

   <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="dist/js/jquery.cookie.js"></script>		
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- Select2 -->
    <script src="plugins/select2/select2.full.min.js"></script>
    <!-- DataTables -->
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- InputMask -->
    <script src="plugins/input-mask/jquery.inputmask.js"></script>
    <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>    
    <!-- SlimScroll -->
    <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- jQueryValidate -->
    <script src="plugins/jQueryValidate/jquery.validate.min.js"></script>      
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
	<!-- base.js -->
	<script src="dist/js/base.js"></script>
	<script src="dist/js/alldevices_js.js"></script>

	</body>
</html>

	<s:if test="page!=null">
		<script type="text/javascript">
		    mv.app.loadPage(<s:property value="page.pageNum"/>,<s:property value="page.totalPage"/>);
		</script>
	</s:if>	
