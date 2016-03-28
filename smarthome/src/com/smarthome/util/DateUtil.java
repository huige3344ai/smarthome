package com.smarthome.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��ʱ���й� author cty
 */
public class DateUtil {

	public final static int YEAR = Calendar.YEAR;
	public final static int MONTH = Calendar.MONTH;
	public final static int WEEK = Calendar.WEEK_OF_YEAR;
	public final static int WEEK_OF_MONTH = Calendar.WEEK_OF_MONTH;
	public final static int WEEK_OF_YEAR = Calendar.WEEK_OF_YEAR;
	public final static int DATE = Calendar.DATE;
	public final static int DAY_OF_MONTH = Calendar.DAY_OF_MONTH;
	public final static int DAY_OF_WEEK = Calendar.DAY_OF_WEEK;
	public final static int DAY_OF_YEAR = Calendar.DAY_OF_YEAR;
	public final static int HOUR = Calendar.HOUR;
	public final static int HOUR_OF_DAY = Calendar.HOUR_OF_DAY;
	public final static int MINUTE = Calendar.MINUTE;
	public final static int SECOND = Calendar.SECOND;
	public final static int MILLISECOND = Calendar.MILLISECOND;

	public final static long MILLISECOND_PER_SECOND = (long) 1000;
	public final static long MILLISECOND_PER_MINUTE = (long) 1000 * 60;
	public final static long MILLISECOND_PER_HOUR = (long) 1000 * 60 * 60;
	public final static long MILLISECOND_PER_DAY = (long) 1000 * 60 * 60 * 24;
	public final static long MILLISECOND_PER_WEEK = (long) 1000 * 60 * 60 * 24
			* 7;
	public final static long MILLISECOND_PER_MONTH = (long) 1000 * 60 * 60 * 24
			* 30;
	public final static long MILLISECOND_PER_YEAR = (long) 1000 * 60 * 60 * 24
			* 365;
	/**
	 * ʱ���ʽ
	 */
	public final static String TIME_FORMAT = "HH:mm:ss:SS";
	
	/**
	 * HH:mm 时分
	 */
	public final static String TIME_SHORT_FORMAT="HH:mm";

	/**
	 * ȱʡ�����ڸ�ʽ
	 */
	public final static String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 中国
	 */
	public final static String DEFAULT_SHORT_DATE_FORMAT_ZH = "yyyy年MM月dd日";

	/**
	 * ȱʡ�����ڸ�ʽ
	 */
	public final static String DEFAULT_LONG_DATE_FORMAT = DEFAULT_SHORT_DATE_FORMAT
			+ " " + TIME_FORMAT;

	/**
	 * Java��֧�ֵ���С�����ַ�yyyy-MM-dd����
	 */
	public final static String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";

	/**
	 * Java��֧�ֵ���С�����ַ�yyyy-MM-dd HH:mm:ss:SS����
	 */
	public final static String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";

	/**
	 * Java��֧�ֵ���С��Timestamp��
	 */
	public final static Timestamp JAVA_MIN_TIMESTAMP = convertStrToTimestamp(JAVA_MIN_LONG_DATE_STR);
	/**
	 * Ĭ�ϵ�ʱ�����ʾ��ʽ
	 */
	public final static String DEFAULT_PERIOD_FORMAT = "{0}��{1}Сʱ{2}����";

	/**
	 * Java��֧�ֵ���������ַ�yyyy-MM-dd����
	 */
	public final static String JAVA_MAX_SHORT_DATE_STR = "9999-12-31";

	/**
	 * �����ڼ���һ��
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDateByDay(Date date, int amount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, amount);
		return gc.getTime();
	}

	/**
	 * ��ʱ����� ĳ��ʱ���
	 * 
	 * @param date
	 * @param type
	 *            ʱ�䵥λ������ΪCalendar.DATE
	 * @param amount
	 * @return
	 */
	public static Date addTime(Date date, int type, int amount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(type, amount);
		return gc.getTime();
	}

	public static Timestamp timestamp(Date date) {

		return null;

	}

	/**
	 * 
	 */
	public static Date cut(Date date, int type) {
		long ts = date.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DAY_OF_MONTH);
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int mi = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);

		if (type == Calendar.YEAR) {
			cal.setTimeInMillis(ts / 1000 * 1000);
			cal.set(y, 0, 1, 0, 0, 0);
			return cal.getTime();
		}
		if (type == Calendar.MONTH) {
			cal.setTimeInMillis(ts / 1000 * 1000);
			cal.set(y, m, 1, 0, 0, 0);
			return cal.getTime();
		}
		if (type == Calendar.DAY_OF_MONTH || type == Calendar.DATE) {
			cal.setTimeInMillis(ts / 1000 * 1000);
			cal.set(y, m, d, 0, 0, 0);
			return cal.getTime();
		}
		if (type == Calendar.HOUR_OF_DAY || type == Calendar.HOUR) {
			cal.setTimeInMillis(ts / 1000 * 1000);
			cal.set(y, m, d, h, 0, 0);
			return cal.getTime();
		}
		if (type == Calendar.MINUTE) {
			cal.setTimeInMillis(ts / 1000 * 1000);
			cal.set(y, m, d, h, mi, 0);
			return cal.getTime();
		}
		if (type == Calendar.SECOND) {
			cal.setTimeInMillis(ts / 1000 * 1000);
			cal.set(y, m, d, h, mi, s);
			return cal.getTime();
		}
		return cal.getTime();
	}

	/**
	 * ���ַ�ת��ΪDate �����ʽ: "2001-12-11 22:59:00"
	 * 
	 * @throws ParseException
	 */
	public static Date strToDate(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str);
	}
	/**
	 * 精确到分鍾 "2001-12-11 22:59"
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate2(String str) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(str);
	}

	/**
	 * ���ַ�ת��ΪDate
	 * 
	 * @param str
	 *            Ҫת�����ַ�
	 * @param format
	 *            ת����ʽ,��"yyyy-MM-dd hh:mm:ss.SSS"
	 */
	public static Date strToDate(String str, String format)
			throws ParseException {
		format = (format == null) ? "yyyy-MM-dd HH:mm:ss.SSS" : format;
		return new SimpleDateFormat(format).parse(str);
	}

	/**
	 * ��һDate���͵Ķ���ת��Ϊһ�� "1998-01-01 01:01:01" ������ַ�
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * ��һDate���͵Ķ���ת��Ϊһ�� "1998-01-01" ������ַ�
	 */
	public static String dateToString2(Date date) {
		return dateToString(date, "yyyy-MM-dd");
	}

	public static String dateToString3(Date date) {
		return dateToString(date, "yyyy年MM月dd号");
	}

	/**
	 * 
	 */
	public static String dateToString(long date) {
		return dateToString(new Date(date));
	}

	/**
	 * ��һDate���͵Ķ���ת��Ϊһ���ַ�
	 * 
	 * @param format
	 *            Ĭ��"yyyy-MM-dd HH:mm:ss.SSS"
	 */
	public static String dateToString(Date date, String formate) {
		if (date == null) {
			return "";
		}
		formate = (formate == null) ? "yyyy-MM-dd HH:mm:ss.SSS" : formate;
		return new SimpleDateFormat(formate).format(date);
	}

	/**
	 * 
	 */
	public static String dateToString(long date, String formate) {
		return dateToString(new Date(date), formate);
	}

	/**
	 * 
	 */
	public static String dateStringToString(String dateString,
			String fromFormat, String toFormat) {
		Date date = null;
		try {
			date = strToDate(dateString, fromFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = dateToString(date, toFormat);
		return result;
	}

	/**
	 * @throws ParseException
	 * 
	 */
	public static String dateStrToStr(String dateString) {
		if (null == dateString || dateString.equals(""))
			return "";
		Date date = null;
		try {
			date = strToDate(dateString, "yyyy-MM-dd HH:mm:ss.S");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = dateToString(date, "yyyy-MM-dd HH:mm:ss");
		return result;
	}

	/**
	 * 
	 */
	public static String dateToGMTString(Date date) {
		DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z",
				Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(date);
	}

	/**
	 * 
	 */
	public static Date GMTStringToDate(String str) throws ParseException {
		DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z",
				Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.parse(str);
	}

	/**
	 * isDateTimeString("2001-01-01 10:03:22")
	 */
	public static boolean isDateTimeString(String str) {
		Pattern pattern = Pattern
				.compile("^([0-9]{4})-([0-9]{2})-([0-9]{2}) ([0-9]{2}):([0-9]{2}):([0-9]{2})$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			int d = 0;
			d = Integer.parseInt(matcher.group(2));
			if (d < 1 || d > 12) {
				return false;
			}
			d = Integer.parseInt(matcher.group(3));
			if (d < 1 || d > 31) {
				return false;
			}
			d = Integer.parseInt(matcher.group(4));
			if (d > 23) {
				return false;
			}
			d = Integer.parseInt(matcher.group(5));
			if (d > 59) {
				return false;
			}
			d = Integer.parseInt(matcher.group(6));
			if (d > 59) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * isDateString("2001-12-01")
	 */
	public static boolean isDateString(String str) {
		Pattern pattern = Pattern.compile("^([0-9]{4})-([0-9]{2})-([0-9]{2})$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			int d = 0;
			d = Integer.parseInt(matcher.group(2));
			if (d < 1 || d > 12) {
				return false;
			}
			d = Integer.parseInt(matcher.group(3));
			if (d < 1 || d > 31) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * isTimeString("23:34:22")
	 */
	public static boolean isTimeString(String str) {
		Pattern pattern = Pattern.compile("^([0-9]{2}):([0-9]{2}):([0-9]{2})$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			int d = 0;
			d = Integer.parseInt(matcher.group(1));
			if (d > 23) {
				return false;
			}
			d = Integer.parseInt(matcher.group(2));
			if (d > 59) {
				return false;
			}
			d = Integer.parseInt(matcher.group(3));
			if (d > 59) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * ��������ʱ���ʱ���
	 * 
	 * @param date1
	 * @param date2
	 * @param type
	 * @return
	 */
	public static double datediff(Date date1, Date date2, int type) {
		if (type == YEAR) {
			return (double) (date1.getTime() - date2.getTime())
					/ MILLISECOND_PER_YEAR;
		} else if (type == MONTH) {
			return (double) (date1.getTime() - date2.getTime())
					/ MILLISECOND_PER_MONTH;
		} else if (type == WEEK || type == WEEK_OF_MONTH
				|| type == WEEK_OF_YEAR) {
			return (double) (date1.getTime() - date2.getTime())
					/ MILLISECOND_PER_WEEK;
		} else if (type == DAY_OF_MONTH || type == DATE) {
			return (double) (date1.getTime() - date2.getTime())
					/ MILLISECOND_PER_DAY;
		} else if (type == HOUR) {
			return (double) (date1.getTime() - date2.getTime())
					/ MILLISECOND_PER_HOUR;
		} else if (type == MINUTE) {
			return (double) (date1.getTime() - date2.getTime())
					/ MILLISECOND_PER_MINUTE;
		} else if (type == SECOND) {
			return (double) (date1.getTime() - date2.getTime())
					/ MILLISECOND_PER_SECOND;
		} else {
			throw new IllegalArgumentException("unknown type=['" + type + "']");
		}
	}

	/**
	 * ��������ʱ���ʱ��� �� Date date1 = D.strToDate("2010-08-08 08:08:08.888",
	 * "yyyy-MM-dd HH:mm:ss.SSS"); Date date2 =
	 * D.strToDate("2010-07-07 07:07:07.777", "yyyy-MM-dd HH:mm:ss.SSS");
	 * datediff2(date1, date2, YEAR) = 0 �� datediff2(date1, date2, MONTH) = 1
	 * �� datediff2(date1, date2, WEEK) = 5 �� (��������Ϊһ�ܵĵ�һ��)
	 * datediff2(date1, date2, DATE) = 32 �� datediff2(date1, date2, HOUR) = 769
	 * Сʱ datediff2(date1, date2, MINUTE) = 46141 �� datediff2(date1, date2,
	 * SECOND) = 2768461 ��
	 * 
	 * @param date1
	 * @param date2
	 * @param type
	 * @return
	 */
	public static long datediff2(Date date1, Date date2, int type) {
		Calendar calendar1 = GregorianCalendar.getInstance();
		Calendar calendar2 = GregorianCalendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		if (type == YEAR) {
			return calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
		} else if (type == MONTH) {
			long yearDiff = datediff2(date1, date2, YEAR);
			return yearDiff * 12 + calendar1.get(Calendar.MONTH)
					- calendar2.get(Calendar.MONTH);
		} else if (type == WEEK || type == WEEK_OF_MONTH
				|| type == WEEK_OF_YEAR) {
			calendarClear(calendar1, DAY_OF_WEEK);
			calendarClear(calendar2, DAY_OF_WEEK);
			return (calendar1.getTime().getTime() - calendar2.getTime()
					.getTime()) / MILLISECOND_PER_WEEK;
		} else if (type == DATE || type == DAY_OF_MONTH || type == DAY_OF_WEEK
				|| type == DAY_OF_YEAR) {
			calendarClear(calendar1, HOUR_OF_DAY);
			calendarClear(calendar2, HOUR_OF_DAY);
			return (calendar1.getTime().getTime() - calendar2.getTime()
					.getTime()) / MILLISECOND_PER_DAY;
		} else if (type == HOUR || type == HOUR_OF_DAY) {
			calendarClear(calendar1, MINUTE);
			calendarClear(calendar2, MINUTE);
			return (calendar1.getTime().getTime() - calendar2.getTime()
					.getTime()) / MILLISECOND_PER_HOUR;
		} else if (type == MINUTE) {
			calendarClear(calendar1, SECOND);
			calendarClear(calendar2, SECOND);
			return (calendar1.getTime().getTime() - calendar2.getTime()
					.getTime()) / MILLISECOND_PER_MINUTE;
		} else if (type == SECOND) {
			calendarClear(calendar1, MILLISECOND);
			calendarClear(calendar2, MILLISECOND);
			return (calendar1.getTime().getTime() - calendar2.getTime()
					.getTime()) / MILLISECOND_PER_SECOND;
		} else {
			throw new IllegalArgumentException("type=[" + type + "]");
		}
	}

	/**
	 * ��һ�����ڵ�ָ�����ݼ����²������
	 */
	public static void calendarClear(Calendar calendar, int type) {
		if (type == MILLISECOND) {
			calendar.set(Calendar.MILLISECOND, 0);
		} else if (type == SECOND) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
		} else if (type == MINUTE) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
		} else if (type == HOUR) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR, 0);
		} else if (type == HOUR_OF_DAY) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
		} else if (type == DATE) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.DATE, 1);
		} else if (type == DAY_OF_MONTH) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		} else if (type == DAY_OF_WEEK) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.DAY_OF_WEEK, 1);
		} else if (type == DAY_OF_YEAR) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.DAY_OF_YEAR, 1);
		} else if (type == MONTH) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.DAY_OF_YEAR, 1);
		} else if (type == YEAR) {
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.YEAR, 1);
		} else {
			throw new IllegalArgumentException("type=[" + type + "]");
		}
	}

	public static String NowData() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		return df.format(new Date());
	}

	public static String NowData2() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}

	public static String NowData3() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		return df.format(new Date());
	}

	public static String NowData4() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return df.format(new Date());
	}

	public static String NowData5() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(new Date());
	}

	/**
	 * �����ڶ���Ӽ��ꡢ�¡��պ�õ��µ����ڶ���
	 * 
	 * @param depart
	 *            yy-�ꡢMM-�¡�dd-��
	 * @param number
	 *            �Ӽ�����
	 * @param date
	 *            ��Ҫ�Ӽ��ꡢ�¡��յ����ڶ���
	 * @return Date �µ����ڶ���
	 */
	public static Date addDate(String datepart, int number, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (datepart.equals("yy")) {
			cal.add(Calendar.YEAR, number);
		} else if (datepart.equals("MM")) {
			cal.add(Calendar.MONTH, number);
		} else if (datepart.equals("dd")) {
			cal.add(Calendar.DATE, number);
		} else {
			throw new IllegalArgumentException(
					"DateUtil.addDate()�����Ƿ�����ֵ��" + datepart);
		}

		return cal.getTime();
	}

	/**
	 * 跟当前的时间相比较
	 * 
	 * @param time1
	 * @return
	 */
	public static boolean compareTime(String time1) {
		return compareTime(time1, getCurrDateStr(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * �Ƚ�ʱ���С
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean compareTime(String time1, String time2) {
		return compareTime(time1, time2, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 时间1 是否在 时间2之前
	 * 
	 * @param time1
	 * @param time2
	 * @param dateFormat
	 * @return ���time1С��time2����true,���򷵻�false
	 */
	public static boolean compareTime(String time1, String time2,
			String dateFormat) {
		SimpleDateFormat t1 = new SimpleDateFormat(dateFormat);
		SimpleDateFormat t2 = new SimpleDateFormat(dateFormat);
		try {
			Date d1 = t1.parse(time1);
			Date d2 = t2.parse(time2);
			return d1.before(d2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ����������ת����ָ����ʽ�������ַ�
	 * 
	 * @param date
	 *            ��ת��������
	 * @param dateFormat
	 *            ���ڸ�ʽ�ַ�
	 * @return String
	 */
	public static String convertDateToStr(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * ��ָ����ʽ���ַ�ת������������
	 * 
	 * @param date
	 *            ��ת���������ַ�
	 * @param dateFormat
	 *            ���ڸ�ʽ�ַ�
	 * @return Date
	 */
	public static Date convertStrToDate(String dateStr, String dateFormat) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			throw new RuntimeException("DateUtil.convertStrToDate():"
					+ e.getMessage());
		}
	}

	/**
	 * ��ָ����ʽ���ַ�ת������������
	 * 
	 * @param date
	 *            ��ת���������ַ�
	 * @param dateFormat
	 *            ���ڸ�ʽ�ַ�
	 * @param locale
	 *            ���ػ�����
	 * @return Date
	 */
	public static Date convertStrToDate(String dateStr, String dateFormat,
			Locale locale) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			throw new RuntimeException("DateUtil.convertStrToDate():"
					+ e.getMessage());
		}
	}

	/**
	 * ���ַ�ת��ΪTimestamp���ͣ����ڶ����ڸ�ʽ���Զ���ʱ����Ϊϵͳ��ǰʱ�䡣
	 * 
	 * @return Timestamp
	 * @see #convertStrToTimestamp(String,boolean)
	 */
	public static Timestamp convertStrToTimestamp(String dateStr) {
		return convertStrToTimestamp(dateStr, false);
	}

	/**
	 * ���ַ�ת��ΪTimestamp���͡�
	 * 
	 * @param dateStr
	 *            - �����ַ�ֻ֧��"yyyy-MM-dd"��"yyyy-MM-dd HH:mm:ss:SS"���ָ�ʽ��
	 *            ���Ϊ"yyyy-MM-dd"��ϵͳ���Զ�ȡ�õ�ǰʱ�䲹�ϡ�
	 * @param addZeroTime
	 *            - �������ַ�Ϊ"yyyy-MM-dd"����ĸ�ʽʱ��addZeroTimeΪtrue��ʾ
	 *            ��0������HH:mm:ss:SS�������õ�ǰTime�����á�
	 * @return Timestamp
	 */
	private static Timestamp convertStrToTimestamp(String dateStr,
			boolean addZeroTime) {
		if (dateStr == null) {
			return null;
		}

		String dStr = dateStr.trim();
		if (dStr.indexOf(" ") == -1) {
			if (addZeroTime) {
				dStr = dStr + " 00:00:00:00";
			} else {
				dStr = dStr + " " + getCurrDateStr(DateUtil.TIME_FORMAT);
			}
		}

		Date utilDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DEFAULT_LONG_DATE_FORMAT);

		try {
			utilDate = simpleDateFormat.parse(dStr);
		} catch (Exception ex) {
			throw new RuntimeException("DateUtil.convertStrToTimestamp(): "
					+ ex.getMessage());
		}

		return new Timestamp(utilDate.getTime());
	}

	/**
	 * ���ַ�ת��ΪTimestamp���ͣ����ڶ����ڸ�ʽ���Զ���ʱ����Ϊ0��
	 * 
	 * @return Timestamp
	 * @see #convertStrToTimestamp(String,boolean)
	 */
	public static Timestamp convertStrToTimestampZero(String dateStr) {
		return convertStrToTimestamp(dateStr, true);
	}

	/**
	 * �������ʱ��ת����ʱ�����ʾ
	 * 
	 * @param period
	 *            ��λΪ��
	 * @return Ĭ�ϸ�ʽΪ"d��hСʱm����"
	 */
	public static String convertToPeriod(long period) {
		long dayUnit = 24 * 60 * 60L;
		long hourUnit = 60 * 60L;
		long minUnit = 60L;
		String result = MessageFormat.format(DEFAULT_PERIOD_FORMAT,
				(period / dayUnit), (period % dayUnit / hourUnit), (period
						% hourUnit / minUnit));
		return result;
	}

	/**
	 * ������������֮���������ꡢ�¡��ա�ע�⣺ֻ�м������������׼ȷ�ģ��������¶���
	 * ����ֵ����һ��365�죬һ��30����㣬������������µĲ��
	 * 
	 * @param datepart
	 *            ��λ�ĸ�ʽ�ַ�yy��ʾ�꣬MM��ʾ�£�dd��ʾ��
	 * @param startdate
	 *            ��ʼ����
	 * @param enddate
	 *            ��������
	 * @return double
	 *         ���enddate>startdate������һ������0��ʵ����򷵻�һ��С�ڵ���0��ʵ��
	 */
	public static double dateDiff(String datepart, Date startdate, Date enddate) {
		if (datepart == null || datepart.equals("")) {
			throw new IllegalArgumentException(
					"DateUtil.dateDiff()�����Ƿ�����ֵ��" + datepart);
		}

		double days = (double) (enddate.getTime() - startdate.getTime())
				/ (60 * 60 * 24 * 1000);

		if (datepart.equals("yy")) {
			days = days / 365;
		} else if (datepart.equals("MM")) {
			days = days / 30;
		} else if (datepart.equals("dd")) {
			return days;
		} else {
			throw new IllegalArgumentException(
					"DateUtil.dateDiff()�����Ƿ�����ֵ��" + datepart);
		}
		return days;
	}

	/**
	 * ���ص�ǰʱ�䣬Ĭ�ϸ�ʽΪyyyy-MM-dd HH:mm:ss,
	 * 
	 * @return
	 */
	public static String getCurrDateStr() {
		return getCurrDateStr("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * <p>
	 * ȡ�õ�ǰ���ڣ�������ת���ɸ�ʽΪ"dateFormat"���ַ� ���ӣ����統ǰ������ 2003-09-24
	 * 9:19:10����
	 * 
	 * <pre>
	 * getCurrDateStr("yyyyMMdd")="20030924"
	 * getCurrDateStr("yyyy-MM-dd")="2003-09-24"
	 * getCurrDateStr("yyyy-MM-dd HH:mm:ss")="2003-09-24 09:19:10"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param dateFormat
	 *            String ���ڸ�ʽ�ַ�
	 * @return String
	 */
	public static String getCurrDateStr(String dateFormat) {
		return convertDateToStr(new Date(), dateFormat);
	}

	/**
	 * �õ�ϵͳ��ǰʱ���Timestamp����
	 * 
	 * @return ϵͳ��ǰʱ���Timestamp����
	 */
	public static Timestamp getCurrTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * תΪ��ʼʱ��
	 * 
	 * @param fieldValue
	 * @return ��ʽ�磺1970-01-01 00:00:00
	 */
	public static String toBeginDate(String fieldValue) {
		if (fieldValue == null || "".equals(fieldValue)) {
			return "";
		}
		String result = "";
		fieldValue += " 00:00:00";
		result = fieldValue;
		return result;
	}

	/**
	 * תΪ����ʱ��
	 * 
	 * @param fieldValue
	 * @return ��ʽ��:1970-01-01 23:59:59
	 */
	public static String toEndDate(String fieldValue) {
		if (fieldValue == null || "".equals(fieldValue)) {
			return "";
		}
		String result = "";
		fieldValue += " 23:59:59";
		result = fieldValue;
		return result;
	}

	/**
	 * ��ȡ��׼��ʽ������ʱ����ʾ�ַ�
	 * 
	 * @param timestampStr
	 *            JDBC Timestamp��ʽ�ַ� ��ʽ:yyyy-MM-dd HH:mm:ss.SSS
	 * @return ��׼��ʽ������ʱ����ʾ�ַ���ʽ��yyyy-MM-dd HH:mm:ss
	 */
	public static String getStandardDatetimeStr(String timestampStr) {
		if (timestampStr == null || "".equals(timestampStr.trim())) {
			return "";
		}
		String result = "";
		try {
			Timestamp timestamp = Timestamp.valueOf(timestampStr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = sdf.format(timestamp);
		} catch (Exception e) {
			throw new RuntimeException("DateUtil.getStandardDatetimeStr()ʧ��:"
					+ e.getMessage());
		}
		return result;
	}

	/**
	 * ��ȡʱ����ַ�ĺ����ʾ���ַ��ʽ ��yyyy-MM-dd HH:mm:ss��
	 * 
	 * @param strTime
	 *            ʱ����ַ�ĺ����ʾ��
	 */
	public static long getTimeMillis(String dateStr) {
		return convertStrToDate(dateStr, "yyyy-MM-dd HH:mm:ss").getTime();
	}

	/**
	 * ��ȡ����ʱ�� tanjun
	 * 
	 */
	public static String getLocalTime() {
		// ��ȡ����ʱ��
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String localTime = sm.format(new Date());
		return localTime;
	}

	/**
	 * ��ȡһ��ǰ��ʱ��
	 * 
	 * @return
	 */
	public static String lastWeek() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - 6;

		if (day < 1) {
			month -= 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				day = 30 + day;
			} else if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				day = 31 + day;
			} else if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
					day = 29 + day;
				else
					day = 28 + day;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";
		String Time = y + "-" + m + "-" + d + " 00:00:00";
		return Time;
	}

	/**
	 * 时间戳
	 * 
	 * @param mill
	 * @return
	 */
	public static String convert(String mill) {
		Long m = Long.parseLong(mill + "000");
		Date date = new Date(m);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	/**
	 * 第几周
	 * 
	 * @return
	 */
	public static int getWeekOfYear() {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// String time = format.format(new Date());
		Calendar calendar = Calendar.getInstance();
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(new Date());
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取周几
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static int getDayOfWeek(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = format.parse(date);
		Calendar calendar = Calendar.getInstance();
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(time);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static int getDayOfWeek(Date date) throws ParseException {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// Date time = format.parse(date);
		Calendar calendar = Calendar.getInstance();
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 本周的第一天
	 * 
	 * @param week
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Long startDayOfWeek(int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, new Date().getYear());
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime().getTime();
	}

	/**
	 * 本周的最后一天
	 * 
	 * @param week
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Long endDayOfWeek(int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, new Date().getYear());
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime().getTime();
	}

	/**
	 * 获取俩天直接的间隔天数
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)
	 * 
	 * @return String
	 * @author pure
	 * @throws ParseException
	 **/
	public static String thisMonthEnd(String time) throws ParseException {
		String strY = null;
		String strZ = null;
		boolean leap = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 陆飞改
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = format.parse(time);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int x = c.get(Calendar.YEAR);
		int y = c.get(Calendar.MONTH) + 1;
		if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10
				|| y == 12) {
			strZ = "31";
		}
		if (y == 4 || y == 6 || y == 9 || y == 11) {
			strZ = "30";
		}
		if (y == 2) {
			leap = leapYear(x);
			if (leap) {
				strZ = "29";
			} else {
				strZ = "28";
			}
		}
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-" + strZ;
	}

	/**
	 * 功能：判断输入年份是否为闰年<br>
	 * 
	 * @param year
	 * @return 是：true 否：false
	 * @author pure
	 */
	public static boolean leapYear(int year) {
		boolean leap;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					leap = true;
				else
					leap = false;
			} else
				leap = true;
		} else
			leap = false;
		return leap;
	}

	/**
	 * 获取指定时间的时间戳
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static long getTimeChuo(String time) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = df.parse(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTimeInMillis() / 1000;
	}
	
	/**
	 * 通过 Unix 转换成字符串形式
	 * @param time
	 * @param format
	 * @return
	 */
	public static String TimeStampToDate(String time,String format){
		  Long timestamp = Long.parseLong(time)*1000;  
		  String date = new java.text.SimpleDateFormat(format).format(new java.util.Date(timestamp));  
		  return date;  	
	}
	
	/**
	 * TimeStamp 转换成 2016-12-13 10:00
	 * @param time
	 * @return
	 */
	public static String TimeStampToShortDate(String time){
		Long timestamp = Long.parseLong(time)*1000;  
		String date = new java.text.SimpleDateFormat(DEFAULT_SHORT_DATE_FORMAT+" "+TIME_SHORT_FORMAT).format(new java.util.Date(timestamp));  
		return date;  	
	}
	
	/**
	 * 去掉分隔符 
	 * @param split
	 * 分隔符 
	 * @return
	 */
	public static String TimeByAngSplit(String time,String split){
		
		return time.replace(split, " ");
		
	}
	/**
	 * 通过生日生成年龄
	 * @param birtday
	 * @return
	 */
	public static int getAge(Date birtday){
		int age=0;
		Calendar cal=Calendar.getInstance();//目前
		
		
		if(birtday!=null){
			if(cal.before(birtday)){
				throw new IllegalArgumentException("Can't be born in the future");
			}
			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
			
			cal.setTime(birtday);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
			
			age = yearNow-yearBirth;
			
			if(monthNow<=monthBirth){
				if(monthNow==monthBirth){
					if(dayOfMonthNow<dayOfMonthBirth){
						age--;
					}
				}
			}else{
				age--;
			}
		}
		
		return age;
	}
	
	/**
	 * 判断是否超过有效期
	 * @param time
	 * @param howlong  分钟
	 * @throws ParseException 
	 * 
	 */
	public static boolean compareTime(String time,int howlong) throws ParseException{
		long now = System.currentTimeMillis();
	    long date = strToDate(time).getTime();
	    date+=howlong*1000*60;
		if(now<=date){//有效期内
			return true;
		}else
		return false;
	}
	
	
	
//	public static void main(String[] args) throws ParseException {
//		int age = getAge(strToDate("2012-12-11","yyyy-MM-dd"));
//		System.err.print(age);
//	}
	
}
