package edu.xuchu.utils;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import edu.xuchu.domain.User;
public class test {
	@Test
	public void test() throws Exception{
		JDBCWraper jdbc=new JDBCWraper();
		Connection conn=jdbc.getConnection();
		String sql="insert into users values(?,?,?)";
		PreparedStatement state=conn.prepareStatement(sql);
		for(int i=4;i<103;i++){
			state.setString(1, "U00"+i);
			state.setString(2, "Jim");
			state.setString(3, "ffgss");
			System.out.println(state.executeUpdate());
		}
		
	}
	
	@Test
	public void testDBCP() throws SQLException{
		System.out.println(DBCPUtils.getDataSource().getConnection());
	}
	
	@Test
	public void testDButils() throws Exception{
		DataSource ds=DBCPUtils.getDataSource();
		System.out.println(ds.getConnection());
		QueryRunner qr=new QueryRunner(ds);
		for(int i=5;i<104;i++){
			String sql = "insert into users values('U00"+i+"','李四"+i+"','888')";
			qr.update(sql);
		}

//		String sql ="SELECT * FROM users";
//		List<User> list=qr.query(sql, new BeanListHandler<User>(User.class));
//		for(User u:list){
//			System.out.println(u);
//		}
	}
	
	@Test
	public void testC3P0() throws Exception{
		DataSource ds=C3P0Utils.getDataSource();
		String dataBaseName="contacts";
		File file=new File(dataBaseName+".xls");
		WriteToExcelUtils.writeToFile(ds, dataBaseName, file);
	}

}
