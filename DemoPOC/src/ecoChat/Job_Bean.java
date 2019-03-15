package ecoChat;

import java.util.Date;

public class Job_Bean {

private int job_id;
private String job_title;
private String job_desc;
private String job_category;
private Date job_date;
public Date getJob_date() {
	return job_date;
}
public void setJob_date(Date job_date) {
	this.job_date = job_date;
}
public int getJob_id() {
	return job_id;
}
public void setJob_id(int job_id) {
	this.job_id = job_id;
}
public String getJob_title() {
	return job_title;
}
public void setJob_title(String job_title) {
	this.job_title = job_title;
}
public String getJob_desc() {
	return job_desc;
}
public void setJob_desc(String job_desc) {
	this.job_desc = job_desc;
}
public String getJob_category() {
	return job_category;
}
public void setJob_category(String job_category) {
	this.job_category = job_category;
}

public Job_Bean(int job_id, String job_title, String job_desc, String job_category,Date job_date) {
	super();
	this.job_id = job_id;
	this.job_title = job_title;
	this.job_desc = job_desc;
	this.job_category = job_category;
	this.job_date=job_date;
}



}
