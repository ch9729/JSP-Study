<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
    <h2>회원 수정 테스트(executeUpdate() 사용)</h2>
    <%
    // DB에 연결
    JDBConnect jdbc = new JDBConnect();
    
    // 테스트용 입력값 준비 
    String id = "test1";	//프라이머리키는 중복이 되면 안되므로 추가 시키고 싶으면 다른 값으로 입력

    // 쿼리문 생성
    String sql = "DELETE member WHERE id=?";  
    jdbc.psmt = jdbc.con.prepareStatement(sql);
    jdbc.psmt.setString(1, id);	//첫번째 물음표 id 입력


    // 쿼리 수행(입력/수정/삭제 시 업데이트)
    int inResult = jdbc.psmt.executeUpdate(); 
    out.println(inResult + "행이 삭제되었습니다.");

    // 연결 닫기
    jdbc.close(); 
    %>
</body>
</html>
