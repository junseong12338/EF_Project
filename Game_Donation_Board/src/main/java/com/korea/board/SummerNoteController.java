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
import dto.AdminNoticeDTO;
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
	
	//Common클래스의 img_root값 넣어주기 각컴퓨터의 이미지 경로
	final String contextRoot = Common.img_root.IMG_ROOT;

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
		int userIdx = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_idx();
		
		ProjectDTO dto = summerNoteService.projectSelectOne(idx);
		
		
		
		//프로젝트번호 무결성체크
		if(dto == null || userIdx != dto.getUser_idx()) {
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
	
	//운영자 공지사항 수정 페이지
   @RequestMapping("admin_notice_modify")
   public String adminNoticeModify(Model model, @RequestParam(value="idx", defaultValue="0") int idx) {
	   if(idx == 0) {
		   return "redirect:/";
	   }
	   	AdminNoticeDTO dto = summerNoteService.selectAdminNoticeOne(idx);
	   
	   if(dto == null) {
		   return "redirect:/";
	   }
	   	
	   	//temp폴더 경로
	    String temp_folder = contextRoot + "temp/";
	    //idx폴더 경로
	    String idx_folder = contextRoot + "admin\\"+idx + "\\";;
	    
	    //idx폴더 안 editordata내용안, project_main_img에 이미지이름이 포함되어있는 파일만 
	    //temp폴더로 복사
	    fileUpload(idx_folder, temp_folder,dto);
	   
	    model.addAttribute("dto",dto);
	    
	    
	   
	   return Common.User.VIEW_PATH + "admin_notice_modify.jsp";
   }
	
	/*
	 * 글 작성중 이미지파일 등록 시 
	 * temp폴더에 임시 이미지 등록
	 */
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest sendRequest)  {
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
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
		
		
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
		    removeDummyFiles(getFileNamesFromFolder(temp_folder,userEmail), temp_folder);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		return "redirect:/";
		
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
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
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
			    removeDummyFiles(getFileNamesFromFolder(idx_folder,userEmail), idx_folder);
			    
			    //temp폴더 안 editordata내용안, project_main_img에 이미지이름이 포함되어있는 파일만 
			    //idx폴더로 복사
			    fileUpload(temp_folder, idx_folder,dto);
				
			    //temp폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
			    removeDummyFiles(getFileNamesFromFolder(temp_folder,userEmail), temp_folder);
			}
			
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		return "redirect:/";
	}
	
	//글 삭제 메서드
	@RequestMapping("project_delete")
	public String project_delete(@RequestParam(value="idx", defaultValue="0") int idx) {
		int userIdx = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_idx();
		
		if(idx ==0) {
			return "redirect:/";
		}
		
		ProjectDTO dto = summerNoteService.projectSelectOne(idx);
		
		if(dto == null) {
			return "redirect:/";
		}
		
		if(dto.getUser_idx() != userIdx) {
			return "redirect:/";
		}
		
		try {
			int res = summerNoteService.deleteProject(idx);
			
			if(res > 0) {
				String idx_folder = contextRoot + idx + "/";
				
				File folder = new File(idx_folder);
				
				// 폴더 삭제
		        if (folder.exists()) { // 폴더가 존재하는지 확인
		            if (folder.isDirectory()) { // 폴더인지 확인
		                deleteFolder(folder);
		            } else {
		                System.out.println("경로가 폴더가 아닙니다.");
		            }
		        } else {
		            System.out.println("폴더가 존재하지 않습니다.");
		        }
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	//공지사항 작성 메서드
	@RequestMapping("admin_summernote_send")
	public String adminNoticeSend(HttpServletRequest sendRequest) {
		String admin_notice_title = sendRequest.getParameter("admin_notice_title");
		String editordata = sendRequest.getParameter("editordata");
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
		
		AdminNoticeDTO dto = new AdminNoticeDTO();
		
		dto.setAd_notice_title(admin_notice_title);
		dto.setAd_notice_content(editordata);
		
		try {
			int idx = summerNoteService.insertAdminNotice(dto,userEmail);
			
			if(!(idx == -1)) {
				// temp폴더 안 글작성에쓰인 이미지,파일을 
				//idx번호로된 폴더를 만들어 파일복사 후 
				//temp폴더 안 더미 데이터 삭제
				
				//temp폴더 경로
			    String temp_folder = contextRoot + "temp\\";
			    //idx폴더 경로
			    String idx_folder = contextRoot + "admin\\"+idx + "\\";
			    
			    
			    
			    //temp폴더 안 editordata내용안에 이미지이름이 포함되어있는 파일만 
			    //idx폴더로 복사
			    fileUpload(temp_folder, idx_folder,dto);
				
			    //temp폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
			    removeDummyFiles(getFileNamesFromFolder(temp_folder,userEmail), temp_folder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/"; 
	}
	
	//공지사항 수정 메서드
	@RequestMapping("admin_summernote_modify")
	public String admin_summernote_modify(HttpServletRequest sendRequest) {
		int admin_notice_idx = Integer.parseInt(sendRequest.getParameter("admin_notice_idx"));
		String admin_notice_title = sendRequest.getParameter("admin_notice_title");
		String editordata = sendRequest.getParameter("editordata");
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
		
		
		AdminNoticeDTO dto = new AdminNoticeDTO();
		
		dto.setAd_notice_idx(admin_notice_idx);
		dto.setAd_notice_title(admin_notice_title);
		dto.setAd_notice_content(editordata);
		
		try {
			int res = summerNoteService.updateAdminNotice(dto,userEmail);
			
			if(res > 0) {
				// temp폴더 안 글작성에쓰인 이미지,파일을 
				//idx번호로된 폴더를 만들어 파일복사 후 
				//temp폴더 안 더미 데이터 삭제
				
				//temp폴더 경로
			    String temp_folder = contextRoot + "temp\\";
			    //idx폴더 경로
			    String idx_folder = contextRoot + "admin\\"+admin_notice_idx + "\\";
			    
			   
			    
			    //idx폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
			    removeDummyFiles(getFileNamesFromFolder(idx_folder,userEmail), idx_folder);
			    
			    //temp폴더 안 editordata내용안에 이미지이름이 포함되어있는 파일만 
			    //idx폴더로 복사
			    fileUpload(temp_folder, idx_folder,dto);
				
			    //temp폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
			    removeDummyFiles(getFileNamesFromFolder(temp_folder,userEmail), temp_folder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	//공지사항 삭제 메서드
	@RequestMapping("admin_notice_delete")
	public String admin_notice_delete(@RequestParam(value="idx", defaultValue="0") int idx) {
		
		if(idx < 1) {
			return "redirect:/";
		}
		
		
		
		if(summerNoteService.selectAdminNoticeOne(idx) == null) {
			return "redirect:/";			
		}
		
		int res = summerNoteService.deleteAdminNotice(idx);
		
		if(res > 0) {
			String idx_folder = contextRoot + "admin\\"+idx + "\\";
			//File객체 생성
			File folder = new File(idx_folder);
			
			// 폴더 삭제
	        if (folder.exists()) { // 폴더가 존재하는지 확인
	            if (folder.isDirectory()) { // 폴더인지 확인
	                deleteFolder(folder);
	            } else {
	                System.out.println("경로가 폴더가 아닙니다.");
	            }
	        } else {
	            System.out.println("폴더가 존재하지 않습니다.");
	        }
		}
			
		
		
		return "redirect:/";
	}
	
	/*
	 * 글 작성,수정 도중 페이지를 벗어나면 동작
	 * temp폴더 안 작성자 email이 포함되어있는 파일을 삭제
	 */
	@RequestMapping("pageOutDelete")
	@ResponseBody
	public void pageOutDelete() {
		
		String userEmail = ((UserDTO)request.getSession().getAttribute("user_email")).getUser_email();
		//temp폴더 경로
		String temp_folder = contextRoot + "temp\\";
		
		
		//temp폴더 안 글작성자의 email이 이름에포함된 모든 이미지파일 삭제
		removeDummyFiles(getFileNamesFromFolder(temp_folder,userEmail), temp_folder);
		
	}
	
	// temp폴더 안 session에 저장된 userEmail값이 이름에 포함된 이미지파일 이름을 리스트로 반환
	private List<String> getFileNamesFromFolder(String folderName, String userEmail) {
	    // 파일이름을 담을 리스트 객체
	    List<String> fileNames = new ArrayList<>();
	    //세션에 저장된 userEmail
	    
	    
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
	private void fileUpload(String temp_folder, String idx_folder, Object dto) {
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
	    	if(dto instanceof ProjectDTO) {
	    		ProjectDTO Pdto = (ProjectDTO)dto;
	    		if(Pdto.getProject_content().contains(file.getName()) || Pdto.getProject_img().contains(file.getName())) {
	    			temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());    			
	    		}
	    	}else if(dto instanceof AdminNoticeDTO) {
    			AdminNoticeDTO Adto = (AdminNoticeDTO)dto;
    			
    			if(Adto.getAd_notice_content().contains(file.getName())) {
	    			temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());    			
	    		}
    		}
	    	
	        
	    	if(temp != null) {
		    		// 폴더인 경우 폴더 생성
		    		if (file.isDirectory()) {
		    			temp.mkdir();
		    		} else {
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
	
	//폴더 삭제 메서드
	public void deleteFolder(File folder) {
			
		File[] files = folder.listFiles(); // 폴더 내의 파일 목록 가져오기

	    if (files != null) {
	        for (File file : files) {
	            if (file.isDirectory()) { // 폴더인 경우 재귀적으로 삭제
	                deleteFolder(file);
	            } else { // 파일인 경우 삭제
	                file.delete();
	            }
	        }
	    }
	    folder.delete(); // 폴더 삭제
	    System.out.println("폴더가 성공적으로 삭제되었습니다.");
	}	
}
