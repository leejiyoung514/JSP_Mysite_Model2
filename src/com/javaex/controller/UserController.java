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
	    
	    if("joinform".equals(actionName)) {//회원가입 폼
	    	WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
	    }else if("join".equals(actionName)) {//회원정보 저장
	    	String name=request.getParameter("name");
	    	String email=request.getParameter("email");
	    	String password=request.getParameter("password");
	    	String gender=request.getParameter("gender");
	    	
	    	//갯수가 많아서 vo로 묶어서 dao한테 던져줘서 저장처리함
	    	UserVO uservo=new UserVO(name, email, password, gender);
	    	//화면에서 데이터가 잘 오는지 테스트작업 
	    	System.out.println(uservo.toString());
	
	    	UserDAO userdao = new UserDAO();
	    	userdao.insert(uservo);
	    	//응답하는 로직
	    	WebUtil.forward(request, response,"/WEB-INF/views/user/joinsuccess.jsp");
	    
	    }else if("loginform".equals(actionName)) {
	    	WebUtil.forward(request, response,"/WEB-INF/views/user/loginform.jsp");
	    }else if("login".equals(actionName)) {
	    	String email=request.getParameter("email");
	    	String password=request.getParameter("password");
	    	UserDAO userdao=new UserDAO();
	    	UserVO uservo=userdao.getUser(email, password);
	    	
	    	if(uservo==null) { //로그인실패
	    		System.out.println("로그인실패");
	    		WebUtil.redirect(request, response,"/mysite/user?a=loginform&result=fail");
	    	}else { //로그인 성공시 session에 담아서 
	    		System.out.println("로그인성공"); 
	    		HttpSession session=request.getSession(); //넣는 곳을 알려주면 위험하니까 컨트롤하는 애를 알려줌
	    	    session.setAttribute("authUser", uservo);
	    	    System.out.println("login" + uservo.toString());
	    	    WebUtil.redirect(request, response, "/mysite/main"); //메인페이지가 이미 있기때문에 리다이렉트
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
