<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="//cdn.tinymce.com/4/tinymce.min.js"
	type="text/javascript" charset="utf-8"></script>
<%@	include file="include.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#ok').click(function() {
			var problemTests = new Array();
			problemTests.push({problemTestId:"1",problemTestInput:"II1",problemTestOutput:"OO1"});
			problemTests.push({problemTestId:"2",problemTestInput:"II2",problemTestOutput:"OO2"});
			problemTests = JSON.stringify(problemTests);
			$.ajax({
				type : 'POST',
				url : '${ctx}/problems/tempSaveProblem1',
				dataType:"json",
				contentType:"application/json",
				data : problemTests,
				success : function(result) {
					alert("save successfully");
				}
			});
		});
	});
</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<button id="ok"></button>
</body>
</html>