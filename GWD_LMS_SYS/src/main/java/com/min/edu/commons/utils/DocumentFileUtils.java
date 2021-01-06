package com.min.edu.commons.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;


@Component("documentFileUtils")
public class DocumentFileUtils {

	private static final String filePath = "C:\\test_file\\"; // 파일이 저장될 위치
	
	public List<AuthorizationFileDto> parseInsertFileInfo( AuthorizationDocumentDto aDto, // 게시글에 해당되는 정보(실상 seq만 가져와도됨)
			MultipartHttpServletRequest mpRequest) throws Exception{
		
		
		List<MultipartFile> listMutilpartFile = mpRequest.getFiles("file"); // MultipartHttpServletRequest iterator가 값이 정확히 안넘어와서 사용
																			// 넘어온 파일 전체 개수				
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<AuthorizationFileDto> list = new ArrayList<AuthorizationFileDto>(); 
		AuthorizationFileDto listFile = null;
		
		
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
				listFile = new AuthorizationFileDto(aDto.getAuthorization_seq(), originalFileName, storedFileName, String.valueOf(multipartFile.getSize())); //해당 파일DTO 생성
				
				list.add(listFile); // 리스트에 add
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
