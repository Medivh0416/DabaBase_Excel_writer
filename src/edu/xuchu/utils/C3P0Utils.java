package edu.xuchu.utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {

	private static DataSource ds=null;
	static{
		ds=new ComboPooledDataSource("xuchu");
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
}
