<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title></title>
</head>
<body>
<h1>
	forwardMenu<br>
	<span>${account.name  }</span><br>
	<span><c:out value="${account.dateOfBirth }"></c:out></span><br>
	<span><c:out value="${account.tel }"></c:out></span>
</h1>
</body>
</html>
