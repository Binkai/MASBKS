package MASWF.db.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil implements dbCred {
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

	public void closeConnection(final Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void closeConnectionQuietly(final Connection connection) {
		try {
			closeConnection(connection);
		} catch (Exception e) {
			// ignore exception, just log
		}
	}

}
