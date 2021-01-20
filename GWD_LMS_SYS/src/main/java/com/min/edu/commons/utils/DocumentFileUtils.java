package com.min.edu.commons.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.min.edu.dto.AuthorizationDocumentDto;
import com.min.edu.dto.AuthorizationFileDto;


@Component("documentFileUtils")
public class DocumentFileUtils {

	
	
	private final String filePath = this.getClass().getResource("/").getPath().substring(0,this.getClass().getResource("/").getPath().indexOf("wtpwebapps")+"wtpwebapps".length())+"/"; 
	
	
	public List<AuthorizationFileDto> parseInsertFileInfo( AuthorizationDocumentDto aDto, // 게시글에 해당되는 정보(실상 seq만 가져와도됨)
			MultipartHttpServletRequest mpRequest) throws IOException{
		
		
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
	
	public String ImageFileInfo(MultipartHttpServletRequest mpRequest) throws IOException{
		MultipartFile multipartFile = mpRequest.getFiles("file").get(0);
		final String filetest = this.getClass().getResource("/").getPath().substring(0,this.getClass().getResource("/").getPath().lastIndexOf("WEB")).concat("images/stamp/");
		File file = new File(filetest);
		System.out.println(filetest);
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		if(multipartFile.isEmpty() == false) {
			originalFileName = multipartFile.getOriginalFilename();
			originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 해당 파일의 확장자를 집어넣음
			storedFileName = getRandomString() + originalFileExtension; //UUID값을 합침
			
			file = new File(filetest + storedFileName); // uuid의 값을 가진 파일 생성
			multipartFile.transferTo(file);
			return file.getPath();
		}
		return null;
	}
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	public void htmlToPdf(Map<String, Object> map) throws IOException, DocumentException {
		String binaryData = (String)map.get("last");
		FileOutputStream stream = null;
		String fileName=  UUID.randomUUID().toString().replaceAll("-", "");
//		String imagePath = filePath+"pdf"+fileName+".png";
		String imagePath = this.getClass().getResource("/").getPath().substring(0,this.getClass().getResource("/").getPath().lastIndexOf("WEB")).concat("images/stamp/").concat(fileName);
//		String pdfPath = filePath+"pdf"+fileName+".pdf";
		String pdfPath = imagePath+".pdf";
		try{
			System.out.println("binary file   "  + binaryData);
//			if(binaryData == null || binaryData.trim().equals("")) {
//			    throw new Exception();
//			}
			binaryData = binaryData.replaceAll("data:image/png;base64,", "");
			byte[] file = Base64.decodeBase64(binaryData);
			stream = new FileOutputStream(imagePath+".png");
			stream.write(file);
			
//			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(file));
			
//			ImageIO.write(bufImg, "png", new File(imagePath+fileName));
			
			stream.close();
			System.out.println("캡처 저장");
		    
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("에러 발생");
		}finally{
			if(stream != null) {
				stream.close();
			}
		}
		
		/// ---------------- pdf 저장
        if (!pdfPath.endsWith(".pdf"))
        {
            System.err.println("Last argument must be the destination .pdf file");
            System.exit(1);
        }
 
        PDDocument doc = new PDDocument();
        try
        {
 
         File imgDir = new File(imagePath+".png");
//         File[] imgFiles = imgDir.listFiles();
//         for(int i=1; i<=imgFiles.length; i++) {
            PDPage page = new PDPage();
            doc.addPage(page);
             
            // capture한 이미지 이름이 1.jpg, 2.jpg 이런식으로 되어있음.
            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath+".png", doc);
             
            PDPageContentStream contents = new PDPageContentStream(doc, page);
             
            contents.drawImage(pdImage, 0, 0, 590, 590);
             
            contents.close();
            doc.save(pdfPath);
//            System.out.print(i+" ");
//            if(i%50 == 0) System.out.println("");
//         }
        }
        finally
        {
            doc.close();
            System.out.println("");
            System.out.println("fin");
        }
        // -------------------------------------------------------itextPDF
        
     // 1. Create a Document which contains a table:
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(imagePath+"p"+".pdf"));
        document.open();
        PdfReader reader = new PdfReader(pdfPath);
        PdfContentByte canvas = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        
        document.newPage();
        
        canvas.addTemplate(page, 0, 0);
        
        // 리스트 길이 만큼 저장
        int length = 5;
        
        makeHeader().writeSelectedRows(0, length, 0, 2, 10, document.top() + 20, canvas);
//        document.add();
        document.close();
        
	}
	
	private static PdfPTable makeHeader() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(4);
		table.setTotalWidth(new float[] {50,50,50,50});
		
        PdfPCell cell1 = new PdfPCell();
        PdfPCell cell2 = new PdfPCell();
        PdfPCell cell3 = new PdfPCell();
        PdfPCell cell4 = new PdfPCell();
        // 2. Inside that table, make each cell with specific height:
        cell1.setFixedHeight(20);
        cell2.setFixedHeight(20);
        cell3.setFixedHeight(20);
        cell4.setFixedHeight(20);
        // 3. Each cell has the same background image
        ImageToPdf imgEvent = new ImageToPdf(Image.getInstance("C:\\Users\\MS\\Desktop\\SE2\\reply.png"));
        cell1.setCellEvent(imgEvent);
        cell2.setCellEvent(imgEvent);
        cell3.setCellEvent(imgEvent);
        // 4. Add text in front of the image at specific position
        // 글씨 저장
        cell1.setCellEvent(new PositionEvent(new Phrase(14, "Top left"), 0, 1, Element.ALIGN_LEFT));
        cell2.setCellEvent(new PositionEvent(new Phrase(14, "Top right"), 1, 1, Element.ALIGN_RIGHT));
        cell3.setCellEvent(new PositionEvent(new Phrase(14, "Top center"), 0.5f, 1, Element.ALIGN_CENTER));
        cell4.setCellEvent(new PositionEvent(new Phrase(14, "Bottom center"), 0.5f, 0, Element.ALIGN_CENTER));
        
        // Wrap it all up!
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        // 줄바꿈
        table.completeRow();
        table.addCell(cell1);
        table.addCell(cell1);
        table.addCell(cell1);
        table.addCell(cell1);
        return table;
	}
   
}
