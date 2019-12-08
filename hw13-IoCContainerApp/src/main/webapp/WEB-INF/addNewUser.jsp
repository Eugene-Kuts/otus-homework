<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createNewUser">
    <p style="font-size:120%;">Create new user</p>
    <p>
        <input type="text" name="userName" placeholder="Name" required/>
    </p>
    <p>
        <input type="number" name="userAge" placeholder="Age" required/>
    </p>
    <p>
        <input type="text" name="userAddress" placeholder="Address" required/>
    </p>
    <p>
        <input type="text" name="userPhone" placeholder="Phone" required/>
    </p>
    <input type="submit" name="createUser" value="Create new user">
</form>
</body>
</html>