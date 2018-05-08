package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDAO;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVO;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String actionName=request.getParameter("a");
	    
	    if("joinform".equals(actionName)) {//ȸ������ ��
	    	WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
	   
	    }else if("join".equals(actionName)) {//ȸ������ ����
	    	String name=request.getParameter("name");
	    	String email=request.getParameter("email");
	    	String password=request.getParameter("password");
	    	String gender=request.getParameter("gender");
	    	
	    	//������ ���Ƽ� vo�� ��� dao���� �����༭ ����ó����
	    	UserVO uservo=new UserVO(name, email, password, gender);
	    	//ȭ�鿡�� �����Ͱ� �� ������ �׽�Ʈ�۾� 
	    	System.out.println(uservo.toString());
	
	    	UserDAO userdao = new UserDAO();
	    	userdao.insert(uservo);
	    	//�����ϴ� ����
	    	WebUtil.forward(request, response,"/WEB-INF/views/user/joinsuccess.jsp");
	    
	    }else if("loginform".equals(actionName)) {
	    	WebUtil.forward(request, response,"/WEB-INF/views/user/loginform.jsp");
	    
	    }else if("login".equals(actionName)) {
	    	String email=request.getParameter("email");
	    	String password=request.getParameter("password");
	    	UserDAO userdao=new UserDAO();
	    	UserVO uservo=userdao.getUser(email, password);
	    	
	    	if(uservo==null) { //�α��ν���
	    		System.out.println("�α��ν���");
	    		WebUtil.redirect(request, response,"/mysite/user?a=loginform&result=fail");
	    	}else { //�α��� ������ session�� ��Ƽ� 
	    		System.out.println("�α��μ���"); 
	    		HttpSession session=request.getSession(); //�ִ� ���� �˷��ָ� �����ϴϱ� ��Ʈ���ϴ� �ָ� �˷���
	    	    session.setAttribute("authUser", uservo);
	    	    System.out.println("login" + uservo.toString());
	    	    WebUtil.redirect(request, response, "/mysite/main"); //������������ �̹� �ֱ⶧���� �����̷�Ʈ
	    	 }
	    	
	    }else if("logout".equals(actionName)) {
	    	HttpSession session=request.getSession();
	    	session.removeAttribute("authUser");
	    	session.invalidate();
	    	WebUtil.redirect(request, response, "/mysite/main"); 
	    	
	    }else if("modifyform".equals(actionName)) {
	        request.setCharacterEncoding("UTF-8");
	    	int no=Integer.parseInt(request.getParameter("no"));
	    	UserDAO userdao = new UserDAO();
	    	UserVO uservo=userdao.getUser(no);	   
	    	
	    	request.setAttribute("uservo", uservo);	    	
	    	WebUtil.forward(request, response,"/WEB-INF/views/user/modifyform.jsp");
	    	
	    }else if("modify".equals(actionName)) {
	    	HttpSession session=request.getSession();
	    	UserVO authUser = (UserVO)session.getAttribute("authUser");
	    	
	    	int no = authUser.getNo();
	    	System.out.println("modify" + authUser.toString());
	    	String name=request.getParameter("name");
	    	String email=request.getParameter("email");
	    	String password=request.getParameter("password");
	    	String gender=request.getParameter("gender");
	    	UserVO uservo=new UserVO(no,name, email, password, gender);
	    	UserDAO dao=new UserDAO();
	    	dao.Update(uservo);
	    	authUser.setName(uservo.getName());
	    	WebUtil.redirect(request, response, "/mysite/main"); 
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
