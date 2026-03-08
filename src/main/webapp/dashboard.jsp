<%@ page import="com.smartagrocare.model.User" %>

<%
User user = (User) session.getAttribute("user");

if(user == null)
{
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>

<body>

<h2>Welcome <%= user.getName() %></h2>
<p>Smart AgroCare Dashboard</p>
<h3>Upload Crop Leaf Image</h3>

<form action="UploadImageServlet" method="post" enctype="multipart/form-data">

Select Leaf Image:<br><br>
<input type="file" name="leafImage" required><br><br>

<input type="submit" value="Detect Disease">

</form>

<br><br>
<a href="LogoutServlet">Logout</a>
</body>
</html>