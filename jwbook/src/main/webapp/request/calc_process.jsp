<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
		String strNum = request.getParameter("num");
    	String regex = "[0-9]+";  // 숫자만
		String result = "";   // 문자열 변수를 전역 변수로 선언
		/* Pattern.matches(정규표현식, 문자열) */
    	if(strNum == "" || !Pattern.matches(regex, strNum)){
    		out.println("<script>");
    		out.println("alert('숫자를 입력해주세요')");
    		out.println("history.back()");
    		out.println("</script>");
	    }else{
				int num = Integer.parseInt(strNum);
				if(num % 2 == 0){
					result = "짝수";
				}else{
					result = "홀수";
				}
		    }
	%>
		<h3>홀수/짝수를 판별하는 프로그램</h3>
		결과는 <%= result %>입니다 

</body>
</html>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
