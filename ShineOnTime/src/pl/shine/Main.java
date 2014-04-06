package pl.shine;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		DbAccess bdAccess = new DbAccess();
		bdAccess.initializeDb();
	}

}
