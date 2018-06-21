package com.rqbank.eelection.util.date;

public enum DateSystem
{
	Miladi (0,
			new int[] {31,28,31,30,31,30,31,31,30,31,30,31},
			new int[] {31,29,31,30,31,30,31,31,30,31,30,31}
			),
	Shamsi (3,
			new int[] {31,31,31,31,31,31,30,30,30,30,30,29},
			new int[] {31,31,31,31,31,31,30,30,30,30,30,30}
			)
	;
	
	public int big_year_rf;
	public int[] little_year_month_days;
	public int[] big_year_month_days;
	
	private DateSystem(int big_year_rf,
			int[] little_year_month_days,
			int[] big_year_month_days)
	{
		this.big_year_rf = big_year_rf;
		this.little_year_month_days = little_year_month_days;
		this.big_year_month_days = big_year_month_days;
	}
	
	// 1 based month
	public int getMonthLastDay(int year, int month)
	{
		if (year % 4 == big_year_rf) return big_year_month_days[month-1];
		else return little_year_month_days[month-1];
	}
}
