<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<p>${command}</p>
<%--<p> The value of the session scoped bean is ${command.sessionScopedValue}. </p>--%>
<%--<p> The value of the request scoped bean is ${command.requestScopedValue}. </p>--%>

<spring:form action="/" modelAttribute="command" method="POST">
	<label for="sessionScopedValue">Session scope:</label>
	<spring:input id="sessionScopedValue" path="sessionScopedValue" />
	<br/>

	<label for="requestScopedValue">Request scope:</label>
	<spring:input id="requestScopedValue" path="requestScopedValue"/>
	<br/>

	<spring:button id="submit" value="Submit">Submit</spring:button>
</spring:form>
</body>
</html>
