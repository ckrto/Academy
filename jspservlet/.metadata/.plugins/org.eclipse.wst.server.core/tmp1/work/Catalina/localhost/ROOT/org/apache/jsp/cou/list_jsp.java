/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.5
 * Generated at: 2022-08-16 00:30:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.cou;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      out.write("<h1>강좌목록</h1>\r\n");
      out.write("<div>\r\n");
      out.write("\t검색 수 : <span id = \"count\"></span>\r\n");
      out.write("</div>\r\n");
      out.write("<div>\r\n");
      out.write("\t<form name = \"frm\">\t\r\n");
      out.write("\t\t<select name = \"key\" id = \"key\">\r\n");
      out.write("\t\t\t<option value = \"lcode\">강좌번호</option>\r\n");
      out.write("\t\t\t<option value = \"lname\">강좌이름</option>\r\n");
      out.write("\t\t\t<option value = \"pname\">담당교수</option>\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<input type = \"text\" id = \"word\" placeholder = \"검색어\">\r\n");
      out.write("\t\t<select name = \"per\" id = \"per\">\r\n");
      out.write("\t\t\t<option value = \"2\">2행</option>\r\n");
      out.write("\t\t\t<option value = \"3\">3행</option>\r\n");
      out.write("\t\t\t<option value = \"5\" selected>5행</option>\r\n");
      out.write("\t\t\t<option value = \"10\">10행</option>\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<button>검색</button>\r\n");
      out.write("\t\t<div style = \"float: right;\">\r\n");
      out.write("\t\t\t<select name = \"order\" id = \"order\">\r\n");
      out.write("\t\t\t\t<option value = \"lcode\">강좌번호</option>\r\n");
      out.write("\t\t\t\t<option value = \"lname\">강좌이름</option>\r\n");
      out.write("\t\t\t\t<option value = \"pname\">담당교수</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\t<select name = \"desc\" id = \"desc\">\r\n");
      out.write("\t\t\t\t<option value = \"asc\">오름차순</option>\r\n");
      out.write("\t\t\t\t<option value = \"desc\">내림차순</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\t<a href = \"/cou/insert\" style=\"margin-left: 20px;\"><button type = \"button\">강좌등록</button></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t\r\n");
      out.write("</div>\r\n");
      out.write("<table id = \"tbl\"></table>\r\n");
      out.write("<script id = \"temp\" type = \"text/x-handlebars-template\">\r\n");
      out.write("\t<tr class = \"title\">\r\n");
      out.write("\t\t<td width = 100>강좌번호</td>\r\n");
      out.write("\t\t<td width = 150>강좌이름</td>\r\n");
      out.write("\t\t<td width = 100>강의시수</td>\r\n");
      out.write("\t\t<td width = 100>강의실</td>\r\n");
      out.write("\t\t<td width = 200>수강인원</td>\r\n");
      out.write("\t\t<td width = 200>담당교수</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t{{#each array}}\r\n");
      out.write("\t<tr class = \"row\" style = \"text-align: center;\" onclick = \"location.href='/cou/read?lcode={{lcode}}'\">\r\n");
      out.write("\t\t<td class = \"lcode\">{{lcode}}</td>\r\n");
      out.write("\t\t<td class = \"lname\">{{lname}}</td>\r\n");
      out.write("\t\t<td class = \"hours\">{{hours}}</td>\r\n");
      out.write("\t\t<td class = \"room\">{{room}}</td>\r\n");
      out.write("\t\t<td class = \"persons\">{{persons}}/{{capacity}}</td>\t\t\t\r\n");
      out.write("\t\t<td class = \"pname\">{{pname}}({{dept}})</td>\t\t\t\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t{{/each}}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div class = \"buttons\" style = \"text-align: center; margin-top: 20px;\">\r\n");
      out.write("\t<button id = \"prev\">이전</button>\r\n");
      out.write("\t<span id = \"page\">1/10</span>\r\n");
      out.write("\t<button id = \"next\">다음</button>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tvar page = 1;\r\n");
      out.write("\tgetList();\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#next\").on(\"click\", function() {\r\n");
      out.write("\t\tpage++;\r\n");
      out.write("\t\tgetList();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#prev\").on(\"click\", function() {\r\n");
      out.write("\t\tpage--;\r\n");
      out.write("\t\tgetList();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#per, #order, #desc\").on(\"change\", function() {\r\n");
      out.write("\t\tpage = 1;\r\n");
      out.write("\t\tgetList();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(frm).on(\"submit\", function(e) {\r\n");
      out.write("\t\te.preventDefault();\r\n");
      out.write("\t\tpage = 1;\r\n");
      out.write("\t\tgetList();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction getList() {\r\n");
      out.write("\t\tvar key = $(frm.key).val();\r\n");
      out.write("\t\tvar word = $(frm.word).val();\r\n");
      out.write("\t\tvar per = $(frm.per).val();\r\n");
      out.write("\t\tvar order = $(frm.order).val();\r\n");
      out.write("\t\tvar desc = $(frm.desc).val();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$.ajax ({\r\n");
      out.write("\t\t\ttype : \"get\",\r\n");
      out.write("\t\t\turl : \"/cou/list.json\",\r\n");
      out.write("\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\tdata : {key : key, word : word, per : per, order : order, desc : desc, page : page},\r\n");
      out.write("\t\t\tsuccess: function(data) {\r\n");
      out.write("\t\t\t\tvar temp = Handlebars.compile($(\"#temp\").html());\r\n");
      out.write("\t\t\t\t$(\"#tbl\").html(temp(data));\r\n");
      out.write("\t\t\t\t$(\"#count\").html(data.total);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(data.total == 0) {\r\n");
      out.write("\t\t\t\t\t$(\"#tbl\").append(\"<tr><td colspan = 6 class = 'none' style = 'text-align: center; color: red;'> 검색된 자료가 없습니다! </td></tr>\");\r\n");
      out.write("\t\t\t\t\t$(\".buttons\").hide();\r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t\telse {\r\n");
      out.write("\t\t\t\t\t$(\"#page\").html(page + \"/\" + data.last);\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(page == 1) {\r\n");
      out.write("\t\t\t\t\t\t$(\"#prev\").attr(\"disabled\", true);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\telse {\r\n");
      out.write("\t\t\t\t\t\t$(\"#prev\").attr(\"disabled\", false);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(page == data.last) {\r\n");
      out.write("\t\t\t\t\t\t$(\"#next\").attr(\"disabled\", true);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\telse {\r\n");
      out.write("\t\t\t\t\t\t$(\"#next\").attr(\"disabled\", false);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$(\".buttons\").show();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
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
}
