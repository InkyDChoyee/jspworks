package voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCUtil;

public class VoterDAO {
	Connection conn = null;  		// DB 연결
	PreparedStatement pstmt = null; // sql 처리
	ResultSet rs = null;			// 검색한 데이터셋

	// 좋아요 추가
	public void insertVote(Voter voter) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO voter (vno, bno, mid) VALUES(seq_vno.NEXTVAL, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, voter.getBno());
			pstmt.setString(2, voter.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	
	
	}

	// 좋아요 삭제
	public void deleteVote(Voter voter) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "DELETE FROM voter WHERE bno=? AND mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, voter.getBno());
			pstmt.setString(2, voter.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}

	// 좋아요 저장 유무 확인
	// 로그인 시 아이디와 비밀번호가 필요한 것과 유사
	// 세션아이디, 글번호가 일치
	public int voteCheck(int bno, String id) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) AS result FROM voter WHERE bno=? AND mid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, id);
			// 검색
			rs = pstmt.executeQuery();
			if(rs.next()) {   // 일치하는 값이 있으면 1 반환
				return rs.getInt("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
		return 0;  // 일치한 값이 없으면 0을 보내줌
	}

	// 좋아요 총개수
	public int voteCount(int bno) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) AS total FROM voter WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 검색
			rs = pstmt.executeQuery();
			if(rs.next()) {   // 카운트하는 값이 있으면 숫자 반환
				return rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
		return 0;  // 카운트한 값이 없으면 0을 보내줌
	}
}
