<%@page import="com.javaex.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%
	UserVO uservo = (UserVO) request.getAttribute("uservo");
%> --%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
	<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>	
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="post" action="/mysite/user?a=modify">

						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="${uservo.name}" />
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="${uservo.email}" readonly="readonly" > 
						<strong></strong>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="${uservo.password}" />
						
						<fieldset>
							<legend>성별</legend>
							
						<%-- 	<label>여</label> <input type="radio" name="gender" value="female" <%=uservo.getGender().equals("female")?"checked":""%>> --%>
							<label>여</label> <input type="radio" name="gender" value="female" ${uservo.gender == 'female' ? "checked" : "" }>
							<%-- <label>남</label> <input type="radio" name="gender" value="male" <%=uservo.getGender().equals("male")?"checked":""%>> --%>
							<label>남</label> <input type="radio" name="gender" value="male" ${uservo.gender == 'male' ? "checked" : "" }>
						</fieldset>
						
						<input type="submit" value="수정완료">
						
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div> <!-- /container -->

</body>
</html>
