package service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.ProjectDAO;
import dto.CategoryNumDTO;
import dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SummerNoteService {
	
	final ProjectDAO projectDAO;
	
	//�� �ۼ� ó�� �޼���
	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public int insertProject(ProjectDTO dto) throws Exception {
		
		dto.setProject_status(0);
		
		
		
		
		int idx = projectDAO.select_idx();
		
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
				categoryDTO.setCategory_num(categoryNum);
				projectDAO.insert_categoryNum(categoryDTO);
			}
			
			return idx;
		}
		return -1;
	}
	
}
