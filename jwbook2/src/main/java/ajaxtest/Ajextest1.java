package ajaxtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/ajax/ajax1" })
public class Ajextest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 컨텐츠 응답
		response.setContentType("text/html; charset=utf-8");
		
		// 메세지 받기
		String message = request.getParameter("message");  // 데이터 "message"
		
		PrintWriter out = response.getWriter();
		// 메세지 보내기
		out.print(message);   // 변수 message
		
	}


}
