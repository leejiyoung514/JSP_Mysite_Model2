<%@page import="com.javaex.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<!-- session���� ���� ������ uservo�������� �ּҰ� ����־� �ּҳѱ�°� 
     �α��εǸ� �ִ°Ű� �α��ξȵǸ� null-->
<%
	UserVO authUser = (UserVO) session.getAttribute("authUser");
%>
	
<div id="header">
	<h1> # JOE  </h1>
	<ul>
	<%  
	    if(authUser==null){
	%>
		<!-- �α��� �� -->
		<li><a href="/mysite/user?a=loginform">�α���</a></li>
		<li><a href="/mysite/user?a=joinform">ȸ������</a></li>
    <%  
	    }else{
	%>
		<!-- �α��� �� -->
	    <li><a href="/mysite/user?a=modifyform&no=<%=authUser.getNo()%>">ȸ����������</a></li>
		<li><a href="/mysite/user?a=logout">�α׾ƿ�</a></li> 
		<li> <%=authUser.getName()%>�� �ȳ��ϼ���^^;</li>		
	<%  
	    }
	%>
	</ul>
</div>
<!-- /header -->