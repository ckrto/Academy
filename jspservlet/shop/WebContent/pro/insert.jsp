<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	form td {border:1px solid black;}
	textarea {padding:20px;}
</style>    
<h1>상품등록</h1>
<form name="frm" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td class="title" width=100>상품코드</td>
			<td width=100><input type="text" name="prod_id" value="${code}" size=8 readonly></td>
			<td class="title" width=100>제조원</td>
			<td width=300><input type="text" name="company" size=35></td>
			<td class="title" width=100>판매가격</td>
			<td width=100><input type="text" name="price1" size=8></td>
		</tr>
		<tr>
			<td class="title">업체코드</td>
			<td colspan=3><select name="mall_id" id="malls"></select></td>
			<td class="title">일반가격</td>
			<td><input type="text" name="price2" size=8></td>
		</tr>
		<tr>
			<td class="title">상품이름</td>
			<td colspan=5><input type="text" name="prod_name" size=100></td>
		</tr>
		<tr>
			<td class="title">상품이미지</td>
			<td colspan=5>
				<img src="https://dummyimage.com/150x150" id="image" width=150>
				<input type="file" name="image" style="display:none">
			</td>
		</tr>
		<tr>
			<td class="title">상품설명</td>
			<td colspan=5><textarea rows="10" cols="100" name="detail" placeholder="상품상세설명"></textarea></td>
		</tr>
	</table>
	<div class="buttons">
		<button>상품등록</button>
		<button type="reset">등록취소</button>
	</div>
</form>
<!-- 업체목록 템플릿 -->
<script id="temp" type="text/x-handlebars-template">
	{{#each array}}
	<option value="{{mall_id}}">{{mall_name}}({{mall_id}})</option>
	{{/each}}
</script>

<script>
	$(frm).on("submit", function(e){
		e.preventDefault();
		var prod_name=$(frm.prod_name).val();
		var price1=$(frm.price1).val();
		var price2=$(frm.price2).val();
		//유효성체크
		if(prod_name==""){
			alert("상품 이름을 입력해주세요!");
			$(frm.prod_name).focus();
			return;
		}
		if(price1.replace(/[0-9]/g,'') || price1==""){
			alert("판매가격을 숫자로 입력해주세요!");
			$(frm.price1).val("");
			$(frm.price1).focus();
			return;
		}
		if(price2.replace(/[0-9]/g,'') || price1==""){
			alert("판매가격을 숫자로 입력해주세요!");
			$(frm.price2).val("");
			$(frm.price2).focus();
			return;
		}
		
		if(!confirm("새로운 상품을 등록하실래요?")) return;
		frm.submit();
	});
	
	//이미지 미리보기
	$(frm.image).on("change", function(e){
		var reader = new FileReader();
		reader.onload=function(e){ 
			$("#image").attr("src", e.target.result); 
		}
		reader.readAsDataURL(this.files[0]);
	});
	
	$("#image").on("click", function(){
		$(frm.image).click();
	});
	
	//업체목록출력
	$.ajax({
		type:"get",
		url:"/mall/list.json",
		data:{key:"mall_id",word:"",per:100,order:"mall_id",desc:"asc",page:1},
		dataType:"json",
		success:function(data){
			var temp=Handlebars.compile($("#temp").html());
			$("#malls").html(temp(data));
		}
	});
</script>