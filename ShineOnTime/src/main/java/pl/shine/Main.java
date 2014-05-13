package pl.shine;

import java.io.IOException;



import pl.shine.core.TimeSlot;
import pl.shine.db.DbAccess;
import pl.shine.gui.gui;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, NumberFormatException, IOException {
		DbAccess bdAccess = new DbAccess();
		bdAccess.initializeDb();
		gui.main(null);
		
	}

}