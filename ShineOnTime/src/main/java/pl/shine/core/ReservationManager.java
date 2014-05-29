package pl.shine.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.shine.db.ReservationDAO;

public class ReservationManager {

	private ReservationDAO reservationDao;
	
	public ReservationManager(ReservationDAO reservationDao) {
		this.reservationDao = reservationDao;
	}

	public List<TimeSlot> getAvailableTimeSlots(Date userDate) {
		
		List<Reservation> reservations = reservationDao.findReservations(userDate);
		List<TimeSlot> availableTimeSlots = new ArrayList<>(); 
		Reservation[] reservedTimeSlots = new Reservation[24];
		Calendar userCalendar = Calendar.getInstance();
		userCalendar.setTime(userDate);
		
		for (Reservation reservation : reservations)
		{
			int hour = reservation.getTimeSlot().getHour();
			reservedTimeSlots[hour] = reservation;
		}
		
		for (int hour = 0; hour < 24; hour++)
		{
			if (reservedTimeSlots[hour] == null)
			{
				Calendar calendar = Calendar.getInstance();
				calendar.set(userCalendar.get(Calendar.YEAR), userCalendar.get(Calendar.MONTH), 
						userCalendar.get(Calendar.DAY_OF_MONTH), hour, 0);
				availableTimeSlots.add(new TimeSlot(calendar.getTime()));
			}
		}
		
		return availableTimeSlots;
	}

	public boolean makeReservation(int yearGUI, int monthGUI, int dayGUI, int hourGUI, String email) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(yearGUI, monthGUI, dayGUI, hourGUI, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		Reservation reservation = new Reservation(new TimeSlot(calendar.getTime()), email);
		return reservationDao.save(reservation);
	}

}
