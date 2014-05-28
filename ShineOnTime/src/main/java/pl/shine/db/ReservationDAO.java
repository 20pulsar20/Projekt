package pl.shine.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.shine.core.Reservation;
import pl.shine.core.TimeSlot;

public class ReservationDao {

	private static final String INSERT_SQL = "insert into reservation (start, duration, email) values (?, ?, ?)";
	private static final String SELECT_SQL = "select start, duration, email from reservation";
	private static final String DELETE_SQL = "delete from reservation where start = ? and duration = ? and email = ?";
	private DbAccess dbAccess;
	
	
	public ReservationDao(DbAccess dbAccess)
	{
		this.dbAccess = dbAccess;
	}
	
	/**
	 * http://www.mkyong.com/jdbc/jdbc-preparestatement-example-insert-a-record/
	 */
	public void save(Reservation reservation) {
		try {
			PreparedStatement insertStatement = dbAccess.getConnection().prepareStatement(INSERT_SQL);
			
			insertStatement.setTimestamp(1, getTimestamp(reservation));
			insertStatement.setInt(2,  reservation.getTimeSlot().getDuration());
			insertStatement.setString(3,  reservation.getEmail());
			
			// execute insert SQL statement
			insertStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Timestamp getTimestamp(Reservation reservation) {
		Date date = reservation.getTimeSlot().getStart();
		return new Timestamp(date.getTime());
	}
	
	/*
	 * http://www.mkyong.com/jdbc/jdbc-statement-example-select-list-of-the-records/
	 */
	public List<Reservation> findAll(){
		try {
			Statement statement = dbAccess.getConnection().createStatement();
			
			// execute select SQL statement
			ResultSet resultSet = statement.executeQuery(SELECT_SQL);
			
			// parse result
			return parseResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private List<Reservation> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Reservation> list = new ArrayList<>();
		while (resultSet.next()) {
			Date start = resultSet.getTimestamp(1);
			Integer duration = resultSet.getInt(2);
			String email = resultSet.getString(3);
			
			list.add(new Reservation(new TimeSlot(start, duration), email));
		}
		return list;
	}

	public void delete(Reservation reservation) {
		try {
			PreparedStatement insertStatement = dbAccess.getConnection().prepareStatement(DELETE_SQL);
			
			insertStatement.setTimestamp(1, getTimestamp(reservation));
			insertStatement.setInt(2,  reservation.getTimeSlot().getDuration());
			insertStatement.setString(3,  reservation.getEmail());
			
			// execute insert SQL statement
			insertStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
