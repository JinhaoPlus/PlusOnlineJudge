<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
<script type="text/javascript">
	$(function() {
		valid_form();
	});
	function valid_form(){
		var validator = $('#form-signin').bootstrapValidator(
		{
			message : 'This value is not valid',
			fields : {
				username : {
					message : 'The username is not valid',
					validators : {
						notEmpty : {
							message : 'The username is required and can\'t be empty'
						},
						stringLength : {
							min : 6,
							max : 30,
							message : 'The username must be more than 6 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z0-9_\.]+$/,
							message : 'The username can only consist of alphabetical, number, dot and underscore'
						},
						different : {
							field : 'password',
							message : 'The username and password can\'t be the same as each other'
						}
					}
				},
				email : {
					validators : {
						notEmpty : {
							message : 'The email address is required and can\'t be empty'
						},
						emailAddress : {
							message : 'The input is not a valid email address'
						}
					}
				},
				password : {
					validators : {
						notEmpty : {
							message : 'The password is required and can\'t be empty'
						},
						identical : {
							field : 'confirm_password',
							message : 'The password and its confirm are not the same'
						},
						different : {
							field : 'username',
							message : 'The password can\'t be the same as username'
						}
					}
				},
				confirm_password : {
					validators : {
						notEmpty : {
							message : 'The confirm password is required and can\'t be empty'
						},
						identical : {
							field : 'password',
							message : 'The password and its confirm are not the same'
						},
						different : {
							field : 'username',
							message : 'The password can\'t be the same as username'
						}
					}
				}
			}
		});
	}
</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="container">
			<form class="form-signin" id="form-signin" action="/PlusOnlineJudge/accounts/signup/" method="post">
				<h3 class="form-signin-heading">Sign Up</h3>
				<hr>
				<div class="form-group">
					<input autofocus="autofocus" id="username" maxlength="30"
						name="username" placeholder="Username" type="text"
						class="form-control">
				</div>
				<div class="form-group">
					<input id="email" name="email" placeholder="E-mail address"
						type="email" class="form-control">
				</div>
				<div class="form-group">
					<input id="password" name="password" placeholder="Password"
						type="password" class="form-control">
				</div>
				<div class="form-group">
					<input id="confirm_password" name="confirm_password"
						placeholder="Confirm Password" type="password"
						class="form-control">
				</div>
				<button class="btn btn-primary" type="submit"
					onclick="valid_submit();">Sign Up for PlusOJ</button>
				<a href="${ctx }/accounts/tologin"  class="btn btn-info">login?</a>
			</form>
			
		</div>
	</div>
</body>
</html>