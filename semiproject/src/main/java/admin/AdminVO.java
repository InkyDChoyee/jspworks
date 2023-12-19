package admin;

import java.io.Serializable;

public class AdminVO implements Serializable{

	private static final long serialVersionUID = 111L;
	
	private String aid;
	private String apasswd;
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getApasswd() {
		return apasswd;
	}
	public void setApasswd(String apasswd) {
		this.apasswd = apasswd;
	}
	
	
}
