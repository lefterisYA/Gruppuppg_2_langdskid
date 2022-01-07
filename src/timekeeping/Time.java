package timekeeping;

import java.util.Calendar;
import java.util.TimeZone;

public class Time implements Comparable<Time> {
	public final Calendar cdr;
	
	public enum Unit { HOURS, MINUTES, SECONDS, MILLIS };

	///////
	// Constructors:

	/** New Time set to the current system time */
	public Time() {
		// To simplify the calculation of the difference and sum of two times, we set the calendar to January 1st 1970.
		// Otherwise, creating a 30 second time object would get wonky...
		// We have to get manually add the offset based on our timezone when we call System.currentTimeMillis.
		cdr = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cdr.setTimeInMillis(System.currentTimeMillis()+TimeZone.getDefault().getRawOffset());
		cdr.set(0, 0, 0);
	}

	/** @param time New time set to @time. */
	public Time(long time) {
		cdr = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cdr.setTimeInMillis(time);
	}

	/** @param time New time set to @time on unit @unit */
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

	/** @param time New time set to String @time with format "hh:mm:ss" */
	public Time(String time) {
		this(parseString(time));
	}

	// Helper for constructor above...
	private static int[] parseString(String time) {
		String[] tArr = time.split(":");
		return new int[] { Integer.parseInt(tArr[0]), Integer.parseInt(tArr[1]), Integer.parseInt(tArr[2]) };
	}

	/** @param time new time set to  int[] {h, m, s}. */
	public Time(int[] time) {
		this(time[0], time[1], time[2], 0);
	}

	/** @param time new time set to  int[] {h, m, s}. */
	public Time(String[] time) {
		this(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]), 0);
	}

	/** @param hr @param mn @param sc hours minutes seconds we wish to set the time to.*/
	public Time(int hr, int mn, int sc) {
		this(hr, mn, sc, 0);
	}
	
	/** @param hr @param mn @param sc @param ms hours minutes seconds milliseconds we wish to set the time to.*/
	public Time(int hr, int mn, int sc, int ms) {
		cdr = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cdr.setTimeInMillis( (hr * 3600 * 1000) + (mn * 60 * 1000) + (sc * 1000) + ms );
	}
	

	///////
	///////
	
	public void setToNow() {
		cdr.setTimeInMillis(System.currentTimeMillis()+TimeZone.getDefault().getRawOffset());
		cdr.set(0, 0, 0);
	}
	
	
	////////////////////
	// "Getters" in different formats...

	/** @return the time given in int[] { hh, mm, ss } format */
	public int[] asHumanTime() {
		return new int[] { 
				cdr.get(Calendar.HOUR_OF_DAY), 
				cdr.get(Calendar.MINUTE), 
				cdr.get(Calendar.SECOND), 
		};
	}
	
	/** @return the time in milliseconds*/
	public long inMs() {
		return cdr.getTimeInMillis();
	}

	/** @return the time in the format hh:ss:mm */
	public String toString() {
		return toString(false);
	}

	/** @return the time in the format hh:ss:mm with an optional .hs (hundreds of a second). */
	public String toString(boolean withHundrs) {
		int[] vals = asHumanTime();

		if ( withHundrs ) {
			int ms = cdr.get(Calendar.MILLISECOND)/10;
			return String.format("%02d:%02d:%02d.%02d", vals[0], vals[1], vals[2], ms );
		} else {
			return String.format("%02d:%02d:%02d", 		vals[0], vals[1], vals[2] );
		}
	}
	

	////////////////////
	//"Smart" methods and math methods!

	/** @param oTime second operand, obj being called on is the first. @return returns the result of: this - oTime */
	public Time diffTo(Time oTime) {
		return new Time( this.inMs() - oTime.inMs() );
	}
	
	/** @param oTime second operand, obj being called on is the first. @return returns the result of: this + oTime */
	public Time addTo(Time oTime) {
		return new Time( this.inMs() + oTime.inMs() );
	}
	
	/** @param factor second operand, obj being called on is the first. @return returns the result of: this * factor */
	public Time productOf(int factor) {
		return new Time( this.inMs() * factor );
	}

	@Override
	public int compareTo(Time oTime) {
		return ( (Long) cdr.getTimeInMillis() ).compareTo(oTime.inMs());
	}
}
