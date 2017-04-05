<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="font-wesome2.css" rel="stylesheet">

<html>
<head>
<title>Twitter</title>
</head>
<body>
	<h2>Tweets:</h2>
	<form action="login.do" method="POST">
		<table align="center">
			<tr>
				<c:choose>
					<c:when test="${fn:length(outputstr) gt 0}">
						<c:forEach var="outputstr" items="${outputstr}">
							<h3 style="color: blue">${outputstr}</h3>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<h3 style="color: red">Disclaimer: No tweets for this user!</h3>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td colspan="2" align="center"><br> <input type="submit"
					name=Homepage value="Home" /></td>
			</tr>
		</table>

		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</form>
</body>

<footer>
	<p>
		<i>Note: The tweets are of format (tweets : sentiment score) </i> <br>
		<i>The higher the score, happier the sentiment :)</i>
	</p>
	<br>
	<p>Copyright © 2017</p>
</footer>
</html>