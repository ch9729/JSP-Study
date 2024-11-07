package model1.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect{

	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	//검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount(Map<String,Object> map) {
		int totalCount = 0;	//게시물 수를 담을 변수
		
		//게시물 수를 얻어오는 쿼리문 작성
		String query = "SELECT COUNT(*) FROM board";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();	//쿼리문 실행
			rs = stmt.executeQuery(query);	//쿼리 실행
			rs.next();	//커서를 첫 번째 행으로 이동(select 쿼리 결과의 1벉째 행)
			totalCount = rs.getInt(1);	//첫 번째 컬럼 값을 가져옴
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();	//결과(게시물 목록)를 담을 변수
		
		String query = "SELECT * FROM board";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY num DESC ";
		
		try {
			stmt = con.createStatement();	//쿼리문 실행
			rs = stmt.executeQuery(query);	//쿼리 실행
			
			while (rs.next()) {		//결과를 순환하며 한 행(게시물 하나)의 내용을  DTO에 저장
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto); //결과를 저장하여 추가
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bbs;
	}
	
}
