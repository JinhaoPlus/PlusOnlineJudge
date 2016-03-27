<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Cloud Runner</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://cdn.bootcss.com/ace/1.2.3/ace.js"
	type="text/javascript" charset="utf-8"></script>
<style type="text/css" media="screen">
.editor {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}
</style>
<%@	include file="include.jsp"%>
<script type="text/javascript">
	$(function() {
		var editor1 = ace.edit("editor1");
		editor1.setTheme("ace/theme/monokai");
		editor1.getSession().setMode("ace/mode/java");
		$('#addTab').click(function () {
		  	var nextTab = $('#tabs li').size();
		  	// create the tab
		  	$('<li><a href="#tab'+nextTab+'" data-toggle="tab">File'+nextTab+'</a></li>').appendTo('#tabs');
		  	// create the tab content
		  	var tab_pane = "<br/><input id='tabId' type='hidden' value='editor"+nextTab+"'/><div class='row' style='margin-bottom: 12px;'>";
		  	tab_pane += $('#settings_row').html();
		  	tab_pane += "</div><pre id='embedded_ace_code' style='height: 400px;' class='col-md-12' style='margin-bottom: 12px;'><div class='editor' id='editor"+nextTab+"'></div></pre></div>";
		  	
		  	$('<div class="tab-pane fade in active" id="tab'+nextTab+'">'+tab_pane+'</div>').appendTo('.tab-content');
		  	var editorx = ace.edit('editor'+nextTab);
		  	editorx.setTheme("ace/theme/xcode");
			editorx.getSession().setMode("ace/mode/java");
		  	$('#tabs a:last').tab('show');
		});
		$('#addTab').click(function () {
			var some = obj.innerHtml;
			var editorX = obj.parent().parent().prev().value();
			var editor = ace.edit(editorX);
			if ($('#theme option:selected').val() == 'monokai')
				editor.setTheme("ace/theme/monokai");
			else if ($('#theme option:selected').val() == 'xcode')
				editor.setTheme("ace/theme/xcode");
			else if ($('#theme option:selected').val() == 'eclipse')
				editor.setTheme("ace/theme/eclipse");
			else if ($('#theme option:selected').val() == 'solarized')
				editor.setTheme("ace/theme/solarized_dark");
		});
	});
	function changeTheme(obj) {
		var some = obj.innerHtml;
		var editorX = obj.parent().parent().prev().value();
		var editor = ace.edit(editorX);
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
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row"> 
			<div class="col-md-offset-1 col-md-10">
				<ul id="tabs" class="nav nav-tabs">
					<li><a id="addTab"> + </a></li>
					<li class="active"><a href="#tab1" data-toggle="tab">
							File1 </a></li>
				</ul>
				<div id="tabContents" class="tab-content">
					<div class="tab-pane fade in active" id="tab1">
						<br/>
						<input id="tabId" type="hidden" value="editor1"/>
						<div id="settings_row" class="row" style="margin-bottom: 12px;">
							<div class="col-lg-2">
								<select class="form-control" id="lang"
									onchange="changeLanguage();">
									<option value="c">C</option>
									<option value="cpp">C++</option>
									<option value="java" selected="selected">Java</option>
									<option value="python">Python</option>
									<option value="ruby">Ruby</option>
									<option value="haskell">Haskell</option>
									<option value="php">Php</option>
									<option value="go">Go</option>
									<option value="javascript">Javascript</option>
									<option value="swift">Swift</option>
								</select>
							</div>
							<div class="col-lg-2">
								<select class="form-control" id="theme" onchange="changeTheme(this)">
									<option value="monokai" selected="selected">Monokai</option>
									<option value="xcode">Xcode</option>
									<option value="eclipse">Eclipse</option>
									<option value="solarized">Solarized</option>
								</select>
							</div>
							<div class="col-lg-6">
								<button class="btn btn-default" type="button"
									onclick="resetCode();">
									<span class="glyphicon glyphicon-refresh"></span>
								</button>
								<button id="run" class="btn btn-success btn-pad"
									type="submit" data-original-title="Shortcut: Command + enter">run</button>
							</div>
						</div>
						<pre id="embedded_ace_code" style="height: 400px;"
							class="col-md-12" style="margin-bottom: 12px;">
							<div id="editor1" class="editor"></div>
						</pre>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<%@	include file="footer.jsp"%>
	<script>
		
		function resetCode() {
			editor.setValue("");
		}
		function changeLanguage() {
			if ($('#lang option:selected').val() == 'java'){
				editor.getSession().setMode("ace/mode/java");
				$("#solutionLanguage").val("java");
				editor.setValue(javaPre);
			}
			else if ($('#lang option:selected').val() == 'c'){
				editor.getSession().setMode("ace/mode/c_cpp");
				$("#solutionLanguage").val("c");
				editor.setValue(cPre);
			}
			else if ($('#lang option:selected').val() == 'cpp'){
				editor.getSession().setMode("ace/mode/c_cpp");
				$("#solutionLanguage").val("cpp");
				editor.setValue(cPre);
			}
			else if ($('#lang option:selected').val() == 'ruby'){
				editor.getSession().setMode("ace/mode/ruby");
				$("#solutionLanguage").val("rb");
				editor.setValue(rbPre);
			}
			else if ($('#lang option:selected').val() == 'python'){
				editor.getSession().setMode("ace/mode/python");
				$("#solutionLanguage").val("py");
				editor.setValue(pyPre);
			}
			else if ($('#lang option:selected').val() == 'haskell'){
				editor.getSession().setMode("ace/mode/haskell");
				$("#solutionLanguage").val("hs");
				editor.setValue(hsPre);
			}
		}
		
	</script>
</body>
</html>