package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.Member;
import member.MemberDAO;

@WebServlet("*.do")   // '/'이하의 경로에서 do로 끝나는 확장자는 모두 허용
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 10L;
	MemberDAO mDAO;
       
    public MainController() { // 생성자
    	mDAO = new MemberDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 경로 설정
		// uri - 컨텍스트 + 파일 경로
		String uri = request.getRequestURI();
		System.out.println(uri);
		String command = uri.substring(uri.lastIndexOf('/'));
		System.out.println(command);
		// 이동 페이지
		String nextPage = "";
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		if(command.equals("/memberlist.do")) {
			// 회원정보를 db에서 가져옴
			List<Member> memberList = mDAO.getMemberList();
			// 모델 생성
			request.setAttribute("memberList", memberList);
			
			// 이동할 페이지
			nextPage = "/member/memberlist.jsp";
		}else if(command.equals("/joinform.do")) {
			// get 방식
			nextPage = "/member/joinform.jsp";
		}else if(command.equals("/insertmember.do")) {
			// 빈 회원 객체 생성 -> 데이터를 받아서 셋팅
			// 폼 데이터 받기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			// 객체에 데이터 셋팅
			Member m = new Member();
			m.setId(id);
			m.setPasswd(passwd);
			m.setName(name);
			m.setEmail(email);
			m.setGender(gender);
			mDAO.insertmember(m);
			// 회원가입 후 이동
			nextPage = "/index.jsp";
		}else if(command.equals("/memberview.do")) {
			// memberlist에서 클릭된 id를 받아옴
			String id = request.getParameter("id");
			// mDAO의 getMember() 호출
			Member member = mDAO.getMember(id);
			// 데이터 보내기 - 모델 생성 
			request.setAttribute("member", member);
			nextPage = "/member/memberview.jsp";
		}else if(command.equals("/loginform.do")) {  // 로그인 폼 페이지로 이동
			nextPage = "/member/loginform.jsp";
		}else if(command.equals("/login.do")) { // 로그인 처리
			// 아이디, 비번 파라미터 받기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			// 빈 객체 생성 -> 아이디 비번 셋팅
			Member m = new Member();
			m.setId(id);
			m.setPasswd(passwd);
			// 로그인 인증
			boolean result = mDAO.checkLogin(m);
			if(result) {   // 결과가 true 이면 session 발급
				session.setAttribute("sessionId", id);
				// 로그인 후 페이지 이동
				nextPage = "/member/index.jsp";
			}else {   // 결과가 false일때 오류 처리
				// 에러 알림창 띄움
			}
		}
		
		
		// dispatcher가 forward로 보내줌
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
