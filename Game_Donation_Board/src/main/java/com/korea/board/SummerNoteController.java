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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import aspect.ModelAndViewRedirectException;
import dto.ProjectDTO;
import dto.UserDTO;
import lombok.RequiredArgsConstructor;
import service.SummerNoteService;
import util.Common;

@Controller
@RequiredArgsConstructor
public class SummerNoteController {
	
	final SummerNoteService summerNoteService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	//각자 컴퓨터 이미지 기본경로 설정 
	//예: C:\\Users\\admin\\Desktop\\EF_work\\EF_Project\\util\\ef_project_img : 이준성 학원 경로
    // C:\\Users\\junhyuk\\Desktop\\이준성\\공부\\GitHub\\EF_Project\\util\\ef_project_img" : 이준성 집 
	final String contextRoot = "C:\\jjs_project\\spring\\koricWorkspace\\EF_Project\\util\\ef_project_img\\";

	// 로그인체크 로그인이 안걸려있으면 ModelAndViewRedirectException예외생성
	// redirect:/로 이동
    @ExceptionHandler(ModelAndViewRedirectException.class)
    public ModelAndView handleRedirectException(ModelAndViewRedirectException ex) {
        return ex.getModelAndView();
    }
	
	//글 작성 페이지 이동
	@RequestMapping("project_editor")
	 public String project_editor(HttpServletRequest request) {
		System.out.println("글작성페이지 이동 메서드 호출");
		return Common.project.VIEW_PATH + "project_editor.jsp";
	}
	
	//글 수정페이지 이동
	@RequestMapping("project_modify")
	public String project_modify(Model model, @RequestParam(value="idx", defaultValue="0") int idx){
		
		//매개변수 무결성체크
		if(idx == 0) {
			return "redirect:/";
		}
		
		
		ProjectDTO dto = summerNoteService.projectSelectOne(idx);
		
		//프로젝트번호 무결성체크
		if(dto == null) {
			return "redirect:/";
		}
		
		//temp폴더 경로
	    String temp_folder = contextRoot + "temp/";
	    //idx폴더 경로
	    String idx_folder = contextRoot + idx + "/";
	    
	    //idx폴더 안 editordata내용안, project_main_img에 이미지이름이 포함되어있는 파일만 
	    //temp폴더로 복사
	    fileUpload(idx_folder, temp_folder,dto);
		
	    
	    
		model.addAttribute("dto",dto);
		
		return Common.project.VIEW_PATH + "project_modify.jsp";
	}
	
	/*
	 * 글 작성중 이미지파일 등록 시 
	 * temp폴더에 임시 이미지 등록
	 */
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		JsonObject jsonObject = new JsonObject();
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
		
		// 실질적인 파일 저장루트, 파일이름에 session으로 받은 유저 이메일을 이미지 이름에 포함하여 관리
		String fileRoot = contextRoot+"temp/"+userEmail;
		
		//파일의 본래이름
		String originalFileName = multipartFile.getOriginalFilename();
		//파일의 확장자 분리 후 저장
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		//파일이름을 암호화한 저장할 이름 설정
		String savedFileName = UUID.randomUUID() + extension;	
		
		File targetFile = new File(fileRoot + savedFileName);	
		try(InputStream fileStream = multipartFile.getInputStream();) {
				
			//지정된 경로에 파일 저장
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	
			
			
			// 화면에 출력할 이미지 경로 설정
			jsonObject.addProperty("url","/ef_project_img/temp/"+userEmail+savedFileName);
			
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			//예외 발생 시 등록된 파일 제거
			FileUtils.deleteQuietly(targetFile);	
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		System.out.println(jsonObject.get("url"));
		String a = jsonObject.toString();
		
		
		return a;
	}
	
	/*
	 * 등록된 이미지를 삭제할 시 동작하는 메서드
	 * 같은 이름의 이미지만 제거
	 */
	@RequestMapping(value = "/deleteSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public void deleteSummernoteImageFile(@RequestParam("file") String fileName) {
		System.out.println("deleteSummernoteImageFile colled.....");
	    // temp폴더 경로 설정
	    String filePath = contextRoot + "temp/";
	    
	    // 삭제할려는 이미지이름과 같은 파일 삭제
	    deleteFile(filePath, fileName);
	}
	
	/*
	 * 글작성완료 동작 메서드 
	 */
	@RequestMapping("/summernote_send")
	public String summernote_send(HttpServletRequest sendRequest, @RequestParam("category") List<Integer> category) {
		String project_title = sendRequest.getParameter("project_title");
		String start_date = sendRequest.getParameter("start_date");
		String end_date = sendRequest.getParameter("end_date");
		int target = Integer.parseInt(sendRequest.getParameter("target"));
		String project_main_img = sendRequest.getParameter("project_main_img");
		String editordata = sendRequest.getParameter("editordata");
		int user_idx = ((UserDTO)(sendRequest.getSession().getAttribute("user_email"))).getUser_idx();
		
		
		try {
			
			ProjectDTO dto = new ProjectDTO(); 
			
			dto.setProject_title(project_title);
			dto.setUser_idx(user_idx);
			dto.setProject_start(start_date);
			dto.setProject_end(end_date);
			dto.setProject_target(target);
			dto.setProject_img(project_main_img);
			dto.setProject_content(editordata);	
			dto.setCategory_list(category);
			
			//글 등록 후 글번호를 받아옴
			int idx = summerNoteService.insertProject(dto);
			
			if(idx == -1) {
				return "redirect:/";
			}
			
			System.out.println("idx :" + idx);
			
			// temp폴더 안 글작성에쓰인 이미지,파일을 
			//idx번호로된 폴더를 만들어 파일복사 후 
			//temp폴더 안 더미 데이터 삭제
			
			//temp폴더 경로
		    String temp_folder = contextRoot + "temp/";
		    //idx폴더 경로
		    String idx_folder = contextRoot + idx + "/";
		    
		    //temp폴더 안 editordata내용안, project_main_img에 이미지이름이 포함되어있는 파일만 
		    //idx폴더로 복사
		    fileUpload(temp_folder, idx_folder,dto);
			
		    //temp폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
		    removeDummyFiles(getFileNamesFromFolder(temp_folder), temp_folder);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
//		return "redirect:/";
		return Common.project.VIEW_PATH + "project_editor.jsp";
	}
	
	/*
	 * 글수정 동작 메서드 
	 */
	@RequestMapping("/update_send")
	public String update_send(HttpServletRequest sendRequest, @RequestParam("category") List<Integer> category) {
		String project_title = sendRequest.getParameter("project_title");
		String start_date = sendRequest.getParameter("start_date");
		String end_date = sendRequest.getParameter("end_date");
		int target = Integer.parseInt(sendRequest.getParameter("target"));
		String project_main_img = sendRequest.getParameter("project_main_img");
		String editordata = sendRequest.getParameter("editordata");
		int user_idx = ((UserDTO)(sendRequest.getSession().getAttribute("user_email"))).getUser_idx();
		int project_idx = Integer.parseInt(sendRequest.getParameter("project_idx"));
		
		try {
			
			ProjectDTO dto = new ProjectDTO(); 
			
			dto.setProject_idx(project_idx);
			dto.setProject_title(project_title);
			dto.setUser_idx(user_idx);
			dto.setProject_start(start_date);
			dto.setProject_end(end_date);
			dto.setProject_target(target);
			dto.setProject_img(project_main_img);
			dto.setProject_content(editordata);	
			dto.setCategory_list(category);
			
			//글 업데이트
			int idx = summerNoteService.updateProject(dto);
			
			if(idx != -1) {
				// temp폴더 안 글작성에쓰인 이미지,파일을 
				//idx번호로된 폴더를 만들어 파일복사 후 
				//temp폴더 안 더미 데이터 삭제
				
				//temp폴더 경로
			    String temp_folder = contextRoot + "temp/";
			    //idx폴더 경로
			    String idx_folder = contextRoot + project_idx + "/";
			    
			    //idx폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
			    removeDummyFiles(getFileNamesFromFolder(idx_folder), idx_folder);
			    
			    //temp폴더 안 editordata내용안, project_main_img에 이미지이름이 포함되어있는 파일만 
			    //idx폴더로 복사
			    fileUpload(temp_folder, idx_folder,dto);
				
			    //temp폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
			    removeDummyFiles(getFileNamesFromFolder(temp_folder), temp_folder);
			}
			
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		return "redirect:/";
	}
	
	
	// temp폴더 안 session에 저장된 userEmail값이 이름에 포함된 이미지파일 이름을 리스트로 반환
	private List<String> getFileNamesFromFolder(String folderName) {
	    // 파일이름을 담을 리스트 객체
	    List<String> fileNames = new ArrayList<>();
	    //세션에 저장된 userEmail
	    String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
	    
	    // 폴더 경로 설정
	    File folder = new File(folderName);
	    // 폴더 안 파일이름을 배열로 받아옴
	    File[] files = folder.listFiles();
	    if (files != null) {
	        // 파일이름 배열안 파일이름이 userEmail을 포함하고 있다면 반환할 리스트에 저장
	        for (File file : files) {
	            if (file.isFile()) {
	            	if(file.getName().contains(userEmail)) {
	            		fileNames.add(file.getName());	            		
	            	}
	            }
	        }
	    }
	    // 파일이름 리스트 반환
	    return fileNames;
	}
	
	
	// temp폴더 안 더미데이터 삭제
	private void removeDummyFiles(List<String> fileNames, String filePath) {
	    // 파일이름 리스트 반복
	    for (String fileName : fileNames) {
	        // filePath안 filname이름의 파일 삭제
	        
	            deleteFile(filePath, fileName);
	        
	    }
	}
	
	// 이미지 한건 삭제 메서드
	private void deleteFile(String filePath, String fileName) {
	    Path path = Paths.get(filePath, fileName);
	    try {
	        Files.delete(path);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// temp폴더 안 파일 idx폴더로 옮기기
	private void fileUpload(String temp_folder, String idx_folder, ProjectDTO dto) {
	    // temp폴더와 idx폴더경로 설정
	    File folder1;
	    File folder2;
	    folder1 = new File(temp_folder);
	    folder2 = new File(idx_folder);

	    
	    
	    // 설정된 폴더경로가 없다면 폴더 생성
	    if (!folder1.exists())
	        folder1.mkdirs();
	    if (!folder2.exists())
	        folder2.mkdirs();
	    
	    // 파일 이름 반복문
	    File[] target_files = folder1.listFiles();
	    for (File file : target_files) {
	        
	    	//editordata,project_img내용에 포함된 파일이름을 가진 파일만 idx폴더로 복사
	    	File temp = null; 	    		
    		if(dto.getProject_content().contains(file.getName()) || dto.getProject_img().contains(file.getName())) {
    			temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());    			
    		}
	    	
	        
	    	if(temp != null) {
		    		// 폴더인 경우 폴더 생성
		    		if (file.isDirectory()) {
		    			temp.mkdir();
		    		} else {
		    			 // 파일 복사를 위해 FileInputStream과 FileOutputStream을 생성합니다.
//		    			FileInputStream fis = null;
//		    			FileOutputStream fos = null;
//		    			try {
//		    				fis = new FileInputStream(file);
//		    				fos = new FileOutputStream(temp);
//		    				byte[] b = new byte[4096];
//		    				int cnt = 0;
//		    				while ((cnt = fis.read(b)) != -1) {
//		    					  // 버퍼를 사용하여 파일 내용을 읽고 복사합니다.
//		    					fos.write(b, 0, cnt);
//		    				}
//		    			} catch (Exception e) {
//		    				e.printStackTrace();
//		    			} finally {
//		    				try {
//		    					//FileInputStream과 FileOutputStream을 닫습니다.
//		    					fis.close();
//		    					fos.close();
//		    				} catch (IOException e) {
//		    					e.printStackTrace();
//		    				}
//		    			}
		    			if (temp != null) {
		                    try {
		                        // 파일 복사
		                        FileUtils.copyFile(file, temp);
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                }
		    		}
		    	}
	    		
	    	}
	}
	
	
	
}
