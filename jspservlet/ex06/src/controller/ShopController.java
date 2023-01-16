package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NaverAPI;
import model.ShopDAO;
import model.ShopVO;


@WebServlet(value={"/shop/search", "/shop/search.json", "/shop/list", "/shop/insert", "/download"})
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShopDAO dao = new ShopDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		PrintWriter out = response.getWriter();
		
		switch(request.getServletPath()) {
		case "/shop/search.json" :
			String query = request.getParameter("query");
			int page = Integer.parseInt(request.getParameter("page"));
			out.println(NaverAPI.search(query, page));
			break;			
		case "/shop/search":
            request.setAttribute("pageName", "/shop/search.jsp");
            dis.forward(request, response);
            break;
		case "/download" :
			String image = request.getParameter("url");
			download(image);
			break;
		case "/shop/list" : 
			page = request.getParameter("page") == null ? 1 :
				Integer.parseInt(request.getParameter("page"));
			request.setAttribute("page", page);
			request.setAttribute("array", dao.list(page));
			request.setAttribute("pageName", "/shop/list.jsp");
			dis.forward(request, response);
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ShopVO vo = new ShopVO();
		vo.setId(request.getParameter("id"));
		vo.setTitle(request.getParameter("title"));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setBrand(request.getParameter("brand"));
		
		switch(request.getServletPath()) {
		case "/shop/insert" :
			vo.setImage(download(request.getParameter("url")));
			System.out.println(vo.toString());
			dao.insert(vo);
			break;
		}
	}
	
	
	public String download(String image) {
		String file = "";
		
		InputStream input = null;
		OutputStream output = null;
		
		try {
			URL url = new URL(image);
			input = url.openStream();
			file = image.substring(image.lastIndexOf("/"));
			output = new FileOutputStream("c:/image/" + file);
			
			while(true) {
				int data = input.read();
				if(data == -1) {
					break;
				}
				else {
					output.write(data);
				}
			}
		} catch(Exception e) {
			System.out.println("다운로드 : " + e.toString());
		}
		
		return file;
	}

}
