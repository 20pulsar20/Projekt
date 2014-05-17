package pl.shine.core;

public class Reservation {
	
	String reservation_start;
	String reservation_end;
	String email;
	

	public Reservation(String reservation_start, String reservation_end, String email) {
		this.reservation_start = reservation_start;
		this.reservation_end = reservation_end;
		this.email = email;
		
	}


	public String getReservation_start() {
		return reservation_start;
	}


	public void setReservation_start(String reservation_start) {
		this.reservation_start = reservation_start;
	}


	public String getReservation_end() {
		return reservation_end;
	}


	public void setReservation_end(String reservation_end) {
		this.reservation_end = reservation_end;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
