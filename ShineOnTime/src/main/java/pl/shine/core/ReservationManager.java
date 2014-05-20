package pl.shine.core;

import java.sql.Date;

import pl.shine.db.DbAccess;

public class ReservationManager {

	
	public ReservationManager() {
		
	}

	public static Reservation addReservation(TimeSlot ts, String email) {

		if (ts.getStatus() == false) {
			ts.setStatus(true);
			return new Reservation(ts, email);
			
		} else
			System.out.println("Termin jest zajêty");
		return null;
	}

	public static void delReservation(TimeSlot ts, String email) {

		if (ts.getStatus() == true) {
			ts.setStatus(false);
			// usuniecie z bazy danych
		} else
			System.out.println("W danym terminie nie ma rezerwacji");
	
	}

}
