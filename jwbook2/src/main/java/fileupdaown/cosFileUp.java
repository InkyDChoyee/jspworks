package fileupdaown;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/cos")
public class cosFileUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 한글 인코딩
    	request.setCharacterEncoding("utf-8");;
    	// 브라우저로 응답 컨텐트 형식
    	response.setContentType("text/html; charset=utf-8");
    	
    	// 브라우저에 데이터 출력
    	PrintWriter out = response.getWriter();
    	
    	String realFolder = "C:\\jspworks\\jwbook2\\src\\main\\webapp\\upload";
    	int maxSize = 10*1024*1024;   // 10MB 
    	String encType = "utf-8";   // 한글 이름 인코딩
    	DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
    	
    	// 5가지 인자
    	MultipartRequest multi = new MultipartRequest(
    			request, realFolder, maxSize, encType, policy);
    	
    	// 일반 name 속성
    	String comment = multi.getParameter("comment");
    	out.println("설명: " + comment + "<br>");
    	
    	// file 파라미터 추출
    	Enumeration<?> files = multi.getFileNames();  // 순서가 없는 반복자 클래스
    	
    	while(files.hasMoreElements()) {  // 파일이름이 있는동안 반복
    		String userFilename = (String)files.nextElement();
    		
    		// 원본 파일 이름
    		String originalFilename = multi.getOriginalFileName(userFilename);
    		if(originalFilename != null) {
    			out.println("원본 파일: " + originalFilename + "<br>");
    			// 실제 저장될 이름
    			String fileSystemname = multi.getFilesystemName(userFilename);
    			out.println("실제 파일: " + fileSystemname + "<br>");
    		}
    	}
    	
    }

}
