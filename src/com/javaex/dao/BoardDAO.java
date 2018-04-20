package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVO;
import com.javaex.vo.UserVO;

public class BoardDAO {
    //글쓰기
	public void insert(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "INSERT INTO BOARD values(seq_board_no.nextval,?,?,0,sysdate,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getUser_no());
			count = pstmt.executeUpdate();
			System.out.println(count + "건 등록");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: list 드라이버 로딩 실패 - " + e);
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
    //글목록
	public List<BoardVO> getlist() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "select b.no, " + 
					"       b.title, " + 
					"       u.name, " + 
					"       b.hit, " + 
					"       b.reg_date, " + 
					"       b.user_no " + 
					"from board b, users u " + 
					"where b.user_no = u.no " + 
					"order by no desc ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
                 int no=rs.getInt("no");
                 String title=rs.getString("title");
                 String user_name=rs.getString("name");
                 System.out.println("user_name"+ user_name);
                 int hit=rs.getInt("hit");
                 String reg_date=rs.getString("reg_date");
                 int user_no=rs.getInt("user_no");
                 BoardVO vo=new BoardVO(no, title, user_name, hit, reg_date, user_no);
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
		System.out.println("dao"+ list.toString());
		return list;
	}
	
	//글 수정 업데이트
	public void Update(BoardVO boardvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int count = 0;
        System.out.println(boardvo.toString());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "update board set title=?, content=? where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardvo.getTitle());
			pstmt.setString(2, boardvo.getContent());
			pstmt.setInt(3, boardvo.getNo());
			count = pstmt.executeUpdate();
			// 결과처리
			System.out.println(count + "건 등록");

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
	
	//글내용 가져오기
	public BoardVO getArticles(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
	    ResultSet rs=null;																																									
	    BoardVO boardvo=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			
			String sql="SELECT no, title, content, user_no FROM board where no=?";
		    pstmt=con.prepareStatement(sql);
		    pstmt.setInt(1, no);
		    rs=pstmt.executeQuery();
		   //결과 처리
		    while(rs.next()) {
		      int wno=rs.getInt("no");
		      String title=rs.getString("title");
		      String content=rs.getString("content");
		      int user_no=rs.getInt("user_no");
		     
		      boardvo=new BoardVO();
		      boardvo.setNo(wno);
		      boardvo.setTitle(title);
		      boardvo.setContent(content);
		      boardvo.setUser_no(user_no);
		      
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
		return boardvo;
	}
	
   //글 삭제
	public int delete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "webdb");
			String sql = "delete from board where no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
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
	
	public void search(String kwd) {
		
		
		
		
	}
	
}
