package pl.shine.core;

public class Reservation {
	private TimeSlot timeSlot;
	private String email;

	public Reservation(TimeSlot timeSlot, String email) {
		this.timeSlot = timeSlot;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot){
		this.timeSlot = timeSlot;
	}
}
