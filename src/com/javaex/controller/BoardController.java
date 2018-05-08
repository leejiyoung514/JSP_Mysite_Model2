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
import com.javaex.util.PageMaker;
import com.javaex.util.WebPaging;
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

		//게시물 삽입하기
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
			
        //게시물 작성페이지를 보내기
		} else if ("writeform".equals(actionName)) {
			request.getParameter("UTF-8");
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
		
		//게시물 수정한거 가져오기
		} else if ("modifyform".equals(actionName)) {
			request.getParameter("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("modifyform "+no);
			BoardDAO boardDao = new BoardDAO();
			BoardVO boardvo = boardDao.getArticles(no);
			System.out.println("modifyform "+boardvo.toString());
			request.setAttribute("boardvo", boardvo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
      
		//게시물 수정하기
		} else if ("modify".equals(actionName)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println("modify "+no);
			BoardVO boardvo=new BoardVO(no, title, content);
			BoardDAO dao=new BoardDAO();
			dao.Update(boardvo);			
			WebUtil.redirect(request, response, "/mysite/board"); 
			
        //게시물 가져오기
		} else if ("read".equals(actionName)) {
			request.setCharacterEncoding("UTF-8");
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
       
		//삭제기능
		} else if ("delete".equals(actionName)) {
			request.getParameter("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDAO dao=new BoardDAO();
			int result=dao.delete(no);
			System.out.println(result+"건 삭제");
			WebUtil.redirect(request, response, "/mysite/board"); 
			
		//검색기능
		}else if("search".equals(actionName)){
			String kwd=request.getParameter("kwd");
			System.out.println("search "+kwd);
			BoardDAO dao=new BoardDAO();
			ArrayList<BoardVO> list=dao.search(kwd);
			request.setAttribute("list", list);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	
		//리스트 가져오기
		} else {
			BoardDAO dao = new BoardDAO();
			
			PageMaker pagemaker =new PageMaker();
			String pagenum=request.getParameter("pagenum");
			String contentnum=request.getParameter("contentnum");
			int cpagenum=Integer.parseInt(pagenum);
			int ccontentnum =Integer.parseInt(contentnum);
	
			pagemaker.setTotalcount(dao.getArticleCount()); //전체 게시글 개수를 지정한다.
			pagemaker.setPagenum(cpagenum-1); //현재 페이지를 페이지 객체에 지정한다 -1을 해야 쿼리에서 사용할 수 있음
			pagemaker.setContentnum(ccontentnum); //한 페이지에 몇개씩 게시글을 보여줄지
			pagemaker.setCurrentblock(cpagenum); //현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
			pagemaker.setLastblock(pagemaker.getTotalcount()); //마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
			
			pagemaker.prevnext(cpagenum);//현재 페이지 번호를 화살표로 나타낼지 정한다
			pagemaker.setStartPage(pagemaker.getCurrentblock()); //시작 페이지를 페이지 블록 번호로 정한다.
			pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());//마지막 페이지를 마지막 페이지 블록과 현재 페이지블록 번호로 정한다.
			
			List<BoardVO> list = dao.listBoard(pagemaker.getPagenum()*10, pagemaker.getContentnum());  //페이지 개시글 가져오기 
			//페이지 번호와 몇개씩 보일지
			request.setAttribute("list", list);
			request.setAttribute("page",pagemaker);
			
			System.out.println("리스트에 내용: "+list.toString());
			
			HttpSession session = request.getSession();
			UserVO authUser = (UserVO) session.getAttribute("authUser");
			session.setAttribute("authUser", authUser);			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		}

	}

}
