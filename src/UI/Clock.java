package UI;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clock {
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
	
	static public void print(int hour, int mins, int secs) {
		System.out.println(gnrtAsciiChars(hour, mins, secs));
	}
	
	static public void prntCurrTime() {
		Calendar cdr = Calendar.getInstance(); // creates a new calendar instance
		int hour = cdr.get(Calendar.HOUR_OF_DAY);
		int mins = cdr.get(Calendar.MINUTE);
		int secs = cdr.get(Calendar.SECOND);

		System.out.println(gnrtAsciiChars(hour, mins, secs));
	}

	static private String gnrtAsciiChars(int hour, int mins, int secs) {
		String rtrnValu = "";

		int[] sprtDgtsH = getSprtDgts(hour);
		int[] sprtDgtsM = getSprtDgts(mins);
		int[] sprtDgtsS = getSprtDgts(secs);

		for ( int row=0; row<asciiDgts[0].length; row++ ) {
			for ( int i=0; i<sprtDgtsH.length; i++ ) {
				rtrnValu += asciiDgts[sprtDgtsH[i]][row];
			}
			rtrnValu += asciiDgts[10][row]; // Column
			for ( int i=0; i<sprtDgtsM.length; i++ ) {
				rtrnValu += asciiDgts[sprtDgtsM[i]][row];
			}
			rtrnValu += asciiDgts[10][row]; // Column
			for ( int i=0; i<sprtDgtsS.length; i++ ) {
				rtrnValu += asciiDgts[sprtDgtsS[i]][row];
			}
			rtrnValu += "\n";
		}
		return rtrnValu;
	}
	
	static private int[] getSprtDgts(int num) {
		int dgtsLen = (int) ( Math.log10(num) + 1 );

		// Since we fill in the digits from the right, if we simply do length++, then the leftmost digit will get the
		// value 0, so it's padded correctly.
		if ( dgtsLen == 1 )
			dgtsLen++;
		
		int[] sprtDgts = new int[dgtsLen];
		for ( int i=0; i<dgtsLen; i++ ) {
			sprtDgts[dgtsLen-i-1] = (num % 10);
			num=num/10;
		}
		return sprtDgts;
	}

	public void run() {
	    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
	    	@Override
	    	public void run() {
	    		prntCurrTime();
	    	}
	    }, 0, 1, TimeUnit.SECONDS);
	}
}
