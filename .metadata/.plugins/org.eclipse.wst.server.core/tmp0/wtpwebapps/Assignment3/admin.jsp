<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to Administrator Dashboard</h1>
<p>Please create new users here: </p>
   <form method="post" action="/Assignment3/addUserServlet">  
   
		<label for="username">Username: </label>
		<input type="text" name="username" placeholder="@username"><br>
		
		<label for="password">Password: </label>
		<input type="password" placeholder="@password" name="password"><br>
		
		<label for="firstName">First Name: </label>
		<input type="text" placeholder="@first name" name="firstname"><br>
		
		<label for="lastName">Last Name: </label>
		<input type="text" placeholder="@last name" name="lastname"><br>
		
		<label for="phone">Contact number: </label>
		<input type="text" placeholder="@phone number" name="phone"><br>
		
		<label for="role">Role: </label>
		<input type="text" placeholder="@student/teacher" name="role"><br>
		
		<label for="course_id">Course_ID: </label>
		<input type="text" placeholder="@course_id" name="course_ID"><br>
		
		<br><input type="submit" value="Create">
</form>
<p>Please create new courses here: </p>
<form method="post" action="/Assignment3/addCourseServlet">
        <label for="coursename">Course name: </label>
		<input type="text" name="coursename" placeholder="@courseName"><br>
		
		<label for="course_ID">course ID: </label>
		<input type="text" name="course_ID" placeholder="@course_ID"><br>
		
		<label for="semester">Semester: </label>
		<input type="text" name="semester" placeholder="@semester"><br>
		
		<br><input type="submit" value="Create">
</form>
<form method="post" action="/Assignment3/LogOut"><br><input type="submit" value="Log out"></form>

</body>
</html>