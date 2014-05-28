package pl.shine.core;

import java.util.Date;

public class TimeSlot {

	private Date start;
	private Integer duration;

	public TimeSlot(Date start, Integer duration) {
		this.start = start;
		this.duration = duration;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
