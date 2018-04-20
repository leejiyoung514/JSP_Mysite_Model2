package com.javaex.controller;

import java.io.IOException;
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

		//���ۼ����
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
        //�۾���������
		} else if ("writeform".equals(actionName)) {
			request.getParameter("UTF-8");
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
		//�����ϴ�������
		} else if ("modifyform".equals(actionName)) {
			request.getParameter("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			BoardDAO boardDao = new BoardDAO();
			BoardVO boardvo = boardDao.getArticles(no);
			System.out.println(boardvo.toString());
			request.setAttribute("boardvo", boardvo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
        //�ۼ��� ��� 
		} else if ("modify".equals(actionName)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println("�ȳ�"+no);
			BoardVO boardvo=new BoardVO(no, title, content);
			BoardDAO dao=new BoardDAO();
			dao.Update(boardvo);			
			WebUtil.redirect(request, response, "/mysite/board"); 
        //�� ���� ������
		} else if ("read".equals(actionName)) {
			request.setCharacterEncoding("UTF-8");
			// no�� ���� �����;���
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			BoardDAO boardDao = new BoardDAO();
			BoardVO boardvo = boardDao.getArticles(no);
			System.out.println(boardvo.toString());
			request.setAttribute("boardvo", boardvo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
       
		//�ۻ��� ��� 
		} else if ("delete".equals(actionName)) {
			request.getParameter("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDAO dao=new BoardDAO();
			int result=dao.delete(no);
			System.out.println(result+"�� ����");
			WebUtil.redirect(request, response, "/mysite/board"); 
		}else if("search".equals(actionName)){
			String kwd=request.getParameter("kwd");
			System.out.println(kwd);
			BoardDAO dao=new BoardDAO();
			dao.search(kwd);
			
			WebUtil.redirect(request, response, "/mysite/board"); 
		//�۸�� ������ 
		} else {
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getlist();
			request.setAttribute("list", list);
			System.out.println(list.toString());
			HttpSession session = request.getSession();
			UserVO authUser = (UserVO) session.getAttribute("authUser");
			session.setAttribute("authUser", authUser);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		}

	}

}
