<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
	#condition {
		overflow: hidden;
	}
	#condition #save {
		float: left;
	}
	#condition form {
		float: right;
	}
	#condition button {
		padding: 5px 20px;
	}
</style>

<h2>도서 검색</h2>

<div id = "condition">
	<button id = "save">선택저장</button>
	<form name = "frm">
		<input style = "margin-bottom: 20px;" type = "text" name = "query" value="자바">
		<button>검색</button>
		검색 수 : <span id = "count">권</span>
	</form>
</div>

<table id = "tbl"></table>
<script id = "temp" type = "text/x-handlebars-template">
	<tr class = "title">
		<td><input type = "checkbox" id = "chkAll"></td>
		<td width = "150">이미지</td>
		<td width = "250">제목</td>
		<td width = "100">저자</td>
		<td width = "200">가격</td>
		<td width = "200">출판사</td>
	</tr>
	{{#each documents}}
	<tr class = "row" isbn = "{{isbn}}">
		<td><input type = "checkbox" class = "chk"></td>
		<td><img src = "{{image thumbnail}}" contents = "{{contents}}" width = 70 class = "thum"></td>
		<td class = "subject">{{title}}</td>
		<td class = "authors">{{authors}}</td>
		<td class = "price">{{price}}</td>
		<td class = "publisher">{{publisher}}</td>
	</tr>
	{{/each}}
</script>

<script>
	Handlebars.registerHelper("image", function(thum) {
		if(thum) {
			return thum;
		} else {
			return "https://via.placeholder.com/70x100";
		}
	});
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
	var size = 5;
	var query = "자바";

	getList();
	
	$("#tbl").on("click", ".row .thum", function() {
		var image = $(this).attr("src");
		var contents = $(this).attr("contents");
		
		$("#image").attr("src", image);
		$("#contents").html(contents);
		$("#background").show();
		
	});
	
	$("#close").on("click", function() {
		$("#background").hide();	
	});
	
	$("#save").on("click", function() {
		var chk = $("#tbl .row .chk:checked").length;
		
		if(chk == 0) {
			alert("저장할 도서들을 선택해주세요.");
			return;
		}
		
		if(!confirm(chk + "권의 도서를 저장하시겠습니까?")) {
			return;
		}
		else {
			$("#tbl .chk:checked").each(function() {
				var row = $(this).parent().parent();
				var thum = row.find(".thum").attr("src");
				var subject = row.find(".subject").html();
				var authors = row.find(".authors").html();
				var price = row.find(".price").html();
				var publisher = row.find(".publisher").html();
				var contents = row.find(".thum").attr("contents");
				var isbn = row.attr("isbn");
				
				$.ajax ({
					type : "post",
					url : "/book/insert",
					data : {title:subject, authors:authors, image:thum, price:price, publisher:publisher, contents:contents, isbn:isbn},
					success : function() {
						
					}
				});
			});
			alert("등록을 완료했습니다!");
			getList();
		}
	});
	
	$("#tbl").on("click", "#chkAll", function() {
		if($(this).is(":checked")) {
			$("#tbl .chk").each(function() {
				$(this).prop("checked", true);
			});
		} 
		else {
			$("#tbl .chk").each(function() {
				$(this).prop("checked", false);
			});
		}
	});
	
	$("#tbl").on("click", ".chk", function() {
		var all = $("#tbl .row .chk").length;
		var chk = $("#tbl .row .chk:checked").length;
		
		if(all == chk) {
			$("#chkAll").prop("checked", true);
		}
		else {
			$("#chkAll").prop("checked", false);	
		}
	});
	
	$(frm).on("submit", function(e) {
		e.preventDefault();
		page = 1;
		getList();
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
		query = $(frm.query).val();
		
		$.ajax ({
			type : "get",
			url : "https://dapi.kakao.com/v3/search/book?target=title",
			dataType : "json",
			headers : {"Authorization" : "KakaoAK c876adec158ad928ac649393c6c25246"},
			data : {page : page, size : size, query : query},
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#count").html(data.meta.pageable_count);
				var last = Math.ceil(data.meta.pageable_count/5);
				$("#page").html(page);
				
				if(page == 1) {
					$("#prev").attr("disabled", true);
				}
				else {
					$("#prev").attr("disabled", false);
				}
				
				if(data.meta.is_end == true) {
					$("#next").attr("disabled", true);
				}
				else {
					$("#next").attr("disabled", false);
				}
			}
			
		});
	}
</script>