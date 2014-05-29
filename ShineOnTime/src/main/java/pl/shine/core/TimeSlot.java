package pl.shine.core;

import java.util.Calendar;
import java.util.Date;

public class TimeSlot {

	private Calendar start;

	public TimeSlot(Date date) {
		this.start = Calendar.getInstance();
		this.start.setTime(date);
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public int getHour() {
		return start.get(Calendar.HOUR_OF_DAY);
	}

	public int getDay() {
		return start.get(Calendar.DAY_OF_MONTH);
	}

	public int getMonth() {
		return start.get(Calendar.MONTH) + 1;
	}

	public int getYear() {
		return start.get(Calendar.YEAR);
	}

}
