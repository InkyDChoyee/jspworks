package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")   // '/'루트 경로 아래에 새로운 경로가 추가 됨
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 경로(uri) 설정 - 커맨드패턴(command)
		// http:localhost:8000/student/studentlist.jsp
		// 맨 뒤 경로 추출 - lastIndexOf('/'), subString(1)
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(uri.lastIndexOf("/"));
		System.out.println(command);
		
		// 이동할 페이지
		String nextPage = "";
		
		if(command.equals("/studentlist")) {
			nextPage = "/student/studentlist.jsp";
		}
			
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
}