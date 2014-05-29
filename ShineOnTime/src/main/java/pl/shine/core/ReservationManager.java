package pl.shine.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.shine.db.ReservationDAO;

public class ReservationManager {

	private ReservationDAO reservationDao;
	
	public ReservationManager(ReservationDAO reservationDao) {
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
