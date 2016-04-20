package com.smarthome.util;

public class Constans
{
  public static final String ADMIN = "admin";
  public static final String ON = "on";
  public static final String OFF = "off";
  public static final String BACKGROUND_INDEX_URI = "page/background/index.jsp";
  public static final String FOREGROUND_INDEX_URI = "page/foreground/index.jsp";
  public static final String LOGIN_URI = "page/login.jsp";
 
  /*
   * 人生阶段常量
   */
  public static final Short ONE=1;//新生儿
  public static final Short TWO=2;//0.12(约3周大)
  public static final Short THREE=3;//0.24(约6周大)
  public static final Short FOUR=4;//0.33(约四个月大)
  public static final Short FIVE=5;//0.55(约6个月大)
  public static final Short SIX=6;//0.75(约9个月大)
  public static final Short SEVEN=7;//1岁
  public static final Short EIGHT=8;//1.5岁
  public static final Short NINE=9;//2岁
  public static final Short TEN=10;//3岁
  
  public static final Short ELEVEN=11;//4~5岁
  public static final Short TWELVE=12;//5~14岁
  
  public static final Short THIRTEEN=13;//15~20岁
  
  public static final Short FOURTEEN=14;//21~30岁
  
  public static final Short FIFTEEN=15;//31~60岁
  public static final Short SIXTEEN=16;//60岁~
  
  
  
  public static final String LOGINPAGE="login.jsp";//登录页面
  
  /**
   * 设备状态  其他 字符串代表设备故障
   */
  public static final String STATUS_ON="1";
  public static final String STATUS_OFF="0";
  
  /**
   * ajax  返回判断
   */
  public static final String RETRUN_EXCEPTION_STATUS="-1";//异常
  public static final String RETRUN_FAILED_STATUS="0";//失败 htm 会判断为空
  public static final String RETRUN_SUCCESS_STATUS="1";//成功
  public static final String RETRUN_NOTHING_STATUS="2";//未有改变
  
  /**
   * 操作
   */
  public static final String OPERA_DELETE="delete";//删除
  public static final String OPERA_FIND="find";//查询
  public static final String OPERA_SAVE="save";//保存
  
  /**
   * 选择状态
   * 
   */
  public static final String SELECT_TRUE="1";//被选择
  public static final String SELECT_FALSE="0";//不被选择
  
  
  
}
