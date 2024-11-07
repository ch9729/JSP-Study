<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - import 속성</title>
</head>
<body>
	<%
	Date today = new Date();	//자바 날짜 Date()객체 생성
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	//날짜  포맷 객체
	String todayStr = dateFormat.format(today);	//날짜를 포맷형식으로 바꿈
	out.println("오늘 날짜 : " + todayStr);
	%>
</body>
</html>