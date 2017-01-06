package edu.xuchu.utils;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.*;

public class DBCPUtils {
	private static DataSource ds=null;
	static{
		InputStream in=DBCPUtils.class.getResourceAsStream("jdbc.properties");
		Properties prop=new Properties();
		try {
			prop.load(in);
			ds=new BasicDataSourceFactory().createDataSource(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static DataSource getDataSource(){
		return ds;
	}
}
