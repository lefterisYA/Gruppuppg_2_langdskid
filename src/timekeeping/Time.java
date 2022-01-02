package timekeeping;

import java.util.Calendar;
import java.util.TimeZone;

public class Time implements Comparable<Time> {
	public final Calendar cdr;
	
	public enum Unit { HOURS, MINUTES, SECONDS, MILLIS };

	/**
	 * Creates a temporary Time object set to the current time so we can call all the non-static format-converters.
	 * @return a Time object set to the current system time.
	 */
	public static Time getCurrTime() {
		return new Time();
	}
	
	private static int[] parseString(String time) {
		String[] timeStrArr = time.split(":");
		return new int[] {
			Integer.parseInt(timeStrArr[0]),
			Integer.parseInt(timeStrArr[1]),
			Integer.parseInt(timeStrArr[2])
		};
	}

	private static String toString(Time time, boolean includeHund) {
		int hr = time.cdr.get(Calendar.HOUR_OF_DAY);
		int mn = time.cdr.get(Calendar.MINUTE);
		int sc = time.cdr.get(Calendar.SECOND);

		if ( includeHund ) {
			int ms = time.cdr.get(Calendar.MILLISECOND)/10;
			return String.format("%02d:%02d:%02d.%02d", hr, mn, sc, ms );
		} else {
			return String.format("%02d:%02d:%02d", 		hr, mn, sc );
		}
	}


	/**
	 * Creates a new Time set to the current system time
	 */
	public Time() {
		// To simplify the calculation of the difference and sum of two times, we set the calendar to January 1st 1970.
		// Otherwise, creating a 30 second time object would get wonky...
		// We have to get manually add the offset based on our timezone when we call System.currentTimeMillis.
		cdr = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cdr.setTimeInMillis(System.currentTimeMillis()+TimeZone.getDefault().getRawOffset());
		cdr.set(0, 0, 0);
	}

	/**
	 * Creates a new Time set to parameter @time in milliseconds since epoch.
	 * @param time the time in milliseconds since epoch.
	 */
	public Time(long time) {
		cdr = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cdr.setTimeInMillis(time);
	}

	public Time(int time, Unit unit) {
		cdr = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		int res=time;
		switch (unit){
		case HOURS:
			res*=60;
		case MINUTES:
			res*=60;
		case SECONDS:
			res*=1000;
		case MILLIS:
		}

		cdr.setTimeInMillis(res);
	}

	public Time(String time) {
		this(parseString(time));
	}

	/**
	 * Creates a new Time set to todays date and time given by parameter time in int[] {h, m, s}
	 * @param time the time in int[] {h, m, s} we wish to set the new time.
	 */
	public Time(int[] time) {
		this(time[0], time[1], time[2], 0);
	}

	public Time(int hr, int mn, int sc) {
		this(hr, mn, sc, 0);
	}
	
	public Time(int hr, int mn, int sc, int ms) {
		cdr = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cdr.setTimeInMillis( (hr * 3600 * 1000) + (mn * 60 * 1000) + (sc * 1000) + ms );
	}

	/**
	 * 
	 * @return the time given in int[] { hh, mm, ss } format
	 */
	public int[] asHumanTime() {
		return new int[] { 
				cdr.get(Calendar.HOUR_OF_DAY), 
				cdr.get(Calendar.MINUTE), 
				cdr.get(Calendar.SECOND), 
		};
	}
	
	/**
	 * 
	 * @return uTime a time given in unix time format, a big long
	 */
	public long asUnixTime() {
		return cdr.getTimeInMillis();
	}

	public String toString() {
		return toString(this, false);
	}

	public String toString(boolean withHundrs) {
		return toString(this, withHundrs);
	}

	public Time diffTo(Time oTime) {
		return new Time( this.asUnixTime() - oTime.asUnixTime() );
	}
	
	public Time addTo(Time oTime) {
		return new Time( this.asUnixTime() + oTime.asUnixTime() );
	}
	
	public Time productOf(int factor) {
		return new Time( this.asUnixTime() * factor );
	}

	@Override
	public int compareTo(Time oTime) {
		return ( (Long) cdr.getTimeInMillis() ).compareTo(oTime.asUnixTime());
	}
//		return new int[] { 
//				cdr.get(Calendar.HOUR_OF_DAY), 
//				cdr.get(Calendar.MINUTE), 
//				cdr.get(Calendar.SECOND), 
//				cdr.get(Calendar.MILLISECOND) / 10

}
