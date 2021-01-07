package com.min.edu.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dao.IDaoFile;
import com.min.edu.dto.FileBoardDto;


@Service
public class ServiceFileImpl implements IServiceFile {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDaoFile dao;
	
//	@Override
//	public boolean setFileBoardUpload(FileBoardDto dto) {
////		log.info("setFileBoardUpload");
////		return dao.setFileBoardUpload(dto);
//	}

	@Override
	public List<FileBoardDto> getFileBoardList(String b_seq)  throws Exception {
		log.info("getFileBoardList");
		return dao.getFileBoardList(b_seq);
	}

	@Override
	public FileBoardDto getFileBoardDownload(String f_seq) throws Exception{
		log.info("getFileBoardDownload");
		return dao.getFileBoardDownload(f_seq);
		
	}

//	@Override
//	public boolean setFileBoardDelete(String f_seq) throws Exception{
//		log.info("setFileBoardDelete");
//		return dao.setFileBoardDelete(f_seq);
//	}
	
	

}
