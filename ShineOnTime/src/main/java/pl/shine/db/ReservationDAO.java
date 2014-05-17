package pl.shine.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import pl.shine.core.Reservation;

public class ReservationDAO {

	private static final String INSERT_SQL = "insert into reservation (reservation_start, reservation_end, email) values (?, ?, ?)";
	private DbAccess dbAccess;
	
	
	public ReservationDAO(DbAccess dbAccess)
	{
		this.dbAccess = dbAccess;
	}
	
	/**
	 * http://www.mkyong.com/jdbc/jdbc-preparestatement-example-insert-a-record/
	 * @param res
	 */
	public void save(Reservation res) {
		try {
			PreparedStatement preparedStatement = dbAccess.getConnection().prepareStatement(INSERT_SQL);
			
			//preparedStatement.setInt(1,  1);
			preparedStatement.setString(1,  res.getReservation_start());
			preparedStatement.setString(2,  res.getReservation_end());
			preparedStatement.setString(3,  res.getEmail());
			
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
