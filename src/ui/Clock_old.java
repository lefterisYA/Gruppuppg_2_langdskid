package ui;

import java.util.Calendar;
import java.time.Instant;

public class Clock_old {
	/**
	 * 
	 * @param startTime a starting time.
	 * @return the time difference between a start time and now.
	 */
	public int[] getRunningTime(int[] startTime) {
		return new int[] { 00, 00, 00 };
	}
	

	/**
	 * Sets the clock time
	 * @newTime the new time 
	 */
	public void setCurrActualTime(int[] newTime) {
		// TODO
	}

	/**
	 * 
	 * @return returs the current actual time in string format
	 */
	public String getCurrTime() {
		cdr.setTimeInMillis(System.currentTimeMillis());
	
		String time = String.format( 
					"%02d:%02d:%02d:%02d", 
					cdr.get(Calendar.HOUR_OF_DAY), 
					cdr.get(Calendar.MINUTE),
					cdr.get(Calendar.SECOND),
					cdr.get(Calendar.MILLISECOND) / 10
				);

		return time;
	}
	
	/**
	 * 
	 * @return returns the actual current time with int[] { HH, MM, SS }
	 */
	public int[] getCurrTimeInts() {
		cdr.setTimeInMillis(System.currentTimeMillis());
		return new int[] { 
				cdr.get(Calendar.HOUR_OF_DAY), 
				cdr.get(Calendar.MINUTE), 
				cdr.get(Calendar.SECOND), 
		};
	}

	final static private String[][] asciiDgts = {
			{
				"  ████  " ,
				" ██  ██ " ,
				" ██  ██ " ,
				" ██  ██ " ,
				"  ████  "
			},
			{
				" ████   " ,
				"   ██   " ,
				"   ██   " ,
				"   ██   " ,
				" ██████ "
			},
			{
				"  ████  " ,
				" ██  ██ " ,
				"    ██  " ,
				"   ██   " ,
				" ██████ "
			},
			{
				"  ████  " ,
				" ██  ██ " ,
				"    ██  " ,
				" ██  ██ " ,
				"  ████  "
			},
			{
				" ██  ██ " ,
				" ██  ██ " ,
				" ██████ " ,
				"     ██ " ,
				"     ██ "
			},
			{
				" ██████ " ,
				" ██     " ,
				" █████  " ,
				"     ██ " ,
				" █████  "
			},
			{
				"  ████  " ,
				" ██     " ,
				" █████  " ,
				" ██  ██ " ,
				"  ████  "
			},
			{
				" ██████ " ,
				"    ██  " ,
				"   ██   " ,
				"  ██    " ,
				" ██     "
			},
			{
				"  ████  " ,
				" ██  ██ " ,
				"  ████  " ,
				" ██  ██ " ,
				"  ████  "
			},
			{
				"  ████  " ,
				" ██  ██ " ,
				"  █████ " ,
				"     ██ " ,
				"  ████  "
			},
			{
				"    " ,
				" ██ " ,
				"    " ,
				" ██ " ,
				"    "
			}
	};
//	final static private int dgtWdth = asciiDgts[0][0].length();
	final static private int dgtHght = asciiDgts[0].length;
//	final static private int colWdth = asciiDgts[10][0].length();
	
	private Calendar cdr; // = Calendar.getInstance(); // creates a new calendar instance
	private final StringBuilder sb = new StringBuilder();

	public Clock_old() {
		cdr = Calendar.getInstance(); // creates a new calendar instance
	}

	public String getInAscii(int hour, int mins, int secs) {
		return gnrtAsciiChars(new int[] {hour, mins, secs});
	}
	
	public String getCurrTimeInAscii() {
		cdr.setTimeInMillis(System.currentTimeMillis());
		int hour = cdr.get(Calendar.HOUR_OF_DAY);
		int mins = cdr.get(Calendar.MINUTE);
		int secs = cdr.get(Calendar.SECOND);
		int hnds = cdr.get(Calendar.MILLISECOND) / 10;

		return gnrtAsciiChars(new int[] {hour, mins, secs, hnds});
	}

	private String gnrtAsciiChars(int[] digtGrps) {
		sb.setLength(0); // Clear stringbuilder

		for ( int row=0; row<dgtHght; row++ ) { // Iterate first each ROW for each Digit
			appendAsciiForRow(digtGrps[0], row);

			// Get row for every group of digits and precede it with a column.
			for ( int i=1; i<digtGrps.length; i++) {
				sb.append( asciiDgts[10][row] );
				appendAsciiForRow(digtGrps[i], row);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private void appendAsciiForRow(int digt, int row) {
		int[] dgts = getSprtDgts(digt); // Split digit into an array of digits
		for ( int i=0; i<dgts.length; i++ ) {
			sb.append( asciiDgts[dgts[i]][row] );
		}
	}
	
	static private int[] getSprtDgts(int num) {
		int dgtsLen;
		if ( num < 10 ) // Pad all digits so they are length 2:
			dgtsLen = 2;
		else
			dgtsLen = (int) ( Math.log10(num) + 1 );

		int[] sprtDgts = new int[dgtsLen];
		for ( int i=0; i<dgtsLen; i++ ) {
			sprtDgts[dgtsLen-i-1] = (num % 10);
			num=num/10;
		}
		return sprtDgts;
	}
}
