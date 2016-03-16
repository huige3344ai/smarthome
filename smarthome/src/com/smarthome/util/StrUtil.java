package com.smarthome.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil
{
  public static int nullToInt(Object vStr)
  {
    int str = -1;
    String v = nullToStr(vStr);
    if (("".equals(v)) || ("null".equals(v)))
      return str;
    try
    {
      str = Integer.valueOf(v).intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  public static Long nullToLong(Object vStr)
  {
    Long str = Long.valueOf(0L);
    String v = nullToStr(vStr);
    if ("".equals(v))
      return str;
    try
    {
      str = Long.valueOf(v);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  public static Double nullToDouble(Object vStr)
  {
    Double str = Double.valueOf(0.0D);
    String v = nullToStr(vStr);
    if ("".equals(v))
      return str;
    try
    {
      str = Double.valueOf(v);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  public static float nullToFloat(Object vStr)
  {
    float str = 0.0F;
    String v = nullToStr(vStr);
    if ("".equals(v))
      return str;
    try
    {
      str = Float.valueOf(v).floatValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  public static boolean nullToBoolean(Object vStr)
  {
    boolean str = false;
    String v = nullToStr(vStr);
    if ("".equals(v))
      return str;
    try
    {
      str = Boolean.valueOf(v).booleanValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  public static String nullToStr(Object obj)
  {
    if ((obj == null) || (obj.toString().equals("null"))) {
      return "";
    }
    return obj.toString().trim();
  }

  public static int StringToInt(String s) {
    int tmp = 0;
    if (s == null)
      return 0;
    try {
      tmp = Integer.parseInt(s);
    } catch (Exception e) {
      tmp = 0;
    }
    return tmp;
  }

  public static float StringToFloat(String s) {
    float tmp = 0.0F;
    if (s == null)
      return 0.0F;
    try {
      tmp = Float.parseFloat(s);
    } catch (Exception e) {
      tmp = 0.0F;
    }
    return tmp;
  }

  public static BigDecimal formatBigDecimal(Number bd, String format)
  {
    DecimalFormat df = new DecimalFormat(format);
    return new BigDecimal(df.format(bd));
  }

  public static BigDecimal nullToBigDecimal(Object obj)
  {
    if ("".equals(nullToStr(obj))) {
      obj = "0.00";
    }
    BigDecimal bd = new BigDecimal(obj.toString());
    return bd;
  }

  public static String encode(String str)
  {
    try
    {
      if ((str == null) || (str.equals(""))) {
        return "";
      }
      return URLEncoder.encode(str, "UTF-8");
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static String decode(String str)
  {
    try
    {
      if ((str == null) || (str.equals(""))) {
        return "";
      }
      return URLDecoder.decode(str, "UTF-8");
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static String forNull(String str, String forNull)
  {
    return str == null ? forNull : str;
  }

  public static String forNull(String str)
  {
    return forNull(str, "");
  }

  public static String forEmpty(String str, String forEmpty)
  {
    return "".equals(str) ? forEmpty : str;
  }

  public static String forEmpty(String str)
  {
    return "".equals(str) ? null : str;
  }

  public static String numToString(Number num, int len)
  {
    String rts = num.toString();

    if (rts.length() > len) {
      return rts.substring(rts.length() - len);
    }

    if (rts.length() < len) {
      int count = len - rts.length();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < count; i++) {
        sb.append("0");
      }
      rts = sb.toString() + rts;
      return rts;
    }

    return rts;
  }

  public static String numToString(String num, int len)
  {
    String rts = num.toString();

    if (rts.length() > len) {
      return rts.substring(rts.length() - len);
    }

    if (rts.length() < len) {
      int count = len - rts.length();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < count; i++) {
        sb.append("0");
      }
      rts = sb.toString() + rts;
      return rts;
    }

    return rts;
  }

  public static boolean isEmpty(String input)
  {
    boolean isEmpty = true;

    if (input != null) {
      for (int i = 0; i < input.length(); i++)
      {
        char c = input.charAt(i);
        if ((c != ' ') && (c != '\r') && (c != '\n')) {
          return false;
        }
      }
    }

    return isEmpty;
  }

  public static boolean isInt(String str)
  {
    try
    {
      Long.parseLong(str);
      return true; } catch (NumberFormatException localNumberFormatException) {
    }
    return false;
  }

  public static boolean isInteger(String str)
  {
    String pattern = "^-?\\d+$";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(str);
    return m.find();
  }

  public static boolean isFloat(String str)
  {
    String pattern = "^(-?\\d+)(\\.\\d+)?$";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(str);
    return m.find();
  }

  public static boolean isIP(String str)
  {
    String[] arr = str.split("\\.");
    if (arr.length != 4) {
      return false;
    }
    for (int i = 0; i < arr.length; i++) {
      try {
        int num = Integer.parseInt(arr[i]);
        if ((num < 0) || (num > 255))
          return false;
      }
      catch (NumberFormatException e) {
        return false;
      }
    }

    return true;
  }

  public static String strToDate(String input)
  {
    String rs = input.substring(0, 4) + "-" + input.substring(4, 6) + "-" + 
      input.substring(6, 8);
    return rs;
  }

  public static String changeEncoding(String str, String fromEncoding, String toEncoding)
    throws UnsupportedEncodingException
  {
    if (str == null) {
      return null;
    }
    if (trim(fromEncoding).equals("")) {
      fromEncoding = System.getProperty("file.encoding");
    }
    if (trim(toEncoding).equals("")) {
      toEncoding = System.getProperty("file.encoding");
    }
    return new String(str.getBytes(fromEncoding), toEncoding);
  }

  public static String strToTime(String input)
  {
    String rs = input.substring(0, 2) + ":" + input.substring(2, 4) + ":" + 
      input.substring(4, 6);
    return rs;
  }

  public static String showLimitedString(String input, int len)
  {
    if (input == null)
      return "";
    if (input.length() > len) {
      return input.substring(0, len - 1) + "..";
    }
    return input;
  }

  public static String substring(String str, int start, int end)
  {
    if (str == null) {
      return "";
    }
    if (start > str.length()) {
      return "";
    }
    if (end > str.length()) {
      return str.substring(start);
    }
    return str.substring(start, end);
  }

  public static String substring(String str, int start)
  {
    if (str == null) {
      return "";
    }
    if (start > str.length()) {
      return "";
    }
    return str.substring(start);
  }

  public static String[] filterNull(String[] arr)
  {
    List result = new ArrayList();
    for (int i = 0; i < arr.length; i++) {
      String s = arr[i];
      if (s != null) {
        result.add(s);
      }
    }
    return (String[])result.toArray(new String[0]);
  }

  public static String[] filterNull(Iterable<String> iterable)
  {
    List result = new ArrayList();
    Iterator iter = iterable.iterator();
    while (iter.hasNext()) {
      String s = (String)iter.next();
      if (s != null) {
        result.add(s);
      }
    }
    return (String[])result.toArray(new String[0]);
  }

  public static String[] filterEmpty(String[] arr)
  {
    List result = new ArrayList();
    for (int i = 0; i < arr.length; i++) {
      String s = arr[i];
      if ("".equals(s)) {
        result.add(s);
      }
    }
    return (String[])result.toArray(new String[0]);
  }

  public static String[] filterEmpty(Iterable<String> iterable)
  {
    List result = new ArrayList();
    Iterator iter = iterable.iterator();
    while (iter.hasNext()) {
      String s = (String)iter.next();
      if (!"".equals(s)) {
        result.add(s);
      }
    }
    return (String[])result.toArray(new String[0]);
  }

  public static String[] filterEmptyTrim(Iterable<String> iterable)
  {
    List result = new ArrayList();
    Iterator iter = iterable.iterator();
    while (iter.hasNext()) {
      String s = (String)iter.next();
      if (!"".equals(trim(s))) {
        result.add(s);
      }
    }
    return (String[])result.toArray(new String[0]);
  }

  public static String join(String[] ss, String s)
  {
    StringBuffer sb = new StringBuffer();
    if (ss.length > 0) {
      sb.append(ss[0]);
    }
    for (int i = 1; i < ss.length; i++) {
      sb.append(s).append(ss[i]);
    }
    return sb.toString();
  }

  public static String join(String[] ss)
  {
    return join(ss, ",");
  }

  public static String join(Iterable<String> iterable, String s)
  {
    StringBuffer sb = new StringBuffer();
    Iterator it = iterable.iterator();
    if (it.hasNext()) {
      sb.append((String)it.next());
    }
    while (it.hasNext()) {
      sb.append(s + (String)it.next());
    }
    return sb.toString();
  }

  public static String join(Iterable<String> iterable)
  {
    return join(iterable, ",");
  }

  public static String wrapString(String[] arr, String seperator, String wrapString)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < arr.length; i++) {
      if (i != 0) {
        sb.append(seperator);
      }
      sb.append(wrapString + arr[i] + wrapString);
    }
    return sb.toString();
  }

  public static String wrapString(Iterable<String> iterable, String seperator, String wrapString)
  {
    StringBuffer sb = new StringBuffer();
    Iterator iter = iterable.iterator();
    for (int i = 0; iter.hasNext(); i++) {
      if (i != 0) {
        sb.append(seperator);
      }
      sb.append(wrapString + (String)iter.next() + wrapString);
    }
    return sb.toString();
  }

  public static String[] fillInArray(String[] ss, String s)
  {
    for (int i = 0; i < ss.length; i++) {
      ss[i] = s;
    }
    return ss;
  }

  public static String dateToString(Date date)
  {
    return DateUtil.dateToString(date);
  }

  public static String dateToString(Date date, String format)
  {
    return DateUtil.dateToString(date, format);
  }

  public static String getFormatDate(String date)
  {
    String[] arr = date.split("-");
    String YYYY = arr[0];
    String MM = arr[1];
    String DD = arr[2];
    if (MM.startsWith("0")) {
      MM = MM.replaceFirst("0", "");
    }
    if (DD.startsWith("0")) {
      DD.replaceFirst("0", " ");
    }

    return YYYY + "-" + MM + "-" + DD;
  }

  public static String[] trim(String[] arr)
  {
    if (arr == null) {
      return new String[0];
    }
    String[] result = new String[arr.length];
    for (int i = 0; i < arr.length; i++) {
      result[i] = arr[i].trim();
    }
    return result;
  }

  public static String trim(String str, String forNull)
  {
    if (str == null) {
      return forNull;
    }
    return str.trim();
  }

  public static String trim(String str)
  {
    return trim(str, "");
  }

  public static String trim2(String str, String forNull)
  {
    if ((str == null) || (str.trim().equals(""))) {
      return forNull;
    }
    return str.trim();
  }

  public static String trim2(String str)
  {
    return trim2(str, "");
  }

  public static String escapeTrim(String s)
  {
    return escapeTrim(s, "");
  }

  public static String escapeTrim(String s, String forNull)
  {
    return trim(escape(s), forNull);
  }

  public static String escape(String s)
  {
    if (s == null) {
      return null;
    }
    StringBuffer sb = new StringBuffer();
    escape(s, sb);
    return sb.toString();
  }

  public static void escape(String s, StringBuffer sb)
  {
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      switch (ch) {
      case '"':
        sb.append("\\\"");
        break;
      case '\\':
        sb.append("\\\\");
        break;
      case '\b':
        sb.append("\\b");
        break;
      case '\f':
        sb.append("\\f");
        break;
      case '\n':
        sb.append("\\n");
        break;
      case '\r':
        sb.append("\\r");
        break;
      case '\t':
        sb.append("\\t");
        break;
      case '/':
        sb.append("\\/");
        break;
      default:
        if (((ch >= 0) && (ch <= '\037')) || 
          ((ch >= '') && (ch <= '')) || (
          (ch >= ' ') && (ch <= '⃿'))) {
          String ss = Integer.toHexString(ch);
          sb.append("\\u");
          for (int k = 0; k < 4 - ss.length(); k++) {
            sb.append('0');
          }
          sb.append(ss.toUpperCase());
        } else {
          sb.append(ch);
        }
        break;
      }
    }
  }

  public static String instead(String preString, String ifStr, String toString)
  {
    if (preString == null) {
      if (ifStr == null) {
        return toString;
      }
      return "null";
    }

    if (preString.equals(ifStr)) {
      return toString;
    }
    return preString;
  }

  public static String codeToString(String str)
  {
    String s = str;
    try {
      byte[] tempB = s.getBytes("8859_1");
      return new String(tempB);
    } catch (Exception localException) {
    }
    return s;
  }

  public static String getFileName(String str)
  {
    str = str.replace("\\", "/");
    String[] arr = str.split("/");
    return arr[(arr.length - 1)];
  }

  public static String replaceFirstChar(String firstStr, String replaceStr, String targetStr)
  {
    if (targetStr.startsWith(firstStr)) {
      targetStr = targetStr.replaceFirst(firstStr, replaceStr);
    }
    return targetStr;
  }

  public static String nullToZero(String paramString)
  {
    throw new Error("Unresolved compilation problem: \n\tType mismatch: cannot convert from Integer to String\n");
  }

  public static Map<String, String> getUrlParameters(String param)
  {
    Map paramMap = new TreeMap();
    if (param == null) {
      return paramMap;
    }
    String[] arr = param.split("&");
    for (int i = 0; i < arr.length; i++) {
      try {
        String s = arr[i];
        if (!s.equals("")) {
          int pos = s.indexOf("=");
          if (pos == -1) {
            String key = s;
            String value = null;
            paramMap.put(key, value);
          } else {
            String key = substring(s, 0, pos);
            String value = 
              substring(s, pos + 1, s.length());
            paramMap.put(key, URLDecoder.decode(value, "utf-8"));
          }
        }
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return paramMap;
  }

  public static String generateSessionId()
  {
    byte[] chars = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 
      66, 67, 68, 69, 70 };
    Random ran = new Random();
    byte[] bytes = new byte[32];
    for (int i = 0; i < 32; i++) {
      bytes[i] = chars[(java.lang.Math.abs(ran.nextInt()) % chars.length)];
    }
    return new String(bytes);
  }

  public static String generateRandom(int len)
  {
    byte[] chars = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 
      98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 
      110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 
      122, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 
      76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 
      88, 89, 90 };
    Random ran = new Random();
    byte[] bytes = new byte[len];
    for (int i = 0; i < len; i++) {
      bytes[i] = chars[(java.lang.Math.abs(ran.nextInt()) % chars.length)];
    }
    return new String(bytes);
  }

  public static String generateUUID(int len)
  {
    return UUID.randomUUID() + "-" + generateRandom(len);
  }

  public static String generateUUID2()
  {
    return generateUUID2(0);
  }

  public static String generateUUID2(int len)
  {
    return UUID.randomUUID().toString().toUpperCase().replace("-", "") + 
      generateRandom(len);
  }

  public static String generateUUID3(int len)
  {
    return DateUtil.dateToString(new Date(), "yyMMddHHmmss") + 
      numToString(Long.toHexString(System.nanoTime()), 9) + 
      generateRandom(11);
  }

  public static String generateUUID3()
  {
    return generateUUID3(11);
  }

  public static boolean like(String input, String sPattern)
  {
    Pattern pattern = Pattern.compile(sPattern.replace("%", "(.*)")
      .replace("_", "(.)"));
    Matcher matcher = pattern.matcher(input);
    return matcher.matches();
  }

  public static String stringToUnicode(String preStr)
  {
    StringBuffer result = new StringBuffer();

    for (int i = 0; i < preStr.length(); i++) {
      String s = Integer.toHexString(preStr.charAt(i)).toUpperCase();
      result.append("\\u").append(
        String.format("%4s", new Object[] { s }).replace(" ", "0"));
    }
    return result.toString();
  }

  public static String unicodeToString(String s)
  {
    String[] arr = s.split("\\\\u");
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < arr.length; i++) {
      if (!"".equals(arr[i])) {
        result.append((char)Integer.parseInt(arr[i], 16));
      }
    }
    return result.toString();
  }

  public static boolean isIpMatchFormat(String ip, String format)
  {
    if ((ip == null) || (format == null)) {
      return false;
    }
    String[] arr = format.split(";");
    for (String s : arr) {
      s = s.trim().replace("*", "");
      if (ip.startsWith(s)) {
        return true;
      }
    }
    return false;
  }

  public static String getSkipSubString(String paramString, int paramInt)
  {
    throw new Error("Unresolved compilation problems: \n\tDuplicate local variable subStr\n\tDuplicate local variable subStr\n");
  }

  public static String upperFirst(String str)
  {
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  public static String lowerFirst(String str)
  {
    return str.substring(0, 1).toLowerCase() + str.substring(1);
  }

  public static String doURLEncode(String str)
    throws UnsupportedEncodingException
  {
    if (str == null) {
      return null;
    }
    return URLEncoder.encode(str, "utf-8").replace("-", "%2D")
      .replace("%", "-");
  }

  public static String doURLDecode(String str)
    throws UnsupportedEncodingException
  {
    if (str == null) {
      return null;
    }
    return URLDecoder.decode(str.replace("-", "%"), "utf-8");
  }

  public static Set<String> arrayToSet(String[] arr)
  {
    Set set = new HashSet();
    for (int i = 0; i < arr.length; i++) {
      String s = arr[i];
      set.add(s);
    }
    return set;
  }

  public static String format(Date date)
  {
    Calendar today = Calendar.getInstance();
    Calendar old = Calendar.getInstance();
    old.setTime(date);

    today.set(10, 0);
    today.set(12, 0);
    today.set(13, 0);
    old.set(10, 0);
    old.set(12, 0);
    old.set(13, 0);

    long intervalMilli = old.getTimeInMillis() - today.getTimeInMillis();
    int xcts = (int)(intervalMilli / 86400000L);

    if ((xcts >= -2) && (xcts <= 2)) {
      if (xcts == -2)
        return "前天";
      if (xcts == -1)
        return "昨天";
      if (xcts == 0)
        return "今天";
      if (xcts == 1) {
        return "明天";
      }
      return "后天";
    }

    return DateUtil.dateToString2(date);
  }

  public static void main(String[] args)
  {
    Date date = null;
    try {
      date = DateUtil.strToDate("2015-06-08 15:45:35", 
        "yyyy-MM-dd hh:mm:ss");
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    System.out.println(format(date));
  }

  public static String getWeekDay(Calendar c)
  {
    if (c == null) {
      return "";
    }

    if (2 == c.get(7)) {
      return "周一";
    }
    if (3 == c.get(7)) {
      return "周二";
    }
    if (4 == c.get(7)) {
      return "周三";
    }
    if (5 == c.get(7)) {
      return "周四";
    }
    if (6 == c.get(7)) {
      return "周五";
    }
    if (7 == c.get(7)) {
      return "周六";
    }
    if (1 == c.get(7)) {
      return "周日";
    }

    return "";
  }

  public static int getFancierLevel(int points)
  {
    if ((points >= 0) && (points <= 10))
      return 0;
    if ((11 <= points) && (points <= 100))
      return 10;
    if ((101 <= points) && (points <= 500))
      return 20;
    if ((501 <= points) && (points <= 1000))
      return 30;
    if ((1001 <= points) && (points <= 2000))
      return 40;
    if ((2001 <= points) && (points <= 4000))
      return 50;
    if ((4001 <= points) && (points <= 7000))
      return 60;
    if ((7001 <= points) && (points <= 11000))
      return 70;
    if ((11001 <= points) && (points <= 15000))
      return 80;
    if ((15001 <= points) && (points <= 20000))
      return 90;
    if (20001 <= points) {
      return 100;
    }
    return 0;
  }
}