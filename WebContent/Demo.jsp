<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>OJ</title>
<script type="text/javascript" src="/OJ/js/jquery.js">
</script>
<script type="text/javascript">
	$(function(){
		$('#sendCode').click(function(){
			$.ajax({  	
			    url : '/OJ/code/', 
			    data : $("#codeForm").serializeArray(),
			    type : 'post',
			    success : function (opts) { 
			        $('#resultDiv').html(opts);
				}  
		    });
		});
	});
</script>
</head>
<body>
	<form id="codeForm">
		<textarea name="source" rows="10" cols="100"></textarea>
		<input id="sendCode" type="button" value="提交"/>
	</form>
	<div id="resultDiv">
	</div>
</body>
</html>