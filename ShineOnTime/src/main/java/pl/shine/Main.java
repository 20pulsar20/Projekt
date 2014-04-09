package pl.shine;

import pl.shine.db.DbAccess;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		DbAccess bdAccess = new DbAccess();
		bdAccess.initializeDb();
	}

}
