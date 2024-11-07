<%@ page import="utils.CookieManager"%>
<%@ page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%
String user_id = request.getParameter("user_id"); 
String user_pw = request.getParameter("user_pw");
String save_check = request.getParameter("save_check");  

System.out.println(user_id +"<>"+ user_pw);

if ("must".equals(user_id) && "1234".equals(user_pw)) { 
    // 로그인 성공하고 체크박스 체크했으면 쿠키에 id 정장
    if (save_check != null && save_check.equals("Y")) { 
        CookieManager.makeCookie(response, "loginId", user_id, 86400); 
    } 
    //아이디 저장 체크를 하지 않았으면 쿠키 삭제
    else {
        CookieManager.deleteCookie(response, "loginId"); 
    }
    
    JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out); 
}
else {
    // 로그인 실패
    JSFunction.alertBack("로그인에 실패했습니다.", out);  
}
%>