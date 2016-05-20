<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="${sessionScope.user.logoImage}" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>${sessionScope.user.userName}</p>
              <i class="fa fa-circle text-success"></i> 在线
            </div>
          </div>

          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">主菜单</li>
            
            <li>
              <a href="page/background/personcenter.jsp">
                <i class="fa fa-user-secret"></i> <span>个人中心</span>
                <small class="label pull-right bg-green"></small>
              </a>
            </li>
            
            <li>
              <a href="devicesActionb_devicesList.action">
                <i class="fa fa-laptop"></i> <span>设备管理</span>
                <s:if test="#session.tips.id.dnum!=null&&#session.tips.id.dnum!=0">
                	<small class="label pull-right  bg-green">New&nbsp ${sessionScope.tips.id.dnum}</small>
                </s:if>
              </a>
            </li>
            
            <li>
              <a href="page/userAction_userList.action">
                <i class="fa fa-users"></i> <span>用户管理</span>
                <s:if test="#session.tips.id.unum!=null&&#session.tips.id.unum!=0">
                	<small class="label pull-right  bg-green">New&nbsp ${sessionScope.tips.id.unum}</small>
                </s:if>
              </a>
            </li>
            
            <li>
              <a href="page/homeAction_homesList.action">
                <i class="fa  fa-home"></i> <span>住所管理</span>
                <s:if test="#session.tips.id.hnum!=null&&#session.tips.id.hnum!=0">
                	<small class="label pull-right  bg-green">New&nbsp ${sessionScope.tips.id.hnum}</small>
                </s:if>
              </a>
            </li>
            
            <li>
              <a href="page/rolesAction_getRoles.action">
                <i class="fa fa-lock"></i> <span>角色管理</span>
              </a>
            </li>
            
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>