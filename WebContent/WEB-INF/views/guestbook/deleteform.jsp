<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import><jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
    			
	
		<div id="wrapper">
			<div id="content">
				<div id="guestbook" class="delete-form">
					
					<form  action="/mysite/gb" method="post">
	                <input type="text" name="a" value="delete">
						
						<label>비밀번호</label>
						<input type="password" name="password">
						<%-- <td><input type="text" name="no" value="<%=request.getParameter("no")%>"></td> --%>
						<td><input type="text" name="no" value="${param.no}"></td>
						<input type="submit" value="확인">
					</form>
					<a href="/mysite/gb">방명록 리스트</a>
					
				</div>
			</div><!-- /content -->
		</div><!-- /wrapper -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>		
		
	</div> <!-- /container -->

</body>
</html>
