package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
			// 회원가입 후 자동 로그인
			session.setAttribute("sessionId", m.getId());     // 아이디를 가져와서 sessionId(세션이름) 발급
			session.setAttribute("sessionName", m.getName()); // 이름을 가져와서 sessionName(세션이름) 발급
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
			Member member= mDAO.checkLogin(m);
			String name = member.getName();
			if(name != null) {
				session.setAttribute("sessionId", id);      // 아이디 세션 발급
				session.setAttribute("sessionName", name);  // 이름 세션 발급
				nextPage = "/index.jsp";
			} else {
				String error = "아이디나 비밀번호를 다시 확인해주세요";
				request.setAttribute("error", error);
				nextPage = "/member/loginform.jsp";
			}
//			if(rs == true) {   // 결과가 true 이면 session 발급
//				session.setAttribute("sessionId", id);
//				// 로그인 후 페이지 이동
//				nextPage = "/index.jsp";
//			}else if(result == false) {   // 결과가 false일때 오류 처리
				// 에러 알림창 띄움
//				out.println("<script>");
//				out.println("alert('아이디나 비밀번호를 확인해주세요);");
//				out.println("history.back();");
//				out.println("</script>");
//				String error = "아이디나 비밀번호를 다시 확인해주세요";
//				request.setAttribute("error", error);
//				// 에러 발생 후 페이지 이동
//				nextPage = "/member/loginform.jsp";
//			}
			
		}else if(command.equals("/logout.do")) {
			session.invalidate();   // 모든 세션 삭제
			nextPage = "/index.jsp";
		}
		
		// 게시판
		if(command.equals("/boardlist.do")) {
			// 페이지 처리
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) {  // 페이지 번호를 클릭하지 않았을 때 기본값
				pageNum = "1";
			}
			// 형재 페이지
			int currentPage = Integer.parseInt(pageNum);
			// 페이지당 게시글 수 = 10(pageSize)
			int pageSize = 10;
			// 1페이지 = 1~10, 2페이지 = 11~20, 3페이지 = 21~30, ...
			// 페이지의 첫번째행(startRow)
			int startRow = (currentPage - 1) * pageSize + 1;
			System.out.println("페이지의 첫 행: " + startRow);
			// 시작 페이지
			int startPage = startRow / pageSize + 1;
			// 종료(끝) 페이지 : 전체 게시글 총수 / 페이지당 개수
			int totalRow = bDAO.getBoardCount();
			int endPage = totalRow / pageSize;
			//페이지당 개수(10)로 나누어 떨어지지 않는 경우 코딩
			endPage = (totalRow % pageSize == 0) ? endPage : endPage + 1;
			System.out.println("총 게시글 수: " + totalRow);
			System.out.println("마지막 페이지: " + endPage);
			
			// 검색 처리
			// 임시 변수
			String _field = request.getParameter("field");
			String _kw = request.getParameter("kw");
			// 실제 들어가는 변수
			String field = "";
			String kw = "";
			
			// null 처리
			if(_field != null) {   // 필드값이 있는 경우
				field = _field;
			}else {   // 필드 값이 없는 경우 (디폴트)
				field = "title";
			}
			
			if(_kw != null) {   // 검색값이 있는 경우
				kw = _kw;
			}else {   // 검색 값이 없는 경우 (디폴트)
				kw = "";
			}
			
			// List<Board> boardList = bDAO.getBoardList(field, kw);
			
			// db에서 list를 가져옴
			// List<Board> boardList = bDAO.getBoardList(currentPage);
			
			// 페이지와 검색 동시 처리
			List<Board> boardList = bDAO.getBoardList(field, kw, currentPage);
			
			// 모델로 생성
			request.setAttribute("boardList", boardList);
			request.setAttribute("Page", currentPage);     // 현재 페이지
			request.setAttribute("startPage", startPage);  // 시작 페이지
			request.setAttribute("endPage", endPage);      // 종료 페이지
			request.setAttribute("field", field);      // 종료 페이지
			request.setAttribute("kw", kw);      // 종료 페이지
			// boardlist.jsp 에서 "boardList"를 받음
			nextPage = "/board/boardlist.jsp";
		}else if (command.equals("/writeform.do")) {
			nextPage = "/board/writeform.jsp";
		}else if (command.equals("/write.do")) {
			
			String realFolder ="C:\\jspworks\\members\\src\\main\\webapp\\upload";
		      int maxSize = 10*1024*1024; //10MB
		      String encType = "utf-8";   //파일이름 한글 인코딩
		      DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		      //5가지 인자
		      MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			
			// 폼 데이터 닫기
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			// 세션 가져오기(id)
			String id = (String)session.getAttribute("sessionId");
			
			//file 속성
		    Enumeration<?> files = multi.getFileNames();
		    String filename = "";
		    while(files.hasMoreElements()) { //파일이름이 있는 동안 반복
		       String userFilename = (String)files.nextElement();
		       
	           //실제 저장될 이름
	           filename = multi.getFilesystemName(userFilename);
		    }
			
			// db에 저장
			Board b = new Board();
			b.setTitle(title);
			b.setContent(content);
			b.setFilename(filename);
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
