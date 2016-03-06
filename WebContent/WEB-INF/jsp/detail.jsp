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
	<a href="mailto:rocking2013@hotmail.com">
		<img src="${ctx }/res/help.png" class="img-responsive" height="60px" width="120px" id="help"/>
	</a>
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
					data-original-title="Shortcut: Command + enter"
					onclick="submitCode();">Submit Solution</button>
				<button id="discuss" class="btn btn btn-success" type="button">
					Go to Discuss !</button>
			</div>
		</div>
		<pre id="embedded_ace_code" style="height: 400px;" class="col-md-12"
			style="margin-bottom: 12px;">
			<div id="editor"></div>
		</pre>
		<div id="resultDiv" class="row table-responsive">
		</div>

	</div>
	<div class="modal fade" id="resultDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   		<div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               OJ Result Detail
	            </h4>
	         </div>
	         <div class="modal-body">
	            在这里添加一些文本
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">close
	            </button>
	         </div>
	      </div><!-- /.modal-content -->
		</div>
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
			$('#resultDiv').addClass('result-content');
			$.ajax({
				type : "POST",
				url : "${ctx}/problems/submitCode",
				data : {
					codeSubmit : codeToSubmit,
					problemId : problemId,
					solutionLanguage : solutionLanguage
				},
				success : function(msg) {
					var results = "";
					var count = 0;
					results += "<table class='table table-hover table-striped col-md-2'>";
					results += "<caption>OJ Results</caption>";
					results += "<thead>";
					results += "<tr>";
					results += 		"<th>#</th>";
					results += 		"<th>Judge</th>";
					results += 		"<th>Input</th>";
					results += 		"<th>Expected Output</th>";
					results += 		"<th>OutPut</th>";
					results += 		"<th>Details</th>";
					results += "</tr>";
					
					results += "</thead>";
					
					results += "<tbody>";
					
					$.each(msg,function(index,item){
						var realTestInput = item.testInput;
						var realTestOutput = item.testOutput;
						var realResult = item.result;
						var realOjResult = item.ojResult;
						
						var cutLength = 30;
						var displayTestInput = realTestInput.length>=cutLength?realTestInput.substr(0,cutLength)+"...":realTestInput;
						var displayTestOutput = realTestOutput.length>=cutLength?realTestOutput.substr(0,cutLength)+"...":realTestOutput;
						var displayResult = realResult.length>=cutLength?realResult.substr(0,cutLength)+"...":realResult;
						
						if(realOjResult == 'AC')
							results += "<tr class='success'><td>"+(++count)+"</td>";
						else if(realOjResult == 'CE')
							results += "<tr class='warning'><td>"+(++count)+"</td>";
						else if(realOjResult == 'WA')
							results += "<tr class='danger'><td>"+(++count)+"</td>";
						results += "<td>"+realOjResult+"</td>";
						results += "<td>"+displayTestInput+"</td>";
						results += "<td>"+displayTestOutput+"</td>";
						results += "<td>"+displayResult+"</td>";
						results += "<td><button type='button' class='btn btn-info' onclick='resultDetail();'>detail</button></td></tr>";
					});
					results += "</tbody>";
					results += "</table>";
					
					$('#resultDiv').empty().append(results);
				}
			});
		}
		function resultDetail(item){
			alert(realOjResult+realTestInput+realTestOutput+realResult);
			alert(item['testInput']);
			$("#resultDetailModal").modal();
		}
	</script>
</body>
</html>