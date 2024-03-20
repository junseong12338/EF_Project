package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.AdminNoticeDTO;
import dto.CategoryNumDTO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SummerNoteService {
	
	final ProjectDAO projectDAO;
	
	final UserDAO userDAO;
	
	//�� �ۼ� ó�� �޼���
	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public int insertProject(ProjectDTO dto) throws Exception {
		
		dto.setProject_status(0);
		
		
		
		
		int idx = projectDAO.select_next_idx();
		
		if(idx > 0) {
			CategoryNumDTO categoryDTO = new CategoryNumDTO();
			dto.setProject_idx(idx);
			
			String replace_editordata = dto.getProject_content().replaceAll("/temp/", "/"+idx+"/");
			String reqlace_mainImg = dto.getProject_img().replaceAll("/temp/", "/"+idx+"/");
			
			dto.setProject_content(replace_editordata);
			dto.setProject_img(reqlace_mainImg);
			
			
			projectDAO.insert_project(dto);
			
			categoryDTO.setProject_idx(idx);
			
			for(int categoryNum : dto.getCategory_list()) {
				categoryDTO.setCategory_idx(categoryNum);
				projectDAO.insert_categoryNum(categoryDTO);
			}
			
			return idx;
		}
		return -1;
	}
	
	public ProjectDTO projectSelectOne(int idx) {
		List<Integer> categoryNumList = new ArrayList<Integer>();

		ProjectDTO projectDTO = projectDAO.selectOne_project(idx);
		
		List<CategoryNumDTO> categoryDTO = projectDAO.select_categoryNum(idx);
		
		
		if(!categoryDTO.isEmpty()) {
		
			for(CategoryNumDTO dto : categoryDTO) {
				categoryNumList.add(dto.getCategory_idx());
			}
			projectDTO.setCategory_list(categoryNumList);
		}
		
		
		
		return projectDTO;
	}
	
	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public int updateProject(ProjectDTO dto) throws Exception {
		
		dto.setProject_status(0);
		
		
			CategoryNumDTO categoryDTO = new CategoryNumDTO();
			
			
			String replace_editordata = dto.getProject_content().replaceAll("/temp/", "/"+ dto.getProject_idx() +"/");
			String reqlace_mainImg = dto.getProject_img().replaceAll("/temp/", "/"+ dto.getProject_idx() +"/");
			
			dto.setProject_content(replace_editordata);
			dto.setProject_img(reqlace_mainImg);
			
			
			int res = projectDAO.update_project(dto);
			
			projectDAO.delete_categery(dto.getProject_idx());
			
			for(int categoryNum : dto.getCategory_list()) {			
				categoryDTO.setProject_idx(dto.getProject_idx());
				categoryDTO.setCategory_idx(categoryNum);
				projectDAO.insert_categoryNum(categoryDTO);
			}
			
			return res;
	}
	
	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public int deleteProject(int idx) {
		
		int res = projectDAO.delete_project(idx);
		
		if(res > 0) {
			res = projectDAO.delete_categery(idx);
			if(res>0) {
				projectDAO.delete_like(idx);				
				projectDAO.delete_donation(idx);
					
				return res;
			}
		}
		
		
		return 0;
	}
	
	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public int insertAdminNotice(AdminNoticeDTO dto, String userEmail) {
		
		int idx = userDAO.select_admin_notice_next_idx();
		if(idx >0) {
			String replace_editordata = dto.getAd_notice_content().replaceAll("/temp/", "/admin/"+idx+"/");
			dto.setAd_notice_idx(idx);
			dto.setAd_notice_content(replace_editordata);
			
			int res = userDAO.insert_admin_notice(dto);
			
			if(res > 0) {
				return idx;
			}
		}
		
		return -1;
	}
	
	public int updateAdminNotice(AdminNoticeDTO dto, String userEmail) {
		
		String replace_editordata = dto.getAd_notice_content().replaceAll("/temp/", "/admin/"+dto.getAd_notice_idx()+"/");
		
		
		dto.setAd_notice_content(replace_editordata);
		
		
		
		int res = userDAO.update_admin_notice(dto);
		
		return res;
		
	}
	
	//어드민 공지사항 삭제
	public int deleteAdminNotice(int idx) {
		return userDAO.deleteAdminNotice(idx);
	}
	
	//어드민 공지사항 한건 가져오기
	public AdminNoticeDTO selectAdminNoticeOne(int idx) {
		return userDAO.selectAdminNoticeOne(idx);
	}
	
}
