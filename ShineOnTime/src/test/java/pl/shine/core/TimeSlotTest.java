package pl.shine.core;

import static org.junit.Assert.*;
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
		
		int reservationDay = 02;
		int reservationMonth = 05;
		int reservationYear = 2014;
		
		assertEquals(reservationDay, todayDay);
		assertEquals(reservationMonth, todayMonth);
		assertEquals(reservationYear, todayYear);
		
		
	}

}
