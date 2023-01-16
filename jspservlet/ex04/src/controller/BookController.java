package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.BookDAO;
import model.BookVO;


@WebServlet(value={"/book/search", "/book/list.json", "/book/insert", "/book/list"})
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookDAO dao = new BookDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		
		switch(request.getServletPath()) {
		case "/book/list.json" :
			int page = Integer.parseInt(request.getParameter("page"));
			ArrayList<BookVO> array = dao.list(page);
			JSONArray jArray = new JSONArray();
			
			for(BookVO vo : array) {
				JSONObject obj = new JSONObject();
				obj.put("id", vo.getId());
				obj.put("title", vo.getTitle());
				obj.put("price", vo.getPrice());
				obj.put("image", vo.getImage());
				obj.put("authors", vo.getAuthors());
				obj.put("wdate", vo.getWdate());
				obj.put("contents", vo.getContents());
				jArray.add(obj);
			}
			
			JSONObject jObj = new JSONObject();
			jObj.put("array", jArray);
			jObj.put("count", dao.count());
			
			out.println(jObj);
			break;
		case "/book/search" :
			request.setAttribute("pageName", "/book/search.jsp");
			dis.forward(request, response);
			break;
		case "/book/list" :
			request.setAttribute("pageName", "/book/list.jsp");
			dis.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BookVO vo = new BookVO();
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("contents"));
		vo.setImage(request.getParameter("image"));
		vo.setPublisher(request.getParameter("publisher"));
		vo.setAuthors(request.getParameter("authors"));
		vo.setIsbn(request.getParameter("isbn"));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		
		switch(request.getServletPath()) {
		case "/book/insert" :
			System.out.println(vo.toString());
			dao.insert(vo);
			response.sendRedirect("/book/search");
			break;
		}
	}

}
