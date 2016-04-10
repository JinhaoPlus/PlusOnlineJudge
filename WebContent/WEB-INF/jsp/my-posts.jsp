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
			<div class="col-md-offset-1 col-md-10">
				<div class="row">
					<div id="brief_stats" class="col-md-12">
						<h3>
							Welcome, <a href="${ctx }/accounts/profile">${loginuser.username }</a>&nbsp;!&nbsp;
							You have Posted <strong>${problemPostNum} </strong> problems.
						</h3>
					</div>
				</div>
				<hr/>
				<div>
					<a id="postOneButton" class="btn btn-primary" href="${ctx }/postProblem"><span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;Post One</a>
				</div>
				<hr/>
				<div id="problemPostListRow" class="row">
					<table id="problemList"
						class="table table-hover table-striped table-centered">
						<thead>
							<tr>
								<th class="header-id">
									<div>#</div>
								</th>
								<th class="header-digest">
									<div>Digest</div>
								</th>
								<th class="header-language">
									<div>Language</div>
								</th>
								<th class="header-status">
									<div>Online Status</div>
								</th>
								<th class="header-opt">
									<div>Operations</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${problemPostList }" var="problem">
								<tr>
									<td>${problem.problemId }</td>
									<td><a href="${ctx }/problems/${problem.problemId }">${problem.problemDigest }</a></td>
									<td>${problem.problemLanguage }</td>
									<td>
										<c:if test="${problem.problemVisable == 1}"><button class="btn btn-success" disabled="disabled">Passed</button></c:if>
										<c:if test="${problem.problemVisable != 1}"><button class="btn btn-warning" disabled="disabled">Waited</button></c:if>
									</td>
									<td>
										<c:if test="${problem.problemVisable == 1}">
											<button class="btn btn-info" disabled="disabled">Edit</button>
											<button class="btn btn-danger" disabled="disabled">Delete</button>
										</c:if>
										<c:if test="${problem.problemVisable != 1}">
											<a id="editProblemButton" class="btn btn-info" href="${ctx }/problems/editProblem/${problem.problemId }">Edit</a>
											<a class="btn btn-danger">Delete</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@	include file="footer.jsp"%>
</body>
</html>