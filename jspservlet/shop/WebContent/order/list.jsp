<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#condition {overflow:hidden;}
	#condition form{float:left;}
	#condition #right{float:right;}
	#condition button, .buttons button{padding:5px 10px;}
</style>
<h1>주문목록</h1>
<div id="condition">
	<form name="frm">
		<select id="key">
			<option value="order_id">주문번호</option>
			<option value="name" selected>주문자이름</option>
			<option value="address">주문자주소</option>
			<option value="tel">전화번호</option>
		</select>
		<input type="text" id="word" placeholder="검색어">
		<select id="per">
			<option value="2">2행</option>
			<option value="3">3행</option>
			<option value="5">5행</option>
			<option value="10">10행</option>
		</select>
		<button>검색</button>
		검색수: <span id="total"></span>건
	</form>
	<div id="right">
		<select id="order">
			<option value="order_id">주문번호</option>
			<option value="name">주문자이름</option>
			<option value="address">주문자주소</option>
			<option value="tel">전화번호</option>
		</select>
		<select id="desc">
			<option value="asc">오름차순</option>
			<option value="desc" selected>내림차순</option>
		</select>
	</div>
</div>
<table id="tbl"></table>
<script id="temp" type="text/x-handlebars-template">
	<tr class="title">
		<td width=100>주문번호</td>
		<td width=200>주문자이름</td>
		<td width=350>주문자주소</td>
		<td width=200>전화번호</td>
		<td width=350>주문일</td>
		<td width=150>구매정보</td>

	</tr>
	{{#each array}}
	<tr class="row" style = "text-align: center">
		<td>{{order_id}}</td>
		<td>{{name}}</td>
		<td>{{address}}</td>
		<td>{{tel}}</td>
		<td>{{pdate}}</td>
		<td><button>구매정보</button></td>
	</tr>
	{{/each}}
</script>
<div class="buttons">
	<button id="prev">이전</button>
	<span id="page">1/10</span>
	<button id="next">다음</button>
</div>

<script>
	var page=1;
	getList();
	
	$(frm).on("submit", function(e){
		e.preventDefault();
		page=1;
		getList();
	});
	
	$("#per, #order, #desc").on("change", function(){
		page=1;
		getList();
	});
	
	$("#next").on("click", function(){
		page++;
		getList();
	});
	
	$("#prev").on("click", function(){
		page--;
		getList();
	});
	
	function getList(){
		var key=$("#key").val();
		var word=$("#word").val();
		var per=$("#per").val();
		var order=$("#order").val();
		var desc=$("#desc").val();
		
		$.ajax({
			type:"get",
			url:"/order/list.json",
			data:{key:key,word:word,per:per,order:order,desc:desc,page:page},
			dataType:"json",
			success:function(data){
				var temp=Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#total").html(data.total);
				
				if(data.total==0){
					var str="<tr><td colspan = 6 style = 'text-align: center; color: red;' ><b>주문 정보가 없습니다</b></td></tr>";
					$("#tbl").append(str);
					$(".buttons").hide();
					
				}else{
					$("#page").html(page + "/" + data.last);
					
					if(page==1) $("#prev").attr("disabled", true);
					else $("#prev").attr("disabled", false);
					
					if(page==data.last) $("#next").attr("disabled", true);
					else $("#next").attr("disabled", false);
					
					$(".buttons").show();
				}
			}
		});
	}
</script>


