<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>액션 태그 - UseBean</title></head>
<body>
<!-- useBeanForm에 입력한 값이 person객체를 생성해서 해당 페이지로 데이터가 넘어와서 보여준다. -->
    <h3>액션 태그로 폼값 한 번에 받기</h3>
    <jsp:useBean id="person" class="common.Person" />  
    <jsp:setProperty property="*" name="person" />  <!-- 모든 속성을 전달받는다. -->
    <ul>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li>  
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
    </ul>
</body>
</html>