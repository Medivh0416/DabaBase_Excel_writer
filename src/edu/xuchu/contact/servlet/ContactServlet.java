package edu.xuchu.contact.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.xuchu.contact.service.ContactService;
import edu.xuchu.domain.Contact;
import edu.xuchu.domain.User;
import edu.xuchu.utils.BaseServlet;

public class ContactServlet extends BaseServlet {

	HttpSession session;
	ContactService service=new ContactService();
	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		System.out.println("ContactServlet Method");
		HttpSession session=req.getSession(); 
		User user=(User) session.getAttribute("user");
		System.out.println(user.getId()+user.getName());
		List<Contact> list=service.getContactListById(user.getId());
		System.out.println(list.size());
		req.setAttribute("list", list);
		try {
			req.getRequestDispatcher("/contact.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("Delete Method");
	}

	public void update(HttpServletRequest req, HttpServletResponse resp){
		System.err.println("update Method");
	}
}
