/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.5
 * Generated at: 2022-08-17 23:45:46 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pro;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class read_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/standard.jar", Long.valueOf(1659404976759L));
    _jspx_dependants.put("jar:file:/C:/data/jsp&servlet/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/shop/WEB-INF/lib/standard.jar!/META-INF/c.tld", Long.valueOf(1098678690000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("<style>\r\n");
      out.write("\tform td {border:1px solid black;}\r\n");
      out.write("\ttextarea {padding:20px;}\r\n");
      out.write("</style>    \r\n");
      out.write("<h1>상품정보</h1>\r\n");
      out.write("<form name=\"frm\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"title\" width=100>상품코드</td>\r\n");
      out.write("\t\t\t<td width=100><input type=\"text\" name=\"prod_id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.prod_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" size=8 readonly></td>\r\n");
      out.write("\t\t\t<td class=\"title\" width=100>제조원</td>\r\n");
      out.write("\t\t\t<td width=300><input type=\"text\" name=\"company\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.company}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" size=35></td>\r\n");
      out.write("\t\t\t<td class=\"title\" width=100>판매가격</td>\r\n");
      out.write("\t\t\t<td width=100><input type=\"text\" name=\"price1\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.price1}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" size=8></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"title\">업체코드</td>\r\n");
      out.write("\t\t\t<td colspan=3><select name=\"mall_id\" id=\"malls\"></select></td>\r\n");
      out.write("\t\t\t<td class=\"title\">일반가격</td>\r\n");
      out.write("\t\t\t<td><input type=\"text\" name=\"price2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.price2}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" size=8></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"title\">상품이름</td>\r\n");
      out.write("\t\t\t<td colspan=3><input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.prod_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" name=\"prod_name\" size=50></td>\r\n");
      out.write("\t\t\t<td class=\"title\">판매상태</td>\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t\t<input id=\"del\" type=\"checkbox\" ");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("> 판매중지\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"prod_del\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.prod_del}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"title\">상품이미지</td>\r\n");
      out.write("\t\t\t<td colspan=5>\r\n");
      out.write("\t\t\t\t<img src=\"/image/shop/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.image}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" id=\"image\" width=150>\r\n");
      out.write("\t\t\t\t<input type=\"file\" name=\"image\" style=\"display:none\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"oldImage\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.image}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"title\">상품설명</td>\r\n");
      out.write("\t\t\t<td colspan=5><textarea rows=\"10\" cols=\"100\" name=\"detail\" placeholder=\"상품상세설명\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.detail}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</textarea></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div class=\"buttons\">\r\n");
      out.write("\t\t<button>상품수정</button>\r\n");
      out.write("\t\t<button type=\"reset\">수정취소</button>\r\n");
      out.write("\t</div>\r\n");
      out.write("</form>\r\n");
      out.write("<!-- 업체목록 템플릿 -->\r\n");
      out.write("<script id=\"temp\" type=\"text/x-handlebars-template\">\r\n");
      out.write("\t{{#each array}}\r\n");
      out.write("\t<option value=\"{{mall_id}}\" {{selected mall_id}}>{{mall_name}}({{mall_id}})</option>\r\n");
      out.write("\t{{/each}}\r\n");
      out.write("</script>\r\n");
      out.write("<script>\r\n");
      out.write("\tvar id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.mall_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\";\r\n");
      out.write("\tHandlebars.registerHelper(\"selected\", function(mall_id){\r\n");
      out.write("\t\tif(id==mall_id) return \"selected\";\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\t//판매중지 체크박스를 클릭한 경우\r\n");
      out.write("\t$(\"#del\").on(\"click\", function(){\r\n");
      out.write("\t\tif($(this).is(\":checked\")) {\r\n");
      out.write("\t\t\t$(frm.prod_del).val(\"1\");\r\n");
      out.write("\t\t\talert(\"판매중지됩니다!\")\r\n");
      out.write("\t\t}else {\r\n");
      out.write("\t\t\t$(frm.prod_del).val(\"0\");\r\n");
      out.write("\t\t\talert(\"판매가능합니다!\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(frm).on(\"submit\", function(e){\r\n");
      out.write("\t\te.preventDefault();\r\n");
      out.write("\t\tvar prod_name=$(frm.prod_name).val();\r\n");
      out.write("\t\tvar price1=$(frm.price1).val();\r\n");
      out.write("\t\tvar price2=$(frm.price2).val();\r\n");
      out.write("\t\t//유효성체크\r\n");
      out.write("\t\tif(prod_name==\"\"){\r\n");
      out.write("\t\t\talert(\"상품 이름을 입력해주세요!\");\r\n");
      out.write("\t\t\t$(frm.prod_name).focus();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(price1.replace(/[0-9]/g,'') || price1==\"\"){\r\n");
      out.write("\t\t\talert(\"판매가격을 숫자로 입력해주세요!\");\r\n");
      out.write("\t\t\t$(frm.price1).val(\"\");\r\n");
      out.write("\t\t\t$(frm.price1).focus();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(price2.replace(/[0-9]/g,'') || price1==\"\"){\r\n");
      out.write("\t\t\talert(\"판매가격을 숫자로 입력해주세요!\");\r\n");
      out.write("\t\t\t$(frm.price2).val(\"\");\r\n");
      out.write("\t\t\t$(frm.price2).focus();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(!confirm(\"상품정보를 수정하실래요?\")) return;\r\n");
      out.write("\t\tfrm.action=\"/pro/update\";\r\n");
      out.write("\t\tfrm.submit();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//이미지 미리보기\r\n");
      out.write("\t$(frm.image).on(\"change\", function(e){\r\n");
      out.write("\t\tvar reader = new FileReader();\r\n");
      out.write("\t\treader.onload=function(e){ \r\n");
      out.write("\t\t\t$(\"#image\").attr(\"src\", e.target.result); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treader.readAsDataURL(this.files[0]);\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#image\").on(\"click\", function(){\r\n");
      out.write("\t\t$(frm.image).click();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//업체목록출력\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\ttype:\"get\",\r\n");
      out.write("\t\turl:\"/mall/list.json\",\r\n");
      out.write("\t\tdata:{key:\"mall_id\",word:\"\",per:100,order:\"mall_id\",desc:\"asc\",page:1},\r\n");
      out.write("\t\tdataType:\"json\",\r\n");
      out.write("\t\tsuccess:function(data){\r\n");
      out.write("\t\t\tvar temp=Handlebars.compile($(\"#temp\").html());\r\n");
      out.write("\t\t\t$(\"#malls\").html(temp(data));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(null);
    // /pro/read.jsp(30,36) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vo.prod_del=='1'?'checked':''}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }
}