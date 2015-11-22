<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
</head>
<body>
	<h2>Notifications</h2>
	<c:forEach var="message" items="${notifications}" varStatus="counter">
		<p>${counter.index}. Date: ${message.date }</p>
		<p> From: ${message.from.fullName }</p>
		<p> Message: ${message.message }</p>
		<c:if test="${message.seen}">
			<p>Seen</p>
		</c:if>
		<c:if test="${not message.seen}">
			<p>New Message</p>
		</c:if>
	</c:forEach>
</body>
</html>