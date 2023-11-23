package servlet.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/for/first")
public class ForFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForFirstServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 요청
		request.setCharacterEncoding("utf-8");
		
		// 응답할 컨텐츠 유형
		response.setContentType("text/html; charset=utf-8");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("second?name=대한민국");
		
		dispatcher.forward(request, response);  // doGet의 객체가 온다
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
