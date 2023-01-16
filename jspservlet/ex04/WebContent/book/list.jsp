<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>도서목록</h2>
<div>
	전체도서수 : <span id = "count"></span>
</div>
<table id = "tbl"></table>
<script id = "temp" type = "text/x-handlebars-template">
	<tr class = "title">
		<td width = "150">이미지</td>
		<td width = "250">제목</td>
		<td width = "100">저자</td>
		<td width = "200">가격</td>
		<td width = "200">등록일</td>
	</tr>
	{{#each array}}
	<tr class = "row" isbn = "{{isbn}}">
		<td><img src = "{{image}}" contents = "{{contents}}" width = 70 class = "thum"></td>
		<td class = "subject">{{title}}</td>
		<td class = "authors">{{authors}}</td>
		<td class = "price">{{price}}</td>
		<td class = "wdate">{{wdate}}</td>
	</tr>
	{{/each}}
</script>

<div class = "buttons" style = "text-align: center; margin-top: 20px;">
	<button id = "prev">이전</button>
	<span id = "page" style = "color: black;">1/10</span>
	<button id = "next">다음</button>
</div>

<div id = "background">
	<div id = "lightbox">
		<div>
			<img src = "" id = "image" width = 500>
			<p style = "margin-top: 20px;" id="contents"></p>
		</div>
		<div style = "text-align: center; margin: 20px 0px">
			<button id = "close">닫기</button>
		</div>
	</div>
</div>

<script>
	var page = 1;
	getList();
	
	$("#close").on("click", function() {
		$("#background").hide();	
	});
	
	$("#tbl").on("click", ".row img", function() {
		var contents = $(this).attr("contents");
		$("#contents").html(contents);
		$("#background").show();
	});
	
	$("#next").on("click", function() {
		page++;
		getList();
	});
	
	$("#prev").on("click", function() {
		page--;
		getList();
	});
	
	function getList() {
		$.ajax ({
			type : "get",
			url : "/book/list.json",
			dataType : "json",
			data : {page : page},
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#count").html(data.count);
				
				var last = Math.ceil(data.count/5);
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
</script>