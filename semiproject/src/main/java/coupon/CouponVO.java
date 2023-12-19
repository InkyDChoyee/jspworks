package coupon;

import java.io.Serializable;
import java.sql.Date;

public class CouponVO implements Serializable{

	private static final long serialVersionUID = 109L;
	
	private int cno;
	private int cid;
	private Date validity; //유효기간
	
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Date getValidity() {
		return validity;
	}
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	
	
}
