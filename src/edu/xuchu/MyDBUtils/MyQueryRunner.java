package edu.xuchu.MyDBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class MyQueryRunner {

	private DataSource ds;
	public MyQueryRunner(DataSource ds){
		this.ds=ds;
	}
	/**
	 * Based on CallBack
	 * @param sql sqlString
	 * @param handler
	 * @return
	 */
	public <T>T myQuery(String sql,MyResultSetHandler<T> handler){
		Connection conn;
		try {
			conn = ds.getConnection();
			Statement stmt=conn.createStatement();
			ResultSet resultSet=stmt.executeQuery(sql);
			return (T) handler.query(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
