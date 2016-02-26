<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<form class="form-signin" method="POST" action="${ctx }/index">
			<h3 class="form-signin-heading">Login</h3>
			<hr />
			<div class="form-group">
				<input autofocus="autofocus" id="username" name="username"
					placeholder="Username" type="text" />
			</div>
			<div class="form-group">
				<input id="password" name="password" placeholder="Password"
					type="password" />
			</div>
			<label class="form-group"><input id="id_remember"
				name="remember" type="checkbox" />Remember Me</label>
			<div class="form-group">

				<button class="btn btn-primary" type="submit">Login</button>
				<a class="pull-right" href="/accounts/password/reset/">Forgot
					Password?</a>
			</div>
		</form>
	</div>
</body>
</html>