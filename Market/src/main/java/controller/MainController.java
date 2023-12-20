package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;



@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 필드
	ProductDAO pdao;
	
    public MainController() {
    	pdao = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		
		// command 패턴 경로 설정
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		String nextPage = "";
		
		if(command.equals("/productlist.do")) {
			// 목록 보기 메서드 호출
			List<Product> productlist = pdao.getProductList();
			// 모델 생성하기
			request.setAttribute("products", productlist);
			// 페이지 이동
			nextPage = "/product/list.jsp";
			
		}else if(command.equals("/productform.do")) {
			nextPage = "/product/pform.jsp";
		}else if(command.equals("/insertproduct.do")) {
			// 입력폼의 데이터 받기
			String pid = request.getParameter("pid");
			String pname = request.getParameter("pname");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			
			// 상품 객체 1개 생성
			Product product = new Product();
			product.setPid(pid);
			product.setPname(pname);
			product.setPrice(price);
			product.setDescription(description);
			
			// db에 등록할 메서드 호출
			pdao.insertProduct(product);
			
			// 경로로 설정해주어야 데이터가 들어감
			nextPage = "/productlist.do";
		}
			
		// 페이지 이동
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
