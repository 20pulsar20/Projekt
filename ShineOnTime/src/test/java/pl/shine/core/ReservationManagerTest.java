package pl.shine.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReservationManagerTest {
	ReservationManager rm;
	Reservation r;

	TimeSlot ts;

	@Before
	public void inicjujTesty() {
		ts = new TimeSlot(2012, 12, 31, 17);
		r = new Reservation(ts, "jan.kowalski@gmail.com");
	}

	@Test
	public void addReservation() {
		Reservation res = rm.addReservation(ts,"jan.kowalski@gmail.com");
		assertEquals(r.email, res.email);
		assertEquals(true, ts.getStatus());
	}

	@Test
	public void addReservationfalse() {
		ts.setStatus(true);
		assertEquals(null, rm.addReservation(ts, "jan.kowalski@gmail.com"));
	}
	@Test
	public void delReservation() {
		ts.setStatus(true);
		rm.delReservation(ts, "jan.kowalski@gmail.com");
		assertEquals(false, ts.getStatus());
	}
}
