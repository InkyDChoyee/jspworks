package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class NoticeDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//게시글 목록 조회
	public List<NoticeVO> getNBoardList(){
		List<NoticeVO> nboardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM notice ORDER BY nno";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeVO nb = new NoticeVO();
				nb.setNno(rs.getInt("nno"));
				nb.setNtitle(rs.getString("ntitle"));
				nb.setNname(rs.getString("nname"));
				nb.setNdate(rs.getTimestamp("ndate"));
				nb.setNhit(rs.getInt("nhit"));
				nb.setNcontent(rs.getString("ncontent"));
				nb.setNfilename(rs.getString("nfilename"));
				
				nboardList.add(nb);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return nboardList;
	}
	
	//공지사항 글쓰기
	public void nWrite(NoticeVO nb) {
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into notice(nno, ntitle, ncontent, nname) "
					+ "VALUES (seq_nno.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nb.getNtitle());
			pstmt.setString(2, nb.getNcontent());
			pstmt.setString(3, nb.getNname());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
}