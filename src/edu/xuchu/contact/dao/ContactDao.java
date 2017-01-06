package edu.xuchu.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import edu.xuchu.domain.Contact;
import edu.xuchu.utils.JDBCUtils;

public class ContactDao {

	public List<Contact> getContactListById(String id){
		List<Contact> list=new LinkedList<Contact>();
		Connection conn=JDBCUtils.getConnection();
		String sql="select * from contacts where UID=?";
		try {
			PreparedStatement pstm=conn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet set=pstm.executeQuery();
			Contact contact=null;
			while(set.next()){
				contact=new Contact(set.getString("id"), set.getString("name"), 
						set.getString("sex"), set.getString("tel"), 
						set.getString("addr"), set.getString("uid"));
				list.add(contact);
			}
			System.out.println("ListSize:"+list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
