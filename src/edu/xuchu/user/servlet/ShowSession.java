package edu.xuchu.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.xuchu.domain.User;

public class ShowSession extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=(String) request.getAttribute("sessionid");
		Map<String, HttpSession> map = (Map<String, HttpSession>) getServletContext().getAttribute("onLogin");
		System.err.println(">>"+map);
		HttpSession session = map.get(id);//
		session.removeAttribute("user");
		//重定向到
		response.sendRedirect(request.getContextPath()+"/ShowServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, HttpSession> map = (Map<String, HttpSession>) getServletContext().getAttribute("Map");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> one=null;
		if (null != map) {
			Set<Entry<String, HttpSession>> set = map.entrySet();
			for (Entry entry : set) {
				one=new HashMap<String, String>();
				HttpSession session=(HttpSession) entry.getValue();
				User user=(User) session.getAttribute("user");
				one.put("SessionId", session.getId());
				one.put("UserId", user.getId());
				one.put("UserName", user.getName());
				one.put("UserPwd", user.getPwd());
				one.put("Time", sdf.format(new Date(session.getCreationTime())));
				list.add(one);
			}
		}
		request.setAttribute("UserList", list);
		
		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

}
