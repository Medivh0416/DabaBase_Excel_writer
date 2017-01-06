package edu.xuchu.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import edu.xuchu.domain.User;
import edu.xuchu.utils.JDBCUtils;

public class UserDao {

	public User login(String name,String pwd){
		User u=null;
		Connection conn=JDBCUtils.getConnection();
		String sql="select * from users where name=? and pwd=?";
		try {
			PreparedStatement pstm=conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, pwd);
			ResultSet set=pstm.executeQuery();
			if(set.next()){
				u=new User();
				u.setId(set.getString("id"));
				u.setName(name);
				u.setPwd(pwd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u;
	}
}
