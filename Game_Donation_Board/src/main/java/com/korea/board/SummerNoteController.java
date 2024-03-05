package com.korea.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.SummerNoteService;

@Controller
@RequiredArgsConstructor
public class SummerNoteController {
	
	final SummerNoteService summerNoteService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	//媛곸옄 而댄벂�꽣 �씠誘몄� 湲곕낯寃쎈줈 �꽕�젙 
	// C:\\Users\\admin\\Desktop\\EF_work\\EF_Project\\util\\ef_project_img : �씠以��꽦 �븰�썝 寃쎈줈
	final String contextRoot = "C:\\hyuni\\develop\\work\\git\\util\\ef_project_img\\";
	
	
	/*
	 * 占쏙옙占쌈놂옙트 占싱뱄옙占쏙옙,占쏙옙占쏙옙 占쏙옙占싸듸옙 처占쏙옙
	 * 占쏙옙占쏙옙占싶울옙 占싱뱄옙占쏙옙, 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙玖占�
	 * 占쌈쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙(fileRoot)
	 */
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		JsonObject jsonObject = new JsonObject();
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
		
        /*
		 * String fileRoot = "C:\\summernote_image\\"; // 占쌤부곤옙管占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙秊占�.
		 */
		//占쏙옙占쏙옙 占쏙옙占싸듸옙 占썩본占쏙옙占�
		
		System.out.println("uploadSummernoteImageFile colled.....");
		// 占쏙옙占싸곤옙管占� 占쏙옙占쏙옙
		String fileRoot = contextRoot+"temp/"+userEmail;
		
		String originalFileName = multipartFile.getOriginalFilename();	//占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占싹몌옙
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//占쏙옙占쏙옙 확占쏙옙占쏙옙
		String savedFileName = UUID.randomUUID() + extension;	//占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();			
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//占쏙옙占쏙옙 占쏙옙占쏙옙
			
			System.out.println("contextRoot : " + contextRoot.toString());
			jsonObject.addProperty("url","/ef_project_img/temp/"+userEmail+savedFileName); // contextroot + resources + 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		
		
		return a;
	}
	
	/*
	 * 占쏙옙 占쌜쇽옙占쏙옙 占싱뱄옙占쏙옙,占쏙옙占쏙옙 占쏙옙占� 占쏙옙 占쏙옙占쏙옙 占쏙옙 
	 * 占싱뱄옙 占쏙옙占쏙옙占� 占싱뱄옙占쏙옙, 占쏙옙占쏙옙 占쏙옙占쏙옙 
	 */
	@RequestMapping(value = "/deleteSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public void deleteSummernoteImageFile(@RequestParam("file") String fileName) {
		System.out.println("deleteSummernoteImageFile colled.....");
	    // 占쏙옙占쏙옙 占쏙옙치
	    String filePath = contextRoot + "temp/";
	    
	    // 占쌔댐옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	    deleteFile(filePath, fileName);
	}
	/*
	 * 占쏙옙 占쌜쇽옙 占싹뤄옙 占쏙옙占쏙옙 占쌨쇽옙占쏙옙
	 */
	@RequestMapping("/summernote_send")
	public String summernote_send(String editordata) {

		// 占쏙옙占쏙옙 占쏙옙치
	    String filePath = contextRoot + "temp/";
		
	    
		
		try {
			int idx = summerNoteService.insertProject(editordata);
			
			if(idx == -1) {
				return "redirect:/";
			}
			System.out.println("idx :" + idx);
			
			// temp 占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占싶듸옙 占쏙옙占싸듸옙
		    String temp_folder = contextRoot + "temp/";
		    String idx_folder = contextRoot + idx + "/";
		    fileUpload(temp_folder, idx_folder,editordata);
			
		    //temp 占쏙옙占쏙옙 占쏙옙 session占쏙옙 占쏙옙占쏙옙퓸占쏙옙獵占� 占쏙옙占쏙옙email占쏙옙 占싱몌옙占쏙옙 占쏙옙占쌉듸옙 占싱뱄옙占쏙옙 占쏙옙占쏙옙
		    removeDummyFiles(getFileNamesFromFolder(temp_folder), temp_folder);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		return "redirect:/";
	}
	
	// 占쏙옙치占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占싱몌옙 占쏙옙占쏙옙占쏙옙占쏙옙
	private List<String> getFileNamesFromFolder(String folderName) {
	    // 占쏙옙占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
	    List<String> fileNames = new ArrayList<>();
	    String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
	    // 占쌍억옙占쏙옙 占쏙옙占쏙옙 占쏙옙罐占� 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙체 占쏙옙占쏙옙
	    File folder = new File(folderName);
	    // 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占싹듸옙占쏙옙 占쏙옙占쏙옙占쏙옙
	    File[] files = folder.listFiles();
	    if (files != null) {
	        // 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙트占쏙옙 占쌩곤옙
	        for (File file : files) {
	            if (file.isFile()) {
	            	if(file.getName().contains(userEmail)) {
	            		fileNames.add(file.getName());	            		
	            	}
	            }
	        }
	    }
	    // 占쏙옙占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙환
	    return fileNames;
	}
	
	
	// 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	private void removeDummyFiles(List<String> fileNames, String filePath) {
	    // 占쌍억옙占쏙옙 占쏙옙占쏙옙 占싱몌옙 占쏙옙占쏙옙트占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	    for (String fileName : fileNames) {
	        // contents 占쏙옙占쌘울옙占쏙옙 占쏙옙占쏙옙 占싱몌옙占쏙옙 占쏙옙占쌉되억옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙
	        
	            deleteFile(filePath, fileName);
	        
	    }
	}
	
	// 占쏙옙占쏙옙 占싹놂옙 占쏙옙占쏙옙
		private void deleteFile(String filePath, String fileName) {
		    Path path = Paths.get(filePath, fileName);
		    try {
		        Files.delete(path);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	
	// 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	private void fileUpload(String temp_folder, String idx_folder, String editordata) {
	    // 占쌍억옙占쏙옙 占쏙옙罐占� 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙체 占쏙옙占쏙옙
	    File folder1;
	    File folder2;
	    folder1 = new File(temp_folder);
	    folder2 = new File(idx_folder);

	    String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
	    
	    // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	    if (!folder1.exists())
	        folder1.mkdirs();
	    if (!folder2.exists())
	        folder2.mkdirs();
	    
	    // 占쏙옙占쏙옙1占쏙옙 占쏙옙占싹듸옙占쏙옙 占쏙옙占쏙옙占쏙옙
	    File[] target_files = folder1.listFiles();
	    for (File file : target_files) {
	        // 占쏙옙占쏙옙2占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	    	File temp = null; 	    		
	    	if(file.getName().contains(userEmail))
	    	{
	    		if(editordata.contains(file.getName()))
	    		temp = new File(folder2.getAbsolutePath() + File.separator + file.getName()); 
	    	}
	        
	    	if(temp != null) {
		    		// 占쏙옙占쏙옙占쏙옙 占쏙옙占�
		    		if (file.isDirectory()) {
		    			// 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
		    			temp.mkdir();
		    		} else {
		    			// 占쏙옙占쏙옙占쏙옙 占싣댐옙 占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙
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
		    					// 占쏙옙占쏙옙 占쌩삼옙 占쏙옙占싸울옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙트占쏙옙占쏙옙 占쏙옙占쏙옙
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
