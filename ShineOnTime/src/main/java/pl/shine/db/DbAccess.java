package pl.shine.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DbAccess {
	private String DB_URL = "jdbc:sqlite:ShineDatabase.db";
	private Path initDbFile = new File("ShineDatabaseStructure.sql").toPath();
	public static Statement stat;
	private static Connection conn;
	public static ArrayList<Date> DateInDB;

	public void initializeDb() throws ClassNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC");// zainicjownie biblioteki
		} catch (ClassNotFoundException e) {
			System.err.println("Brak Sterownika JDBC");
			e.printStackTrace();
		}
		try {

		conn = DriverManager.getConnection(DB_URL); // stworzenie po³¹czenia do bazy
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Problem z otwarciem po³¹czenia");
			e.printStackTrace();
		}
		createTables();
	}

	public boolean createTables() {
		String createTimeSlot = "CREATE TABLE IF NOT EXISTS TimeSlot (id_timeslot INTEGER PRIMARY KEY AUTOINCREMENT, day int, month int, year int, hour int, status boolean, data string)";
		String createReservation = "CREATE TABLE IF NOT EXISTS Reservation (id_reservation INTEGER PRIMARY KEY AUTOINCREMENT, id_timeslot int, email varchar(255))";

		try {
			stat.execute(createTimeSlot);
			stat.execute(createReservation);
		} catch (SQLException e) {
			System.err.println("Blad przy tworzeniu tabeli");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean insertTimeSlot(int day, int month, int year, int hour, boolean status, String data) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("insert into TimeSlot (day, month, year, hour, status, data) values (?, ?, ?,?,?, ?);");
			prepStmt.setInt(1, day);
			prepStmt.setInt(2, month);
			prepStmt.setInt(3, year);
			prepStmt.setInt(4, hour);
			prepStmt.setBoolean(5, status);
			prepStmt.setString(6, data);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu TimeSlot");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertReservation(int id_timeslot, String email) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement("insert into TimeSlot values (NULL, ?, ?, ?,?);");
			prepStmt.setInt(1, id_timeslot);
			prepStmt.setString(2, email);
			prepStmt.execute();
		} catch (SQLException e) {
			System.err.println("Blad przy wstawianiu Rezerwacji");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	//Sets date and hour to compare with existing in DB
	public static Date setDeklarDateWithHour(int yearDeklar, int monthDeklar, int dayDeklar, int hourDeklar){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:00");
		String dateInString = Integer.toString(dayDeklar)+"-"+Integer.toString(monthDeklar)+"-"+Integer.toString(yearDeklar)+" "+Integer.toString(hourDeklar)+":00";
		try {
			Date deklarDate = formatter.parse(dateInString);
			return deklarDate;
			
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}
			
	}
	
	//Checks date and hour from db
	public static boolean checkDateAndHour(int yearDeklar, int monthDeklar, int dayDeklar, int hourDeklar) {

        boolean availability = false;
        
		String selectTimeSlot = "SELECT day, month, year, hour FROM TimeSlot where status = 'true'";
		ResultSet rs;
		ArrayList<Date> DateInDB = new ArrayList<Date>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:00");
		dateChecking:
		try {
	        rs = stat.executeQuery(selectTimeSlot);
	        while (rs.next()) {
	        	int rsk = 1;
	            int year = rs.getInt("year");
	            int month = rs.getInt("month");
	            int day = rs.getInt("day");
	            int hour = rs.getInt("hour");

	    		String dateInString = Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+" "+Integer.toString(hour)+":00";
				Date dateWBazie;
				dateWBazie = formatter.parse(dateInString);
				DateInDB.add(dateWBazie);
				rsk++;
				for (int i=0; i<rsk; i++) {
					if (dateWBazie.compareTo(setDeklarDateWithHour(yearDeklar, monthDeklar, dayDeklar, hourDeklar))==0) {
						availability = false;
						break dateChecking;
					}
					else {
						availability = true;
					}	
	            }
	        }
	        
		} catch (SQLException | java.text.ParseException e2) {
			System.err.println("Blad przy sprawdzaniu dostêpnoœci terminu");
			e2.printStackTrace();
			return availability;
		}
		return availability;
	}


	//Sets date to compare with existing in DB
	public static Date setDeklarDate(int yearDeklar, int monthDeklar, int dayDeklar){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:00");
		String dateInString = Integer.toString(dayDeklar)+"-"+Integer.toString(monthDeklar)+"-"+Integer.toString(yearDeklar);
		try {
			Date deklarDate = formatter.parse(dateInString);
			return deklarDate;
			
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}
			
	}

	//Gets hours for date from db
	public static ArrayList<Integer> getHourFromDB(int yearDeklar, int monthDeklar, int dayDeklar) {

        
		String selectTimeSlot = "SELECT day, month, year, hour FROM TimeSlot where status = 'true'";
		ResultSet rs;
	//	ArrayList<Date> DateInDB = new ArrayList<Date>();
		ArrayList<Integer> HoursInDB = new ArrayList<Integer>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:00");
		try {
	        rs = stat.executeQuery(selectTimeSlot);
	        while (rs.next()) {
	            int year = rs.getInt("year");
	            int month = rs.getInt("month");
	            int day = rs.getInt("day");
	            int hour = rs.getInt("hour");

	    		String dateInString = Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+" "+Integer.toString(hour) + ":00";
				Date dateInDB;
				dateInDB = formatter.parse(dateInString);
				for (int i = 0; i <= 23; i++) {

		    		String dateTMPInString = Integer.toString(dayDeklar)+"-"+Integer.toString(monthDeklar)+"-"+Integer.toString(yearDeklar)+" "+Integer.toString(i) + ":00";
					Date dateTMP;
					dateTMP = formatter.parse(dateTMPInString);
					if (dateTMP.compareTo(dateInDB)==0) {
						HoursInDB.add(i);
					}
	            }
	        }
	        
		} catch (SQLException | java.text.ParseException e2) {
			System.err.println("Blad przy sprawdzaniu dostêpnoœci terminu");
			e2.printStackTrace();
			HoursInDB = null;
		}
		return HoursInDB;

	}
	
	public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}
