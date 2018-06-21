package com.rqbank.eelection.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTools
{
	public static Date addDay(Date date, int count)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, count);
		return cal.getTime();
	}
	
	public static Date addHoure(Date date, int count)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, count);
		return cal.getTime();
	}
	
	public static Date addMinute(Date date, int count)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, count);
		return cal.getTime();
	}
	
	public static Date addSecond(Date date, int count)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, count);
		return cal.getTime();
	}
	
	static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static String to_String(Date date)
	{
		return format.format(date);
	}
	
	static SimpleDateFormat format_short = new SimpleDateFormat("HH:mm:ss");
	public static String to_String_short(Date date)
	{
		return format_short.format(date);
	}
	
	public static Date beginOfDay(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal.getTime();
	}
	
	public static String secondsString(int seconds)
	{
		String res = "";
		int sec = seconds % 60;
		seconds /= 60;
		int min = seconds % 60;
		seconds /= 60;
		int hour = seconds;
		if (hour!=0) res += hour + ":";
		if (hour!=0 || min!=0) res += min + ":";
		res += sec;
		return res;
	}
	
	// 1 based month
	public static Date beginOfShamsiMonth(int year, int month)
	{
		mDate d = new mDate(year, month, 1);
		
		return DateConverter.shamsiToMiladi(d);
	}
	
	// 1 based month
	public static Date endOfShamsiMonth(int year, int month)
	{
		mDate d = new mDate(year, month, DateSystem.Shamsi.getMonthLastDay(year, month));
		d.hour = 23;
		d.second = 59;
		
		return DateConverter.shamsiToMiladi(d);
	}
	
	public static mDate shamsiNow()
	{
		return DateConverter.miladiToShamsi(new Date());
	}
	

}
