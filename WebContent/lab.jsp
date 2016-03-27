<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"
	rel="stylesheet">
<link href="${ctx }/css/customize.css" rel="stylesheet">

<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="http://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="http://cdn.bootcss.com/ace/1.2.3/ace.js" type="text/javascript"
	charset="utf-8"></script>
<style type="text/css" media="screen">
#editor {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}
</style> 
</head>
<script type="text/javascript">
	$(function() {

	});
	function changeTheme(obj){
		var some = obj.html;
		alert(some);
	}
	function changeText(obj){
		var some = obj.value;
		alert(some);
	}
</script>
</head>
<body>
	<div id="tabs">
		<select id="Xselect" onchange="changeTheme(this)">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
		</select>
	</div>

	<input type="text" onchange="changeText(this)">
</body>
</html>