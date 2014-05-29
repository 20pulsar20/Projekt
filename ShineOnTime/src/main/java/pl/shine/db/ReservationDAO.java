package pl.shine.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.shine.core.Reservation;
import pl.shine.core.TimeSlot;

public class ReservationDAO {

	private static final String INSERT_SQL = "insert into reservation (start, email) values (?, ?)";
	private static final String SELECT_SQL = "select start, email from reservation";
	private static final String DELETE_SQL = "delete from reservation where start = ? and email = ?";
	private static final String FIND_SQL = "select start, email from reservation where start >= ? and start < ?";

	private DbAccess dbAccess;

	public ReservationDAO(DbAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	/**
	 * http://www.mkyong.com/jdbc/jdbc-preparestatement-example-insert-a-record/
	 */
	public boolean save(Reservation reservation) {
		try {
			PreparedStatement insertStatement = dbAccess.getConnection().prepareStatement(INSERT_SQL);

			insertStatement.setTimestamp(1, getTimestamp(reservation));
			insertStatement.setString(2, reservation.getEmail());

			// execute insert SQL statement
			insertStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Timestamp getTimestamp(Reservation reservation) {
		Calendar calendar = reservation.getTimeSlot().getStart();
		return new Timestamp(calendar.getTimeInMillis());
	}

	/*
	 * http://www.mkyong.com/jdbc/jdbc-statement-example-select-list-of-the-records
	 * /
	 */
	public List<Reservation> findAll() {
		try {
			PreparedStatement selectStatement = dbAccess.getConnection().prepareStatement(SELECT_SQL);

			// execute select SQL statement
			ResultSet resultSet = selectStatement.executeQuery();

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
			String email = resultSet.getString(2);

			list.add(new Reservation(new TimeSlot(start), email));
		}
		return list;
	}

	public void delete(Reservation reservation) {
		try {
			PreparedStatement insertStatement = dbAccess.getConnection().prepareStatement(DELETE_SQL);

			insertStatement.setTimestamp(1, getTimestamp(reservation));
			insertStatement.setString(2, reservation.getEmail());

			// execute insert SQL statement
			insertStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Reservation> findReservations(Date userDate) {

		Calendar beginningOfDay = Calendar.getInstance();
		beginningOfDay.setTime(userDate);

		Calendar endOfDay = Calendar.getInstance();
		endOfDay.setTime(beginningOfDay.getTime());
		endOfDay.add(Calendar.DAY_OF_MONTH, 1);

		ResultSet resultSet = null;
		try {
			PreparedStatement findStatement = dbAccess.getConnection().prepareStatement(FIND_SQL);
			findStatement.setTimestamp(1, new Timestamp(beginningOfDay.getTimeInMillis()));
			findStatement.setTimestamp(2, new Timestamp(endOfDay.getTimeInMillis()));
			resultSet = findStatement.executeQuery();

			return parseResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
