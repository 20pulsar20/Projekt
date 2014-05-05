package pl.shine.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import pl.shine.gui.gui;

public class TimeSlot {
	
	public static Date reservationDate; //sam nie wiem czy ta klasa powinna mieæ te atrybuty czy tylko metodê generuj¹c¹ godziny
	int hour; //jeszcze jedna metoda usuwaj¹ca te wiersze z tabelki availableHoursOfDay, które s¹ ju¿ zarezerwowane
	
	
	public TimeSlot(Date reservationDate, int hour) {
		TimeSlot.reservationDate = reservationDate;
		this.hour = hour;	
	}

	public Date getData() {
		return reservationDate;
	}

	public void setData(Date reservationDate) {
		TimeSlot.reservationDate = reservationDate;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public static int getTodaysDayOfMonth() {
		  final Calendar c = Calendar.getInstance();
		  int todayDay = c.get(Calendar.DAY_OF_MONTH);
		  return todayDay;
		}
	
	public static int getTodaysMonth() {
		  final Calendar c = Calendar.getInstance();
		  int todaysMonth = c.get(Calendar.MONTH) + 1;
		  return todaysMonth;
		}
	
	public static int getTodaysYear() {
		  final Calendar c = Calendar.getInstance();
		  int todayYear = c.get(Calendar.YEAR);
		  return todayYear;
		}

	
	 
	public static ArrayList<Integer> ListHour(String stringDate) throws NumberFormatException, ArrayIndexOutOfBoundsException, IOException {
		
		try {
		Calendar calendar = Calendar.getInstance();
		int presentDay = getTodaysDayOfMonth();
		int presentMonth = getTodaysMonth();
		int presentYear = getTodaysYear();

	    String getDate = stringDate;
	    String[] splitedDate = getDate.split("-"); //podana przez u¿ytkownika data jest dzielona na dzieñ, miesi¹c, rok
	    int reservationYear = Integer.parseInt(splitedDate[0]);
	    int reservationMonth = Integer.parseInt(splitedDate[1]);
	    int reservationDay = Integer.parseInt(splitedDate[2]);
	    
		calendar.set(presentYear, presentMonth -1, presentDay); //-1 dlatego, ¿e w getTodaysMonth() doda³em 1 (inaczej miesi¹c styczeñ = 0, luty = 1, itp.)
		int maximumOfDaysThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		calendar.set(presentYear, presentMonth, presentDay);
		int maximumOfDaysNextMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    
		ArrayList<Integer> availableHoursOfDay = new ArrayList<Integer>();	{
			//w pierwszej kolejnoœci spradzane jest czy pierwsza wartoœæ (rok) ma 4 cyfry itp.
			String asStringYear = String.valueOf(reservationYear);
			String asStringMonth = String.valueOf(reservationMonth);
			String asStringDay = String.valueOf(reservationDay);
			
			if (asStringYear.length() != 4){
				JOptionPane.showMessageDialog(null, "Spróbuj jeszcze raz podaj¹c rok w formacie: RRRR!");
				return null;
			}
			if (asStringMonth.length() > 2){
				JOptionPane.showMessageDialog(null, "Spróbuj jeszcze raz podaj¹c miesi¹c: M lub MM!");
				return null;
			}
			if (asStringDay.length() > 2){
				JOptionPane.showMessageDialog(null, "Spróbuj jeszcze raz podaj¹c dzieñ: D lub DD!");
				return null;
			}
				
			
			if (presentYear == reservationYear)	{	//pozosta³em instrukcje sprawdzaj¹ce poprawnoœæ daty
				if (reservationMonth >= presentMonth)	{
					if (reservationMonth <= presentMonth +1)	{
						if (presentDay == reservationDay) {
							if (reservationMonth == presentMonth) {	
								if (reservationDay <= maximumOfDaysThisMonth) { //spradzenie czy data dnia dla danego miesi¹ca jest <= maksymalnej iloœci dni
									calendar.getTime();
									int presentHour = calendar.get(Calendar.HOUR_OF_DAY);
									presentHour ++; //rezerwacja jest mo¿liwa od nastêpnej godziny
									for (int i = presentHour; i <= 24; i++) {
									//	if (i != calendar.equals(/*godziny pobrane z bazy danych?????*/)) {
											availableHoursOfDay.add(i);
									//	}
									}
									return availableHoursOfDay;	
								}
								else {
									JOptionPane.showMessageDialog(null, "Liczba dni w miesi¹cu: " + reservationMonth + " wynosi: " + maximumOfDaysThisMonth);
								}
							}
							
							else {
								if (reservationDay <= maximumOfDaysNextMonth) {
									for (int i = 1; i <= 24; i++) {
										availableHoursOfDay.add(i);
										}
									return availableHoursOfDay;
								}
								else {
									JOptionPane.showMessageDialog(null, "Liczba dni w miesi¹cu: " + reservationMonth + " wynosi: " + maximumOfDaysNextMonth);
								}
					        }
				        } 
						else if (presentDay < reservationDay) {
							if (reservationMonth == presentMonth) {
								if (reservationDay <= maximumOfDaysThisMonth) {
									for (int i = 1; i <= 24; i++) {
										availableHoursOfDay.add(i);
										}
									return availableHoursOfDay;
								}
								else {
									JOptionPane.showMessageDialog(null, "Liczba dni w miesi¹cu: " + reservationMonth + " wynosi: " + maximumOfDaysThisMonth);
								}
							}
							else {
								if (reservationDay <= maximumOfDaysNextMonth) {
									for (int i = 1; i <= 24; i++) {
										availableHoursOfDay.add(i);
										}
									return availableHoursOfDay;
								}
								else {
									JOptionPane.showMessageDialog(null, "Liczba dni w miesi¹cu: " + reservationMonth + " wynosi: " + maximumOfDaysNextMonth);
								}
							}
				        }
						else {
							JOptionPane.showMessageDialog(null, "Podana data jest dat¹ historyczn¹");
				        } 

					}
					else {
						JOptionPane.showMessageDialog(null, "Rezerwacja dostêpna w najbli¿szych dwóch miesi¹cach!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Podany miesi¹c jest miesi¹cem historycznym!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Rezerwacja dostêpna tylko na rok: " + presentYear);
			}
		}
	} 
	catch(NumberFormatException e){
	JOptionPane.showMessageDialog(null, "Niepoprawny format daty! Spróbuj RRRR-MM-DD");
	}
		return null;
	} 

	
}
