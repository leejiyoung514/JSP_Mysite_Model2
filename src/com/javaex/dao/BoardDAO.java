package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVO;

public class BoardDAO {
	// 글쓰기
	public void insert(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
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

	// 글목록
	public List<BoardVO> getlist() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "select b.no, " + "       b.title, " + "       u.name, " + "       b.hit, "
					+ "       b.reg_date, " + "       b.user_no " + "from board b, users u " + "where b.user_no = u.no "
					+ "order by no desc ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String user_name = rs.getString("name");
				System.out.println("user_name" + user_name);
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");
				BoardVO vo = new BoardVO(no, title, user_name, hit, reg_date, user_no);
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
		System.out.println("dao" + list.toString());
		return list;
	}

	// 글 수정 업데이트
	public void Update(BoardVO boardvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		System.out.println(boardvo.toString());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "update board set title=?, content=? where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardvo.getTitle());
			pstmt.setString(2, boardvo.getContent());
			pstmt.setInt(3, boardvo.getNo());
			count = pstmt.executeUpdate();
			// 결과처리
			System.out.println(count + "건 등록");

		} catch (ClassNotFoundException e) {
			System.out.println("error: insert 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
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

	// 글내용 가져오기
	public BoardVO getArticles(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardvo = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "SELECT no, title, content, user_no FROM board where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			// 결과 처리
			while (rs.next()) {
				int wno = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int user_no = rs.getInt("user_no");
				boardvo = new BoardVO();
				boardvo.setNo(wno);
				boardvo.setTitle(title);
				boardvo.setContent(content);
				boardvo.setUser_no(user_no);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: insert 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boardvo;
	}

	// 글삭제
	public int delete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
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

	// 글검색
	public ArrayList<BoardVO> search(String kwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "select b.no, " + 
					"       b.title, " + 
					"       u.name, " + 
					"       b.hit, " + 
					"       b.reg_date, " + 
					"       b.user_no " + 
					"from board b, users u " + 
					"where b.user_no = u.no " + 
					"AND b.title like ? " + 
					"order by b.no desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+kwd+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String user_name = rs.getString("name");
				System.out.println("user_name" + user_name);
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");
				BoardVO boardvo = new BoardVO(no, title, user_name, hit, reg_date, user_no);
				list.add(boardvo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: insert 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 조회수 증가 
	public void increaseHit(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "update board set hit = hit+1 where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			count = pstmt.executeUpdate();
			// 결과처리
			System.out.println("조회수"+count + "회 증가");
		} catch (ClassNotFoundException e) {
			System.out.println("error: insert 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
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
	
	//페이지 개수를 구하기 위한 전체 게시글 개수 
	public int getArticleCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "SELECT count(*) FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 결과 처리
			while (rs.next()) {
			  count=rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: insert 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	//페이징 한 글목록 리스트
	public ArrayList<BoardVO> listBoard(int start, int end){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "SELECT B.rnum, B.no, B.title, B.name, B.hit, B.reg_date, B.user_no " + 
					" FROM " + 
					"   (SELECT rownum AS rnum, A.no, A.title, A.name, A.hit, A.reg_date, A.user_no " + 
					"    FROM(SELECT b.no, " + 
					"                b.title, " + 
					"                u.name, " + 
					"                b.hit, " + 
					"                b.reg_date, " + 
					"                b.user_no " + 
					"         FROM board b, users u " + 
					"         WHERE b.user_no = u.no " + 
					"         ORDER BY NO DESC) A " + 
					"    WHERE rownum <= ? ) B " + 
					" WHERE B.rnum >= ? "; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String user_name = rs.getString("name");
				System.out.println("user_name" + user_name);
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");
				BoardVO vo = new BoardVO(no, title, user_name, hit, reg_date, user_no);
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
		System.out.println("dao" + list.toString());
		
		return list;
	}
	
	

}
