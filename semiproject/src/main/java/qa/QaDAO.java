package qa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class QaDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//게시글 목록 조회
	public List<QaVO> getQABoardList(){
		List<QaVO> qaboardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM notice ORDER BY qno";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QaVO qab = new QaVO();
				qab.setQno(rs.getInt("qno"));
				qab.setQtitle(rs.getString("qtitle"));
				qab.setQname(rs.getString("qname"));
				qab.setQfilename(rs.getString("qfilename"));
				qab.setQdate(rs.getTimestamp("qdate"));
				qab.setQhit(rs.getInt("qhit"));
				qab.setId(rs.getString("id"));
				
				qaboardList.add(qab);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return qaboardList;
	}
	
	//문의게시판 글쓰기
	public void qaWrite(QaVO qab) {
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into notice(qno, qtitle, qcontent, id) "
					+ "VALUES (seq_qno.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qab.getQtitle());
			pstmt.setString(2, qab.getQcontent());
			pstmt.setString(3, qab.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
}