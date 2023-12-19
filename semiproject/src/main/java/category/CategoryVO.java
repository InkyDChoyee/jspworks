package category;

import java.io.Serializable;

public class CategoryVO implements Serializable{
	
	private static final long serialVersionUID = 108L;
	
	private int cnum;
	private int cnum2;
	private String categoryName;
	private int pno;
	
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getCnum2() {
		return cnum2;
	}
	public void setCnum2(int cnum2) {
		this.cnum2 = cnum2;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	
}
