package edu.xuchu.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import edu.xuchu.domain.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
public class SessionListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		Map<String,HttpSession> map;
		if(null==se.getSession().getServletContext().getAttribute("Map")){
			map=new HashMap<String,HttpSession>();
		}else{
			map=(Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("Map");
		}
		if("user".equals(se.getName())){
			map.put(se.getSession().getId(),se.getSession());
			se.getSession().getServletContext().setAttribute("Map", map);
			System.out.println("Added Session");
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String key= se.getName();
		if(key.equals("user")){
			Map<String,HttpSession> map = 
					(Map<String, HttpSession>) se.getSession()
					.getServletContext().getAttribute("Map");
			map.remove(se.getSession().getId());
		}

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

}
