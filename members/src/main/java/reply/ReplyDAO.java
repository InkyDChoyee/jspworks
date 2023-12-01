package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

// 댓글을 추가, 수정, 삭제, 검색하는 클래스
public class ReplyDAO {
	Connection conn = null;  		// DB 연결
	PreparedStatement pstmt = null; // sql 처리
	ResultSet rs = null;			// 검색한 데이터셋
	
	// 댓글글 목록
		public List<Reply> getReplyList(int bno){
			List<Reply> replyList = new ArrayList<Reply>();
			try {
				conn = JDBCUtil.getConnection();
				String sql = "SELECT * FROM reply WHERE bno= ? ORDER BY rdate";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				// sql 처리
				rs = pstmt.executeQuery();
				while(rs.next()) {
					// 빈 댓글을 생성해서 db에서 정보를 가져와서 세팅
					Reply r = new Reply();
					r.setRno(rs.getInt("rno"));
					r.setBno(rs.getInt("bno"));
					r.setRcontent(rs.getString("rcontent"));
					r.setReplyer(rs.getString("replyer"));
					r.setRdate(rs.getTimestamp("rdate"));
					r.setRupdate(rs.getTimestamp("rupdate"));
					// ArrayList에 댓글을 추가
					replyList.add(r);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}
			return replyList;
		}
		
		// 댓글 등록
		public void insertreply(Reply r) {
			try {
				conn = JDBCUtil.getConnection();
				String sql = "INSERT INTO reply (rno, bno, rcontent, replyer) "
						+ "VALUES (seq_rno.NEXTVAL, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, r.getBno());
				pstmt.setString(2, r.getRcontent());
				pstmt.setString(3, r.getReplyer());
				// sql 처리
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt);
			}
		}
		
		// 댓글 삭제 - 댓글 번호를 검색해서 삭제 = rno
		public void deletereply(int rno) {
			try {
				conn = JDBCUtil.getConnection();
				String sql = "INSERT INTO reply (rno, bno, rcontent, replyer) "
						+ "VALUES (seq_rno.NEXTVAL, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, r.getBno());
				pstmt.setString(2, r.getRcontent());
				pstmt.setString(3, r.getReplyer());
				// sql 처리
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt);
			}
		}
}
