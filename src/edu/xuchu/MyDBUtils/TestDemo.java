package edu.xuchu.MyDBUtils;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;

import edu.xuchu.domain.Contact;
import edu.xuchu.utils.C3P0Utils;

public class TestDemo {
	
	@Test
	public void test() throws Exception{
		DataSource ds=C3P0Utils.getDataSource();
		String sql="select * from contacts";
		MyQueryRunner qr=new MyQueryRunner(ds);
		List<Contact> list=qr.myQuery(sql, new MyBeanHandler<Contact>(Contact.class));
		System.out.println(list.size());
	}
}
