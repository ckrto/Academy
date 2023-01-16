<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1 style = "text-align: center;">게시판목록</h1>

<div>
	<span>검색 게시글 수 : <span id = "count"></span>개</span>
</div>

<div style = "padding-left: 20px 0px; margin-top: 20px;">
	<select id = "type">
		<option value="title">제목</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>		
	</select>
	<input id = "query" type = "text" placeholder = "검색어">
	<button id = "search" style = "padding: 5px 10px">검색</button>
	<button style = "float : right;" onclick="location.href = '/bbs/insert'">글쓰기</button>
</div>

<table id = "tbl"></table>
<script id = "temp" type = "text/x-handlebars-template">
	<tr class = "title" style = "text-align: center;">
		<td width=100>No.</td>	
		<td width=400>제목</td>	
		<td width=250>날짜</td>	
		<td width=150>글쓴이</td>
	</tr>
	{{#each array}}
	<tr class = "row" onclick = "location.href='/bbs/read?no={{no}}'" style = "text-align: center;">
		<td>{{no}}</td>
		<td>{{title}}</td>
		<td>{{wdate}}</td>
		<td>{{writer}}</td>
	</tr>
	{{/each}}
</script>
<div class = "buttons" style = "text-align: center; margin-top: 20px">
	<button id = "home">처음으로</button>
	<button id = "prev">이전</button>
	<span id = "page" style ="color: black;">1/20</span>
	<button id = "next">다음</button>
	<button id = "last">마지막으로</button>
</div>
<script>
	var page = 1;
	var perPageNum = page;
	getList();
	
	$("#next").on("click", function() {
		page++;
		perPageNum = page;
		getList();
	});
	
	$("#prev").on("click", function() {
		page--;
		perPageNum = page;
		getList();
	});
	
	function getList() {
		var type = $("#type").val();
		var query = $("#query").val();

		$.ajax ({
			type : "get",
			url : "/bbs/list.json",
			data : {page : page, type : type, query : query},
			dataType : "json",
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#count").html(data.count);
				
				last = Math.ceil(data.count/5);
				$("#page").html(page + "/" + last);
				
				if(page == 1) {
					$("#prev").attr("disabled", true);
				}
				else {
					$("#prev").attr("disabled", false);
				}
				
				if(page == last) {
					$("#next").attr("disabled", true);
				}
				else {
					$("#next").attr("disabled", false);
				}
				
			}
		});		
	}
	
	$("#home").on("click", function() {
		page = 1;
		perPageNum = page;
		getList();
	});
	
	$("#last").on("click", function() {
		page = last;
		perPageNum = page;
		getList();
	});
	
	$("#search").on("click", function() {
		page = 1;
		getList();
	});
	
	$("#query").on("keydown", function(e) {
		if(e.keyCode == 13) {
			$("#search").click();
		}
	});
	
</script>