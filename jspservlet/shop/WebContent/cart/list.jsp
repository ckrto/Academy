<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1>장바구니</h1>
<table id = "tbl">
	<tr class = "title">
		<td><input type="checkbox" id = "all"></td>
		<td width = 100>상품코드</td>
		<td width = 300>상품이름</td>
		<td width = 150>상품가격</td>
		<td width = 150>상품수량</td>
		<td width = 300>결제금액</td>
		<td width = 100>삭제</td>
	</tr>
	<c:forEach items = "${carts}" var = "vo">
	<tr class = "row" style = "text-align: center;">
		<td><input type = "checkbox" class = "chk"></td>
		<td class = "id">${vo.prod_id}</td>
		<td class = "ellipsis">${vo.prod_name}</td>
		<td><fmt:formatNumber value = "${vo.price1}" pattern = "#,###원"/></td>
		<td>
			<input class = "qnt" type = "text" value = "${vo.qnt}" size = 2>
			<button class = "update" style = "padding: 5px 10px;">수정</button>
		</td>
		<td>
			<fmt:formatNumber value = "${vo.sum}" pattern = "#,###원"/>
			<span class = "sum" style = "display: none;">${vo.sum}</span>
		</td>
		<td><button class = "delete" id = "${vo.prod_id}">삭제</button></td>
	</tr>
	</c:forEach>
	<c:if test="${carts.size() == 0  || carts == null}">
		<tr><td colspan = 7 style = "text-align: center; color: red;">장바구니가 비어있습니다.</td></tr>
	</c:if>
	<c:if test="${carts.size() >= 1}">
	<tr>
		<td colspan = 2 class = "title"> 합계 </td>
		<td colspan = 5 style = "text-align: center;"><span id = "total"></span>원</td>
	</tr>
	</c:if>
	
</table>
<script>
	var total = 0;
	
	$("#tbl").on("click", ".row .delete", function() {
		var id = $(this).attr("id");
		if(!confirm(id + " 상품을 장바구니에서 삭제하시겠습니까?")) {
			return;
		}
		else {
			$.ajax ({
				type : "get",
				url : "/cart/delete",
				data : {id : id},
				success : function() {
					alert("삭제가 완료되었습니다.");
					location.href = '/cart/list';
				}
			});
		}
	});
	
	$("#tbl .row .sum").each(function() {
		total += parseInt($(this).html());
	});
	
	$("#total").html(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
	
	$("#tbl").on("click", ".row .update", function() {
		var row = $(this).parent().parent();
		var id = row.find(".id").html();
		var qnt = row.find(".qnt").val();
		
		if(qnt.replace(/[0-9]/g,'') || qnt == "") {
			alert("수량을 숫자로 입력해주세요");
			qnt.val("");
			row.find(".qnt").focus();
			return;
		}
		if(!confirm(id + " 상품의 수량을 " + qnt + "개 로 수정하시겠습니까?")) {
			return;
		}
		else {
			$.ajax ({
				type : "get",
				url : "/cart/update",
				data : {id : id, qnt : qnt},
				success : function() {
					alert("수정이 완료되었습니다.");
					location.href = '/cart/list'
				}
			});
		}
	});
</script>