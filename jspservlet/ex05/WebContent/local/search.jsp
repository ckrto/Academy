<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#info {
		padding: 10px;
		text-align: center;
	}
	#condition {
		overflow: hidden;
	}
	#condition #save {
		float: left;
	}
	#condition form {
		float: right;
	}

</style>

<h2>지역검색</h2>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b15832e727746231b94e4455f51ed8f0"></script>
<div id = "condition">
	<button id = "save">선택저장</button>
	<form name = "frm">
		<select name = "local">		
			<option>인천</option>
			<option>서울</option>
			<option>경기도</option>			
		</select>
		<input type = "text" name = "query" value = "인천 버거킹">
		<button>검색</button>
		검색 수 : <span id = "count"></span>
	</form>
</div>

<table id = "tbl"></table>
<script id = "temp" type = "text/x-handlebars-template">
	<tr class = "title" style = "text-align: center;">
		<td><input type = "checkbox" id = "chkAll"></td>
		<td width = "300">장소이름</td>	
		<td width = "300">주소</td>	
		<td width = "300">전화번호</td>	
		<td width = "100">위치보기</td>	
	</tr>
	{{#each documents}}
	<tr class = "row" id = "{{id}}" x = "{{x}}" y = "{{y}}">
		<td><input type = "checkbox" class = "chk"></td>
		<td class = "place">{{place_name}}</td>
		<td class = "address">{{address_name}}</div></td>
		<td class = "phone">{{phone}}</td>
		<td><button x = "{{x}}" y = "{{y}}">위치보기</button></td>
	</tr>
	{{/each}}
</script>

<div class = "buttons" style = "text-align: center; margin-top: 20px;">
	<button id = "prev">이전</button>
	<span id = "page" style = "color: black;">1/10</span>
	<button id = "next">다음</button>
</div>

<div id = "background">
	<div id = "lightbox">
			<div id="map" style="width:700px;height:400px;"></div>
		<div style = "text-align: center; margin: 20px 0px">
			<button id = "close">닫기</button>
		</div>
	</div>
</div>

<script>
	var page = 1;
	var query = "인천 버거킹";
	getList();
	
	$("#save").on("click", function() {
		var chk = $("#tbl .row .chk:checked").length;
		if(chk == 0) {
			alert("저장할 항목을 선택해주세요.");
			return;
		}
		
		if(!confirm("선택한 항목들을 저장하시겠습니까?")) {
			return;
		}
		else {
			$("#tbl .row .chk:checked").each(function() {
				var row = $(this).parent().parent();
				var id = row.attr("id");
				var name = row.find(".place").html();
				var phone = row.find(".phone").html();
				var address = row.find(".address").html();
				var x = row.attr("x");
				var y = row.attr("y");
				var keyword = $(frm.query).val();
				
				$.ajax ({
					type: "post",
					url : "/local/insert",
					data : {id:id, address:address, name:name, phone:phone, keyword:keyword, x:x, y:y, },
					success : function() {
						
					}
				});
			});
			
			alert("저장이 완료되었습니다.");
			getList();
		}
		
	});
	
	$("#tbl").on("click", ".row .chk", function() {
		var all = $("#tbl .row .chk").length;
		var chk = $("#tbl .row .chk:checked").length;
		
		if(all == chk) {
			$("#tbl #chkAll").prop("checked", true);
		}
		else {
			$("#tbl #chkAll").prop("checked", false);
		}
		
	});
	
	$("#tbl").on("click", "#chkAll", function() {
		if($(this).is(":checked")) {
			$("#tbl .row .chk").each(function () {
				$(this).prop("checked", true);
			});
		}
		else {
			$("#tbl .row .chk").each(function () {
				$(this).prop("checked", false);
			});
		}
	});
	
	$("#tbl").on("click", ".row button", function() {
		$("#background").show();
		
		var x = $(this).attr("x");
		var y = $(this).attr("y");
		
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(y, x), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};

		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		var markerPosition  = new kakao.maps.LatLng(y, x); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		
		var row = $(this).parent().parent();
		var place = row.find(".place").html();
		var phone = row.find(".phone").html();
		// alert(place + phone);
		
		var str1 = place + " " + phone;

		var str = "<div id='info'>";
		str += place + "<br>";
		str += phone;
		str += "</div>";
		
		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
		    content : str
		});

		// 마커에 마우스오버 이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'mouseover', function() {
		    infowindow.open(map, marker);
		});

		// 마커에 마우스아웃 이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'mouseout', function() { 
		    infowindow.close();
		});
		
	});
	
	$("#close").on("click", function() {
		$("#background").hide();
	});
	
	$(frm).on("submit", function(e) {
		e.preventDefault();
		page = 1;
		getList();
	});
	
	$(frm.local).on("change", function() {
		page = 1;
		getList();
	});
	
	
	$("#next").on("click", function() {
		page++;
		getList();
	});
	
	$("#prev").on("click", function() {
		page--;
		getList();
	});
	
	function getList() {
		query = $(frm.local).val() + " " + $(frm.query).val();
		
		$.ajax ({
			type : "get",
			url : "https://dapi.kakao.com/v2/local/search/keyword.json",
			dataType : "json",
			headers : {"Authorization" : "KakaoAK c876adec158ad928ac649393c6c25246"},
			data : {page : page, size : 5, query : query},
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#count").html(data.meta.pageable_count);
				
				var last = Math.ceil(data.meta.pageable_count/5);
				$("#page").html(page + "/" + last);
				
				if(page == 1) {
					$("#prev").attr("disabled", true);
				}
				else {
					$("#prev").attr("disabled", false);
				}
				
				if(data.meta.is_end == true) {
					$("#next").attr("disabled", true);
				}
				else {
					$("#next").attr("disabled", false);
				}
			}
		});
	}
</script>