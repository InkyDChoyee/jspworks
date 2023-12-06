package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       // DAO를 가져옴
		ProductDAO dao;
       
    public FrontController() {
    	dao = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		// 서버 경로 설정
		String command = uri.substring(uri.lastIndexOf("/"));
		// 연결 페이지 선언
		String nextPage = "";
		
		if(command.equals("/list.do")) {
			nextPage = "/product/list.jsp";
			// dao.list()로 받아온 상품list를 "list"이름으로 보내줌
			request.setAttribute("list", dao.list());
		}else if (command.equals("/cart.do")) {
			dao.addCart();
			nextPage = "/product/cart.jsp";
		}
		// 페이지 연결
	    RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
	    dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
