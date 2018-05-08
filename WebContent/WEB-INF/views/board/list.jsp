<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" var="boardVO">
						<tr>
							<td>${boardVO.no}</td>
							<td><a href="/mysite/board?a=read&no=${boardVO.no}">${boardVO.title}</a></td>
							<td>${boardVO.user_name}</td>
							<td>${boardVO.hit}</td>
							<td>${boardVO.reg_date}</td>
							<td>
						   <c:if test="${sessionScope.authUser.name == boardVO.user_name}">
							<a href="/mysite/board?a=delete&no=${boardVO.no}"
								class="del">삭제</a>
					        </c:if>
					        </td>
						</tr>
					</c:forEach>
				</table>
				
				
				<div class="pager">
				
					<ul>
					  <c:if test="${page.prev}">
						<li><a style="text-decoration:none;" href="${page.getStartPage()-1 }">◀</a></li>					  
					  </c:if>
					  <c:forEach begin="${page.getStartPage()}" end="${page.getStartPage()}" var="idx">
					    <li><a href="">1</a>${idx}</li>
					  </c:forEach>
					  
					  <c:if test="${page.prev}">
						<li><a style="text-decoration:none;" href="${page.getStartPage()-1 }">◀</a></li>					  
					  </c:if>
					  
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				
				
				
				
				
				
				
				
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				
				
			
				    <c:if test="${! empty sessionScope.authUser }">
				   	 <div class="bottom">
							 <a href="/mysite/board?a=writeform" id="new-book">글쓰기</a> 
						</div>
				    </c:if>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
</body>
</html>