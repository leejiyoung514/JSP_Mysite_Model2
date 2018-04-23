package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVO;

public class UserDAO {

	public void insert(UserVO uservo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "INSERT INTO users VALUES(SEQ_USER_NO.NEXTVAL,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uservo.getName());
			pstmt.setString(2, uservo.getEmail());
			pstmt.setString(3, uservo.getPassword());
			pstmt.setString(4, uservo.getGender());
			count = pstmt.executeUpdate();
			// ���ó��
			System.out.println(count + "�� ���");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
    public void Update(UserVO uservo) {
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int count = 0;
        System.out.println(uservo.toString());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "update users set name=?, email=?, password=?, gender=? where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uservo.getName());
			pstmt.setString(2, uservo.getEmail());
			pstmt.setString(3, uservo.getPassword());
			pstmt.setString(4, uservo.getGender());
			pstmt.setInt(5, uservo.getNo());
			count = pstmt.executeUpdate();
			// ���ó��
			System.out.println(count + "�� ���");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	//ȸ�� ���� ��� 
	public UserVO getUser(String email, String password) { //�̸��� ��� �Ѱ��༭ ������ �ѹ��� �̸��ϰ�޶��
		Connection con = null;
		PreparedStatement pstmt = null;
	    ResultSet rs=null;																																									
	    UserVO uservo=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			
			String sql="select no, name from users where email=? and password=?";
		    pstmt=con.prepareStatement(sql);
		    pstmt.setString(1, email);
		    pstmt.setString(2, password);
		    rs=pstmt.executeQuery();
		   //��� ó��
		    while(rs.next()) {
		    	int no=rs.getInt("no");
		    	String name=rs.getString("name");
		    	uservo=new UserVO();    //��ü����� while �ȿ����� �ۿ��� �����������
		    	uservo.setNo(no);
		    	uservo.setName(name);
		    }

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return uservo;
	}
	public UserVO getUser(int no) { //�̸��� ��� �Ѱ��༭ ������ �ѹ��� �̸��ϰ�޶��
		Connection con = null;
		PreparedStatement pstmt = null;
	    ResultSet rs=null;																																									
	    UserVO uservo=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			
			String sql="select name, email, password, gender from users where no=?";
		    pstmt=con.prepareStatement(sql);
		    pstmt.setInt(1, no);
		    rs=pstmt.executeQuery();
		   //��� ó��
		    while(rs.next()) {
		    	String name=rs.getString("name");
		    	String email=rs.getString("email");
		    	String password=rs.getString("password");
		    	String gender=rs.getString("gender");
		    	uservo=new UserVO();    //��ü����� while �ȿ����� �ۿ��� �����������
		    	uservo.setName(name);
		    	uservo.setEmail(email);
		    	uservo.setPassword(password);
                uservo.setGender(gender);		    
		    }

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return uservo;
	}

	
	
	
}
