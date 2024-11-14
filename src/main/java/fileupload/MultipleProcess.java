package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 요청주소, 최대 파일사이즈 1Mb, 10Mb는 한번에 전송가능(여러파일)
@WebServlet("/13FileUpload/MultipleProcess.do")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1,
	maxRequestSize = 1024 * 1024 * 10
)
public class MultipleProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//파일 업로드는 무조건 Post메서드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		try {
			//드라이브 명으로 절대경로 지정하기 
			//String saveDirectory = "C:/02Workspaces/MustHaveJSP/src/main/webapp/Uploads";
			//Uploads 디렉토리의 물리적 경로 가져오기(실제 톰캣서버가 프로젝트를 실행하는 주소의 폴더 위치)
		    String saveDirectory = getServletContext().getRealPath("/Uploads");
		    
		    //멀티파일 업로드 하기
		    ArrayList<String> listFileName = FileUtil.multipleFile(req, saveDirectory);
		    //저장된 파일명 변경하기
		    for(String originalFileName : listFileName) {
		    	String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
		    	//DB에 저장하기
		        insertMyFile(req, originalFileName, savedFileName);
		    }
	        //파일 목록 페이지로 이동하기
	        resp.sendRedirect("FileList.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("MultiFileUploadMain.jsp").forward(req, resp);
		}
	}
	
	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
		//파일 외 폼값 받기
	    String title = req.getParameter("title");
	    String[] cateArray = req.getParameterValues("cate");	// 체크박스는 여러개 => 배열로 받는다.
	    StringBuffer cateBuf = new StringBuffer();	// 카테고리는 배열이므로 문자열 붙히기
	    if (cateArray == null) {
	        cateBuf.append("선택한 항목 없음");
	    }
	    else {
	        for (String s : cateArray) {
	            cateBuf.append(s + ", ");
	        }
	    }
	    System.out.println("파일외폼값:"+title +"\n"+ cateBuf);
	    
		//DB에 입력하기
        MyFileDTO dto = new MyFileDTO();
        dto.setTitle(title);
        dto.setCate(cateBuf.toString());
        dto.setOfile(oFileName);
        dto.setSfile(sFileName);

        //DAO를 통해 데이터베이스에 반영
        MyFileDAO dao = new MyFileDAO();
        dao.insertFile(dto);
        dao.close();
	}
}
