package ecoChat;


import java.util.Date;

public class UserBean {


	private String userid;
	private String name;
	private String mobile;
	private String email;
	private String gender;
	private String dOB;
	private String category;
	public UserBean(String userid, String name, String mobile, String email, String gender, String dOB, String category) {
		super();
		this.userid = userid;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.dOB = dOB;
		this.category = category;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getdOB() {
		return dOB;
	}
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	}
