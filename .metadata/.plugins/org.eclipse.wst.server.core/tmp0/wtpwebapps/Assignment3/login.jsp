<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<h1>Login Page</h1>

	<form method="post" action="/Assignment3/servlet1">
		<label for="username">Username: </label>
		<input type="text" name="username" placeholder="@username"><br>
		
		<label for="password">Password: </label>
		<input type="password" placeholder="@password" name="password"><br>
		
		
		<label for="dropdown">Choose the user type: </label>
		<select name="dropdown">
        <option value="option1">student</option>
        <option value="option2">teacher</option>
        <option value="option3">administrator</option>
        </select><br>
        
		<input type="submit" value="Login">
	</form>
	
</body>
</html>