<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품이미지검색</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<h1>상품이미지검색</h1>
	<input type="text" id="query" value="노트북">
	<button id="download">선택이미지 다운로드</button>
	<table id="tbl"></table>
	<script id="temp" type="text/x-handlebars-template">
		{{#each items}}
		<tr class="row">
			<td><input type="checkbox" class="chk" image="{{image}}"></td>
			<td><img src="{{image}}" width=100></td>
			<td>{{image}}</td>	
		</tr>
		{{/each}}
	</script>
	<div>
		<button id="prev">이전</button>
		<span id="page"></span>
		<button id="next">다음</button>
	</div>
</body>

<script>
	var page=1;
	
	$("#download").on("click", function(){
		$("#tbl .row .chk:checked").each(function(){
			var image = $(this).attr("image");
			$.ajax({
				type: "post",
				url: "/api/download",
				data: {image: image},
				success: function(){}
			});
		});	
	});
	
	$("#query").on("keydown", function(e){
		if(e.keyCode==13){
			page=1;
			getList();
		}	
	});
	
	$("#prev").on("click", function(){
		page--;
		getList();
	});
	
	$("#next").on("click", function(){
		page++;
		getList();
	});
	
	getList();
	function getList(){
		var query=$("#query").val();
		$.ajax({
			type: "get",
			url: "/naver/shop.json",
			data:{query:query, page:page},
			success:function(data){
				var temp=Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#page").html(page);
			}
		});
	}
</script>
</html>