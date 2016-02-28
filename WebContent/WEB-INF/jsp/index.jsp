<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>PlusOnlineJudge!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@	include file="include.jsp"%>
</head>
<body>
	<%@	include file="topnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-1 col-md-8 blog-main">
				<div class="row">
					<div id="brief_stats" class="col-md-8">
						<p>
							Welcome, <a href="/profile/">${username }</a>! You have solved <strong>0
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
									<td><a
										href="${ctx }/problems/${problem.problemId }">
										${problem.problemDigest }
										</a></td>
									<td>${problem.problemLanguage }</td>
									<td>30.4%</td>

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
							href="/discuss/ask?cat=246">Post an Interview Question</a></li>
					</ul>
				</div>
				<div class="row sidebar-module">
					<ul class="col-md-offset-3 col-md-9 list-group">
						<li class="list-group-item list-group-item-danger"><strong>
								<span class="glyphicon glyphicon-floppy-disk"></span> <span>&nbsp;Sessions</span>
						</strong></li>
						<li class="list-group-item col-md-12"><a
							style="white-space: normal;"
							class="btn btn-success btn-md qa-ask-btn col-md-12"
							href="/session/">Manage Sessions</a></li>
					</ul>
				</div>

				<div class="row sidebar-module">
					<ul class="col-md-offset-3 col-md-9 list-group">
						<li class="list-group-item list-group-item-danger"><strong>
								<span class="glyphicon glyphicon-fire"></span> <span>&nbsp;Top
									10 Popular</span>
						</strong></li>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/alien-dictionary/" class="list-group-item"> <small>1.
								Alien Dictionary <i class="fa fa-lock"></i>

						</small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/tenth-line/" class="list-group-item"> <small>2.
								Tenth Line </small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/range-sum-query-2d-mutable/"
							class="list-group-item"> <small>3. Range Sum Query 2D
								- Mutable <i class="fa fa-lock"></i>

						</small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/shortest-word-distance-ii/"
							class="list-group-item"> <small>4. Shortest Word
								Distance II <i class="fa fa-lock"></i>

						</small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/word-break/" class="list-group-item"> <small>5.
								Word Break </small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/maximum-size-subarray-sum-equals-k/"
							class="list-group-item"> <small>6. Maximum Size
								Subarray Sum Equals k <i class="fa fa-lock"></i>

						</small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/merge-intervals/" class="list-group-item"> <small>7.
								Merge Intervals </small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/unique-word-abbreviation/"
							class="list-group-item"> <small>8. Unique Word
								Abbreviation <i class="fa fa-lock"></i>

						</small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/clone-graph/" class="list-group-item"> <small>9.
								Clone Graph </small>
						</a>

						<a onclick="ga('send', 'event', 'top-10', 'click', '')"
							href="/problems/find-the-celebrity/" class="list-group-item">
							<small>10. Find the Celebrity <i class="fa fa-lock"></i>

						</small>
						</a>

					</ul>
				</div>

				<div class="row sidebar-module">
					<ul class="col-md-offset-3 col-md-9 list-group">
						<li class="list-group-item list-group-item-warning"><strong>
								<i class="fa fa-building"></i> <span>&nbsp;&nbsp;Company</span>
						</strong></li>


						<a href="/company/linkedin/" class="list-group-item"> <span
							class="badge">32</span> <small>LinkedIn</small>
						</a>



						<a href="/company/google/" class="list-group-item"> <span
							class="badge">69</span> <small>Google</small>
						</a>



						<a href="/company/uber/" class="list-group-item"> <span
							class="badge">24</span> <small>Uber</small>
						</a>



						<a href="/company/airbnb/" class="list-group-item"> <span
							class="badge">11</span> <small>Airbnb</small>
						</a>



						<a href="/company/facebook/" class="list-group-item"> <span
							class="badge">57</span> <small>Facebook</small>
						</a>



						<a href="/company/twitter/" class="list-group-item"> <span
							class="badge">1</span> <small>Twitter</small>
						</a>



						<a href="/company/zenefits/" class="list-group-item"> <span
							class="badge">16</span> <small>Zenefits</small>
						</a>



						<a href="/company/amazon/" class="list-group-item"> <span
							class="badge">23</span> <small>Amazon</small>
						</a>



						<a href="/company/microsoft/" class="list-group-item"> <span
							class="badge">52</span> <small>Microsoft</small>
						</a>



						<a href="/company/snapchat/" class="list-group-item"> <span
							class="badge">1</span> <small>Snapchat</small>
						</a>



						<a href="/company/cryptic-studios/" class="list-group-item"> <span
							class="badge">2</span> <small>Cryptic Studios</small>
						</a>



						<a href="/company/apple/" class="list-group-item"> <span
							class="badge">1</span> <small>Apple</small>
						</a>



						<a href="/company/yahoo/" class="list-group-item"> <span
							class="badge">2</span> <small>Yahoo</small>
						</a>



						<a href="/company/hedvig/" class="list-group-item"> <span
							class="badge">1</span> <small>Hedvig</small>
						</a>



						<a href="/company/tinyco/" class="list-group-item"> <span
							class="badge">1</span> <small>TinyCo</small>
						</a>



						<a href="/company/dropbox/" class="list-group-item"> <span
							class="badge">2</span> <small>Dropbox</small>
						</a>



						<a href="/company/epic-systems/" class="list-group-item"> <span
							class="badge">1</span> <small>Epic Systems</small>
						</a>



						<a href="/company/bloomberg/" class="list-group-item"> <span
							class="badge">50</span> <small>Bloomberg</small>
						</a>



						<a href="/company/palantir/" class="list-group-item"> <span
							class="badge">2</span> <small>Palantir</small>
						</a>


					</ul>
				</div>


				<div class="row sidebar-module">
					<ul class="col-md-offset-3 col-md-9 list-group">
						<li class="list-group-item list-group-item-warning"><strong>
								<span class="glyphicon glyphicon-tags"></span> <span>&nbsp;&nbsp;Tag</span>
						</strong></li>


						<a href="/tag/array/" class="list-group-item"> <span
							class="badge">73</span> <small>Array</small>
						</a>



						<a href="/tag/hash-table/" class="list-group-item"> <span
							class="badge">34</span> <small>Hash Table</small>
						</a>



						<a href="/tag/linked-list/" class="list-group-item"> <span
							class="badge">24</span> <small>Linked List</small>
						</a>



						<a href="/tag/math/" class="list-group-item"> <span
							class="badge">38</span> <small>Math</small>
						</a>



						<a href="/tag/two-pointers/" class="list-group-item"> <span
							class="badge">27</span> <small>Two Pointers</small>
						</a>



						<a href="/tag/string/" class="list-group-item"> <span
							class="badge">45</span> <small>String</small>
						</a>



						<a href="/tag/divide-and-conquer/" class="list-group-item"> <span
							class="badge">12</span> <small>Divide and Conquer</small>
						</a>



						<a href="/tag/binary-search/" class="list-group-item"> <span
							class="badge">24</span> <small>Binary Search</small>
						</a>



						<a href="/tag/dynamic-programming/" class="list-group-item"> <span
							class="badge">39</span> <small>Dynamic Programming</small>
						</a>



						<a href="/tag/backtracking/" class="list-group-item"> <span
							class="badge">29</span> <small>Backtracking</small>
						</a>



						<a href="/tag/stack/" class="list-group-item"> <span
							class="badge">18</span> <small>Stack</small>
						</a>



						<a href="/tag/heap/" class="list-group-item"> <span
							class="badge">8</span> <small>Heap</small>
						</a>



						<a href="/tag/greedy/" class="list-group-item"> <span
							class="badge">9</span> <small>Greedy</small>
						</a>



						<a href="/tag/sort/" class="list-group-item"> <span
							class="badge">14</span> <small>Sort</small>
						</a>



						<a href="/tag/bit-manipulation/" class="list-group-item"> <span
							class="badge">13</span> <small>Bit Manipulation</small>
						</a>



						<a href="/tag/tree/" class="list-group-item"> <span
							class="badge">41</span> <small>Tree</small>
						</a>



						<a href="/tag/depth-first-search/" class="list-group-item"> <span
							class="badge">28</span> <small>Depth-first Search</small>
						</a>



						<a href="/tag/breadth-first-search/" class="list-group-item">
							<span class="badge">18</span> <small>Breadth-first Search</small>
						</a>



						<a href="/tag/union-find/" class="list-group-item"> <span
							class="badge">6</span> <small>Union Find</small>
						</a>



						<a href="/tag/graph/" class="list-group-item"> <span
							class="badge">7</span> <small>Graph</small>
						</a>



						<a href="/tag/design/" class="list-group-item"> <span
							class="badge">15</span> <small>Design</small>
						</a>



						<a href="/tag/topological-sort/" class="list-group-item"> <span
							class="badge">4</span> <small>Topological Sort</small>
						</a>



						<a href="/tag/trie/" class="list-group-item"> <span
							class="badge">3</span> <small>Trie</small>
						</a>



						<a href="/tag/binary-search-tree/" class="list-group-item"> <span
							class="badge">3</span> <small>Binary Search Tree</small>
						</a>



						<a href="/tag/recursion/" class="list-group-item"> <span
							class="badge">2</span> <small>Recursion</small>
						</a>



						<a href="/tag/segment-tree/" class="list-group-item"> <span
							class="badge">3</span> <small>Segment Tree</small>
						</a>



						<a href="/tag/binary-indexed-tree/" class="list-group-item"> <span
							class="badge">3</span> <small>Binary Indexed Tree</small>
						</a>



						<a href="/tag/brainteaser/" class="list-group-item"> <span
							class="badge">1</span> <small>Brainteaser</small>
						</a>



						<a href="/tag/memoization/" class="list-group-item"> <span
							class="badge">1</span> <small>Memoization</small>
						</a>


					</ul>
				</div>

			</div>
		</div>

	</div>
</body>
</html>