package pl.shine.core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class TimeSlotTest {

	@Before
//	public void inicjujTesty() {
	//	TimeSlot ts = new TimeSlot;

	
	
	@Test
	public void testPoprawnoscDaty() {
		int todayDay = TimeSlot.getTodaysDayOfMonth();
		int todayMonth = TimeSlot.getTodaysMonth();
		int todayYear = TimeSlot.getTodaysYear();
		
		int reservationDay = 13;	//przy puszczaniu testu zmieniæ datê na dzisiejsz¹
		int reservationMonth = 05;
		int reservationYear = 2014;
		
		assertEquals(reservationDay, todayDay);
		assertEquals(reservationMonth, todayMonth);
		assertEquals(reservationYear, todayYear);		
	}
	
	@Test
	public void testMaksymalnaIloscDniWMiesiacu() {
		int maxDayOfThisMonth = TimeSlot.getMaxDaysOfThisMonth();
		int maxDayOfNextMonth = TimeSlot.getMaxDaysOfNextMonth();
		
		int verifyLenghtOfMay = 31;
		int verifyLenghtOfJune = 30;
		
		assertEquals(verifyLenghtOfMay, maxDayOfThisMonth);
		assertEquals(verifyLenghtOfJune, maxDayOfNextMonth);
		
		
	}
	
	@Test
	public void testPodzialDate() throws IOException {
		String stringDate = "25-05-2014";
	    int reservationDay = TimeSlot.getReservationDay(stringDate);
	    int reservationMonth = TimeSlot.getReservationMonth(stringDate);
	    int reservationYear = TimeSlot.getReservationYear(stringDate);
		
		int day = 25;
		int month = 05;
		int year = 2014;
		
		assertEquals(day, reservationDay);
		assertEquals(month, reservationMonth);
		assertEquals(year, reservationYear);
		
	}

}
