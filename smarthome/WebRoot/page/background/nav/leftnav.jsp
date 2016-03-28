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
              <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>${session.user.userName}</p>
              <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
          </div>

          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">主菜单</li>
            
            <li>
              <a href="page/background/userm/personcenter.jsp">
                <i class="fa fa-user-secret"></i> <span>个人中心</span>
                <small class="label pull-right bg-red">3</small>
              </a>
            </li>
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa-laptop"></i>
                <span>设备管理</span>
                <i class="fa fa-angle-left pull-right"></i>
                <span class="label label-primary pull-right">4</span>
              </a>
              <ul class="treeview-menu">
                <li><a href=""><i class="fa fa-circle-o"></i> General</a></li>
                <li><a href=""><i class="fa fa-circle-o"></i> Icons</a></li>
                <li><a href=""><i class="fa fa-circle-o"></i> Buttons</a></li>
                <li><a href=""><i class="fa fa-circle-o"></i> Sliders</a></li>
                <li><a href=""><i class="fa fa-circle-o"></i> Timeline</a></li>
                <li><a href=""><i class="fa fa-circle-o"></i> Modals</a></li>
              </ul>
            </li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>