package pl.shine.core;

import java.sql.Date;

public class ReservationManager {
	
	Date data;
	int hour;
	String email;
	
	public ReservationManager(Date data, int hour, String email) {
		this.data = data;
		this.hour = hour;
		this.email = email;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
