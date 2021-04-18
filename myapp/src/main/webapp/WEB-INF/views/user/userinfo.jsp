<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setAttribute("id", "shin");	//값에 Object전달 가능. <- param 내장 객체와 다른점.
session.setAttribute("url", "127.0.0.1");

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${userMsg }
<br>
<c:if test="${age eq '23' }">
	<c:out value="${age} "></c:out>
</c:if>

<c:forEach var="hobby" items="스포츠, 영화, 음악">
	<c:out value="${hobby }" /> <br> 
</c:forEach>
<br>
requestTitle = ${param.title } <br>	<!-- ${param.title} == request.getParameter("title") -->
id = ${param.id } <br>
10+10=${10+10 }<br>
${requestScope.id }
</body>
</html>