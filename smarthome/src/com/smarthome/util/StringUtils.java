/*    */ package com.smarthome.util;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URLEncoder;
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*  9 */   private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", 
/* 10 */     "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
/*    */ 
/*    */   private static String byteArrayToHexString(byte[] b)
/*    */   {
/* 13 */     StringBuffer resultSb = new StringBuffer();
/* 14 */     for (int i = 0; i < b.length; i++) {
/* 15 */       resultSb.append(byteToHexString(b[i]));
/*    */     }
/* 17 */     return resultSb.toString();
/*    */   }
/*    */ 
/*    */   private static String byteToHexString(byte b) {
/* 21 */     int n = b;
/* 22 */     if (n < 0)
/* 23 */       n += 256;
/* 24 */     int d1 = n / 16;
/* 25 */     int d2 = n % 16;
/* 26 */     return hexDigits[d1] + hexDigits[d2];
/*    */   }
/*    */ 
/*    */   public static String toMD5(String origin) {
/* 30 */     String resultString = null;
/*    */     try {
/* 32 */       resultString = new String(origin);
/* 33 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 34 */       resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
/*    */     } catch (Exception ex) {
/* 36 */       ex.printStackTrace();
/*    */     }
/* 38 */     return resultString;
/*    */   }
/*    */ 
/*    */   public static String encodeParam(String param) {
/* 42 */     String encodeParam = null;
/*    */     try {
/* 44 */       encodeParam = URLEncoder.encode(param, "UTF-8");
/*    */     } catch (UnsupportedEncodingException e) {
/* 46 */       e.printStackTrace();
/*    */     }
/* 48 */     return encodeParam;
/*    */   }
/*    */ 
/*    */   public static String arrayToString(String[] vlaues) {
/* 52 */     StringBuffer buffer = new StringBuffer(vlaues.length);
/* 53 */     if (vlaues != null) {
/* 54 */       for (int i = 0; i < vlaues.length; i++) {
/* 55 */         buffer.append(vlaues[i]).append(",");
/*    */       }
/*    */     }
/* 58 */     if (buffer.length() > 0) {
/* 59 */       return buffer.toString().substring(0, buffer.length() - 1);
/*    */     }
/* 61 */     return "";
/*    */   }
/*    */ 
/*    */   public static boolean isEmpty(String s) {
/* 65 */     return (s == null) || (s.length() == 0);
/*    */   }
/*    */ 
/*    */   public static boolean isTrimedEmpty(String s) {
/* 69 */     return (s == null) || (s.trim().length() == 0);
/*    */   }
/*    */ 
/*    */   public static boolean isNotEmpty(String s) {
/* 73 */     return (s != null) && (s.length() > 0);
/*    */   }
/*    */ }

/* Location:           E:\毕业论文项目\smarthome\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.smarthome.util.StringUtils
 * JD-Core Version:    0.6.1
 */