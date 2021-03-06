package pl.shine.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pl.shine.core.ReservationManager;
import pl.shine.core.TimeSlot;
import pl.shine.db.DbAccess;
import pl.shine.db.ReservationDAO;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	static JPanel myPanel;
	JButton myButton_hours;
	JLabel myLabel_info;
	JLabel myLabel;
	JTextArea myTextArea_blank;
	JTextArea myTextArea_info;
	JTextArea myTextArea_list;
	JTextField myTextField_date;
	JTextArea myTextArea_reservation;
	JTextArea myTextArea_reservationDate;
	JTextArea myTextArea_reservationHour;
	JTextArea myTextArea_reservationEmail;
	JTextArea myTextArea_reservationAfterField;
	JTextField myTextField_reservationDay;
	JTextField myTextField_reservationMonth;
	JTextField myTextField_reservationYear;
	JTextField myTextField_reservationHour;
	JTextField myTextField_reservationEmail;
	JButton myButton_reservation;
	JButton myButton_cancel;
	JButton myButton_close;
	
	private ReservationManager reservationManager;

	public Gui() {
		DbAccess dbAccess = new DbAccess();
		ReservationDAO reservationDao = new ReservationDAO(dbAccess);
		reservationManager = new ReservationManager(reservationDao);
				
		myPanel = new JPanel();
		myPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		myPanel.setBackground(Color.WHITE);

		myButton_hours = new JButton("Akceptuj");
		myButton_hours.setToolTipText("Kliknij aby wy�wietli� dost�pne godziny");

		myButton_reservation = new JButton("Rezerwuj");
		myButton_reservation.setToolTipText("Kliknij aby zarezerwowa� myjni�");

		myButton_cancel = new JButton("Anuluj");
		myButton_cancel.setToolTipText("Kliknij aby anulowa� rezerwacj�");

		myButton_close = new JButton("Zamknij");
		myButton_close.setToolTipText("Kliknij aby zamkn�� program");

		myLabel = new JLabel();
		myLabel_info = new JLabel();

		// textarea do pomocy przy u�o�eniu r�nych komponent�w
		final JTextArea myTextArea_blank = new JTextArea(2, 40);
		myTextArea_blank.setEditable(false);

		JLabel logo = new JLabel(new ImageIcon("shineontime.jpg")); // obrazek
																	// luzem w
																	// folderze
																	// 'ShineOntime'
		logo.setBorder(new EmptyBorder(10, 20, 40, 20));

		final JTextField myTextField_date = new JTextField(15);
		myTextField_date.setHorizontalAlignment(SwingConstants.CENTER);
		myTextField_date.setToolTipText("Wpisz dat�: DD-MM-RRRR");
		myTextField_date.setFont(new Font("Serif", Font.PLAIN, 12));
		myTextField_date.setEditable(true);
		myLabel_info.setBorder(new EmptyBorder(5, 0, 15, 40));

		myLabel_info.setText("Wpisz dat� rezerwacji: ");
		myLabel_info.setBorder(new EmptyBorder(10, 50, 10, 40));
		myLabel_info.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		myLabel_info.setVisible(true);

		final JTextArea myTextArea_list = new JTextArea("");
		myTextArea_list.setWrapStyleWord(true);
		myTextArea_list.setColumns(2);
		myTextArea_list.setLineWrap(true);
		myTextArea_list.setPreferredSize(new Dimension(400, 150));
		myTextArea_list.setEditable(false);
		myTextArea_list.setBorder(new EmptyBorder(20, 20, 50, 20));

		final JTextArea myTextArea_reservationDate = new JTextArea("");
		myTextArea_reservationDate.setText("Data:");
		myTextArea_reservationDate.setBorder(new EmptyBorder(20, 80, 10, 40));
		myTextArea_list.setEditable(false);

		final JTextArea myTextArea_reservationHour = new JTextArea("");
		myTextArea_reservationHour.setText("Godzina:");
		myTextArea_reservationHour.setBorder(new EmptyBorder(20, 65, 10, 40));
		myTextArea_list.setEditable(false);

		final JTextArea myTextArea_reservationYear = new JTextArea("");
		myTextArea_reservationYear.setText("Email");
		myTextArea_reservationYear.setBorder(new EmptyBorder(20, 30, 10, 70));
		myTextArea_list.setEditable(false);

		final JTextArea myTextArea_reservationAfterField = new JTextArea("");
		myTextArea_reservationAfterField.setText("");
		myTextArea_reservationAfterField.setBorder(new EmptyBorder(0, 190, 0, 180));
		myTextArea_list.setEditable(false);

		final JTextField myTextField_reservationDay = new JTextField(5);
		myTextField_reservationDay.setHorizontalAlignment(SwingConstants.CENTER);
		myTextField_reservationDay.setEditable(false);

		final JTextField myTextField_reservationMonth = new JTextField(5);
		myTextField_reservationMonth.setHorizontalAlignment(SwingConstants.CENTER);
		myTextField_reservationMonth.setEditable(false);

		final JTextField myTextField_reservationYear = new JTextField(7);
		myTextField_reservationYear.setHorizontalAlignment(SwingConstants.CENTER);
		myTextField_reservationYear.setEditable(false);

		final JTextField myTextField_reservationHour = new JTextField(5);
		myTextField_reservationHour.setHorizontalAlignment(SwingConstants.CENTER);

		final JTextField myTextField_reservationEmail = new JTextField(15);
		myTextField_reservationEmail.setHorizontalAlignment(SwingConstants.CENTER);

		myPanel.add(myTextArea_blank);
		myPanel.add(logo);
		myPanel.add(myTextArea_blank);
		myPanel.add(myTextArea_blank);
		myPanel.add(myLabel_info);
		myPanel.add(myTextField_date);
		myPanel.add(myTextArea_blank);
		myPanel.add(myButton_hours);
		myPanel.add(myTextArea_list);
		myPanel.add(myTextArea_blank);
		myPanel.add(myTextArea_reservationDate);
		myPanel.add(myTextArea_reservationHour);
		myPanel.add(myTextArea_reservationYear);
		myPanel.add(myTextField_reservationDay);
		myPanel.add(myTextField_reservationMonth);
		myPanel.add(myTextField_reservationYear);
		myPanel.add(myTextField_reservationHour);
		myPanel.add(myTextField_reservationEmail);
		myPanel.add(myTextArea_reservationAfterField);
		myPanel.add(myButton_reservation);
		myPanel.add(myButton_cancel);
		myPanel.add(myTextArea_blank);
		myPanel.add(myButton_close);
		getContentPane().add(myPanel);

		// wykorzystanie TimeSlot.ListHour aby pobra� dat� oraz wylistowa�
		// dost�pne godziny
		myButton_hours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// doda� textSelect albo co� w tym stylu dla p�l ju�
				// zarezerwowanych
				myTextArea_list.setText("");
				String date = myTextField_date.getText();
				Date userDate = null;
				try {
					userDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
					TimeSlot timeSlot = new TimeSlot(userDate);
					myTextField_reservationDay.setText(String.valueOf(timeSlot.getDay()));
					myTextField_reservationMonth.setText(String.valueOf(timeSlot.getMonth()));
					myTextField_reservationYear.setText(String.valueOf(timeSlot.getYear()));
				} catch (ParseException e) { // | IOException
					JOptionPane.showMessageDialog(null, "B��d przy podawaniu daty! Spr�buj DD-MM-RRRR");
					myTextArea_list.setText("Podaj poprawne dane");
					myTextField_reservationDay.setText("");
					myTextField_reservationMonth.setText("");
					myTextField_reservationYear.setText("");
					myTextField_date.setText("");
				}
				try {
					
					List<TimeSlot> timeSlots = reservationManager.getAvailableTimeSlots(userDate);
					if (timeSlots != null) {
						myTextArea_list.setFont(new Font("Serif", Font.BOLD, 15));
						myTextArea_list.setText("Dost�pne godziny: " + "\n" + "\n");
						for (TimeSlot timeSlot : timeSlots) {
							myTextArea_list.setFont(new Font("Serif", Font.ITALIC, 14));
							myTextArea_list.append(timeSlot.getHour() + "\t"); // "\n" - dla
																// nowej linii
						}
					} else {
						myTextArea_list.setText("Podaj poprawne dane: DD-MM-RRRR");
						myTextField_reservationDay.setText("");
						myTextField_reservationMonth.setText("");
						myTextField_reservationYear.setText("");
						myTextField_date.setText("");
					}

				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "B��d przy podawaniu daty! Spr�buj DD-MM-RRRR");
					myTextArea_list.setText("Podaj poprawne dane");
					myTextField_reservationDay.setText("");
					myTextField_reservationMonth.setText("");
					myTextField_reservationYear.setText("");
					myTextField_date.setText("");
				}

			}
		});

		// rezerwacja do bazy danych
		myButton_reservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int yearGUI = Integer.parseInt(myTextField_reservationYear.getText());
				int monthGUI = Integer.parseInt(myTextField_reservationMonth.getText());
				int dayGUI = Integer.parseInt(myTextField_reservationDay.getText());
				int hourGUI = Integer.parseInt(myTextField_reservationHour.getText());

				boolean availability = true; // DbAccess.checkDateAndHour(yearGUI,
												// monthGUI, dayGUI, hourGUI);

				if (availability == true) {
					// rezerwuj

					JOptionPane.showMessageDialog(null, "Termin jest wolny!");
				} else {
					JOptionPane.showMessageDialog(null, "Podany termin jest ju� zaj�ty! Wybierz inny!");
				}

			}
		});

		// anulowanie rezerwacji
		myButton_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				myTextArea_list.setText("");
				myTextField_date.setText("");
				myTextField_reservationHour.setText("");
				myTextField_reservationDay.setText("");
				myTextField_reservationMonth.setText("");
				myTextField_reservationYear.setText("");
				myTextField_reservationEmail.setText("");

			}
		});

		// zamykanie aplikacji
		myButton_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// zerwanie po��czenia z baz� danych
				System.exit(0);
			}
		});

		// Pasek menu "Informacje"
		JMenuBar menubar = new JMenuBar();
		JMenu information = new JMenu("Informacje");
		information.setMnemonic(KeyEvent.VK_P);

		JMenuItem about = new JMenuItem("O programie");
		about.setMnemonic(KeyEvent.VK_O);

		// Podpiecie akcji pod "O programie"
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				AboutDialog ad = new AboutDialog();
				ad.setVisible(true);
			}
		});

		information.add(about);
		menubar.add(information);
		setJMenuBar(menubar);
	}

	public static void main(String[] args) {
		Gui mainWindow = new Gui();
		mainWindow.setTitle("Shine on Time - rezerwacja myjni samochodowej");
		mainWindow.setSize(500, 750);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}

}
