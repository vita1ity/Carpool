<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Notifications</title>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<%-- <h2>Notifications</h2>
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
	</c:forEach> --%>
	
	
	
	<div class="wrap">
		<div class="content">
			<h2>Notifications</h2>
			<c:forEach var="message" items="${notifications}" varStatus="counter">
			<div class="msg">
				<c:if test="${not message.seen}">
				<div class="unread">
					<fmt:parseDate value="${message.date}" pattern="yyyy-MM-dd" 
                          var="parsedDate" type="date" />
					<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
					<h4>${counter.index + 1}. Date: <span class="info">${newParsedDate}</span></h4>
					<h4>From: <span class="info">${message.from.fullName}</span></h4>
					<p>${message.message }</p>
				</div>
				</c:if>
				<c:if test="${message.seen}">
				<div class="read">
					<fmt:parseDate value="${message.date}" pattern="yyyy-MM-dd" 
                          var="parsedDate" type="date" />
					<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy" />
					<h4>${counter.index + 1}. Date: <span class="info">${newParsedDate}</span></h4>
					<h4>From: <span class="info">${message.from.fullName}</span></h4>
					<p>${message.message }</p>
				</div>
				</c:if>
			</div>
			</c:forEach>
		</div>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>