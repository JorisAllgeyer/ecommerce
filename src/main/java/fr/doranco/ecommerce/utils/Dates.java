package fr.doranco.ecommerce.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Dates {
	
	private static final String formatDate = "dd/MM/yyyy";

	public static final Date stringToDate(String dateStr) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static final String dateToString(Date date) {
		
		String dateStr = null;
		try {
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		dateStr = formatter.format(date);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	
	public static final String customDateToString(Date date, String format) {
		
		String dateStr = null;
		try {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		dateStr = formatter.format(date);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	

	public static final java.sql.Date convertDateUtilToDateSql(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}
	
	public static final java.util.Date convertDateSqlToDateUtil(java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}

}
