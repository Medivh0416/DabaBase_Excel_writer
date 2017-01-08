package edu.xuchu.MyDBUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import edu.xuchu.domain.Contact;

public class MyBeanHandler<T> implements MyResultSetHandler<List<T>> {

	private Class<T> cls;

	public MyBeanHandler(Class<T> cls) {
		super();
		this.cls = cls;
	}

	@Override
	public List<T> query(ResultSet rs) {
		List<T> list = new LinkedList<T>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			System.out.println(count);
			T t = null;
			while (rs.next()) {
				t = cls.newInstance();
				try {
					for (int i = 0; i < count; i++) {
						String coluName = rsmd.getColumnName(i + 1);
						String methodName = "set" + coluName.substring(0, 1).toUpperCase()
								+ coluName.substring(1).toLowerCase();
						String className = rsmd.getColumnClassName(i + 1);
						Method method = Contact.class.getMethod(methodName, Class.forName(className));
						method.invoke(t, rs.getObject(i + 1));
					}
					System.out.println(t);
					list.add(t);
				} catch (Exception e) {

				}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
