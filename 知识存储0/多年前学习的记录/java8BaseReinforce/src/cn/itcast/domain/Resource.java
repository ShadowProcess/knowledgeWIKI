package cn.itcast.domain;

import java.sql.Timestamp;

public class Resource {
	// id INT PRIMARY KEY AUTO_INCREMENT,
	// uuidname VARCHAR(100) UNIQUE NOT NULL,
	// realname VARCHAR(40) NOT NULL,
	// savepath VARCHAR(100) NOT NULL,
	// uploadtime TIMESTAMP ,
	// description VARCHAR(255)

	private int id;
	private String uuidname;
	private String realname;
	private String savepath;
	private Timestamp uploadtime;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuidname() {
		return uuidname;
	}

	public void setUuidname(String uuidname) {
		this.uuidname = uuidname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public Timestamp getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
