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
	<form action="${ctx }/problems/submitCode" method="post">
	<div class="container">
		<div class="row">
			<div class="col-md-11">
				<div class="question-title" style="overflow: hidden;">
					<h1 style="display: inline-block; margin-top: 0px;">
						#${chosenProblem.problemId}. ${chosenProblem.problemDigest}
					</h1>

					<h5>Tips : You can use <span class="orange">${chosenProblem.problemLanguage}</span> as your programming Languages.</h5>
				</div>
				<hr>
				
				<div class="row question-content">
					<div class="col-md-12">${chosenProblem.problemContent }</div>
				</div>
				<%int count = 0; %>
				<div class="row result-content table-responsive">
					<table class="table table-hover table-striped col-md-2">
						<caption>Optional OJ Tests</caption>
						<thead>
							<tr>
								<th>#</th>
								<th>Input</th>
								<th>OutPut</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${displayTests }" var="test">
								<tr>
									<td><%=++count %></td>
									<td>${test.problemTestInput }</td>
									<td>${test.problemTestOutput }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<br />
		<div class="row" style="margin-bottom: 12px;">
			<div class="col-md-2">
				<select class="form-control" id="lang" onchange="changeLanguage();">
					<c:forEach items="${problemLanguages }" var="language">
						<c:if test="${language eq 'c' }">
							<option value="c">C</option>
						</c:if>
						<c:if test="${language eq 'cpp' }">
							<option value="cpp">C++</option>
						</c:if>
						<c:if test="${language eq 'java' }">
							<option value="java">Java</option>
						</c:if>
						<c:if test="${language eq 'python' }">
							<option value="py">Python</option>
						</c:if>
						<c:if test="${language eq 'ruby' }">
							<option value="rb">Python</option>
						</c:if>
						<c:if test="${language eq 'haskell' }">
							<option value="haskell">Haskell</option>
						</c:if>
						
					</c:forEach>
					
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
					data-original-title="Shortcut: Command + enter">Submit Solution</button>
				<button id="discuss" class="btn btn btn-success" type="button">
					Go to Discuss !</button>
			</div>
		</div>
		<pre id="embedded_ace_code" style="height: 400px;" class="col-md-12"
			style="margin-bottom: 12px;">
			<div id="editor"></div>
		</pre>
		<div>
			<input id="problemId" name="problemId" value="${chosenProblem.problemId}" type="hidden"/>
			<textarea id="codeToSubmit" name="codeSubmit" style="display:none"></textarea>
			<input id="solutionLanguage" name="solutionLanguage" type="hidden"/>
		</div>
	</div>
	</form>
	
	<script>
		var editor = ace.edit("editor");
		editor.setTheme("ace/theme/monokai");
		editor.getSession().setMode("ace/mode/java");
		editor.getSession().on("change", function(e){
			$("#codeToSubmit").val(editor.getValue());
		});
		$("#solutionLanguage").val($('#lang option:selected').val());
		function resetCode() {
			editor.setValue("");
		}
		function changeLanguage() {
			if ($('#lang option:selected').val() == 'java'){
				editor.getSession().setMode("ace/mode/java");
				$("#solutionLanguage").val("java");
			}
			else if ($('#lang option:selected').val() == 'c'){
				editor.getSession().setMode("ace/mode/c");
				$("#solutionLanguage").val("c");
			}
			else if ($('#lang option:selected').val() == 'cpp'){
				editor.getSession().setMode("ace/mode/c");
				$("#solutionLanguage").val("cpp");
			}
			else if ($('#lang option:selected').val() == 'ruby'){
				editor.getSession().setMode("ace/mode/ruby");
				$("#solutionLanguage").val("ruby");
			}
			else if ($('#lang option:selected').val() == 'python'){
				editor.getSession().setMode("ace/mode/python");
				$("#solutionLanguage").val("python");
			}
			else if ($('#lang option:selected').val() == 'haskell'){
				editor.getSession().setMode("ace/mode/haskell");
				$("#solutionLanguage").val("haskell");
			}
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
	</script>
</body>
</html>