package pl.shine.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccess {
	private String databaseFilename = "ShineDatabase.db";
	private Path initDbFile = new File("ShineDatabaseStructure.sql").toPath();

	public void initializeDb() throws ClassNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC"); // zainicjownie biblioteki
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseFilename); // stworzenie po³¹czenia do bazy
			Statement stat = conn.createStatement();
			
			// czytamy zawartoœæ pliku
			String fileContents = readFileContent();
			// przekazujemy go do uruchomienia
			stat.execute(fileContents);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String readFileContent() {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader reader = Files.newBufferedReader(initDbFile, Charset.defaultCharset())) {
			String lineFromFile = "";
			while ((lineFromFile = reader.readLine()) != null) {
				builder.append(lineFromFile);
			}
		} catch (IOException exception) {
			System.out.println("Error while reading file");
		}
		return builder.toString();
	}
}
