<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<table>
	<tr class = "title">
		<td width = 100>No.</td>
		<td width = 100>이미지</td>
		<td width = 400>제목</td>
		<td width = 100>가격</td>
		<td width = 100>브랜드</td>
		<td width = 200>등록일</td>
	</tr>
	<c:forEach items = "${array}" var = "vo">
	<tr class = "row">
		<td>${vo.id}</td>
		<td>${vo.image}</td>
		<td>${vo.title}</td>
		<td>${vo.price}</td>
		<td>${vo.brand}</td>
		<td>${vo.wdate}</td>		
	</tr>	
	</c:forEach>
</table>