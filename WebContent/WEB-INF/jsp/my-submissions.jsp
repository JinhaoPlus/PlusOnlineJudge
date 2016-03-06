<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>My Submissions</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div id="brief_stats" class="col-md-8">
				<p>
					Hello, <a href="/profile/">${username }</a>! You have solved <strong>0
						/ 1</strong> problems. (<a href="/session/">manage sessions</a>)
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
			<table id="problemList" class="table table-striped table-centered">
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
							<td>ac</td>
							<td><a href="${ctx }/problems/${problem.problemId }">
									${problem.problemDigest } </a></td>
							<td>${problem.problemLanguage }</td>
							<td>30.4%</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</body>
</html>