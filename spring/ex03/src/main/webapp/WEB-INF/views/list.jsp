<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시판 목록</title>
</head>
<body>
	<h1>게시판 목록</h1>
	<table id="tbl" border = 1></table>
	<script id="temp" type="text/x-handlebars-template">
		<tr>
			<th width=100>No.</th>
			<th width=300>제목</th>
			<th width=100>조회수</th>
			<th width=100>댓글수</th>
		</tr>
   		{{#each.}}
			<tr>
				<td>{{bno}}</td>
				<td><a href= "/read/{{bno}}">{{title}}</a></td>
				<td>{{viewcnt}}</td>
				<td>{{replycnt}}</td>
			</tr>		
		{{/each}}
	</script>
</body>
<script>
	getList();
	
	function getList() {
		$.ajax ({
			type : "get",
			url : "/api/board/list",
			dataType : "json",
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
			}
		})
	}
</script>
</html>