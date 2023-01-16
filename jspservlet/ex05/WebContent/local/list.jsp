<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<h2>지역목록</h2>
<div>
	전체 데이터 수 : <span>${count}건</span>
</div>
<table>
	<tr class = "title">
		<td width = "100">No.</td>
		<td width = "250">Name</td>
		<td width = "300">Address</td>
		<td width = "200">Phone</td>
		<td width = "100">Keyword</td>
		<td width = "100">Date</td>
	</tr>
	<c:forEach items = "${array}" var = "vo">
	<tr class = "row">
		<td>${vo.id}</td>
		<td>${vo.name}</td>
		<td>${vo.address}</td>
		<td>${vo.phone}</td>
		<td>${vo.keyword}</td>
		<td><fmt:formatDate value="${vo.wdate}" pattern = "yyyy-MM-dd HH:mm:ss"/>
	</tr>
	</c:forEach>
</table>

<div class = "buttons" style = "text-align: center; margin-top: 20px;">
	<form name = "frm">
		<button id = "prev" <c:out value = "${page == 1 ? 'disabled' : ''}"/>>이전</button>
		<input type = "hidden" value = "${page}" name = "page">
		<span style = "color: black;">${page}/${last}</span>
		<button id = "next" <c:out value = "${page == last ? 'disabled' : ''}"/>>다음</button>
	</form>
</div>

<script>
	var page = $(frm.page).val();
	
	$("#next").on("click", function() {
		page++;
		$(frm.page).val(page);
	});

	$("#prev").on("click", function() {
		page--;
		$(frm.page).val(page);
	});

</script>
