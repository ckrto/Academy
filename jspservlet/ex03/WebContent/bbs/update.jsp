<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>게시판수정</h1>
<form name="frm" method="post">
   <input type="hidden" name="no" value="${vo.no}">
   <table>
      <tr>
         <td>
         <input type="text" name="title" size=80 value="${vo.title}">
         </td>
      </tr>
      <tr>
         <td>
         <textarea rows="10" cols="81" placeholder="내용을 입력하세요" name="content">${vo.content}</textarea>
         </td>
      </tr>
   </table>
   <div class="buttons" style = "text-align: center; margin-top: 20px;">
      <button type="submit">수정</button>
      <button type="reset">취소</button>
   </div>
</form>

<script>
   $(frm).on("submit",function(e){
      e.preventDefault();
      
      var title=$(frm.title).val();
      var content=$(frm.content).val();
      
      if(title==""){
         alert("제목을 입력하세요");
         $(frm.title).focus();
         return;
      }
      if(content==""){
         alert("내용을 입력하세요");
         $(frm.content).focus();
         return;
      }
      if(!confirm("내용을 수정하시겠습니까")) {
    	  return;
      } else {
    	  frm.submit(); 	  
      }
   });
</script>