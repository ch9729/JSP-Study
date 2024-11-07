<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>내장 객체 - Response</title></head>
<body>
<%
//id, pwd를 요청폼 입력창 name과 동일시 name의 값으로 가져온다.
String id = request.getParameter("user_id");
String pwd = request.getParameter("user_pwd"); 
//id가 must이고 패스워드가 1234이면 ResponseWelcome.jsp 페이지로 넘어간다
if (id.equalsIgnoreCase("must") && pwd.equalsIgnoreCase("1234")) {
	//sendRedirect는 요청을 새로 한다.
    response.sendRedirect("ResponseWelcome.jsp");
}
else {	//아니면 로그인 에러 쿼리스트링을 붙여서 다시 메인페이지로 돌아간다.
    request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")
       .forward(request, response); 
}
%>
</body>
</html>