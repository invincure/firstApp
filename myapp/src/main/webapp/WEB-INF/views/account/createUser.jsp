<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title></title>
</head>
<body>
<h1>
	createUser  
</h1>
<form:form modelAttribute="accountCreateForm" action="/account/">
	<span>NAME</span><form:input path="name" /><br>
	<span>TEL</span><form:input path="tel" /><br>
	<span>Birth</span><form:input path="dateOfBirth" /><br>
	<span>Email</span><form:input path="email" /><br><br>
	<input type="button" value="확인" />
</form:form>


</body>
</html>
