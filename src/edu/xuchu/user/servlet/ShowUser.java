package edu.xuchu.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.xuchu.domain.User;
import edu.xuchu.utils.C3P0Utils;

public class ShowUser extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int pageSize=15;
		int pageCount=10;
		int Count;
		int startPage = 0;
		int endPage = 0;
		List<User> list=null;
		String currentPage=request.getParameter("current");
		int current;
		if(null==currentPage||currentPage.trim().equals("")){
			current=1;
		}else{
			current=Integer.parseInt(currentPage);

		}
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select count(1) from users";
		try {
			Count=Integer.parseInt(qr.query(sql,new ScalarHandler()).toString());
			int page=Count/pageSize+(Count%pageSize==0?0:1);
			if(current<1){
				current=1;
			}
			if(current>page){
				current=page;
			}
			if(page<=pageCount){
				startPage=1;
				endPage=page;
			}else{

			}
			String sql1="select * from users limit "+(current-1)*pageSize+","+pageSize+"";
			System.out.println(sql1);
			list=qr.query(sql1, new BeanListHandler<User>(User.class));
			request.setAttribute("start", startPage);
			request.setAttribute("end", endPage);
			request.setAttribute("current", current);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/users.jsp").forward(request, response);;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
