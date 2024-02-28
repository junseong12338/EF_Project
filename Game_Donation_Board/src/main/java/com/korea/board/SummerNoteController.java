package com.korea.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import service.BoardService;
import service.SummerNoteService;

@Controller
@RequiredArgsConstructor
public class SummerNoteController {
	
	final SummerNoteService summerNoteService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;
	
	
	
	/*
	 * 섬머노트 이미지,파일 업로드 처리
	 * 에디터에 이미지, 파일을 등록하면
	 * 임시폴더에 저장(fileRoot)
	 */
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		JsonObject jsonObject = new JsonObject();
		
        /*
		 * String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
		 */
		//파일 업로드 기본경로
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		System.out.println("uploadSummernoteImageFile colled.....");
		// 내부경로로 저장
		String fileRoot = contextRoot+"resources/fileupload/temp/io";
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();			
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			
			System.out.println("contextRoot : " + contextRoot.toString());
			jsonObject.addProperty("url","/board/resources/fileupload/temp/io"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		
		
		return a;
	}
	
	/*
	 * 글 작성중 이미지,파일 등록 후 삭제 시 
	 * 이미 저장된 이미지, 파일 삭제 
	 */
	@RequestMapping(value = "/deleteSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public void deleteSummernoteImageFile(@RequestParam("file") String fileName) {
		System.out.println("deleteSummernoteImageFile colled.....");
		//파일 업로드 기본경로
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
	    // 폴더 위치
	    String filePath = contextRoot + "resources/fileupload/temp/";
	    
	    // 해당 파일 삭제
	    deleteFile(filePath, fileName);
	}

	// 파일 하나 삭제
	private void deleteFile(String filePath, String fileName) {
	    Path path = Paths.get(filePath, fileName);
	    try {
	        Files.delete(path);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	/*
	 * 글 작성 완료 매핑 메서드
	 */
	@RequestMapping("/summernote_send")
	public String summernote_send(String editordata) {

		//파일 업로드 기본경로
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		// 폴더 위치
	    String filePath = contextRoot + "resources/fileupload/temp/";
		
	    
		
		try {
			int idx = summerNoteService.insertProject(editordata);
			
			if(idx == -1) {
				return "redirect:/";
			}
			System.out.println("idx :" + idx);
			
			// temp 에서 저장된 데이터들 업로드
		    String path_folder1 = contextRoot + "resources/fileupload/temp/";
		    String path_folder2 = contextRoot + "resources/fileupload/" + idx + "/";
		    fileUpload(path_folder1, path_folder2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		return "redirect:editor_test";
	}
	
	// 하위 폴더 복사
	private void fileUpload(String path_folder1, String path_folder2) {
	    // 주어진 경로를 기반으로 폴더 객체 생성
	    File folder1;
	    File folder2;
	    folder1 = new File(path_folder1);
	    folder2 = new File(path_folder2);

	    // 폴더가 존재하지 않으면 새로 생성
	    if (!folder1.exists())
	        folder1.mkdirs();
	    if (!folder2.exists())
	        folder2.mkdirs();
	    
	    // 폴더1의 파일들을 가져옴
	    File[] target_files = folder1.listFiles();
	    for (File file : target_files) {
	        // 폴더2에 동일한 파일 생성
	    	File temp = null; 	    		
	    	if(file.getName().contains("io"))
	    	{
	    		temp = new File(folder2.getAbsolutePath() + File.separator + file.getName()); 
	    	}
	        
	    	if(temp != null) {
		    		// 파일인 경우
		    		if (file.isDirectory()) {
		    			// 폴더인 경우 동일한 폴더 생성
		    			temp.mkdir();
		    		} else {
		    			// 폴더가 아닌 경우 파일 복사
		    			FileInputStream fis = null;
		    			FileOutputStream fos = null;
		    			try {
		    				fis = new FileInputStream(file);
		    				fos = new FileOutputStream(temp);
		    				byte[] b = new byte[4096];
		    				int cnt = 0;
		    				while ((cnt = fis.read(b)) != -1) {
		    					fos.write(b, 0, cnt);
		    				}
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    			} finally {
		    				try {
		    					// 예외 발생 여부와 상관없이 파일 입출력 스트림을 닫음
		    					fis.close();
		    					fos.close();
		    				} catch (IOException e) {
		    					e.printStackTrace();
		    				}
		    			}
		    		}
		    	}
	    		
	    	}
	}
	
	
	
}
