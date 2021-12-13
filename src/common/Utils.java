package common;

/*
 * 
 * @author Jessica Pamlin
 * Utils en är klass där vi kan ha nyttiga metoder som de olika delarna i vårt applikation kan nyttja.
 * Fördelen är att metoderna bara finns i en enda upplaga i en (1) enda instans (kallas "singleton-klass")
 * Se i programmets "main-metod" hur vi skapar en instans som alla klasser sen kan nyttja
 */

public class Utils {
	private static Utils utils;
	
	private Utils() {
		
	}
	
	public static Utils getInstance() {
		if ( utils == null )
			utils = new Utils();

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
	public static int timeConverter(int[] time) {
		int minutes = 0;
		int seconds = 0;
		minutes=minutes+(time[0]*60)+time[1];
		seconds=seconds+(minutes*60)+time[2];
		return seconds;
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
}
