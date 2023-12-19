package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class ReviewDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//게시글 목록 조회
	public List<ReviewVO> getRBoardList(){
		List<ReviewVO> rboardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM notice ORDER BY rno";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewVO rb = new ReviewVO();
				rb.setRno(rs.getInt("rno"));
				rb.setRtype(rs.getString("rtype"));
				rb.setRtitle(rs.getString("rtitle"));
				rb.setRcontent(rs.getString("rcontent"));
				rb.setRdate(rs.getTimestamp("rdate"));
				rb.setRfilename(rs.getString("rfilename"));
				rb.setRrate(rs.getInt("rrate"));
				rb.setLikes(rs.getInt("likes"));
				rb.setReceipt(rs.getString("receipt"));
				
				rboardList.add(rb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return rboardList;
	}
	
	//공지사항 글쓰기
	public void rWrite(ReviewVO rb) {
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into notice(rno, rtitle, rcontent, rname) "
					+ "VALUES (seq_rno.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
}