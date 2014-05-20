package pl.shine.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Reservation {
	TimeSlot ts;
	String email;

	public Reservation(TimeSlot ts, String email) {
		super();
		this.ts = ts;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TimeSlot getTs() {
		return ts;
	}

	

}
