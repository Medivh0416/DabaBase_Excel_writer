package edu.xuchu.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {

	private static Connection conn = null;
	private static String URL = "jdbc:mysql://localhost:3306/contacts";
	static List<Connection> pool = new LinkedList<Connection>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties prop = new Properties();
			prop.put("user", "root");
			for (int i = 0; i < 3; i++) {
				conn = DriverManager.getConnection(URL, prop);
				Object proxy = Proxy.newProxyInstance(JDBCUtils.class.getClassLoader(),
						new Class[] { Connection.class }, new InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								synchronized (pool) {
									if (method.getName().equals("close")) {
										pool.add((Connection) proxy);
										System.out.println("Proxy Close method to put back,Size"+pool.size());
										pool.notifyAll();
										return null;
									}
								}
								return method.invoke(conn, args);
							}
						});
				pool.add((Connection) proxy);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		synchronized (pool) {
			if (pool.isEmpty()) {
				try {
					pool.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return getConnection();
			}else{
				System.out.println("Pool Size"+pool.size());
				return pool.remove(0);
			}
		}
	}
}
