package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.Board;
import board.BoardDAO;
import member.Member;
import member.MemberDAO;
import reply.Reply;
import reply.ReplyDAO;

@WebServlet("*.do")   // '/'이하의 경로에서 do로 끝나는 확장자는 모두 허용
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 10L;
	// 필드
	MemberDAO mDAO;
	BoardDAO bDAO;
	ReplyDAO rDAO;
       
    public MainController() { // 생성자
    	mDAO = new MemberDAO();
    	bDAO = new BoardDAO();
    	rDAO = new ReplyDAO();
    }
    
    // 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		// 응답할 컨텐츠 유형
		response.setContentType("text/html; charset=utf-8");
		
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
		// view에 출력할 출력 객체 생성
		PrintWriter out = response.getWriter();
		
		// 회원
		if(command.equals("/main.do")) {
			// 메인 페이지에 게시글 보내기
			List<Board> boardList = bDAO.getBoardList();
			out.println(boardList.size() + "개");
			request.setAttribute("boardList", boardList);
			
			if(boardList.size() >= 3) {
				Board[] newBoards = {boardList.get(0), boardList.get(1), boardList.get(2)};
				request.setAttribute("boardList", newBoards);
			}
			nextPage = "/main.jsp";
		}else if(command.equals("/memberlist.do")) {
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
			if(result == true) {   // 결과가 true 이면 session 발급
				session.setAttribute("sessionId", id);
				// 로그인 후 페이지 이동
				nextPage = "/index.jsp";
			}else if(result == false) {   // 결과가 false일때 오류 처리
				// 에러 알림창 띄움
//				out.println("<script>");
//				out.println("alert('아이디나 비밀번호를 확인해주세요);");
//				out.println("history.back();");
//				out.println("</script>");
				String error = "아이디나 비밀번호를 다시 확인해주세요";
				request.setAttribute("error", error);
				// 에러 발생 후 페이지 이동
				nextPage = "/member/loginform.jsp";
			}
		}else if(command.equals("/logout.do")) {
			session.invalidate();   // 모든 세션 삭제
			nextPage = "/index.jsp";
		}
		
		// 게시판
		if(command.equals("/boardlist.do")) {
			// db에서 list를 가져옴
			List<Board> boardList = bDAO.getBoardList();
			// 모델로 생성
			request.setAttribute("boardList", boardList);
			// boardlist.jsp 에서 "boardList"를 받음
			nextPage = "/board/boardlist.jsp";
		}else if (command.equals("/writeform.do")) {
			nextPage = "/board/writeform.jsp";
		}else if (command.equals("/write.do")) {
			// 폼 데이터 닫기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			// 세션 가져오기(id)
			String id = (String)session.getAttribute("sessionId");
			// db에 저장
			Board b = new Board();
			b.setTitle(title);
			b.setContent(content);
			b.setId(id);
			// write()호출, 실행
			bDAO.write(b);
			
			nextPage = "/board/boardlist.jsp";
		}else if(command.equals("/boardview.do")) {
			// 글 제목 클릭시 요청되는 글 번호 받기
			int bno = Integer.parseInt(request.getParameter("bno"));
			// 글 상세보기 처리
			Board board = bDAO.getBoard(bno);
			
			// 댓글 목록 보기
			List<Reply> replyList = rDAO.getReplyList(bno);
			
			// 모델 생성 -> 뷰로 보내기
			request.setAttribute("board", board);
			request.setAttribute("replyList", replyList);
			
			nextPage = "/board/boardview.jsp";
		}else if(command.equals("/deleteboard.do")) {
			// 글 제목 클릭시 요청되는 글 번호 받기
			int bno = Integer.parseInt(request.getParameter("bno"));
			// 삭제 처리
			bDAO.deleteboard(bno);
			
			nextPage = "/boardlist.do";
		}else if(command.equals("/updateboardform.do")) {
			// 수정을 위해서 게시글 가져오기
			int bno = Integer.parseInt(request.getParameter("bno"));
			// 게시글 1건 가져오기
			Board board = bDAO.getBoard(bno);
			// 모델 생성
			request.setAttribute("board", board);
			
			nextPage = "/board/updateboardform.jsp";
		}else if(command.equals("/updateboard.do")) {
			// 게시글 제목, 내용을 파라미터로 받음
			int bno = Integer.parseInt(request.getParameter("bno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			// 수정 처리 메서드
			Board b = new Board();
			b.setTitle(title);
			b.setContent(content);
			b.setBno(bno);
			bDAO.updateboard(b);
			nextPage = "/boardlist.do";
		}
		
		// 댓글 구현
		if(command.equals("/insertreply.do")) {
			// 댓글 폼 데이터 받기
			int bno = Integer.parseInt(request.getParameter("bno"));
			String rcontent = request.getParameter("rcontent");
			String replyer = request.getParameter("replyer");
			
			// 댓글 등록 처리
			Reply r = new Reply();
			r.setBno(bno);
			r.setRcontent(rcontent);
			r.setReplyer(replyer);
			
			rDAO.insertreply(r);
		}else if(command.equals("/deletereply.do")) {
			int rno = Integer.parseInt(request.getParameter("rno"));
			// 삭제 처리 메서드 호출
			rDAO.deletereply(rno);
		}
		
		
		// redirect와 forward 구분하기
		if(command.equals("/write.do") || command.equals("/updateboard.do")) {
			// 새로고침 중복 생성 문제 해결
			response.sendRedirect("/boardlist.do");
		}else if(command.equals("/insertreply.do") || command.equals("/deletereply.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			response.sendRedirect("/boardview.do?bno=" + bno);
		}else {
			// dispatcher가 forward로 보내줌
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}
}
