package board;

import java.io.Serializable;
import java.sql.Timestamp;

public class Board implements Serializable{

	private static final long serialVersionUID = 12L;

	private int bno;			 	// 게시글 번호
	private String title;			// 게시글 제목
	private String content;		 	// 게시글 내용
	private Timestamp createDate;   // 작성일
	private Timestamp modifyDate;   // 수정일
	private int hit;		 		// 조회수
	private String filename;	 	// 파일이름
	private String id;	 			// 아이디
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
