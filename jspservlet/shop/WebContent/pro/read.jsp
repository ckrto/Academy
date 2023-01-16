<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<style>
	form td {border:1px solid black;}
	textarea {padding:20px;}
</style>    
<h1>상품정보</h1>
<form name="frm" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td class="title" width=100>상품코드</td>
			<td width=100><input type="text" name="prod_id" value="${vo.prod_id}" size=8 readonly></td>
			<td class="title" width=100>제조원</td>
			<td width=300><input type="text" name="company" value="${vo.company}" size=35></td>
			<td class="title" width=100>판매가격</td>
			<td width=100><input type="text" name="price1" value="${vo.price1}" size=8></td>
		</tr>
		<tr>
			<td class="title">업체코드</td>
			<td colspan=3><select name="mall_id" id="malls"></select></td>
			<td class="title">일반가격</td>
			<td><input type="text" name="price2" value="${vo.price2}" size=8></td>
		</tr>
		<tr>
			<td class="title">상품이름</td>
			<td colspan=3><input type="text" value="${vo.prod_name}" name="prod_name" size=50></td>
			<td class="title">판매상태</td>
			<td>
				<input id="del" type="checkbox" <c:out value="${vo.prod_del=='1'?'checked':''}"/>> 판매중지
				<input type="hidden" name="prod_del" value="${vo.prod_del}">
			</td>
		</tr>
		<tr>
			<td class="title">상품이미지</td>
			<td colspan=5>
				<img src="/image/shop/${vo.image}" id="image" width=150>
				<input type="file" name="image" style="display:none">
				<input type="hidden" name="oldImage" value="${vo.image}">
			</td>
		</tr>
		<tr>
			<td class="title">상품설명</td>
			<td colspan=5><textarea rows="10" cols="100" name="detail" placeholder="상품상세설명">${vo.detail}</textarea></td>
		</tr>
	</table>
	<div class="buttons">
		<button>상품수정</button>
		<button type="reset">수정취소</button>
	</div>
</form>
<!-- 업체목록 템플릿 -->
<script id="temp" type="text/x-handlebars-template">
	{{#each array}}
	<option value="{{mall_id}}" {{selected mall_id}}>{{mall_name}}({{mall_id}})</option>
	{{/each}}
</script>
<script>
	var id="${vo.mall_id}";
	Handlebars.registerHelper("selected", function(mall_id){
		if(id==mall_id) return "selected";
	});
</script>

<script>
	//판매중지 체크박스를 클릭한 경우
	$("#del").on("click", function(){
		if($(this).is(":checked")) {
			$(frm.prod_del).val("1");
			alert("판매중지됩니다!")
		}else {
			$(frm.prod_del).val("0");
			alert("판매가능합니다!");
		}
	});
	
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
		
		if(!confirm("상품정보를 수정하실래요?")) return;
		frm.action="/pro/update";
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