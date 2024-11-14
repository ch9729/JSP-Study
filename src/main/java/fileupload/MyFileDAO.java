package fileupload;

import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyFileDAO extends DBConnPool{

	public int insertFile(MyFileDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO myfile ( "
					+ " idx, title, cate, ofile, sfile) "
					+ "VALUES ( "
					+ " seq_board_num.nextval, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getCate());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return applyResult;
	}
	
	// 파일 목록을 반환
	public List<MyFileDTO> myFileList() {
		List<MyFileDTO> fileList = new Vector<MyFileDTO>();
		
		// 쿼리문 작성
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				MyFileDTO dto = new MyFileDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setCate(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setPostdate(rs.getString(6));
				
				fileList.add(dto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return fileList;
	}
}
