package pl.shine;

import java.io.IOException;
import pl.shine.core.Reservation;
import pl.shine.db.DbAccess;
import pl.shine.db.ReservationDAO;
//import pl.shine.gui.gui;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, NumberFormatException, IOException {
		DbAccess bdAccess = new DbAccess();
		bdAccess.initializeDb();
		
		Reservation res = new Reservation("start", "end", "test@user.pl");
		
		ReservationDAO dao = new ReservationDAO(bdAccess);
		dao.save(res);
		// gui.main(null);
		
	}

}