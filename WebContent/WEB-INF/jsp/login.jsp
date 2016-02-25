<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login In!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<form class="form-signin" method="POST"
			action="${ctx }/accounts/login/">
			<h3 class="form-signin-heading">Sign In</h3>
			<hr />
			<input type='hidden' name='csrfmiddlewaretoken'
				value='m4RXMtYwimWOhZ9HrNSJFWGRTNVjmJNq' />
			<div class="form-group">
				<input autofocus="autofocus" id="id_login" name="login"
					placeholder="Username or e-mail" type="text" />
			</div>
			<div class="form-group">
				<input id="id_password" name="password" placeholder="Password"
					type="password" />
			</div>
			<label class="form-group"><input id="id_remember"
				name="remember" type="checkbox" />Remember Me</label>
			<div class="form-group">

				<button class="btn btn-primary" type="submit">Sign In</button>
				<a class="pull-right" href="/accounts/password/reset/">Forgot
					Password?</a>
			</div>
		</form>
	</div>
</body>
</html>