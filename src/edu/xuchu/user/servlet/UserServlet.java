package edu.xuchu.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.xuchu.domain.User;
import edu.xuchu.user.servlce.UserService;
import edu.xuchu.utils.BaseServlet;

public class UserServlet extends BaseServlet {

	private UserService service=new UserService();
	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Excute method");
	}
	public void login(HttpServletRequest req, HttpServletResponse resp){
		String name=req.getParameter("name");
		String pwd=req.getParameter("pwd");
		User user=service.login(name, pwd);
		if(user==null){
			try {
				resp.getWriter().print("Wrong username or passwrod");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			HttpSession session=req.getSession();
			session.setAttribute("user",user);
			try {
				resp.sendRedirect(req.getContextPath()+"/ContactServlet");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
