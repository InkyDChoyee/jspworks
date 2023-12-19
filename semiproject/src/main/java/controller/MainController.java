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

import member.MemberDAO;
import member.MemberVO;
import product.ProductDAO;
import product.ProductVO;

@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//필드
	MemberDAO mDAO;
	ProductDAO pDAO;

	public MainController() {
		mDAO = new MemberDAO();
		pDAO = new ProductDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		//응답할 컨텐츠 유형
		response.setContentType("text/html; charset=utf-8");

		//경로 설정
		//uri - 컨텍스트(/) 파일(.do)
		String uri = request.getRequestURI();
		System.out.println(uri);
		//command 패턴
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(command);

		String nextPage = "";
		//세션 객체 생성
		HttpSession session = request.getSession();

		//view에 출력 객체 생성
		PrintWriter out = response.getWriter();

		//회원
		if(command.equals("/joinform.do")) {
			nextPage = "/member/joinform.jsp";
		}else if(command.equals("/join1.do")) {
			nextPage = "/member/join1.jsp";
		}else if(command.equals("/join2.do")) {
			nextPage = "/member/join2.jsp";
		}else if(command.equals("/insertmember.do")) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String zip_code = request.getParameter("zip_code");
			String address = request.getParameter("address");
			String detailaddress = request.getParameter("detailaddress");

			MemberVO m = new MemberVO();
			m.setId(id);
			m.setPasswd(passwd);
			m.setName(name);
			m.setEmail(email);
			m.setPhone(phone);
			m.setZip_code(zip_code);
			m.setAddress(address);
			m.setDetailaddress(detailaddress);
			mDAO.insertMember(m);
			
			session.setAttribute("sessionId", id);
			session.setAttribute("sessionName", name);
			
			nextPage = "/member/join3.jsp";
		}else if(command.equals("/main.do")){
			nextPage = "/main.jsp";
		}else if(command.equals("/loginform.do")){
			nextPage = "/member/loginform.jsp";
		}else if(command.equals("/login.do")) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");

			MemberVO m = new MemberVO();
			m.setId(id);
			m.setPasswd(passwd);

			MemberVO member = mDAO.checkLogin(m);
			String name = member.getName();

			if(name != null) {
				session.setAttribute("sessionId", id);
				session.setAttribute("sessionName", name);

				nextPage = "/main.jsp";
			}else {
				String error = "아이디나 비밀번호를 확인해주세요.";
				request.setAttribute("error", error);
				//에러 발생 후 페이지 이동
				nextPage = "/member/loginform.jsp";
			}
		}else if(command.equals("/logout.do")) {
			session.invalidate(); //세션삭제
			nextPage = "/main.jsp";
		}else if (command.equals("/memberview.do")) {
			String id =request.getParameter("id");

			MemberVO member = mDAO.getMember(id);

			request.setAttribute("member", member);

			nextPage = "/member/memberview.jsp";
		}else if (command.equals("/memberlist.do")) {

			List<MemberVO> memberList = mDAO.getMemberList();

			request.setAttribute("memberList", memberList);

			nextPage = "/member/memberlist.jsp";
		}else if (command.equals("/cart.do")) {
			nextPage = "/member/cart.jsp";
		}else if (command.equals("/deletemember.do")) {
			String id = request.getParameter("id");
			mDAO.deletemember(id);
			nextPage = "/main.jsp";
		}


		//상품
		else if(command.equals("/productlist.do")) {
			List<ProductVO> productList = pDAO.getProductList();
			request.setAttribute("productList", productList);
			nextPage = "/product/productlist.jsp";
		}else if(command.equals("/productview.do")) {
			int pno = Integer.parseInt(request.getParameter("pno"));
			ProductVO productList = pDAO.getProductVO(pno);
			request.setAttribute("productlist", productList);
			
			nextPage = "/product/productview.jsp";
		}else if(command.equals("/productmanagement.do")) {
			List<ProductVO> productList = pDAO.getProductList();
			request.setAttribute("productList", productList);
			nextPage = "/product/productmanagement.jsp";
			
		}else if(command.equals("/deleteproduct.do")) {
			int pno = Integer.parseInt(request.getParameter("pno"));
			pDAO.deleteproduct(pno);
			
			nextPage = "/productmanagement.do";
			
		}else if(command.equals("/updateform.do")) {
			int pno = Integer.parseInt(request.getParameter("pno"));
			ProductVO product = pDAO.getProductVO(pno);
			request.setAttribute("product", product);
			
			nextPage = "/product/updateform.jsp";
		}else if(command.equals("/update.do")) {
			String realFolder ="C:\\jspworks\\semiproject\\src\\main\\webapp\\fileupload";
			int maxSize = 10*1024*1024; //10MB
			String encType = "utf-8";   //파일이름 한글 인코딩
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			//5가지 인자
			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			//폼 일반 속성 데이터 받기
			int pno = Integer.parseInt(multi.getParameter("pno"));
			String pname = multi.getParameter("pname");
			int price = Integer.parseInt(multi.getParameter("price"));
			String pcontent = multi.getParameter("pcontent");
			String category = multi.getParameter("category");
			
			//file 속성
			Enumeration<?> files = multi.getFileNames();
			String pfilename = "";
			while(files.hasMoreElements()) { //파일이름이 있는 동안 반복
				String userFilename = (String)files.nextElement();
				
				//실제 파일 이름
				pfilename = multi.getFilesystemName(userFilename);
			}
			
			//db에 저장
			ProductVO p = new ProductVO();
			p.setPno(pno);
			p.setPname(pname);
			p.setPrice(price);
			p.setPcontent(pcontent);
			p.setCategory(category);
			p.setPfilename(pfilename);
			
			pDAO.updateproduct(p);
			
			nextPage = "/productmanagement.do";
		}else if(command.equals("/categorylist.do")) {
			System.out.println(request.getParameter("category"));
			List<ProductVO> p = pDAO.getCategoryList(request.getParameter("category"));
			
			request.setAttribute("categoryList", p);
			
			nextPage = "/product/categorylist.jsp";
		}else if(command.equals("/insertproduct.do")) {
			String realFolder ="C:\\jspworks\\semiproject\\src\\main\\webapp\\fileupload";
			int maxSize = 10*1024*1024; //10MB
			String encType = "utf-8";   //파일이름 한글 인코딩
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			//5가지 인자
			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			//폼 일반 속성 데이터 받기
			String pname = multi.getParameter("pname");
			int price = Integer.parseInt(multi.getParameter("price"));
			String pcontent = multi.getParameter("pcontent");
			String category = multi.getParameter("category");
			
			//file 속성
			Enumeration<?> files = multi.getFileNames();
			String pfilename = "";
			while(files.hasMoreElements()) { //파일이름이 있는 동안 반복
				String userFilename = (String)files.nextElement();
				
				//실제 파일 이름
				pfilename = multi.getFilesystemName(userFilename);
			}
			
			//db에 저장
			ProductVO p = new ProductVO();
			p.setPname(pname);
			p.setPrice(price);
			p.setPcontent(pcontent);
			p.setCategory(category);
			p.setPfilename(pfilename);
			
			//write매서드 실행
			pDAO.insertProduct(p);
			
			nextPage = "/productmanagement.do";
		}
		
		
		
		//게시판
		else if(command.equals("/boardlist.do")) {

			nextPage = "/board/neboardlist.jsp";
		}
		
		
		//redirect와 forward 구분하기
		if(command.equals("/insertproduct.do") || command.equals("/deleteproduct.do") || command.equals("/update.do")){
			response.sendRedirect("/productmanagement.do");
		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}

	}

}
