package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalcController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		
		
		// 1. 폼에 입력된 데이터 받아오기
		int n1 = Integer.parseInt(request.getParameter("n1"));
		String op = request.getParameter("op");
		int n2 = Integer.parseInt(request.getParameter("n2"));
		int result = 0;  // 계산 결과값
		
		// 2. 계산하기
		switch(op) {
		case "＋" : 
			result = n1 + n2; break;
		case "－" : 
			result = n1 - n2; break;
		case "×" : 
			result = n1 * n2; break;
		case "÷" : 
			result = n1 / n2; break;
		}
		
		// 3. 컨텐츠 유형 보내기
		/*
		 * PrintWriter out = response.getWriter();
		 * out.append("<html><body><h2>계산기</h2><hr>") .append("<p>계산결과: " + result +
		 * "</body></html>");
		 */
		
		// 계산 결과 - 모델(Model)
		request.setAttribute("result", result);
		// 결과를 받아줄 페이지
		RequestDispatcher dispathcer = 
				request.getRequestDispatcher("/mvc/calcResult.jsp");
		
		dispathcer.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
