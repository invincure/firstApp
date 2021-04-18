<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="echoForm">
	<div>텍스트를 입력하세요</div>
	<div>
		<form:input path="text"/>
		<form:errors path="text"/>
	</div>
	<div>
		<form:button>전송</form:button>
	</div>
</form:form>

</body>
</html>