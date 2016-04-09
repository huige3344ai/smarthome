<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


 <header class="main-header">
        <!-- Logo -->
        <a href="page/background/index.jsp" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>SH</b>C</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>SMARTHOME</b>C</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a id="hy_sidebar-toggle" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="page/background/personcenter.jsp" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="${session.user.logoImage}" class="user-image" alt="User Image">
                  <span class="hidden-xs">${session.user.userName}</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="${session.user.logoImage}" class="img-circle" alt="User Image">
                    <p>
                      ${session.user.userName}
                      <small>Member since 2016</small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="page/background/personcenter.jsp" class="btn btn-default btn-flat">个人信息</a>
                    </div>
                    <div class="pull-right">
                      <a href="page/loginAction_logout.action" class="btn btn-default btn-flat">注销</a>
                    </div>
                  </li>
                </ul>
              </li>
  
            </ul>
          </div>
        </nav>
      </header>
