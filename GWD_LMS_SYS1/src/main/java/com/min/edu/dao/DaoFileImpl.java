package com.min.edu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.FileBoardDto;


@Repository
public class DaoFileImpl implements IDaoFile {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.min.edu.service.IServiceFile.";
	@Override
	public boolean setFileBoardUpload(FileBoardDto dto) {
		int n = session.insert(NS+"setFileBoardUpload",dto);
		return n>0 ? true: false;
	}

	@Override
	public List<FileBoardDto> getFileBoardList(String b_seq) {
		return session.selectList(NS+"getFileBoardList",b_seq);
	}

	@Override
	public FileBoardDto getFileBoardDownload(String f_seq) throws Exception{
		return session.selectOne(NS+"getFileBoardDownload",f_seq);
	}

	@Override
	public boolean setFileBoardDelete(String f_seq) throws Exception{
		int n = session.update(NS+"setFileBoardDelete",f_seq);
		return n>0 ? true: false;
	}

}
