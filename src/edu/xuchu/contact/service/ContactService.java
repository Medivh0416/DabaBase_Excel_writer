package edu.xuchu.contact.service;

import java.util.List;

import edu.xuchu.contact.dao.ContactDao;
import edu.xuchu.domain.Contact;

public class ContactService {

	ContactDao dao=new ContactDao();
	public List<Contact> getContactListById(String id){
		return dao.getContactListById(id);
	}
}
