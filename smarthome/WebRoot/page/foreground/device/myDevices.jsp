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
	<!-- css -->
	<style type="text/css">
	.btn-off{
		background: url("dist/img/light_off.png") no-repeat center;
	}
	

	.btn-on{
		background: url("dist/img/light_on.png") no-repeat center;
	}
	
	.btn-off:hover{
		background: url("dist/img/light_off.png") no-repeat center;
	}
	
	.btn-on:hover{
		background: url("dist/img/light_on.png") no-repeat center;
	}
	
	.btn-off:disabled{
		background: url("dist/img/light_off.png") no-repeat center;
	
	}

	</style>
	<!--  -->
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
					首页 <small>我的设备</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="page/foreground/index.jsp"><i
							class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">我的设备</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Main row -->
				<div class="row">
			 		<!-- 查询表单 -->
			         <form id="pagerForm" name="pagerForm"
			            action="devicesAction_myDevices.action" method="post">
			            <input type="hidden" name="pageNum" id="pageNum" value="1" />
			            <input type="hidden" name="parentName" id="parentName" value="<s:property value='parentName'/>" />
			            <input type="hidden" name="query.homeId" id="hy_form_homeId" value="${query.homeId}">
			            <input type="hidden" name="numPerPage" id="numPerPage" value="<s:property value='page.numPerPage'/>" />
			            <!-- 可选，每页显示多少行 -->
			        </form>
				    <div class="box">
					    <div class="box-header">
					      <h3 class="box-title">所有设备</h3>
					    </div><!-- /.box-header -->
					    
					    <div class="box-body">
						    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
							    <div class="row">
							    
							    <div class="col-xs-4  col-sm-4 col-md-2">
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
									      
								    </div>
							    </div>

							    
							    <div class="col-xs-8 col-sm-8 col-md-4  col-md-offset-2">
								    <div id="DataTables_Table_0_length" class="dataTables_length">
								    		<select size="1"   id="hy_homeId" onchange="javascript:submitSelec();">
						                  		 <option value="0">请选择</option>
						                     </select>
								    </div>

							    </div>
							    
							    						   
							   <div  class="col-xs-4 col-sm-4 col-md-2  col-md-offset-2 col-xs-offset-2" style="margin-bottom: 10px;margin-top: 10px">
								    <div id="DataTables_Table_0_length" class="dataTables_length">
								    	<button class="btn btn-block btn-danger" id="hy_closeAll">关闭所有</button>
							    	</div>
							    </div>
							     							   
							    </div><!-- ./box-body -->
							    
							    
							    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
							    
							    <tbody role="alert" aria-live="polite" aria-relevant="all">
								 <tr role="row" class="odd">
								        <td class="center ">
											<s:iterator value="page" var="p" status="s">
									        	<div class="col-xs-6 col-sm-3">
									        		<s:if test="#p.status!=null&#p.status!=''">
									        			<s:if test="#p.status==0">
											        		<h4  style="text-align: center;">${p.deviceName}</h4>
															<input type="button" class="btn btn-app  btn-block btn-flat btn-style btn-off" onclick="updateDevices(this,${p.id},'1');" name="myDevices" style="height: 100px;"></input>
									        			</s:if>
									        			<s:if test="#p.status==1">
											        		<h4  style="text-align: center;">${p.deviceName}</h4>
															<input  type="button" class="btn btn-app  btn-block btn-flat btn-style btn-on"  onclick="updateDevices(this,${p.id},'0');"   name="myDevices" style="height: 100px;"></input >
									        			</s:if>
									        		</s:if>
									        		<s:else>
											        		<h4  style="text-align: center;">故障ing</h4>
															<button class="btn btn-app  btn-block btn-flat btn-off" disabled="disabled"  style="height: 100px;"></button>
									        		</s:else>
									        	</div>
											</s:iterator>    
								        </td>
								 </tr>		
								 
							   
							    </tbody>
							    </table>
								    <div class="row">
									    <div class="col-md-5">
									  	  <div class="dataTables_info" id="DataTables_Table_0_info">
										  	  <s:if test="page!=null">
							                   	现在是第<span class="STYLE3"><s:property value="page.pageNum" />
							                    </span>页，一共有<span class="STYLE4"><s:property value="page.totalPage" />
							                    </span>页 共有 <s:property value="page.totalCount" />个设备
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
    <!-- jQueryValidate -->
    <script src="plugins/jQueryValidate/jquery.validate.min.js"></script>    	
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
	<!-- base.js -->
	<script src="dist/js/base.js"></script>
	<script type="text/javascript">
	var  submitSelec =  function(){
		var homeId = $('#hy_homeId').val();
		$('#hy_form_homeId').val(homeId);
    	$("#pagerForm").submit();
	}	
	
	$(function(){
	var homeId = $('#hy_form_homeId').val();
	initSelect(homeId);
	});
	var initSelect = function(homeId){
			$.ajax({
			  type: 'POST',
			  url: "devicesAction_getMyHomeList.action",
			  data: {},
			  success: function(json){
			  	if(!jQuery.isEmptyObject(json)){
				  	$('#hy_homeId').empty();
				  	$.each(json, function(index,element){
							if(homeId==element.id){
								var op = "<option value='"+element.id+"' selected='true'>"+element.address+"</option>";
							}else{
								var op = "<option value='"+element.id+"'>"+element.address+"</option>";
							}
							$('#hy_homeId').append(op);
				  	});
			  	}
			  
			  },
			  dataType:"json", 
			  error:function(){
			  	alert('加载数据失败请重新尝试')
			  },
			});
			
			/*close AllDevices*/
			$('#hy_closeAll').click(function(){
					if(confirm('将关闭当前的所有设备，是否继续？')){
						var homeId = $('#hy_form_homeId').val();
						$.ajax({
						  type: 'POST',
						  url: "devicesAction_closeAllMyDevices.action",
						  data: {'query.homeId':homeId},
						  async: false,
						  success: function(msg){
						  		if(msg!=''){
						  			if(msg=='1'){
			  							alert('所有设备已经关闭!');
										//self.location.reload();//刷新
			  							self.location.reload();//刷新页面
						  			}else if(msg=='-1'){
			  							alert('当前没有任何设备需要关闭的呢');
						  			}else
			  							alert('关闭失败 ，请重新尝试');
						  		}else{
			  						alert('关闭失败 ，请重新尝试');
						  		}
						  },
						  dataType:"json", 
						  error:function(){
			  					alert('error:关闭失败 ，请重新尝试');
						  
						  },
						});		
						
					}
							
			});		
			
	}
	
	var updateDevices = function(object,devicesID,status){
	
			$.ajax({
			  type: 'POST',
			  url: "devicesAction_updateMyDevices.action",
			  data: {'query.id':devicesID,'query.status':status},
			  success: function(msg){
			  	if(msg!=''){
			  		if(msg=='1'){
						//self.location.reload();//刷新
						if(status=='0'){
							alert('关闭成功');
							$(object).attr('onclick', "updateDevices(this,"+devicesID+",'1')")
							$(object).removeClass('btn-on');
							$(object).addClass('btn-off');
						}else if(status=='1'){
							alert('启动成功');
							$(object).attr('onclick', "updateDevices(this,"+devicesID+",'0')")
							$(object).removeClass('btn-off');
							$(object).addClass('btn-on');
						}else {
							$(object).attr("disabled", "true");
						}
						
			  		}else if(msg=='0'){
			  			alert('操作失败');
			  		}else{
			  			alert('你不能修改别人设备的呢');
			  		}
			  	}else{
			  		alert('操作失败')
			  	}
			  
			  },
			  error:function(){
			  	alert('操作失败')
			  },
			});	
	}
		
	
	</script>
	
	</body>
</html>


	<s:if test="page!=null">
		<script type="text/javascript">
		    mv.app.loadPage(<s:property value="page.pageNum"/>,<s:property value="page.totalPage"/>);
		</script>
	</s:if>	

