package com.min.edu.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.commons.utils.FileUtils;
import com.min.edu.dao.IDaoBoard;
import com.min.edu.dao.IDaoFile;
import com.min.edu.dto.Board_Dto;
import com.min.edu.dto.FileBoardDto;
import com.min.edu.dto.Reply_Dto;


@Service
public class ServiceBoardImpl implements IServiceBoard{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Autowired
	private IDaoFile iFile; 
	
	@Autowired
	private IDaoBoard dao;
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Board_Dto> selectAll() {
		logger.info("\t BoardServiceImpl [[selectAll]]");
		return dao.selectAll();
	}

	@Override
	public Board_Dto getOneBoard(String boardseq) {
		logger.info("\t BoardServiceImpl [[getOneBoard]]");
		return dao.getOneBoard(boardseq);
	}

	@Transactional
	@Override
	public boolean modBoard(Board_Dto dto,String[] files, String[] fileNames,MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("\t BoardServiceImpl [[modBoard]]");
		boolean isc = dao.modBoard(dto);
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(dto, files,fileNames, mpRequest);
		// 파일의 형태를 Map 형태로 받음 -> 리스트 안에 Map이 들어가 있음
		// List안의 Map의 형태 -> List{ Map{"file":FileBoardDto,"IsNew":"N"}, Map{"file":FileBoardDto,"IsNew":"Y"}} 형태로 들어가 있음
		
		Map<String, Object> tempMap = null; //
		FileBoardDto tempFile = null;
		int size = list.size(); // 리스트의 개수담음(파일의 개수)
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i); // Map{"file":FileBoardDto,"IsNew":"N"} 하나를 담음
			if(tempMap.get("IsNew").equals("Y")) { // Y 일경우 파일을 새로 입력
				tempFile = (FileBoardDto) tempMap.get("file");
				iFile.setFileBoardUpload(tempFile);
			} else {  // 아닐 경우 해당 file에 해당되는 delflag를 Y로 변경
				iFile.setFileBoardDelete((String)tempMap.get("f_seq"));
			}
		}
		
		return isc ? true : false;
	}
	
	@Transactional
	@Override
	public boolean del(String boardseq) {
		System.out.println("boardseqboardseqboardseqboardseq"+boardseq);
		logger.info("\t BoardServiceImpl [[del]]");
		boolean isc2 =dao.dReply(boardseq);
		boolean isc1 =dao.delBoard(boardseq);
		return (isc1||isc2)?true:false;
	}
	
	@Transactional
	@Override
	public boolean inputBoard(Board_Dto dto,MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("\t BoardServiceImpl [[inputBoard]]");
		boolean isc = dao.inputBoard(dto);
		
		List<FileBoardDto> list = fileUtils.parseInsertFileInfo(dto, mpRequest);
		System.out.println(list);
		int size = list.size();
		for(int i=0; i<size; i++) { // 파일의 개수만큼 실행
			iFile.setFileBoardUpload(list.get(i));
		}
		
		return (isc) ? true : false;
	}
	

	@Override
	public boolean deleteBoard(String boardseq) {
		logger.info("\t BoardServiceImpl [[deleteBoard]]");
		return dao.delBoard(boardseq);
	}

	@Override
	public boolean multiDel(Map<String, String[]> map) {
		logger.info("\t BoardServiceImpl [[multiDel]]");
		return dao.multiDel(map);
	}

	@Override
	public boolean inputReply(Reply_Dto dto) {
		logger.info("\t BoardServiceImpl [[inputReply]]");
		return dao.inputReply(dto);
	}

	@Override
	public List<Reply_Dto> oneBoardReply(String boardseq) {
		logger.info("\t BoardServiceImpl [[oneBoardReply]]");
		return dao.oneBoardReply(boardseq);
	}

	@Override
	public boolean delReply(String replyseq) {
		logger.info("\t BoardServiceImpl [[delReply]]");
		return dao.delReply(replyseq);
	}

	@Override
	public boolean modiReply(Map<String, Object> map) {
		logger.info("\t BoardServiceImpl [[modReply]]");
		return dao.modiReply(map);
	}

	@Override
	public Reply_Dto oneRe(String boardseq) {
		
		return dao.oneRe(boardseq);
	}

	
}
