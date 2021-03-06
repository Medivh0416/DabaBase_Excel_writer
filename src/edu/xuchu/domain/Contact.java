package edu.xuchu.domain;

public class Contact {

	private String id;
	private String name;
	private String sex;
	private String tel;
	private String addr;
	private String uid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Contact(String id, String name, String sex, String tel, String addr, String uid) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.addr = addr;
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", sex=" + sex + ", tel=" + tel + ", addr=" + addr + ", uid="
				+ uid + "]";
	}
	public Contact() {
		super();
	}
	
}
