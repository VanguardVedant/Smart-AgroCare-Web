<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Smart AgroCare</title>
</head>

<body>

<h2>Login</h2>

<form action="LoginServlet" method="post">

Email:<br>
<input type="email" name="email" required><br><br>

Password:<br>
<input type="password" name="password" required><br><br>

<input type="submit" value="Login">

</form>

</body>
</html>