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
import com.min.edu.dto.FileBoardDto;
import com.min.edu.dto.MessengerDto;

@Component("chatFileUtils")
public class ChatFileUtils {

	private static final String filePath = "C:\\chat_test_file\\"; // 파일이 저장될 위치
	
	public List<FileBoardDto> parseInsertFileInfo( MessengerDto mDto,String owner, MultipartHttpServletRequest mpRequest) throws Exception{
		List<MultipartFile> listMutilpartFile = mpRequest.getFiles("file");			
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
			System.out.println("MultipartFile 개수 : =============="+i);
			multipartFile = filelist;
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listFile = new FileBoardDto(String.valueOf(mDto.getSeq()), originalFileName, storedFileName, String.valueOf(multipartFile.getSize()), owner);
				list.add(listFile);
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

