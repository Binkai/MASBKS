package MASWF.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * JDBC Klasse die die Kontrolle über die Verbindungen hat.
 * @author kaikuhfeld
 *
 */
public class JDBCUtil implements dbCred {
	/**
	 * Verbindet sich mit der Datenbank.
	 * @return die Connection
	 */
	public Connection setConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
/**
 * Schließt die Connection
 * @param connection welche geschlossen werden soll.
 */
	public void closeConnection(final Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
/**
 * Wirft keine Exception wenn die Connection nicht richtig geschlossen wird.
 * @param connection welche ohne Exception geschlossen wird.
 */
	public void closeConnectionQuietly(final Connection connection) {
		try {
			closeConnection(connection);
		} catch (Exception e) {
			// ignore exception, just log
		}
	}

}
