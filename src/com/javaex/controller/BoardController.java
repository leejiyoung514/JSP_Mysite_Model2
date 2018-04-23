package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDAO;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVO;
import com.javaex.vo.UserVO;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Controller");
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");

		//글작성기능
		if ("write".equals(actionName)) {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			UserVO authUser = (UserVO) session.getAttribute("authUser");
			int user_no = authUser.getNo();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			BoardVO vo = new BoardVO(title, content, user_no);
			BoardDAO dao = new BoardDAO();
			dao.insert(vo);
			WebUtil.redirect(request, response, "/mysite/board"); 
			
        //글쓰는페이지
		} else if ("writeform".equals(actionName)) {
			request.getParameter("UTF-8");
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
		
		//수정하는페이지
		} else if ("modifyform".equals(actionName)) {
			request.getParameter("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("modifyform "+no);
			BoardDAO boardDao = new BoardDAO();
			BoardVO boardvo = boardDao.getArticles(no);
			System.out.println("modifyform "+boardvo.toString());
			request.setAttribute("boardvo", boardvo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
      
		//글수정 기능 
		} else if ("modify".equals(actionName)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println("modify "+no);
			BoardVO boardvo=new BoardVO(no, title, content);
			BoardDAO dao=new BoardDAO();
			dao.Update(boardvo);			
			WebUtil.redirect(request, response, "/mysite/board"); 
			
        //글 보는 페이지 (조회수 추가) 
		} else if ("read".equals(actionName)) {
			request.setCharacterEncoding("UTF-8");
			// no로 글을 가져와야함
			int no = Integer.parseInt(request.getParameter("no"));
			//System.out.println("view "+no);
			BoardDAO boardDao = new BoardDAO();
            boardDao.increaseHit(no);
			BoardVO boardvo = boardDao.getArticles(no);
			//System.out.println("view "+boardvo.toString());
			request.setAttribute("boardvo", boardvo);
			HttpSession session = request.getSession();
			UserVO authUser = (UserVO) session.getAttribute("authUser");
			session.setAttribute("authUser", authUser);
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
       
		//글삭제 기능 
		} else if ("delete".equals(actionName)) {
			request.getParameter("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDAO dao=new BoardDAO();
			int result=dao.delete(no);
			System.out.println(result+"건 삭제");
			WebUtil.redirect(request, response, "/mysite/board"); 
			
		//글검색 기능 	
		}else if("search".equals(actionName)){
			String kwd=request.getParameter("kwd");
			System.out.println("search "+kwd);
			BoardDAO dao=new BoardDAO();
			ArrayList<BoardVO> list=dao.search(kwd);
			request.setAttribute("list", list);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		//페이징 처리
		}else if("paging".equals(actionName)) {
			String pageNoVal=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoVal!=null) {
				pageNo=Integer.parseInt(pageNoVal);
				
				
			}
			
			
			WebUtil.redirect(request, response,"/mysite/board");
		//글목록 
		} else {
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getlist();
			request.setAttribute("list", list);
			System.out.println("리스트안에 "+list.toString());
			HttpSession session = request.getSession();
			UserVO authUser = (UserVO) session.getAttribute("authUser");
			session.setAttribute("authUser", authUser);			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		}

	}

}
