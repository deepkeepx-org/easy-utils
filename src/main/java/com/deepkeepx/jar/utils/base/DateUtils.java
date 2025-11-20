package com.deepkeepx.jar.utils.base;

import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * This class is designed to handle date related operations.
 *
 * @author deepkeepx
 * @version 1.0.0
 */
public class DateUtils {

	/**
	 * Create a random datetime string in today.
	 *
	 * @return a random datetime string in today
	 */
	public static String create(){
		return create("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Create a random date time string for today within the specified hours.
	 *
	 * @param minHours the minimum hours of the date
	 * @param maxHours the maximum hours of the date
	 * @return a random date string between the specified hours
	 */
	public static String create(int minHours, int maxHours){
		String baseDate = create("yyyy-MM-dd");
		Random random = new Random();
		int hour = (random.nextInt(maxHours - minHours + 1) + minHours);
		baseDate += " " + (hour < 10 ? "0" + hour : hour);
		int minute = (random.nextInt(59 - 1 + 1) + 1);
		int second = (random.nextInt(59 - 1 + 1) + 1);
		baseDate += ":" + (minute < 10 ? "0" + minute : minute);
		baseDate += ":" + (second < 10 ? "0" + second : second);
		return baseDate;
	}

	/**
	 * Create a random datetime string in today with the specified format.
	 *
	 * @param format the format of the datetime string
	 * @return a random datetime string in today with the specified format
	 */
	public static String create(String format){
		return format(new Date(), format);
	}

	/**
	 * Format the specified date with the specified format.
	 *
	 * @param date the date to be formatted
	 * @return the formatted date string
	 */
	public static String format(String date){
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Format the specified date with the specified format.
	 *
	 * @param fileTime the date to be formatted
	 * @return the formatted date string
	 */
	public static String format(FileTime fileTime){
		return format(fileTime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Format the specified date with the specified format.
	 *
	 * @param fileTime the date to be formatted
	 * @param format the format of the datetime string
	 * @return the formatted date string
	 */
	public static String format(FileTime fileTime, String format){
		LocalDateTime dateTime = fileTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return dateTime.format(formatter);
	}

	/**
	 * Format the specified date with the specified format.
	 *
	 * @param date the date to be formatted
	 * @return the formatted date string
	 */
	public static String format(Date date){
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Format the specified date with the specified format.
	 *
	 * @param date the date to be formatted
	 * @return the formatted date string
	 */
	public static String format(long date){
		return format(new Date(date), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Format the specified date with the specified format.
	 *
	 * @param date the date to be formatted
	 * @param format the format of the datetime string
	 * @return the formatted date string
	 */
	public static String format(String date, String format){
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Format the specified date with the specified format.
	 *
	 * @param date the date to be formatted
	 * @param format the format of the datetime string
	 * @return the formatted date string
	 */
	public static String format(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Get the number of days between today and the specified date.
	 *
	 * @param date the specified date
	 * @return the number of days between today and the specified date
	 */
	public static int getBetweenDays(String date) {
		return getBetweenDays(date, create());
	}

	/**
	 * Get the number of days between two dates.
	 *
	 * @param date1 the first date
	 * @param date2 the second date
	 * @return the number of days between two dates
	 */
	public static int getBetweenDays(String date1, String date2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date1.replaceAll("-", "/")));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(date2.replaceAll("-", "/")));
			long time2 = cal.getTimeInMillis();
			long between_days=(time2-time1)/(1000*3600*24);
			return Math.abs(Integer.parseInt(String.valueOf(between_days)));
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Get  days of the specified year and month.
	 *
	 * @param year is the specified year
	 * @param month is the specified month
	 * @return  days
	 */
	public static Integer getDays(Integer year, Integer month){
		return LocalDate.of(year, month, 1).lengthOfMonth();
	}
	
	/**
	 * Get  month date of the specified date.
	 *
	 * @param date is the specified date
	 * @return  date number
	 */
	public static Integer getDay(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate.getDayOfMonth();
	}
	
	/**
	 * Get  year of the specified date.
	 *
	 * @param date is the specified date
	 * @param amount of date or time to be added to the field.
	 * @return  year of the specified date
	 */
	public static String getYear(Date date, Integer amount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, amount);
		return new SimpleDateFormat("yyyy").format(calendar.getTime());
	}
	public static String getYear(LocalDate date, Integer amount){
		return getYear(localDateToDate(date), amount);
	}
	public static String getYear(String date, Integer amount){
		return getYear(strToDate(date), amount);
	}
	public static String getYear(Integer amount){ return getYear(new Date(), amount);}
	
	/**
	 * Get  year month of the specified date.
	 *
	 * @param date is the specified date
	 * @param amount of date or time to be added to the field.
	 * @return  year month of the specified date
	 */
	public static String getYearMonth(Date date, Integer amount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
	}
	public static String getYearMonth(LocalDate localDate, Integer amount){
		return getYearMonth(localDateToDate(localDate), amount);
	}
	public static String getYearMonth(String date, Integer amount){
		return getYearMonth(strToDate(date), amount);
	}
	public static String getYearMonth(Integer amount){ return getYearMonth(new Date(), amount);}
	
	/**
	 * Get  year month of the specified date.
	 *
	 * @param date is the specified date
	 * @param amount of date or time to be added to the field.
	 * @return  year month of the specified date
	 */
	public static String getYearMonthDate(Date date, Integer amount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}
	public static String getYearMonthDate(LocalDate localDate, Integer amount){
		return getYearMonthDate(localDateToDate(localDate), amount);
	}
	public static String getYearMonthDate(String date, Integer amount){
		return getYearMonthDate(strToDate(date), amount);
	}
	public static String getYearMonthDate(Integer amount){ return getYearMonthDate(new Date(), amount);}
	
	/**
	 * Get  the format of a specified date and time.
	 *
	 * @param datetime is the specified datetime
	 * @return  datetime format
	 */
	public static String getFormat(String datetime) {
		if(Pattern.compile("\\d{4}").matcher(datetime).matches()){
			return "yyyy";
		} else if(Pattern.compile("\\d{4}-\\d{2}").matcher(datetime).matches()){
			return "yyyy-MM";
		} else if(Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(datetime).matches()){
			return "yyyy-MM-dd";
		} else if(Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}").matcher(datetime).matches()){
			return "yyyy-MM-dd HH";
		} else if(Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}").matcher(datetime).matches()){
			return "yyyy-MM-dd HH:mm";
		} else if(Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}").matcher(datetime).matches()){
			return "yyyy-MM-dd HH:mm:ss";
		} else {
			return null;
		}
	}
	
	/**
	 * LocalDate to Date.
	 *
	 * @param localDate is the specified localDate
	 * @return  Date
	 */
	public static Date localDateToDate(LocalDate localDate) {
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}
	
	/**
	 * Date to LocalDate.
	 *
	 * @param date is the specified date
	 * @return  LocalDate
	 */
	public static LocalDate dateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * String date time to Date.
	 *
	 * @param datetime is the specified datetime
	 * @return  Date
	 */
	public static Date strToDate(String datetime) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(getFormat(datetime));
			return formatter.parse(datetime);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Determine how many hours have passed.
	 *
	 * @param time1 is the specified time
	 * @param time2 is the specified time
	 * @param hours is the specified hours
	 * @return  Boolean true if more than hours, false otherwise
	 */
	public static boolean isMoreThanHours(Instant time1, Instant time2, Integer hours) {
		Duration duration = Duration.between(time1, time2).abs();
		return duration.compareTo(Duration.ofHours(hours)) > 0;
	}
	public static boolean isMoreThanHours(Instant time, Integer hours) {
		return isMoreThanHours(time, new Date().toInstant(), hours);
	}
	
	/**
	 * Determine how many days have passed.
	 *
	 * @param time1 is the specified time
	 * @param time2 is the specified time
	 * @param days is the specified days
	 * @return  Boolean true if more than days, false otherwise
	 */
	public static boolean isMoreThanDays(Instant time1, Instant time2, Integer days) {
		Duration duration = Duration.between(time1, time2).abs();
		return duration.compareTo(Duration.ofDays(days)) > 0;
	}
	public static boolean isMoreThanDays(Instant time, Integer hours) {
		return isMoreThanDays(time, new Date().toInstant(), hours);
	}
	
}