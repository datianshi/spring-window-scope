<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<p>Window ID: ${org.springframework.web.context.request.WindowScopeRequestAttributes.WINDOW_ID}</p>

<form:form action="/" modelAttribute="command" method="POST">
	<input type="hidden" id="wid" name="wid" value="${org.springframework.web.context.request.WindowScopeRequestAttributes.WINDOW_ID}"/>

	<label for="sessionScopedValue">Session scope:</label>
	<form:input id="sessionScopedValue" path="sessionScopedValue" />
	<br/>

	<label for="windowScopedValue">Window scope:</label>
	<form:input id="windowScopedValue" path="windowScopedValue"/>
	<br/>

	<label for="requestScopedValue">Request scope:</label>
	<form:input id="requestScopedValue" path="requestScopedValue"/>
	<br/>

	<form:button id="submit" value="Submit">Submit</form:button>

	<spring:url value="/" var="home"/>
	<a href="${home}">Refresh</a>
</form:form>
</body>
</html>
