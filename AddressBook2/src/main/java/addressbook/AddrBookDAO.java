package addressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;



// AddrBook 자료형을 이용하여 주소를 생성, 검색, 수정, 삭제를 담당하는 클래스
public class AddrBookDAO {
	// Jdbc 관련 변수
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;    // db에 있는 자료
	
	// 주소 추가
	public void addAddrBook(AddrBook addrBook) {
		// 물리적 연결
		conn = JDBCUtil.getConnection();
	
		try {
			// sql 처리
			String sql = "INSERT INTO addrbook(bnum, username, tel, email, gender) "
					+ "VALUES(seq_bnum.NEXTVAL, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			// 폼에 입력된 자료를 가져와서 DB에 저장
			pstmt.setString(1, addrBook.getUsername());
			pstmt.setString(2, addrBook.getTel());
			pstmt.setString(3, addrBook.getEmail());
			pstmt.setString(4, addrBook.getGender());
			// sql 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 주소 전체 목록
	public List<AddrBook> getListAll(){
		// db 연결
		conn = JDBCUtil.getConnection();
		List<AddrBook> addrList = new ArrayList<>();
		
		// sql 처리
		try {
			String sql = "SELECT * FROM addrbook ORDER BY bnum";
			pstmt = conn.prepareStatement(sql);
			// db의 주소록을 꺼내옴
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AddrBook addrBook = new AddrBook(); // 빈 객체 생성
				// db에 있는 주소를 가져와서 주소 객체에 저장(화면 목록)
				addrBook.setBnum(rs.getInt("bnum"));
				addrBook.setUsername(rs.getString("username"));
				addrBook.setTel(rs.getString("tel"));
				addrBook.setEmail(rs.getString("email"));
				addrBook.setGender(rs.getString("gender"));
				addrBook.setRegDate(rs.getTimestamp("regdate"));
			
				// 리스트에 1개 객체를 저장(순서에 따라)
				addrList.add(addrBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// db 종료
		finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return addrList;
	}
	
	// 주소 정보(1건) 가져오기
	
	public AddrBook getAddrBook(int bnum){
		conn = JDBCUtil.getConnection();
		AddrBook addrBook = new AddrBook(); // 빈 객체 생성
		try {
			String sql = "SELECT * FROM addrbook WHERE bnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			// bnum에 일치하는 1개의 주소 가져옴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// db에 있는 주소를 가져와서 주소 객체에 저장(화면 목록)
				addrBook.setBnum(rs.getInt("bnum"));
				addrBook.setUsername(rs.getString("username"));
				addrBook.setTel(rs.getString("tel"));
				addrBook.setEmail(rs.getString("email"));
				addrBook.setGender(rs.getString("gender"));
				addrBook.setRegDate(rs.getTimestamp("regdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return addrBook;
		
	}
	
	// 로그인 체크
	public boolean checkLogin(String email) {
		
		// db연결
		conn = JDBCUtil.getConnection();
		
		try {
			// sql 처리
			String sql = "SELECT email FROM addrbook "
					+ "WHERE email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) { // 검색한 이메일이 있으면 true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return false;
	}
	
}
