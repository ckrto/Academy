package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProDAO;
import model.ProVO;
import model.SqlVO;


@WebServlet(value={"/pro/clist.json", "/pro/slist.json", "/pro/read", "/pro/list", "/pro/list.json", "/pro/insert"})
public class ProController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	ProDAO pdao = new ProDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		
		switch(request.getServletPath()) {
		
		case "/pro/slist.json" :
			String pcode = request.getParameter("pcode");
			out.println(pdao.slist(pcode));
			break;
		
		case "/pro/clist.json" :
			pcode = request.getParameter("pcode");
			out.println(pdao.clist(pcode));
			break;
		
		case "/pro/read" :
			pcode = request.getParameter("pcode");
			request.setAttribute("vo", pdao.read(pcode));
			request.setAttribute("pageName", "/pro/read.jsp");
			dis.forward(request, response);
			break;
		
		case "/pro/insert" :
			request.setAttribute("code", pdao.getCode());
			request.setAttribute("now", sdf.format(new Date()));
			request.setAttribute("pageName", "/pro/insert.jsp");
			dis.forward(request, response);
			break;
		
		case "/pro/list.json" :
			SqlVO svo = new SqlVO();
			svo.setKey(request.getParameter("key"));
			svo.setWord(request.getParameter("word"));
			svo.setOrder(request.getParameter("order"));
			svo.setDesc(request.getParameter("desc"));
			svo.setPage(Integer.parseInt(request.getParameter("page")));
			svo.setPer(Integer.parseInt(request.getParameter("per")));			
			out.println(pdao.list(svo));
			break;
		
		case "/pro/list" :
			request.setAttribute("pageName", "/pro/list.jsp");
			dis.forward(request, response);
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProVO vo = new ProVO();
		
		switch(request.getServletPath()) {
		case "/pro/insert" :
			vo.setPcode(request.getParameter("pcode"));
			vo.setPname(request.getParameter("pname"));
			vo.setDept(request.getParameter("dept"));
			vo.setTitle(request.getParameter("title"));
			vo.setHiredate(request.getParameter("hiredate"));			
			int salary = request.getParameter("salary") == "" ? 0 : Integer.parseInt(request.getParameter("salary"));
			vo.setSalary(salary);	
			System.out.println(vo.toString());
			pdao.insert(vo);
			response.sendRedirect("/pro/list");
			break;
		}
	}

}
