package MASWF.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import MASWF.db.object.Kredit;
import MASWF.db.util.JDBCUtil;

public class KreditDaoImpl implements KreditDao {

	@Override
	public boolean insertKredit(Kredit kredit) {
		Connection connection = new JDBCUtil().setConnection();
		PreparedStatement preparedState = null;
		boolean check = false;
		int anzahl = 0;
		String query = "INSERT INTO KREDIT(KREDITHOEHE, RUECKZAHLUNGSRATE, STARTDATUM, LAUFZEIT, VERWENDUNGSZWECK, K_ID) VALUES(?,?,?,?,?,?)";
		try {
			preparedState = connection.prepareStatement(query);
			preparedState.setFloat(1, kredit.getKredithoehe());
			preparedState.setFloat(2, kredit.getRueckzahlungrate());
			preparedState.setDate(3, new java.sql.Date(kredit.getStartdatum().getTime()));
			preparedState.setLong(4, kredit.getLaufzeit());
			preparedState.setString(5, kredit.getVerwendungszweck());
			preparedState.setInt(6, kredit.getKundenID());
			anzahl = preparedState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (anzahl != 0) {
			check = true;
		}
		new JDBCUtil().closeConnection(connection);
		return check;
	}

}
