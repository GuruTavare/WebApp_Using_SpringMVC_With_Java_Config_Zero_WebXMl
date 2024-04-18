<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deleted Student Details</title>
</head>
<body>
    <h1>Deleted Student Details</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
        </tr>
        <tr>
            <td>${deletedStudent.id}</td>
            <td>${deletedStudent.name}</td>
            <td>${deletedStudent.address}</td>
        </tr>
    </table>
</body>
</html>
