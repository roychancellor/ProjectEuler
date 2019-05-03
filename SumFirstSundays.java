/*
 * You are given the following information, but you may prefer to do some research for yourself.

********1 Jan 1900 was a Monday.********
Thirty days has September,
April, June and November.
All the rest have thirty-one,
Saving February alone,
Which has twenty-eight, rain or shine.
And on leap years, twenty-nine.
A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.

How many Sundays fell on the first of the month during the twentieth century (***1 Jan 1901*** to 31 Dec 2000)?
 */
package Euler19_SumFirstSundays;

import java.util.HashMap;
public class SumFirstSundays {
    public static void main(String args[]) {
		HashMap<Integer, Integer> monthDays = new HashMap<Integer, Integer>();
	    
		//populate each month and its number of days (leap year handled below)
	    monthDays.put(1, 31);
	    monthDays.put(2, 28);
	    monthDays.put(3, 31);
	    monthDays.put(4, 30);
	    monthDays.put(5, 31);
	    monthDays.put(6, 30);
	    monthDays.put(7, 31);
	    monthDays.put(8, 31);
	    monthDays.put(9, 30);
	    monthDays.put(10, 31);
	    monthDays.put(11, 30);
	    monthDays.put(12, 31);
	    
	    int date = 1, month = 1, year = 1900, dayOfWeek = 1;  //Monday = day 1
	    int numDays, sundayCount=0;
	    
	    while(year <= 2000) {
	    	numDays = monthDays.get(month);
	    	if(month == 2 && isLeapYear(year)) numDays++;
	    	while(date<=numDays) {
	    		if(dayOfWeek == 7) {  //then day is Sunday
	    			if(date == 1 && year >= 1901) sundayCount++;  //then Sunday is on the first of the month
	    			dayOfWeek = 1;  //reset day to Monday
	    		}
	    		else dayOfWeek++;
	    		date++;
	    	} //end while
	    	
	    	if(month == 12) {  //end of year reached:  increment the year and reset the month to January
	    		year++;
	    		month = 1;
	    		date = 1;
	    	}
	    	else {  //end of the month reached:  increment the month and reset the date to the first
	    		month++;
	    		date = 1;
	    	}
	    } //end while
	    System.out.println("The number of first Sundays is " + sundayCount);
    } //end main
    
    static boolean isLeapYear(int year) {
    	if((year%4 == 0 && year%100 != 0) || (year%100 == 0 && year%400 == 0)) return true;
    	return false;
    }
}
