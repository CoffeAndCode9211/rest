package com.webservice.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Utils {

	public static String calDateInStr(Date startDate){
		LocalDate currDate = LocalDate.now();
		Instant instant = new java.util.Date(startDate.getTime()).toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate startTime = zdt.toLocalDate();
		Period p = Period.between(startTime, currDate);
		int year = p.getYears();
		int month = p.getMonths();
		int days = p.getDays();
		StringBuilder tarStr = new StringBuilder();
		if(year > 0){
			tarStr.append(year);
			tarStr.append("Y ");
		}
		if(month > 0){
			tarStr.append(month);
			tarStr.append("M ");
		}
		if(days > 0){
			tarStr.append(days);
			tarStr.append("D ");
		}
		return tarStr.toString();
	}
	
	
	public static String nextEventDays(Date startDate){
		LocalDate today = LocalDate.now();
		
		Instant instant = new java.util.Date(startDate.getTime()).toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate birthday = zdt.toLocalDate();
		
        LocalDate nextBDay = birthday.withYear(today.getYear());
        //If your birthday has occurred this year already, add 1 to the year.
        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1);
        }

        Period p = Period.between(today, nextBDay);
        long totalDays = ChronoUnit.DAYS.between(today, nextBDay);
        int month = p.getMonths();
		int days = p.getDays();
		StringBuilder tarStr = new StringBuilder();
		if(month > 0){
			tarStr.append(month);
			tarStr.append("M ");
		}
		if(days > 0){
			tarStr.append(days);
			tarStr.append("D ");
		}
		tarStr.append(" (");
		tarStr.append(totalDays);
		tarStr.append(" Days) ");
		return tarStr.toString();
	}

}
