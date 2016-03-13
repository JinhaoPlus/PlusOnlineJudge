<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
<script type="text/javascript">
$(function(){ 
}); 
</script>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-8 blog-main">
				<div class="row">
					<div id="brief_stats" class="col-md-8">
						<p>
							Welcome, <a href="${ctx }/profile">${loginuser.username }</a>! You have solved <strong>0
								/ 1</strong> problems.
						</p>
					</div>
					<input id="searchText" type="text" placeholder="Search Problems"
						class="col-md-4 pull-right search-query">
				</div>
				<div class="row col-md-4">
					<select id="filterchosen" class="form-control"
						onchange="selecthandler(this.value);">
						<option value="default" selected="selected">Choose one
							filter</option>
						<option value="solved">Solved Problems</option>
						<option value="unsolved">Unsolved Problems</option>
						<option value="attempt">Attempted Problems</option>
					</select>
				</div>
				<div id="problemListRow" class="row">
					<table id="problemList" class="table table-hover table-striped table-centered">
						<thead>
							<tr>
								<th class="header-id">
									<div>#</div>
								</th>
								<th class="header-status">
									<div>Status</div>
								</th>
								<th class="header-digest">
									<div>Digest</div>
								</th>
								<th class="header-language">
									<div>Language</div>
								</th>
								<th class="header-ac">
									<div>Acceptance</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${problemsList }" var="problem">
								<tr>
									<td>${problem.problemId }</td>
									<td>${problem.someUserResult }</td>
									<td><a
										href="${ctx }/problems/${problem.problemId }">
										${problem.problemDigest }
										</a></td>
									<td>${problem.problemLanguage }</td>
									<td> 
										<c:if test="${problem.problemSolveTimes == '0' }">
											<div class="progress progress-striped">
												<div class="progress-bar progress-bar-success" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 
											      	style="0%;">
											  	</div>
											</div>
										</c:if>
										<c:if test="${problem.problemSolveTimes != '0' }">
											<div class="progress progress-striped">
												<div class="progress-bar progress-bar-success" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 
											      	style="width: ${problem.problemAcTimes/problem.problemSolveTimes*100}%;">
											  	</div>
											   	<div class="progress-bar progress-bar-danger" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 	
											      	style="width: ${problem.problemWaTimes/problem.problemSolveTimes*100}%;">
											   	</div>
											   	<div class="progress-bar progress-bar-warning" role="progressbar" 
											      	aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 	
											      	style="width: ${problem.problemCeTimes/problem.problemSolveTimes*100}%;">
											   	</div>
											</div>
									   	</c:if>
									</td>

								</tr>
							</c:forEach>
						</tbody>
						
					</table>
				</div>
			</div>
			<div class="col-md-3 blog-sidebar">
				<div class="row sidebar-module">
					<ul class="col-md-offset-3 col-md-9 list-group">
						<li class="list-group-item list-group-item-danger"><strong>
								<span class="glyphicon glyphicon-heart"></span> <span>&nbsp;Contribute</span>
						</strong></li>
						<li class="list-group-item"><a style="white-space: normal;"
							class="btn btn-success btn-md qa-ask-btn"
							href="#">Post an Problem</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<%@	include file="footer.jsp"%>
</body>
</html>