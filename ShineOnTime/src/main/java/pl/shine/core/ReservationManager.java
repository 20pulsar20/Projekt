package pl.shine.core;

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

}
