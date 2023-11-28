package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.Student;
import student.StudentDAO;

@WebServlet("*.do")   // '/'루트 경로 아래에 do로 끝나는 확장자인 파일은 모두 올 수 있음
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 객체 생성
	StudentDAO sDAO;
       
    public StudentController() {
    	sDAO = new StudentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 경로(uri) 설정 - 커맨드패턴(command)
		// http:localhost:8000/student/studentlist.jsp
		// 맨 뒤 경로 추출 - lastIndexOf('/'), subString(0)
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(uri);
		System.out.println(uri.lastIndexOf("/"));
		System.out.println(command);
		
		// 이동할 페이지
		String nextPage = "";
		
		if(command.equals("/studentlist.do")) {
			List<Student> students = sDAO.getStudentList();
			
			// 모델 생성
			request.setAttribute("students", students);
			// 뷰 페이지로 이동
			nextPage = "/student/studentlist.jsp";
		}
			
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
}