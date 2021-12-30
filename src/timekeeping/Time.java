package timekeeping;

import java.util.Calendar;

public class Time {
	final public long time;

	private Calendar cdr; // = Calendar.getInstance(); // creates a new calendar instance
	
	public Time(long time) {
		this.time = time;
		cdr = Calendar.getInstance(); // creates a new calendar instance
	}

	public Time(int[] time) {
		this.time = asUnixTime(time);
		cdr = Calendar.getInstance(); // creates a new calendar instance
	}


	/**
	 * Private, maybe usefull for methods in this class?
	 * @param uTime a time given in unix time format, a big long
	 * @return a time given in int[] { hh, mm, ss } format
	 */
	private int[] asHumanTime() {
		return null;
	}
	
	/**
	 * Private, maybe usefull for methods in this class?
	 * @param time a time given in int[] { hh, mm, ss } format
	 * @return uTime a time given in unix time format, a big long
	 */
	private long asUnixTime(int[] time) {
		System.currentTimeMillis();
		return 0; // TODO
	}

	public String getCurrTime(Time time) {
//		cdr.setTimeInMillis(System.currentTimeMillis());
//	
//		String time = String.format( 
//					"%02d:%02d:%02d:%02d", 
//					cdr.get(Calendar.HOUR_OF_DAY), 
//					cdr.get(Calendar.MINUTE),
//					cdr.get(Calendar.SECOND),
//					cdr.get(Calendar.MILLISECOND) / 10
//				);

//		return time;
		return null;
	}
	
	public int[] getCurrTimeInts() {
		cdr.setTimeInMillis(System.currentTimeMillis());
		return new int[] { 
				cdr.get(Calendar.HOUR_OF_DAY), 
				cdr.get(Calendar.MINUTE), 
				cdr.get(Calendar.SECOND), 
		};
	}

	/**
	 * Sets the clock time
	 * @newTime the new time 
	 */
	public void setCurrActualTime(int[] newTime) {
		// TODO
	}
}
