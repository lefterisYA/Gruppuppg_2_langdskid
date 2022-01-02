package timekeeping;

import java.util.Calendar;
import java.util.TimeZone;

public class Time implements Comparable<Time> {
	private Calendar cdr;
	
	/**
	 * Creates a temporary Time object set to the current time so we can call all the non-static formats converters.
	 * @return a Time object set to the current system time.
	 */
	public static Time getCurrTime() {
		return new Time();
	}
	
	/**
	 * Creates a new Time set to the current system time
	 */
	public Time() {
		this(System.currentTimeMillis());
	}

	/**getTimeInMillis
	 * Creates a new Time set to parameter time in milliseconds since epoch.
	 * @param time the time in milliseconds since epoch.
	 */
	public Time(long time) {
		cdr = Calendar.getInstance();
		cdr.setTimeZone(TimeZone.getTimeZone("GMT"));
		cdr.setTimeInMillis(time);
	}

	/**
	 * Creates a new Time set to todays date and time given by parameter time in int[] {h, m, s}
	 * @param time the time in int[] {h, m, s} we wish to set the new time.
	 */
	public Time(int[] time) {
		this(System.currentTimeMillis());
		cdr.set(cdr.get(Calendar.YEAR), cdr.get(Calendar.MONTH), cdr.get(Calendar.DATE), time[0], time[1], time[2]);
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

	/**
	 * 
	 */
	public String toString() {
		return String.format( 
					"%02d:%02d:%02d", 
					cdr.get(Calendar.HOUR_OF_DAY), 
					cdr.get(Calendar.MINUTE),
					cdr.get(Calendar.SECOND)
				);
	}

	public String toString(boolean includeHund) {
		return String.format( 
					"%02d:%02d:%02d.%02d", 
					cdr.get(Calendar.HOUR_OF_DAY), 
					cdr.get(Calendar.MINUTE),
					cdr.get(Calendar.SECOND),
					cdr.get(Calendar.MILLISECOND)/10
				);
	}

	public Time diffTo(Time oTime) {
		return new Time( this.asUnixTime() - oTime.asUnixTime() );
	}
	
	public Time addTo(Time oTime) {
		return new Time( this.asUnixTime() + oTime.asUnixTime() );
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
