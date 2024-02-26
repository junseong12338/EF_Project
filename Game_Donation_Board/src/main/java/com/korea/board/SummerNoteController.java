package com.korea.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

@Controller
@RequiredArgsConstructor
public class SummerNoteController {
	final BoardService boardService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		JsonObject jsonObject = new JsonObject();
		
        /*
		 * String fileRoot = "C:\\summernote_image\\"; // �ܺΰ�η� ������ ����Ҷ�.
		 */
		System.out.println("uploadSummernoteImageFile colled.....");
		// ���ΰ�η� ����
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = contextRoot+"resources/fileupload/temp/";
		
		String originalFileName = multipartFile.getOriginalFilename();	//�������� ���ϸ�
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//���� Ȯ����
		String savedFileName = UUID.randomUUID() + extension;	//����� ���� ��
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();			
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//���� ����
			
			System.out.println("contextRoot : " + contextRoot.toString());
			jsonObject.addProperty("url","/board/resources/fileupload/temp/"+savedFileName); // contextroot + resources + ������ ���� ������
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//����� ���� ����
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		
		
		return a;
	}
	
	@RequestMapping("/summernote_send")
	@ResponseBody
	public String summernote_send(String editordata) {
		System.out.println(editordata);
		
//		String path_folder1 ="/upload/image/fileupload/29/";
//	    String path_folder2 = "/upload/image/fileupload/";
//
//	    fileUpload(path_folder1, path_folder2);
		
		
		return null;
	}
	
	// ���� ���� ����
	private void fileUpload(String path_folder1, String path_folder2) {
	    // ����1�� ����2�� ��Ÿ���� File ��ü ����
	    File folder1;
	    File folder2;
	    folder1 = new File(path_folder1);
	    folder2 = new File(path_folder2);

	    // ����1�� �������� ������ ����
	    if (!folder1.exists())
	        folder1.mkdirs();
	    
	    // ����2�� �������� ������ ����
	    if (!folder2.exists())
	        folder2.mkdirs();
	    
	    // ����1�� ���� ����� ������
	    File[] target_file = folder1.listFiles();
	    
	    // ����1�� ������ ����2�� ����
	    for (File file : target_file) {
	        File tmp = new File(folder2.getAbsolutePath() + File.separator + file.getName());
	        
	        // ������ ��� ���� ����
	        if (file.isDirectory()) {
	            tmp.mkdir();
	        } else { // ������ ��� ������ ����
	            FileInputStream fis = null;
	            FileOutputStream fos = null;
	            try {
	                fis = new FileInputStream(file);
	                fos = new FileOutputStream(tmp);
	                
	                byte[] b = new byte[4096];
	                int cnt = 0;
	                while ((cnt = fis.read(b)) != -1) {
	                    fos.write(b, 0, cnt);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                try {
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
