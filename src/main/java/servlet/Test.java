package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//서블릿 클래스는 HttpServlet 상속
public class Test extends HttpServlet{
	//요청 request 응답 response

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get 요청시 실행 코드
		//1. 로그인 화면 요청 시 => 로그인 하면 보여줌. 포워드 또는 리다이렉트 로그인.jsp
		//2. 게시판 보여주기 => DB 해당 테이블 리스트 가져온다. => list.jsp (리스트도 함께 보낸다.)
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post 요청시 실행 코드
		// 로그인 요청 시 id,pw => DB 확인 => 로그인 성공하면 로그인.jsp , 틀리면 실패.jsp
		
	}
	
}
