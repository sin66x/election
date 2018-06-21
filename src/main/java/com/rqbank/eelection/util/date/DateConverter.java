package com.rqbank.eelection.util.date;

import java.util.Calendar;
import java.util.Date;



public class DateConverter
{
	static int getMonthDayes(DateSystem ds, int year, int month) // its month is zero indexed! ( really month - 1 )
	{
		if ( year % 4 == ds.big_year_rf )
			return ds.big_year_month_days[month];
		else 
			return ds.little_year_month_days[month];
	}
	
	static DivResult div_inmonth(DateSystem ds,int cy,int cm,int s)
	{
		DivResult res = new DivResult();
		int day = 0;
		while ( !((s-day) < getMonthDayes(ds,cy,cm) && (s-day) >= 0) )
		{
			if ( s >= 0 ) // go forward
			{
				day += getMonthDayes(ds,cy,cm);
				res.d ++;
				cm ++;
				if ( cm >= 12 ) { cm = 0; cy ++; }
			}
			else // go backward
			{
				res.d --;
				cm --;
				if ( cm < 0 ) { cm = 11; cy --; }
				day -= getMonthDayes(ds,cy,cm);
			}
		}
		res.r = s - day;
		return res;
	}
	
	// divide S to F  =  reminder -> R  ,  div -> D
	static DivResult div(int s, int f)
	{
		DivResult res = new DivResult();
		res.r = s % f;
		if ( res.r < 0 ) res.r += f;
		res.d = (s-(res.r))/f;
		return res;
	}
	
	static void timedateAdd(DateSystem ds,mDate source, mDate add) // add parts can be negative, positive, over
	{
		DivResult dr;

		source.day --;
		source.month --;

		source.day += add.day;
		dr = div_inmonth(ds,source.year,source.month,source.day);
		source.day = dr.r;

		source.month += add.month + dr.d;
		dr = div(source.month,12);
		source.month = dr.r;

		source.year += add.year + dr.d;
		
		source.day ++;
		source.month ++;
	}
	
	static int dayofYear(DateSystem ds,mDate source) // return its index! ( first day have zero index! )
	{
		int i;
		int dayofyear = 0;

		for ( i = 0 ; i < (source.month)-1 ; i ++ )
			dayofyear += getMonthDayes(ds,source.year,i);

		dayofyear += (source.day) - 1;

		return dayofyear;
	}
	
	static mDate getmDate(Date date)
	{
		mDate res = new mDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		res.year = cal.get(Calendar.YEAR);
		res.month = cal.get(Calendar.MONTH)+1;
		res.day = cal.get(Calendar.DAY_OF_MONTH);
		res.hour = cal.get(Calendar.HOUR_OF_DAY);
		res.minute = cal.get(Calendar.MINUTE);
		res.second = cal.get(Calendar.SECOND);
		return res;
	}
	
	static Date getDate(mDate date)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, date.year);
		cal.set(Calendar.MONTH, date.month-1);
		cal.set(Calendar.DAY_OF_MONTH, date.day);
		cal.set(Calendar.HOUR_OF_DAY, date.hour);
		cal.set(Calendar.MINUTE, date.minute);
		cal.set(Calendar.SECOND, date.second);
		return cal.getTime();
	}
	
	public static mDate miladiToShamsi(Date date)
	{
		if(date==null)
			return new mDate();
		return miladiToShamsi(getmDate(date));
	}
	
	static mDate clone(mDate date)
	{
		mDate nd = new mDate();
		nd.second = date.second;
		nd.minute = date.minute;
		nd.hour = date.hour;
		nd.day = date.day;
		nd.month = date.month;
		nd.year = date.year;
		return nd;
	}
	
	public static mDate miladiToShamsi(mDate miladi)
	{
		mDate shamsi = clone(miladi);
		mDate days = new mDate(); 
		
		shamsi.year -= 622;
		shamsi.month = 10;
		shamsi.day = 11;
		if ( miladi.year % 4 == 1 ) shamsi.day ++;
		days.day = dayofYear(DateSystem.Miladi, miladi);
		timedateAdd(DateSystem.Shamsi, shamsi, days);
		return shamsi;
	}
	
	public static mDate shamsiToMiladi_mDate(mDate shamsi)
	{
		mDate miladi = clone(shamsi);
		mDate days = new mDate(); 
		
		miladi.year += 621;
		miladi.month = 3;
		miladi.day = 21;
		if ( shamsi.year % 4 == 3 ) miladi.day --;
		days.day = dayofYear(DateSystem.Shamsi, shamsi);
		timedateAdd(DateSystem.Miladi, miladi, days);
		return miladi;
	}
	
	public static Date shamsiToMiladi(mDate shamsi)
	{
		return getDate(shamsiToMiladi_mDate(shamsi));
	}
	
}

class DivResult
{
	public int d;
	public int r;
}
