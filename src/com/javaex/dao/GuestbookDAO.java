package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVO;

public class GuestbookDAO {

	// 등록기능
	public void insert(GuestbookVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "INSERT INTO GUESTBOOK values (seq_guestbook_no.nextval,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			count = pstmt.executeUpdate();
			System.out.println(count + "건 등록");

		} catch (ClassNotFoundException e) {
			System.out.println("error: insert 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<GuestbookVO> getlist() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<GuestbookVO> list = new ArrayList<GuestbookVO>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "SELECT NO, NAME, PASSWORD, CONTENT, REG_DATE" + " FROM GUESTBOOK" + " ORDER BY NO DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String password = rs.getString("PASSWORD");
				String content = rs.getString("CONTENT");
				String req_date = rs.getString("REG_DATE");
				GuestbookVO vo = new GuestbookVO(no, name, password, content, req_date);
				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: list 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 삭제기능
	public int delete(int no, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "delete from guestbook where no=? and password=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			count = pstmt.executeUpdate();

			

		} catch (ClassNotFoundException e) {
			System.out.println("error: list 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
}
