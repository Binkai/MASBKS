package MASWF.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import MASWF.db.object.Kunde;
import MASWF.db.util.JDBCUtil;
import MASWF.db.DAO.KundeDao;

public class KundeDaoImpl implements KundeDao {
	private Connection connection;
	public KundeDaoImpl(){
		this.connection = new JDBCUtil().setConnection();
	}
	@Override
	public Kunde getKunde(String Vorname, String Nachname, Date Geburtsdatum){
		PreparedStatement preparedState = null;
		ResultSet result = null;
		String query = "SELECT * FROM KUNDE WHERE Vorname = ? AND Nachname = ? AND Geburtsdatum = ?";
		try {
			preparedState = connection.prepareStatement(query);
			preparedState.setString(1, Vorname);
			preparedState.setString(2, Nachname);
			preparedState.setDate(3, new java.sql.Date(Geburtsdatum.getTime()));
			result = preparedState.executeQuery();
			if (result.next()) {
				Kunde kunde = new Kunde();
				kunde.setVorname(Vorname);
				kunde.setNachname(Nachname);
				kunde.setGeburtsdatum(new java.sql.Date(Geburtsdatum.getTime()));
				kunde.setScore(result.getFloat("Score"));
				kunde.setKundeID(result.getInt("KundenID"));
				kunde.setMonateinkommen(result.getFloat("monateinkommen"));
				new JDBCUtil().closeConnection(this.connection);
				return kunde;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new JDBCUtil().closeConnection(this.connection);
		return null;
	}

}
