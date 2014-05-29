package pl.shine.core;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.stream.events.StartDocument;

import org.junit.Before;
import org.junit.Test;

public class TimeSlotTest {
	private int day;
	private int month;
	private int year;
	private int hour;
	
	Date date = new Date();
	Calendar cal = Calendar.getInstance();
	TimeSlot ts = new TimeSlot(date);
	
	
	@Before
	public void inicjujTesty() {
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH)+1;
		year = cal.get(Calendar.YEAR);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		
	}

	@Test
	public void testGetStart() {
		cal.setTime(date);
		assertEquals(cal, ts.getStart());
	}

	@Test
	public void testSetStart() {
		Calendar cal2 = Calendar.getInstance();
		ts.setStart(cal2);
		assertEquals(cal2, ts.getStart());
	}

	@Test
	public void testGetHour() {
		assertEquals(hour, 17);
	}

	@Test
	public void testGetDay() {
		assertEquals(day, 29);
	}

	@Test
	public void testGetMonth() {
		assertEquals(month, 5);
	}

	@Test
	public void testGetYear() {
		assertEquals(year, 2014);
	}

}
