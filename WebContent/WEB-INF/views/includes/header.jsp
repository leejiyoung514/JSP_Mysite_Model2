<%@page import="com.javaex.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- session에서 정보 꺼내기 uservo변수에는 주소가 담겨있어 주소넘기는거 
     로그인되면 있는거고 로그인안되면 null-->
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
	
<div id="header">
	<h1> # JOE  </h1>
	<ul>
	<c:choose>
	   <c:when test="${empty authUser}" >
		<!-- 로그인 전 -->
		<li><a href="/mysite/user?a=loginform">로그인</a></li>
		<li><a href="/mysite/user?a=joinform">회원가입</a></li>
    	</c:when>
		<c:otherwise>
		<!-- 로그인 후 -->
	    <li><a href="/mysite/user?a=modifyform&no=${authUser.no }">회원정보수정</a></li>
		<li><a href="/mysite/user?a=logout">로그아웃</a></li> 
		<li> ${authUser.name }님 안녕하세요^^;</li>		
	   	</c:otherwise>
	</c:choose>
	
	
	
	
	
	
	
<%-- 	<%  
	    if(authUser==null){
	%>
		<!-- 로그인 전 -->
		<li><a href="/mysite/user?a=loginform">로그인</a></li>
		<li><a href="/mysite/user?a=joinform">회원가입</a></li>
    <%  
	    }else{
	%>
		<!-- 로그인 후 -->
	    <li><a href="/mysite/user?a=modifyform&no=<%=authUser.getNo()%>">회원정보수정</a></li>
		<li><a href="/mysite/user?a=logout">로그아웃</a></li> 
		<li> <%=authUser.getName()%>님 안녕하세요^^;</li>		
	<%  
	    }
	%> --%>
	</ul>
</div>
<!-- /header -->