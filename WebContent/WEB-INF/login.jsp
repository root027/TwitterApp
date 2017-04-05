<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="font-wesome.css" rel="stylesheet">

<html>
<head>
<title>Login Page</title>
</head>

<body>

	<h1>Twitter Application</h1>
	
	<form action="login.do" method="POST">
		<table align="center">
			<tr>
				<td style="font-size: x-large font-family: courier; color: maroon">Twitter
					User ID:</td>
				<td><input type="text" name="twitterid"
					value="${form.twitterid}" required /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><br> <input type="submit"
					name=Loginbutton value="Submit" /></td>
			</tr>
		</table>
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</form>
	
</body>
</html>
