<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.tit {
    	background:gray;
    	color:white;
    	text-align: center;
	}
	td {
		border: 1px solid black;
	}
	button {
		background: gray;
	}
</style>
<h1>강좌정보</h1>
<form name = "frm" method = "post">
	<table>
		<tr>
			<td width = 150 class = "tit">강좌번호</td>
			<td width = 200><input type = "text" name = "lcode" size = 5 value = "${vo.lcode}" readonly></td>
			
			<td width = 150 class = "tit">강의실</td>
			<td width = 150><input type = "text" name = "room" size = 5 value = "${vo.room}" readonly></td>
			
			<td width = 150 class = "tit">강의시수</td>
			<td width = 150><input type = "text" name = "hours" size = 5 value = "${vo.hours}"></td>
			
		</tr>
		<tr>
			<td class = "tit" >강좌이름</td>
			<td colspan = "3"><input type = "text" name = "lname" size = "50" value = "${vo.lname}" readonly></td>
			
			<td class = "tit">최대수강인원</td>
			<td><input type = "text" name = "capacity" size = 5 value = "${vo.capacity}"></td>
		</tr>
		<tr>
			<td class = "tit">담당교수</td>
			<td colspan = "3">
				<input type = "text" name = "instructor" size = "5" value = "${vo.instructor}">
				<input type = "text" name = "pname" size = "5" value = " ${vo.pname}">
			</td>
			
			<td class = "tit">수강신청인원</td>
			<td><span>${vo.persons}</span></td>
		</tr>
	</table>
	<div class = "buttons">
		<button type = "submit" style="size: 2px">정보수정</button>
		<button type = "reset">수정취소</button>
	</div>
</form>

<h1 style = "margin-top: 40px;">수강신청학생</h1>
<button id = "update">선택수정</button>
<table id = "slist"></table>
<script id = "stemp" type = "text/x-handlebars-template">
	<tr class = "tit">
		<td><input type = "checkbox" id = "all"></td>
		<td width = 100>학생번호</td>
		<td width = 100>학생이름</td>
		<td width = 100>학생학과</td>
		<td width = 100>학생학년</td>
		<td width = 200>수강신청일</td>
		<td width = 200>점수</td>
	</tr>
	{{#each .}}
	<tr class = "row" style = "text-align: center;">
		<td><input type = "checkbox" class = "chk"></td>
		<td class = "scode">{{scode}}</td>
		<td class = "sname">{{sname}}</td>
		<td class = "dept">{{dept}}</td>
		<td class = "year">{{year}}</td>
		<td class = "edate">{{edate}}</td>
		<td>
			<input type = "text" class = "grade" value = "{{grade}}" size = 5 maxlength = 3>
			<button style = "padding: 5px 10px;">수정</button>
		</td>
	</tr>
	{{/each}}
</script>

<script>
	var lcode="${vo.lcode}";
	getList();
	
	$("#update").on("click", function() {
		var chk = $("#slist .row .chk:checked").length;
		if(chk == 0) {
			alert("수정할 학생들을 선택해주세요");
			return;
		}
		
		if(!confirm(chk + "명의 점수를 수정하시겠습니까?")) {
			return;
		} 
		else {
			$("#slist .row .chk:checked").each(function() {
				var row = $(this).parent().parent();
				var scode = row.find(".scode").html();
				var sname = row.find(".sname").html();
				var grade = row.find(".grade").val();
				
				$.ajax ({
					type : "post",
					url : "/enroll/update",
					data : {lcode : lcode, scode : scode, grade : grade},
					success : function() {
					}
				});
			});
			alert("성적이 수정되었습니다.");
			getList();
		}
	});
	
	$("#slist").on("click", "#all", function() {
		if($(this).is(":checked")) {
			$("#slist .row .chk").each(function() {
				$(this).prop("checked", true);
			});
		}
		else {
			$("#slist .row .chk").each(function() {
				$(this).prop("checked", false);
			});
		}
	});
	
	$("#slist").on("click", ".row .chk", function() {
		var all = $("#slist .row .chk").length;
		var chk = $("#slist .row .chk:checked").length;
		
		if(all == chk) {
			$("#all").prop("checked", true);
		}
		else {
			$("#all").prop("checked", false);	
		}
	});
	
	//특정 강좌를 수강신청 한 학생목록
	function getList(){
		$.ajax({
			type:"get", 
			url:"/enroll/slist.json", 
			data:{lcode : lcode}, 
			dataType:"json", 
			success:function(data){
				var temp = Handlebars.compile($("#stemp").html());
				$("#slist").html(temp(data));
			}
		});
	}
	
	$("#slist").on("click", ".row button", function() {
		var row = $(this).parent().parent();
		var scode = row.find(".scode").html();
		var sname = row.find(".sname").html();
		var grade = row.find(".grade").val();
		
		if(!confirm(sname + " 학생의 점수를 " + grade + " 로 수정하시겠습니까?")) {
			return;
		}
		else {
			$.ajax ({
				type : "post",
				url : "/enroll/update",
				data : {lcode : lcode, scode : scode, grade : grade},
				success : function() {
					alert("성적이 수정되었습니다.");
					getList();
				}
			});
		}
	});
	
	
	
	
	
</script>
	