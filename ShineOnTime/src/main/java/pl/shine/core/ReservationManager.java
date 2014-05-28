package pl.shine.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.shine.db.ReservationDao;

public class ReservationManager {

	private ReservationDao reservationDao;
	
	public ReservationManager(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public void addReservation(Reservation reservation) {
		reservationDao.save(reservation);
	}

	public void deleteReservation(Reservation reservation) {
		reservationDao.delete(reservation);
	}

	public List<TimeSlot> getAvailableTimeSlots(Date userDate) {
		// TODO: napisaæ tê metodê
		List<TimeSlot> list = new ArrayList<>();
		list.add(new TimeSlot(new Date()));
		return list;
	}

}
