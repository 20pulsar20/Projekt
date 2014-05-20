package pl.shine.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReservationTest {
	Reservation r;
	TimeSlot ts;
	TimeSlot mockTimeSlot;
	@Before
	public void inicjujTesty() {
		ts = new TimeSlot(2012,12,31,17);
		r = new Reservation(ts,"jan.kowalski@gmail.com");
		

	}

	@Test
	public void testGetEmail() {
		assertEquals("jan.kowalski@gmail.com", r.getEmail());
	}
	@Test
	public void testSetEmail() {
		r.setEmail("janek.kowalski@gmail.com");
		assertEquals("janek.kowalski@gmail.com", r.getEmail());
	}
	@Test
	public void testGetTs() {
		assertEquals(ts, r.getTs());
	}
	
}
		
	
