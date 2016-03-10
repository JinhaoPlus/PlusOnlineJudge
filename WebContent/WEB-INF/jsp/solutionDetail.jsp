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
			<div class="col-md-11">
				<div class="question-title" style="overflow: hidden;">
					<h1 style="display: inline-block; margin-top: 0px;">
						#${chosenProblem.problemId}. ${chosenProblem.problemDigest}
					</h1>

					<h5>Tips : You used <span class="orange">${solution.solutionLanguage}</span> as your programming Language.</h5>
				</div>
				<hr>
				
				<div class="row question-content">
					<div class="col-md-12">${chosenProblem.problemContent }</div>
				</div>
				<%int count = 0; %>
				<div class="row result-content table-responsive">
					<table class="table table-hover table-striped  col-md-12">
						<caption>Optional OJ Tests</caption>
						<thead>
							<tr>
								<th width="20%">#</th>
								<th width="80%">Content</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${testResults }" var="result">
								<c:if test="${result.ojResult == 'AC'}">
								<tr class="success">
									<td>Judge</td>
									<td>${result.ojResult }</td>
								</tr>
								</c:if>
								<c:if test="${result.ojResult != 'AC'}">
								<tr class="danger">
									<td>Judge</td>
									<td>${result.ojResult }</td>
								</tr>
								</c:if>
								<tr>
									<td>Input</td>
									<td>${result.testInput }</td>
								</tr>
								<tr>
									<td>Expected Output</td>
									<td>${result.testOutput }</td>
								</tr>
								<tr>
									<td>Output</td>
									<td>${result.result }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<br />
		<div class="row" style="margin-bottom: 12px;">
			<span class="label label-primary">
				${solution.solutionLanguage }
			</span>
			<span class="label label-danger">
				${solution.finalOJResult }
			</span>
		</div>
		<pre id="embedded_ace_code" style="height: 400px;" class="col-md-12"
			style="margin-bottom: 12px;">
			<div id="editor"></div>
		</pre>
		<div id="resultDiv" class="row table-responsive">
		</div>
	</div>
	
	<script>
		var editor = ace.edit("editor");
		editor.setReadOnly(true);
		editor.setTheme("ace/theme/monokai");
		editor.getSession().setMode("ace/mode/java");
		editor.setValue("${solution.codeSubmit}");
	</script>
</body>
</html>