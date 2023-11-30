package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class BoardDAO {
	Connection conn = null;  		// DB 연결
	PreparedStatement pstmt = null; // sql 처리
	ResultSet rs = null;			// 검색한 데이터셋
	
	// 게시글 목록
	public List<Board> getBoardList(){
		List<Board> boardList = new ArrayList<Board>();
		try {
			// db 연결
			conn = JDBCUtil.getConnection();
			// sql 처리
			String sql = "SELECT * FROM board ORDER BY createdate DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();  // 검색된 데이터셋(모음)
			while(rs.next()) {
				// 빈 회원을 생성해서 db에서 정보를 가져와서 세팅
				Board b = new Board();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getTimestamp("createdate"));
				b.setModifyDate(rs.getTimestamp("modifydate"));
				b.setHit(rs.getInt("hit"));
				b.setFilename(rs.getString("filename"));
				b.setId(rs.getString("id"));
				// ArrayList에 회원을 추가
				boardList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		// db 종료
		return boardList;
	}
}
