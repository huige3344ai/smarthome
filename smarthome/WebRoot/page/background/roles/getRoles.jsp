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
	<link href="//cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">
	<!-- Ionicons -->
	<link href="//cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"> 
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
	<!-- base css -->
    <link rel="stylesheet" href="dist/css/base.css">

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
					首页 <small>个人中心</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="page/background/index.jsp"><i
							class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">权限角色管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Main row -->
				<div class="row">
			 		<!-- 查询表单 -->
			         <form id="pagerForm" name="pagerForm"
			            action="page/rolesAction_getRoles.action" method="post">
			            <input type="hidden" name="pageNum" id="pageNum" value="1" />
			            <input type="hidden" name="parentName" id="parentName"
			                value="<s:property value='parentName'/>" />
			            <input type="hidden" name="query.roleName" value="${query.roleName}">
			            <input type="hidden" name="numPerPage" id="numPerPage"
			                value="<s:property value='page.numPerPage'/>" />
			            <!-- 可选，每页显示多少行 -->
			        </form>
				    <div class="box">
						    <div class="box-header">
						      <h3 class="box-title">所有角色</h3>
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
								    <button  type="button" class="btn btn-success" data-toggle="modal"  id="hy_add_button" >
									                <i class="glyphicon glyphicon-edit icon-white"></i>添加
									</button>
							    </label>
								      
							    </div>
							    </div>
							    
							    <div class="col-md-6">
								    <div class="dataTables_filter" id="DataTables_Table_0_filter">
								        <form action="page/rolesAction_getRoles.action" method="post">
								             <input type="hidden" name="pageNum" id="pageNum" value="1"/>
									    	 <label>角色名: <input type="text"  name="query.roleName" value="${query.roleName}"></label>									   		
									   		 <input type="submit" value="搜索" id="search" class="btn btn-default" style="margin: 4px;"/>
								        </form>
								    </div>
							    </div>
							    
							    </div>
							    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
							    <thead>
							    <tr role="row">
							    	<th>序号</th>
							    	<th>角色名称</th>
							    	<th>角色代码</th>
							    	<th>添加日期</th>
							    	<th>最新修改日期</th>
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
									        <s:if test="#p.roleName != ''">${p.roleName}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center">
									        <s:if test="#p.role != ''">${p.role}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.recordTime != ''">${p.recordTime}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.exchangeTime != ''">${p.exchangeTime}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
								            <button  type="button" class="btn btn-info"  onclick="return fillData(${p.id})" data-toggle="modal" data-target="#updateModal">
								                <i class="glyphicon glyphicon-edit icon-white"></i>编辑
								            </button>            	            
								            <button class="btn btn-danger" onclick="deleteOp('page/rolesAction_deleteRoles.action',${p.id},'是否需要删除,将清空所有权限信息,角色');">
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

		<!-- add modal -->
		<div class="modal" id="addModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h4 class="modal-title">添加角色</h4>
                  </div>
                  <form  class="form-horizontal" id="hy_add_form"><!-- 表单式样 -->
	                  <div class="modal-body">
	                    	<div class="form-group">
	                    		<label for="hy_roleName" class="col-sm-3 control-label">*角色名称</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.roleName" id="hy_roleName"  data-placement="top"   placeholder="角色名称" >
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_role" class="col-sm-3 control-label">*角色代号</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.role" id="hy_role"  data-placement="top"   placeholder="角色代号">
	                    		</div>
	                    	</div>

	                    	<div class="form-group">
	                    		<label class="col-sm-3 control-label">权限选择</label>
								<div  class="row">
									<div class="col-sm-8">
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_role_user">
											<label for="module">用户权限:</label><br/>
										</div>
							               <br>										
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_role_device">
											<label for="module">设备权限:</label><br/>
										</div>
											<br>
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_role_home">
											<label for="module">住所权限:</label><br/>
										</div>
										
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_role_power">
											<label for="module">授权权限:</label><br/>
										</div>
										
									</div>
									
								</div><!-- /.row-addroles -->
	                    	</div>
	                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button  type="submit" id="hy_save_roles" class="btn btn-primary">保存</button>
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
                    <h4 class="modal-title">添加角色</h4>
                  </div>
                  <form  class="form-horizontal" id="hy_update_form"><!-- 表单式样 -->
	                  <input type="hidden"  id="hy_update_id"  name="model.id">
	                  <div class="modal-body">
	                    	<div class="form-group">
	                    		<label for="hy_roleName" class="col-sm-3 control-label">*角色名称</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.roleName" id="hy_update_roleName"  data-placement="top"   placeholder="角色名称" >
	                    		</div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="hy_role" class="col-sm-3 control-label">*角色代号</label>
	                    		<div class="col-sm-9">
		                    		<input type="text" class="form-control" name="model.role" id="hy_update_role"  data-placement="top"   placeholder="角色代号">
	                    		</div>
	                    	</div>

	                    	<div class="form-group">
	                    		<label class="col-sm-3 control-label">权限选择</label>
								<div  class="row">
									<div class="col-sm-8">
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_update_role_user">
											<label for="module">用户权限:</label><br/>
										</div>
							               <br>										
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_update_role_device">
											<label for="module">设备权限:</label><br/>
										</div>
											<br>
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_update_role_home">
											<label for="module">住所权限:</label><br/>
										</div>
										
										<div class="col-xs-12 col-sm-12 col-md-12" id="hy_update_role_power">
											<label for="module">授权权限:</label><br/>
										</div>
										
									</div>
									
								</div><!-- /.row-addroles -->
	                    	</div>
	                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button  type="submit" id="hy_update_roles" class="btn btn-primary">保存</button>
                  </div>
                  </form>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
           </div>

		<!-- ./update modal -->


	
	</div><!-- ./wrapper -->
	
    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="dist/js/jquery.cookie.js"></script>			
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
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
	<script src="dist/js/role.js"></script>
	</body>
</html>


	<s:if test="page!=null">
		<script type="text/javascript">
		    mv.app.loadPage(<s:property value="page.pageNum"/>,<s:property value="page.totalPage"/>);
		</script>
	</s:if>	

