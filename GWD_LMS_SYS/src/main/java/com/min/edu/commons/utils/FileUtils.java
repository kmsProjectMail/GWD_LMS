package com.min.edu.commons.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dto.Board_Dto;
import com.min.edu.dto.FileBoardDto;



@Component("fileUtils")
public class FileUtils {

	private static final String filePath = "/tmp/test_file/"; // 파일이 저장될 위치
	
	public List<FileBoardDto> parseInsertFileInfo( Board_Dto aDto, // 게시글에 해당되는 정보(실상 seq만 가져와도됨)
			MultipartHttpServletRequest mpRequest) throws Exception{
		
		
		List<MultipartFile> listMutilpartFile = mpRequest.getFiles("file"); // MultipartHttpServletRequest iterator가 값이 정확히 안넘어와서 사용
																			// 넘어온 파일 전체 개수				
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<FileBoardDto> list = new ArrayList<FileBoardDto>(); 
		FileBoardDto listFile = null;
		
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		int i=0;
		for (MultipartFile filelist : listMutilpartFile) {
			i++;
			System.out.println("MultipartFile 개수 : =============="+i); // 파일 크기
			multipartFile = filelist;
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 해당 파일의 확장자를 집어넣음
				storedFileName = getRandomString() + originalFileExtension; //UUID값을 합침
				
				file = new File(filePath + storedFileName); // uuid의 값을 가진 파일 생성
				multipartFile.transferTo(file);
				listFile = new FileBoardDto(aDto.getBoardseq(), originalFileName, storedFileName, String.valueOf(multipartFile.getSize())); //해당 파일DTO 생성
				
				list.add(listFile); // 리스트에 add
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> parseUpdateFileInfo(Board_Dto aDto, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception{ 
		// 위의 함수와 거의 동일하게 동작 아래 if(files != null && fileNames != null) 부분부터 거의 다름
		List<MultipartFile> listMutilpartFile = mpRequest.getFiles("file");
		MultipartFile multipartFile = null; 
		String originalFileName = null; 
		String originalFileExtension = null; 
		String storedFileName = null; 
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		FileBoardDto listFile = null;
		Map<String,Object> map =null;
		for (MultipartFile filelist : listMutilpartFile) {
			multipartFile = filelist;
			if(multipartFile.isEmpty() == false){ 
				originalFileName = multipartFile.getOriginalFilename(); 
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); 
				storedFileName = getRandomString() + originalFileExtension; 
				multipartFile.transferTo(new File(filePath + storedFileName)); 
				listFile = new FileBoardDto(aDto.getBoardseq(), originalFileName, storedFileName, String.valueOf(multipartFile.getSize()));
				map=new HashMap<String, Object>();
				map.put("IsNew","Y"); // Y일 경우 새로운 파일 입력(게시글 수정중 파일 추가 했을 경우)
				map.put("file",listFile);
				list.add(map); 
			} 
		}
		if(files != null && fileNames != null){ 
			for(int i = 0; i<fileNames.length; i++) {
					map = new HashMap<String, Object>();
					map.put("IsNew","N"); // N일 경우 기존 파일의 delflag를 Y로 변경(게시글 수정중 파일을 삭제 했을 경우)
					map.put("f_seq",files[i]);
					list.add(map); 
			}
		}
		return list; 
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
