package timekeeping;

import java.util.Locale;

/*
 * 
 * @author Jessica Pamlin
 * Utils en är klass där vi kan ha nyttiga metoder som de olika delarna i vårt applikation kan nyttja.
 * Fördelen är att metoderna bara finns i en enda upplaga i en (1) enda instans (kallas "singleton-klass")
 * Se i programmets "main-metod" hur vi skapar en instans som alla klasser sen kan nyttja
 */

public class TimeUtils {
	private static TimeUtils utils;
	
	private TimeUtils() {
		
	}
	
	public static TimeUtils getInstance() {
		if ( utils == null )
			utils = new TimeUtils();

		return utils;
	}

	public static int[] timeConverter(int secondsTotal) {
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		if (secondsTotal >= 60) {
			minutes = secondsTotal / 60;
			seconds = secondsTotal % 60;
			if (minutes >= 60) {
				hours = minutes / 60;
				minutes = minutes % 60;
			}
		} else
			seconds = secondsTotal;
		if (hours >= 1) {
			int[] hms = { hours, minutes, seconds };
			return hms;
		}
		if (minutes >= 1) {
			int[] ms = { 0, minutes, seconds };
			return ms;
		} else {
			int[] s = { 0, 0, seconds };
			return s;
		}

	}
	
	public static Long[] timeConverterFromLong(Long secondsTotal) {
		long hours = 0;
		long minutes = 0;
		long seconds = 0;
		if (secondsTotal >= 60) {
			minutes = secondsTotal / 60;
			seconds = secondsTotal % 60;
			if (minutes >= 60) {
				hours = minutes / 60;
				minutes = minutes % 60;
			}
		} else
			seconds = secondsTotal;
		if (hours >= 1) {
			Long[] hms = { hours, minutes, seconds };
			return hms;
		}
		if (minutes >= 1) {
			Long[] ms = { (long) 0, minutes, seconds };
			return ms;
		} else {
			Long[] s = { (long) 0, (long) 0, seconds };
			return s;
		}

	}
	
	public static String convertSecondsToHMmSs(long seconds) {
	    long s = seconds % 60;
	    long m = (seconds / 60) % 60;
	    long h = (seconds / (60 * 60)) % 24;
	    return String.format("%d:%02d:%02d", h,m,s);
	}
	
	public static int timeConverter(int[] time) {
		int minutes = 0;
		int seconds = 0;
		minutes=minutes+(time[0]*60)+time[1];
		seconds=seconds+(minutes*60)+time[2];
		return seconds;
	}
	
	public static int[] timeAdder(int[] time1, int[] time2) {
		int time1S=timeConverter(time1);
		int time2S=timeConverter(time2);
		time1S=time1S+time2S;
		time1=timeConverter(time1S);
		return time1;
	}
	
	public static String toString(int[] hms) {
		return (hms[0] + ":" + hms[1] + ":" + hms[2]);
//		if (hms[0]>0)
//			return (hms[0]+":"+hms[1]+":"+hms[2]);
//		if (hms[1]>0)
//			return (hms[1]+":"+hms[2]);
//		if (hms[2]>0)
//			return (hms[2]+"");
//		else
//			return ("Error");
	}
	
	public static String toStringLong(Long[] hms) {
		return (hms[0] + ":" + hms[1] + ":" + hms[2]);
	}
}
