package pl.shine.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import pl.shine.db.DbAccess;

public class TimeSlot {

	int year;
	int month;
	int day;
	int hour;
	boolean status;

	public TimeSlot(int day, int month, int year, int hour, boolean status) {
		super(); 
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.status = false;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
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

	public static int getTodaysHour() {
		final Calendar c = Calendar.getInstance();
		int todayYear = c.get(Calendar.HOUR_OF_DAY);
		return todayYear;
	}

	public static int getMaxDaysOfThisMonth() {
		final Calendar c = Calendar.getInstance();
		c.set(getTodaysYear(), getTodaysMonth() - 1, getTodaysDayOfMonth()); // -1
																				// dlatego,
																				// ¿e
																				// w
																				// getTodaysMonth()
																				// doda³em
																				// 1
																				// inaczej
																				// miesi¹c
																				// styczeñ
																				// =
																				// 0,
																				// luty
																				// =
																				// 1,
																				// itp.)
		int getMaxDaysOfThisMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return getMaxDaysOfThisMonth;
	}

	public static int getMaxDaysOfNextMonth() {
		final Calendar c = Calendar.getInstance();
		c.set(getTodaysYear(), getTodaysMonth(), getTodaysDayOfMonth());
		int getMaxDaysOfNextMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return getMaxDaysOfNextMonth;
	}

	public static int getReservationDay(String stringDate) throws IOException {
		try {
			String getDate = stringDate;
			String[] splitedDate = getDate.split("-"); // podana przez
														// u¿ytkownika data jest
														// dzielona na dzieñ,
														// miesi¹c, rok
			int reservationDay = Integer.parseInt(splitedDate[0]);
			return reservationDay;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		} catch (NumberFormatException e1) {
			return 0;
		}
	}

	public static int getReservationMonth(String stringDate) {
		try {
			String getDate = stringDate;
			String[] splitedDate = getDate.split("-"); // podana przez
														// u¿ytkownika data jest
														// dzielona na dzieñ,
														// miesi¹c, rok
			int reservationMonth = Integer.parseInt(splitedDate[1]);
			return reservationMonth;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		} catch (NumberFormatException e1) {
			return 0;
		}
	}

	public static int getReservationYear(String stringDate) {
		try {
			String getDate = stringDate;
			String[] splitedDate = getDate.split("-"); // podana przez
														// u¿ytkownika data jest
														// dzielona na dzieñ,
														// miesi¹c, rok
			int reservationYear = Integer.parseInt(splitedDate[2]);
			return reservationYear;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		} catch (NumberFormatException e1) {
			return 0;
		}
	}

	public static ArrayList<Integer> ListHour(String stringDate)
			throws NumberFormatException, ArrayIndexOutOfBoundsException,
			IOException {

		try {
			Calendar calendar = Calendar.getInstance();
			int presentDay = getTodaysDayOfMonth();
			int presentMonth = getTodaysMonth();
			int presentYear = getTodaysYear();

			int reservationDay = getReservationDay(stringDate);
			int reservationMonth = getReservationMonth(stringDate);
			int reservationYear = getReservationYear(stringDate);

			int maximumOfDaysThisMonth = getMaxDaysOfThisMonth();
			int maximumOfDaysNextMonth = getMaxDaysOfNextMonth();

			ArrayList<Integer> hoursToCompare = new ArrayList<Integer>();
			hoursToCompare = DbAccess.getHourFromDB(reservationYear, reservationMonth, reservationDay);
			
			ArrayList<Integer> availableHoursOfDay = new ArrayList<Integer>();
			{
				// w pierwszej kolejnoœci spradzane jest czy pierwsza wartoœæ
				// (rok) ma 4 cyfry itp.
				String asStringYear = String.valueOf(reservationYear);
				String asStringMonth = String.valueOf(reservationMonth);
				String asStringDay = String.valueOf(reservationDay);

				if (asStringYear.length() != 4) {
					JOptionPane
							.showMessageDialog(null,
									"Spróbuj jeszcze raz podaj¹c rok w formacie: RRRR!");
					return null;
				}
				if (asStringMonth.length() > 2) {
					JOptionPane.showMessageDialog(null,
							"Spróbuj jeszcze raz podaj¹c miesi¹c: M lub MM!");
					return null;
				}
				if (asStringDay.length() > 2) {
					JOptionPane.showMessageDialog(null,
							"Spróbuj jeszcze raz podaj¹c dzieñ: D lub DD!");
					return null;
				}

				if (presentYear == reservationYear) { // pozosta³em instrukcje
														// sprawdzaj¹ce
														// poprawnoœæ daty
					if (reservationMonth >= presentMonth) {
						if (reservationMonth <= presentMonth + 1) {
							if (presentDay == reservationDay) {
								if (reservationMonth == presentMonth) {
									if (reservationDay <= maximumOfDaysThisMonth) { // spradzenie czy  data dnia dla  danego miesi¹ca jest  <= maksymalnej iloœci dni
										calendar.getTime();
										int presentHour = calendar.get(Calendar.HOUR_OF_DAY); presentHour++; // rezerwacja jest mo¿liwa od nastêpnej godziny

										for (int i = presentHour; i <= 24; i++) {
											if (hoursToCompare != null) {
												for (int j = 0; j < hoursToCompare.size(); j++) {
													if (i != hoursToCompare.get(j)) {
														availableHoursOfDay.add(i);
													}
												}
											} else
												availableHoursOfDay.add(i);
										}
										return availableHoursOfDay;
									} else {
										JOptionPane
												.showMessageDialog(
														null,
														"Liczba dni w miesi¹cu: "
																+ reservationMonth
																+ " wynosi: "
																+ maximumOfDaysThisMonth);
									}
								}

								else {
									if (reservationDay <= maximumOfDaysNextMonth) {
										for (int i = 1; i <= 24; i++) {
											if (hoursToCompare != null) {
												for (int j = 0; j < hoursToCompare.size(); j++) {
													if (i != hoursToCompare.get(j)) {
														availableHoursOfDay.add(i);
													}
												}
											} else
												availableHoursOfDay.add(i);
										}
										return availableHoursOfDay;
									} else {
										JOptionPane
												.showMessageDialog(
														null,
														"Liczba dni w miesi¹cu: "
																+ reservationMonth
																+ " wynosi: "
																+ maximumOfDaysNextMonth);
									}
								}
							} else if (presentDay < reservationDay) {
								if (reservationMonth == presentMonth) {
									if (reservationDay <= maximumOfDaysThisMonth) {
										for (int i = 1; i <= 24; i++) {
											if (hoursToCompare != null) {
												for (int j = 0; j < hoursToCompare.size(); j++) {
													if (i != hoursToCompare.get(j)) {
														availableHoursOfDay.add(i);
													}
												}
											} else
												availableHoursOfDay.add(i);
										}
										return availableHoursOfDay;
									} else {
										JOptionPane
												.showMessageDialog(
														null,
														"Liczba dni w miesi¹cu: "
																+ reservationMonth
																+ " wynosi: "
																+ maximumOfDaysThisMonth);
									}
								} else {
									if (reservationDay <= maximumOfDaysNextMonth) {
										for (int i = 1; i <= 24; i++) {
											if (hoursToCompare != null) {
												for (int j = 0; j < hoursToCompare.size(); j++) {
													if (i != hoursToCompare.get(j)) {
														availableHoursOfDay.add(i);
													}
												}
											} else
												availableHoursOfDay.add(i);
										}
										return availableHoursOfDay;
									} else {
										JOptionPane
												.showMessageDialog(
														null,
														"Liczba dni w miesi¹cu: "
																+ reservationMonth
																+ " wynosi: "
																+ maximumOfDaysNextMonth);
									}
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Podana data jest dat¹ historyczn¹");
							}

						} else {
							JOptionPane
									.showMessageDialog(null,
											"Rezerwacja dostêpna w najbli¿szych dwóch miesi¹cach!");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Podany miesi¹c jest miesi¹cem historycznym!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Rezerwacja dostêpna tylko na rok: " + presentYear);
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Niepoprawny format daty! Spróbuj DD-MM-RRRR");
		}
		return null;
	}

	public void dodanie() {
		Calendar calendar = Calendar.getInstance();
		int presentDay = getTodaysDayOfMonth();
		int presentMonth = getTodaysMonth();
		int presentYear = getTodaysYear();
		int presentHour = getTodaysHour();
		int maximumOfDaysThisMonth = getMaxDaysOfThisMonth();
		int maximumOfDaysNextMonth = getMaxDaysOfNextMonth();
		String data = null;
		for (int i = presentDay; i <= maximumOfDaysThisMonth; i++) {
			day = i;
			month = presentMonth;
			year = presentYear;
			
			if (day == presentDay) {
				for (int j = presentHour; j < 24; j++) {
					hour = j;
					data=Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+"-"+Integer.toString(hour)+":00";
					DbAccess.insertTimeSlot(day, month, year, hour,status,data);
				}
			} else {
				for (int j = 0; j < 24; j++) {
					hour = j;
					data=Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+"-"+Integer.toString(hour)+":00";
					DbAccess.insertTimeSlot(day, month, year, hour,status,data);
				}
				if (day == maximumOfDaysThisMonth) {
					for (int k = 1; k < (31 - (maximumOfDaysThisMonth - presentDay)); k++) {
						day = k;
						month = presentMonth+1;
						year = presentYear;
						for (int j = 0; j < 24; j++) {
							hour = j;
							data=Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year)+"-"+Integer.toString(hour)+":00";
							DbAccess.insertTimeSlot(day, month, year, hour,status,data);
						}
					}
			}
			}
		}
	}
	
}
