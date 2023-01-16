<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 정보</title>
</head>
<body>
	<h1>게시글 정보</h1>
	<h3>번호 : ${bno}</h3>
	<h3>
		조회수 : <span id = "viewcnt"></span>
		댓글수 : <span id = "replycnt"></span>
	</h3>
	<h3>제목 : <span id = "title"></span></h3>
	<p id = "content"></p>
	<a href = "/list"><button>목록으로</button></a>
	<hr/>
	<h1>댓글 목록</h1>
	<div>
		<input placeholder = "댓글입력" size=80><button>등록</button>
	</div>
	<br/>
	<table id="tbl"></table>
	<script id="temp" type="text/x-handlebars-template">
		{{#each.}}
			<tr class="row">
				<td>
					{{rno}} : {{content}}
					<button class="delete" rno = {{rno}}>삭제</button>
				</td>
			</tr>
		{{/each}}	
	</script>
</body>
<script>
	var bno = "${bno}";
	getRead();
	getReplyList();
	
	$("#tbl").on("click", ".row .delete", function() {
		var rno = $(this).attr("rno");
		if(!confirm(rno + "번 댓글을 삭제하시겠습니까?")){
			return;
		} else {
			$.ajax ({
				type : "post",
				url : "/api/reply/delete/" + rno,
				success : function() {
					getRead();
					getReplyList();
					alert("삭제가 완료되었습니다.");
				}
			});
		}
	});
	
	function getRead() {
		$.ajax ({
			type : "get",
			url : "/api/board/read/" + bno,
			dataType : "json",
			success : function(data) {
				$("#title").html(data.title);
				$("#content").html(data.content);
				$("#replycnt").html(data.replycnt);
				$("#viewcnt").html(data.viewcnt);
			}
		})
	}
	
	function getReplyList() {
		$.ajax ({
			type : "get",
			url : "/api/reply/list/" + bno,
			dataType : "json",
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
			}
		})
	}
</script>
</html>