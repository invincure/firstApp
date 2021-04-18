<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title></title>
<script type="text/javascript">
function goNext(){
	document.reqForm.submit();
}
</script>
</head>
<body>
<h1>
	CreateForm  
</h1>
<form:form name="reqForm" modelAttribute="accountCreateForm" action="/account/createUser">
	<span>NAME</span><form:input path="name" /><br>
	<span>TEL</span><form:input path="tel" /><br>
	<span>Birth</span><form:input path="dateOfBirth" /><br>
	<span>Email</span><form:input path="email" /><br><br>
	<span>CardInfo</span>
	<span>CardNo</span><form:input path="card.no" /><br>
	<span>CardNo</span><form:input path="card.expire" /><br><br>
	
	
	<input type="button" value="확인" onclick="goNext()"/>
</form:form>


</body>
</html>
