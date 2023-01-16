<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>상품등록</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h1>상품등록</h1>
	<form name="frm">
		<input placeholder="상품명" name="title">
		<hr>
		<input placeholder="가격" name="price">
		<hr>
		<input type="file" name="file">
		<button>상품등록</button>
	</form>
</body>
<script>
	$(frm).on("submit", function(e){
		e.preventDefault();
		if(!confirm('상품을 등록하실래요?')) return;
		
		var formData = new FormData();
		formData.append("file", $(frm.file)[0].files[0]);
		formData.append("title", $(frm.title).val());
		formData.append("price", $(frm.price).val());
		
		$.ajax({
			type:"post",
			url: "/api/shop/insert",
			data: formData,
			processData:false,
			contentType:false,
			success:function(){
				alert("등록성공....");
			}
		});
	});
</script>
</html>