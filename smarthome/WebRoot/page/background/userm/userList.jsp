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
    <!-- Select2 -->
    <link rel="stylesheet" href="plugins/select2/select2.min.css">   
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- base css -->
    <link rel="stylesheet" href="dist/css/base.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

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
					首页 <small>用户管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="page/background/index.jsp"><i
							class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">用户管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Main row -->
				<div class="row">
			 		<!-- 查询表单 -->
			         <form id="pagerForm" name="pagerForm"
			            action="page/userAction_userList.action" method="post">
			            <input type="hidden" name="pageNum" id="pageNum" value="1" />
			            <input type="hidden" name="parentName" id="parentName"
			                value="<s:property value='parentName'/>" />
			            <input type="hidden" name="query.userName" value="${query.userName}">
			            <input type="hidden" name="query.email" value="${query.email}">
			            <input type="hidden" name="query.phone" value="${query.phone}">
			            <input type="hidden" name="numPerPage" id="numPerPage"
			                value="<s:property value='page.numPerPage'/>" />
			            <!-- 可选，每页显示多少行 -->
			        </form>
				    <div class="box">
						    <div class="box-header">
						      <h3 class="box-title">所有用户</h3>
						    </div><!-- /.box-header -->
					    <div class="box-body">
						    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
							    <div class="row">
							    <div class="col-md-4">
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
								    <button  type="button" class="btn btn-success" data-toggle="modal" id="hy_add_button" data-target="#addModal">
									                <i class="glyphicon glyphicon-edit icon-white"></i>添加
									</button>
							    </label>
								      
							    </div>
							    </div>
							    
							    <div class="col-md-8">
								    <div class="dataTables_filter" id="DataTables_Table_0_filter">
								        <form action="page/userAction_userList.action" method="post">
								             <input type="hidden" name="pageNum" id="pageNum" value="1"/>
									    	 <label>用户名: <input type="text"  name="query.userName" value="${query.userName}"></label>
									    	 <label>手机号码: <input type="text"  name="query.phone" value="${query.phone}"></label>
									    	 <label>邮箱: <input type="text"  name="query.email" value="${query.email}"></label>
									   		 <input type="submit" value="搜索" id="search" class="btn btn-default" style="margin: 4px;"/>
								        </form>
								    </div>
							    </div>
							    
							    </div>
							    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
							    <thead>
							    <tr role="row">
							    	<th>序号</th>
							    	<th>用户名</th>
							    	<th>用户状态</th>
							    	<th>手机号码</th>
							    	<th>邮箱</th>
							    	<th>注册时间</th>
							    	<th>角色</th>
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
									        <s:if test="#p.userName != ''">${p.userName}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.status == 1">
									        	正常
									        </s:if>
									        <s:elseif test="#p.status == 0">
									        	禁用
									        </s:elseif>									        
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.phone != ''&&#p.phone != null">${p.phone}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>
								        <td class="center ">
									        <s:if test="#p.email != ''&&#p.email != null">${p.email}</s:if> 
									        <s:else >未知</s:else>	        
								        </td>

								        <td class="center ">
								        
									        <s:if test="#p.registerTime !=null">
									        	<s:date name="#p.registerTime" format="yyyy-MM-dd HH:mm"/>
									        </s:if> 
									        <s:else >未知 </s:else>	        
								        </td>
								        
								        <td class="center ">
									        <s:if test="#p.roles != null">
												<s:iterator value="#p.roles" var="var"> 
												    ${var.roleName}&nbsp;
												  </s:iterator> 									        
									        </s:if> 
									        <s:else >无</s:else>	        
								        </td>	
								        <td class="center">
								        	<s:if test="#p.isAdmin!=null&&#p.isAdmin==1">
									            <button  type="button" class="btn btn-success"  onclick="setRole(${p.id})" data-toggle="modal" >
									                <i class="glyphicon glyphicon-pencil"></i>&nbsp授权
									            </button>
								        	</s:if>
								            <button  type="button" class="btn btn-info"  onclick="fillData(${p.id})" data-toggle="modal" data-target="#updateModal">
								                <i class="glyphicon glyphicon-edit icon-white"></i>&nbsp编辑
								            </button>   
								            <s:if test="#p.status == 1">
									            <button  type="button" class="btn btn-warning"  onclick="updateOp('page/userAction_forbiddenUser.action',${p.id},'0','是否需要禁用该用户');" data-toggle="modal">
									                <i class="glyphicon glyphicon-stop"></i>&nbsp禁用
									            </button>            	            
								            </s:if> 
								            <s:else>
									            <button  type="button" class="btn btn-warning"  onclick="updateOp('page/userAction_forbiddenUser.action',${p.id},'1','是否需要恢复该用户');" data-toggle="modal" >
									                <i class="glyphicon glyphicon-play"></i>&nbsp启用
									            </button>            	            
								            </s:else>        	            
								            <button class="btn btn-danger" onclick="deleteOp('page/userAction_deleteUser.action',${p.id},'是否需要删除该用户');">
								                <i class="fa fa-user-times"></i>&nbsp删除
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
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">添加用户</h4>
                  </div>
                  
	              <div class="modal-body">
					        <form action="" id="hy_register_form" method="post">
					          <input type="hidden" id="nowDate" >
					          <div class="form-group has-feedback">
					            <input  class="form-control"  name="model.userName" id="add_userName" placeholder="用户名" data-placement="bottom">
					            <span class="glyphicon glyphicon-user form-control-feedback"></span>
					          </div>
					          <div class="form-group has-feedback">
					            <input type="email" class="form-control"  name="model.email" id="add_email" placeholder="邮箱" data-placement="bottom">
					            <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
					          </div>
					          
					          <div class="form-group has-feedback">
					            <input type="text" class="form-control"  name="model.phone" id="add_phone" placeholder="手机号码" data-placement="bottom">
					         	<span class="glyphicon glyphicon-earphone  form-control-feedback"></span>
					         </div>
					         
					         <div class="form-group has-feedback">
					            <input type="text" id="datemask" class="form-control"  name="model.birthday" placeholder="出生年月日" data-placement="bottom">
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
					         <div class="form-group has-feedback">
								<select name="model.isAdmin" >
									<option value="0">普通用户</option>
									<option value="1">管理员</option>
								</select>
					         </div>					          
			                 <div class="modal-footer">
			                   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                   <button type="submit" id="hy_save_user"  class="btn btn-primary">保存</button>
			                 </div>					
					  </form><!-- /.form -->
					</div><!-- /.modal-body -->
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
                    <h4 class="modal-title">修改用户信息</h4>
                  </div>
                  
	              <div class="modal-body">
					        <form action="" id="hy_update_form" method="post">
					          <input type="hidden" name="query.id" id="user_ud">
					          <div class="form-group has-feedback">
					            <input  class="form-control" id="up_userName" readonly="readonly" name="model.userName" placeholder="用户名" data-placement="bottom">
					            <span class="glyphicon glyphicon-user form-control-feedback"></span>
					          </div>
					          <div class="form-group has-feedback">
					            <input type="email" class="form-control" id="up_email" name="model.email" placeholder="邮箱" data-placement="bottom">
					            <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
					          </div>
					          
					          <div class="form-group has-feedback">
					            <input type="text" class="form-control" id="up_phone" name="model.phone"  placeholder="手机号码" data-placement="bottom">
					         	<span class="glyphicon glyphicon-earphone  form-control-feedback"></span>
					         </div>
					         
					         <div class="form-group has-feedback">
					            <input type="text" id="up_datemask" class="form-control" name="model.birthday" placeholder="出生年月日" data-placement="bottom">
					         	<span class="glyphicon glyphicon-gift  form-control-feedback" ></span>
					         </div>
					          <div class="form-group has-feedback">
					            <input type="password" class="form-control" placeholder="(不更新密码无需填写)更新密码" id="hy_up_pwd" name="model.pwd" data-placement="bottom"> 
					            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
					          </div>
					          <div class="form-group has-feedback">
					            <input type="password" class="form-control" placeholder="确认更新密码" id="hy_up_confirm_pwd"  name="query.confirmpwd" data-placement="bottom">
					            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
					          </div>
					         <div class="form-group has-feedback">
								<select name="model.isAdmin" id="isAdmin">
									<option value="0">普通用户</option>
									<option value="1">管理员</option>
								</select>
					         </div>					          
			                 <div class="modal-footer">
			                   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                   <button type="submit" id="hy_updte_user"  class="btn btn-primary">保存</button>
			                 </div>					
					  </form><!-- /.form -->
					</div><!-- /.modal-body -->
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
           </div>

		<!-- ./update modal -->
		
		<!-- role modal -->
		<div class="modal" id="roleModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">权限设置</h4>
                  </div>
                  
	              <div class="modal-body">
					        <form action="" id="hy_role_form" method="post">
					          <input type="hidden" name="query.userId" id="roles_user_ud">
				                  <div class="form-group">
				                    <label>权限选择</label>
				                    <select class="form-control select2" id="roles_select2" name="query.rids" multiple="multiple" data-placeholder="请选择" style="width: 100%;">
				                    </select>
				                  </div><!-- /.form-group -->
			                 <div class="modal-footer">
			                   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                   <button type="submit" id="hy_updte_user"  class="btn btn-primary">保存</button>
			                 </div>					
					  </form><!-- /.form -->
					</div><!-- /.modal-body -->
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
           </div>

		<!-- ./role modal -->


	</div>
	<!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="dist/js/jquery.cookie.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- Select2 -->
    <script src="plugins/select2/select2.full.min.js"></script>    
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- InputMask -->
    <script src="plugins/input-mask/jquery.inputmask.js"></script>
    <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>    
    <!-- jQueryValidate -->
    <script src="plugins/jQueryValidate/jquery.validate.min.js"></script>  
    <script src="plugins/jQueryValidate/validate.expand.js"></script>  	
	<script src="dist/js/add_user_form.js"></script>
	<!-- base.js -->
	<script src="dist/js/base.js"></script>
	</body>
    <script type="text/javascript">
    $(function(){
       	//date	
        $("#datemask").inputmask("yyyy-mm-dd");
        //phone Euro	
        $("#add_phone").inputmask("[99999999999]");
        
    });
	        
    </script>	
</html>

	<s:if test="page!=null">
		<script type="text/javascript">
		    mv.app.loadPage(<s:property value="page.pageNum"/>,<s:property value="page.totalPage"/>);
		</script>
	</s:if>	