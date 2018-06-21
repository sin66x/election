package com.rqbank.eelection.util.date;



public class mDate
{
	public int year;
	public int month;
	public int day;
	public int hour;
	public int minute;
	public int second;
	
	public mDate()
	{
		
	}
	
	public mDate(int year, int month, int day)
	{
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public static String getPaded(int s)
	{
		return ((""+s).length()==1?"0":"") + s;
	}
	
	// like : 92/05/11-21:41   -> 14 chars
	@Override
	public String toString()
	{
		String res="";
		res += getPaded(year%100) + "/" + getPaded(month) + "/" + getPaded(day);
		res += "-";
		res += getPaded(hour) + ":" + getPaded(minute);
		return res;
	}
	
	// like : 92/05/11-21:41:30
	public String toString_sec()
	{
		String res="";
		res += getPaded(year%100) + "/" + getPaded(month) + "/" + getPaded(day);
		res += "-";
		res += getPaded(hour) + ":" + getPaded(minute) + ":" + getPaded(second);
		return res;
	}
	
	// like : 92/05/11
	public String toString_date()
	{
		String res="";
		res += getPaded(year%100) + "/" + getPaded(month) + "/" + getPaded(day);
		return res;
	}
	
	// like : 920511
	public String toString_date_comp()
	{
		String res="";
		res += getPaded(year%100) + getPaded(month) + getPaded(day);
		return res;
	}
	
	static String getPattern(String pattern, String need, String data) throws Exception
	{
        try{
		int b = pattern.indexOf(need);
		if (b == -1) throw new Exception("Pattern not exist.");
		return data.substring(b,b+need.length());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
	}
	
	static int getPatternNum(String pattern, String need, String data) throws Exception
	{
		return Integer.parseInt(getPattern(pattern, need, data));
	}
	
	static String putPattern(String pattern, String need, String data)
	{
		return pattern.replace(need, data);
	}
	
	static String putPatternNum(String pattern, String need, int data)
	{
		String s = "" + data;
		while(s.length() < need.length()) s = "0" + s;
		return putPattern(pattern, need, s);
	}
	
	public String toString(String pattern)
	{
		String res = pattern;
		
		if (res.indexOf("yyyy") >= 0) res = putPatternNum(res, "yyyy", year);
		else res = putPatternNum(res, "yy", year%100);
		res = putPatternNum(res, "mm", month);
		res = putPatternNum(res, "dd", day);
		
		res = putPatternNum(res, "hh", hour);
		res = putPatternNum(res, "mi", minute);
		res = putPatternNum(res, "ss", second);
		
		return res;
	}
	
	public static mDate parse(String pattern, String in) throws Exception
	{
		mDate res = new mDate();
		
		if (pattern.indexOf("yyyy") >= 0) res.year = getPatternNum(pattern, "yyyy", in);
		else res.year = getPatternNum(pattern, "yy", in);
		res.month = getPatternNum(pattern, "mm", in);
		res.day = getPatternNum(pattern, "dd", in);
		
		return res;
	}
}
