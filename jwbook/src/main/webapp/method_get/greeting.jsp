<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	int cnt = Integer.parseInt(request.getParameter("cnt"));

	for(int i = 0; i < cnt; i++){
		out.println("안녕~JSP<br>");
	}
%> --%>

<%
	// 오류 처리 - null string 처리
	int cnt = 0;  //cnt 초기화
	if(request.getParameter("cnt")!=null){
		cnt = Integer.parseInt(request.getParameter("cnt"));
	}
	
	for(int i = 0; i < cnt; i++){
		out.println("안녕~JSP<br>");
	}
%>