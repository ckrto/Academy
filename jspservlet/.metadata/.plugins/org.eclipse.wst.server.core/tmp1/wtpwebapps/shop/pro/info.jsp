<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
	#info {
		overflow: hidden;
	}
	
	#image {
		float: left;
		border-right: 1px dotted gray;
		padding-right: 20px;
	}
	
	#content {
		float : left;
		margin-left: 40px;
		padding-left;
	}
	
	.price2 {
		text-decoration: line-through 2px solid red;
	}
	
	.status {
		font-size: 15px; 
		color: red;
	}
	
	h2, h3 {
		margin-bottom: 30px;
	}
	
	#buy {
		width: 180px;
		height: 25px;
		cursor: pointer;
		background: red;
		color: white;
		float: right;
		text-align: center;
		padding: 10px 0px;
	}
	
	#cart {
		width: 180px;
		height: 25px;
		cursor: pointer;
		background: black;
		color: white;
		float: left;
		text-align: center;
		padding: 10px 0px;
	}
</style>
<h1>상품 정보</h1>
<div id = "info">
	<div id = "image">
		<img src = "/image/shop/${vo.image}" width = 500>
	</div>
	<div id = "content">
		<h2>
			${vo.prod_id}
			<span class = "status"><c:out value="${vo.prod_del == '1' ? '판매중지' : '' }"/></span>
		</h2>
		<h2>${vo.prod_name}</h2>
		<h3 class = "price2">원가 : <fmt:formatNumber value = "${vo.price2}" pattern = "#,###원"/></h3>
		<h3 class = "price1">할인가 : <fmt:formatNumber value = "${vo.price1}" pattern = "#,###원"/></h3>
		<h3>판매처 : ${vo.mall_name}(${vo.mall_id})</h3>
		<h3>제조사 : ${vo.company}</h3>
		<div style = "overflow: hidden; margin-top: 130px">
			<div id = "buy">구매하기</div>
			<div id = "cart">장바구니</div>
		</div>
	</div>
</div>

<script>
	var status = "${vo.prod_del}";
	var prod_id = "${vo.prod_id}";
	
	$("#buy").on("click", function() {
		if(status == '1') {
			alert("판매중지된 상품입니다.");
			return;
		}
	});
	
	$("#cart").on("click", function() {
		if(status == '1') {
			alert("판매중지된 상품입니다.");
			return;
		}
		else {
			$.ajax ({
				type : "get",
				url : "/cart/insert",
				data : {id : prod_id},
				success : function() {
					if(!confirm("상품을 장바구니에 넣으시겠습니까?")) {
						return;
					}
					else {
						location.href = "/cart/list";
					}
				}
			});
		}
	});
</script>