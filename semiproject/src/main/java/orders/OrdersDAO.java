package orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class OrdersDAO {
	Connection conn;
    PreparedStatement pstmt;
    ResultSet rs ;
    
    public void insertOrder(OrdersVO o) {
    	conn=JDBCUtil.getConnection();
     try {
    	 String sql ="INSERT INTO orders (orderno, orderprice, cardcompany, count, id, pname, filename)  "
    	 		+ " VALUES(seq_ono.nextval, ?, ?, ?, ?, ?, ?) ";
    	 
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, o.getOrderPrice());
		pstmt.setString(2, o.getCardCompany());
		pstmt.setInt(3, o.getCount());	
		pstmt.setString(4, o.getId());
		pstmt.setString(5, o.getPname());
		pstmt.setString(6, o.getFilename());
		
		pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCUtil.close(conn, pstmt);
	}
    }
    
    public List<OrdersVO> getlistAll(){
   	 conn=JDBCUtil.getConnection();
   	 List<OrdersVO> ol = new ArrayList<>();
   	 try {
   		 String sql = "select * from orders  ";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				OrdersVO ov = new OrdersVO();
				ov.setOrderNo(rs.getInt("orderno"));
				ov.setPname(rs.getString("pname"));
				ov.setOrderDate(rs.getTimestamp("orderdate"));
				ov.setCount(rs.getInt("count"));
				ov.setOrderPrice(rs.getInt("orderprice"));
				ov.setId(rs.getString("id"));
				ov.setFilename(rs.getString("filename"));
				ol.add(ov);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return ol;
   	 
    }
    public int getListcount() {
    	int total=0;
    		
	   try {
		   conn=JDBCUtil.getConnection();
		   String sql = "select count(*) as total from orders ";
		pstmt = conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		if(rs.next()) {
			total = rs.getInt("total");
		}						
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCUtil.close(conn, pstmt, rs);
	}
    	
		return total;
    }
    
    
    public List<OrdersVO> getblist(int page, String id){
    	conn=JDBCUtil.getConnection();
		List<OrdersVO> ol= new ArrayList<>();
	   try {
		   String sql = "select *  "
	   + "from (select rownum as RN, od.* from(select * from orders order by orderno desc) od) "
		   		+ "where RN >= ? and RN <= ? and id =? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, (page-1)*7+1);
		pstmt.setInt(2, page*7);
		pstmt.setString(3, id);
		rs= pstmt.executeQuery();
		while(rs.next()) {
			OrdersVO ov =new OrdersVO();
			
			ov.setOrderNo(rs.getInt("orderno"));
			ov.setPname(rs.getString("pname"));
			ov.setOrderDate(rs.getTimestamp("orderdate"));
			ov.setCount(rs.getInt("count"));
			ov.setOrderPrice(rs.getInt("orderprice"));
			ov.setId(rs.getString("id"));
			ov.setFilename(rs.getString("filename"));
			ol.add(ov);
		}						
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCUtil.close(conn, pstmt, rs);
	}
    	
		return ol;
    }
}