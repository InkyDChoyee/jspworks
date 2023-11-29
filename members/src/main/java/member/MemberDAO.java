package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

// 회원을 추가, 검색, 수정, 삭제할 클래스
public class MemberDAO {
	Connection conn = null;  		// DB 연결
	PreparedStatement pstmt = null; // sql 처리
	ResultSet rs = null;			// 검색한 데이터셋
	
	// 회원 목록
	public List<Member> getMemberList(){
		List<Member> memberList = new ArrayList<>();
		try {
			// db 연결
			conn = JDBCUtil.getConnection();
			// sql 처리
			String sql = "SELECT * FROM member ORDER BY mno";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// 빈 회원을 생성해서 db에서 정보를 가져와서 세팅
				Member m = new Member();
				m.setMno(rs.getInt("mno"));
				m.setId(rs.getString("id"));
				m.setPasswd(rs.getString("passwd"));
				m.setName(rs.getString("name"));
				m.setEmail(rs.getString("email"));
				m.setGender(rs.getString("gender"));
				m.setJoinDate(rs.getTimestamp("joindate"));
				// ArrayList에 회원을 추가
				memberList.add(m);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		// db 종료
		return memberList;
	}
}