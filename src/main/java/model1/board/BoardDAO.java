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
	
	//페이징 된 게시물 목록 가져오기
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();	//결과(게시물 목록)를 담을 변수
		
		String query = "SELECT * FROM ("
				+ "    SELECT Tb.*, ROWNUM rNum FROM ("
				+ "        SELECT * FROM board ";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		query += " ORDER BY num DESC"
				+ "    ) Tb"
				+ ")"
				+ "WHERE rNum BETWEEN ? AND ?";
		
		try {
			psmt = con.prepareStatement(query);	//쿼리문 실행
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs = psmt.executeQuery();
			
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
	//게시글 데이터를 받아서 DB에 추가
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "INSERT INTO board ( " + "num,title,content,id,visitcount) " + " VALUES ( " + " seq_board_num.NEXTVAL, ?, ?, ?, 0)";
			
			psmt = con.prepareStatement(query);	//동적 쿼리
			psmt.setString(1,  dto.getTitle());
			psmt.setString(2,  dto.getContent());
			psmt.setString(3,  dto.getId());
			
			result = psmt.executeUpdate();	//입력,수정,삭제시 리턴값은 행의 숫자
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 글 번호로 게시물을 찾아서 내용 반환
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		 
		String query = "SELECT B.*, M.name " + "FROM member M INNER JOIN board B " + " ON M.id=B.id " + " WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//게시글 클릭시 조회수 증가 메서드
	public void updateVisitCount(String num) {
		
		String query = "UPDATE board SET visitcount=visitcount+1 " + " WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "UPDATE board SET title =?, content=? WHERE num=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "DELETE FROM board WHERE num=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
