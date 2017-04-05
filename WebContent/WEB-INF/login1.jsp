<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login Page</title>
</head>

<body>

	<h2>Twitter App Login</h2>

	<form action="login.do" method="POST">
		<table align="center">
			<tr>
				<td style="font-size: x-large">Twitter User ID:</td>
				<td><input type="text" name="twitterid"
					value="${form.twitterid}" autofocus required /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name=Loginbutton value="Login" /></td>
			</tr>
		</table>
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</form>
</body>
</html>

