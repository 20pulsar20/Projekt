package pl.shine.core;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ReservationTest {
	
	Date date = new Date();
	TimeSlot ts = new TimeSlot(date);
	String email = "jan.kowalski@gmail.com";
	Reservation r = new Reservation(ts, email);
	
	
	@Test
	public void testGetEmail() {
		assertEquals("jan.kowalski@gmail.com",r.getEmail());
	}
	@Test
	public void testSetEmail() {
		r.setEmail("janek.kowalski@gmail.com");
		assertEquals("janek.kowalski@gmail.com", r.getEmail());
	}
	@Test
	public void testGetTimeSlot() {
		assertEquals(ts, r.getTimeSlot());
	}
	
	@Test
	public void testSetTimeSlot() {
		Date date1 = new Date();
		TimeSlot ts1 =new TimeSlot(date1);
		r.setTimeSlot(ts1);
		assertEquals(ts1, r.getTimeSlot());
	}
}
		
	
