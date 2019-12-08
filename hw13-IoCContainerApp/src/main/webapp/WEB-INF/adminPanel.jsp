<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home work 13</title>
</head>
<hr>
<hr>
<br>
<table border="1">
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Age</th>
        <th>Address</th>
        <th>Phone</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.addressDataSet.street}</td>
            <td>${user.phoneDataSet[0].number}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<hr>
<br>
<a href="${pageContext.request.contextPath}/addNewUserPage">Add new User</a>

</body>
</html>