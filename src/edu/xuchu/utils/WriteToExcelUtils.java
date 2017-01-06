package edu.xuchu.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteToExcelUtils {

	public static boolean writeToFile(DataSource ds,String dataBaseName,File file) {
		try {
			Connection conn=ds.getConnection();
			DatabaseMetaData dbmd=conn.getMetaData();
			String name=dbmd.getDatabaseProductName();
			System.err.println(name);
			ResultSet cataRs=dbmd.getCatalogs();
			while(cataRs.next()){
				System.out.println(cataRs.getString(1));
			}
			ResultSet tableNameRs=dbmd.getTables(dataBaseName, dataBaseName, null, new String[]{"Table"});
			System.out.println();
			List<String> tableName=new ArrayList<String>();
			HSSFWorkbook wb=new HSSFWorkbook();
			while(tableNameRs.next()){
				String dbName=tableNameRs.getString("Table_Name");
				tableName.add(dbName);
				System.out.println(dbName);
			}
			for(String nameTable:tableName){
				HSSFSheet sheet=wb.createSheet(nameTable);
				Statement st=conn.createStatement();
				String sql="select * from "+dataBaseName+"."+nameTable;
				ResultSet rs=st.executeQuery(sql);
				ResultSetMetaData rsmd=rs.getMetaData();
				int columnCount=rsmd.getColumnCount();
				HSSFRow row=sheet.createRow(0);
				for(int i=0;i<columnCount;i++){
					HSSFCell cel=row.createCell(i);
					cel.setCellValue(rsmd.getColumnName(i+1));
				}
				int i=1;
				while(rs.next()){
					HSSFRow tRow=sheet.createRow(i++);
					for(int m=0;m<columnCount;m++){
						HSSFCell tCell=tRow.createCell(m);
						tCell.setCellValue(rs.getString(m+1));
					}
				}
			}
			


			FileOutputStream fos=new FileOutputStream(new File("contacts.xls"));
			wb.write(fos);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
