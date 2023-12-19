package board;

import java.io.Serializable;
import java.sql.Timestamp;

public class BoardVO implements Serializable{

	private static final long serialVersionUID = 112L;
	
	private int bno;
	private String btitle;
	private String bcontent;
	private Timestamp createDate; //등록일
	private Timestamp bupDate; //수정일
	private String id;
	private String aid; //관리자 아이디
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getBupDate() {
		return bupDate;
	}
	public void setBupDate(Timestamp bupDate) {
		this.bupDate = bupDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	
	
	
}
