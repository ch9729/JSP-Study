<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - UseBean</title>
</head>
<body>
    <h2>useBean 액션 태그</h2>
    <h3>자바빈즈 생성하기</h3>
    <!-- 자바객체를 태그로 대신만든다. Person person = new Person();  동일-->
    <!-- class는 위치 -->
    <jsp:useBean id="person" class="common.Person" scope="request" />

    <h3>setProperty 액션 태그로 자바빈즈 속성 지정하기</h3>
    <!-- setProperty는 person 객체에 key와 value설정 -->
    <!-- person 객체 name 지역변수 값은 임꺽정 -->
    <!-- age 지역변수의 값은 41 저장 -->
    <jsp:setProperty name="person" property="name" value="임꺽정" /> 
    <jsp:setProperty name="person" property="age" value="41" /> 

    <h3>getProperty 액션 태그로 자바빈즈 속성 읽기</h3>
    <ul>
    <!-- name과 age 값 출력 -->
        <li>이름 : <jsp:getProperty name="person" property="name" /></li> 
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
    </ul>
</body>
</html>