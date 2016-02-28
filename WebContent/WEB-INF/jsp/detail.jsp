<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${ctx }/js/ace/ace.js" type="text/javascript"
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
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="question-title" style="overflow: hidden;">
					<h3 style="display: inline-block; margin-top: 0px;">
						#${chosenProblem.problemId}. ${chosenProblem.problemDigest}</h3>


				</div>
				<hr>
				<div class="row question-content">
					<div class="col-md-12">${chosenProblem.problemContent }</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row" style="margin-bottom: 12px;">
			<div class="col-md-2">
				<select class="form-control" id="lang" onchange="changeLanguage();">
					<option value="c">C</option>
					<option value="cpp">C++</option>
					<option value="java" selected="selected">Java</option>
					<option value="py">Python</option>
					<option value="rb">Ruby</option>
					<option value="haskell">Haskell</option>
				</select>
			</div>
			<div class="col-md-2">
				<select class="form-control" id="theme" onchange="changeTheme();">
					<option value="monokai" selected="selected">Monokai</option>
					<option value="xcode">Xcode</option>
					<option value="eclipse">Eclipse</option>
					<option value="solarized">Solarized</option>
				</select>
			</div>
			<div class="col-md-6">
				<button class="btn btn-default" type="button" onclick="resetCode();">
					<span class="glyphicon glyphicon-refresh"></span>
				</button>
				<button id="submit" class="btn btn-primary btn-pad" type="submit"
					data-original-title="Shortcut: Command + enter" onclick="submitCode();">Submit
					Solution</button>
				<button id="discuss" class="btn btn btn-success" type="button">
					Go to Discuss !</button>
			</div>
		</div>
		<pre id="embedded_ace_code" style="height: 400px;" class="col-md-12"
			style="margin-bottom: 12px;">
			<div id="editor"></div>
		</pre>

	</div>



	<script>
		var editor = ace.edit("editor");
		editor.setTheme("ace/theme/monokai");
		editor.getSession().setMode("ace/mode/java");
		function resetCode() {
			editor.setValue("");
		}
		function changeLanguage() {
			if ($('#lang option:selected').val() == 'java')
				editor.getSession().setMode("ace/mode/java");
			else if ($('#lang option:selected').val() == 'c')
				editor.getSession().setMode("ace/mode/c");
			else if ($('#lang option:selected').val() == 'cpp')
				editor.getSession().setMode("ace/mode/c");
			else if ($('#lang option:selected').val() == 'ruby')
				editor.getSession().setMode("ace/mode/ruby");
			else if ($('#lang option:selected').val() == 'python')
				editor.getSession().setMode("ace/mode/python");
			else if ($('#lang option:selected').val() == 'haskell')
				editor.getSession().setMode("ace/mode/haskell");
		}
		function changeTheme() {
			if ($('#theme option:selected').val() == 'monokai')
				editor.setTheme("ace/theme/monokai");
			else if ($('#theme option:selected').val() == 'xcode')
				editor.setTheme("ace/theme/xcode");
			else if ($('#theme option:selected').val() == 'eclipse')
				editor.setTheme("ace/theme/eclipse");
			else if ($('#theme option:selected').val() == 'solarized')
				editor.setTheme("ace/theme/solarized_dark");
		}
		function submitCode() {
			var codeToSubmit = editor.getValue();
			var problemId = ${chosenProblem.problemId};
			var solutionLanguage = $('#lang option:selected').val();
			$.ajax({
				type:"POST",
				url:"${ctx}/problems/submitCode",
				data:{
					codeSubmit:codeToSubmit,
					problemId:problemId,
					solutionLanguage:solutionLanguage
				},
				success:function(msg){
					alert("Code Submitted");
				}
			});
		}
	</script>
</body>
</html>