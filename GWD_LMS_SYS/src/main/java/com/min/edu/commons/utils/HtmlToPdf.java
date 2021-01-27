package com.min.edu.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.min.edu.dto.AuthorizationStampDto;

@Component("htmlToPDFUtils")
public class HtmlToPdf {
	
	public void htmlToPdf(Map<String, Object> map,List<AuthorizationStampDto> stamp) throws IOException, DocumentException {
		String binaryData = (String)map.get("last");
		FileOutputStream stream = null;
		String fileName=  UUID.randomUUID().toString().replaceAll("-", "");
//		String imagePath = filePath+"pdf"+fileName+".png";
		String imagePath = this.getClass().getResource("/").getPath().substring(0,this.getClass().getResource("/").getPath().lastIndexOf("WEB")).concat("images/stamp/").concat(fileName);
//		String pdfPath = filePath+"pdf"+fileName+".pdf";
		String pdfPath = imagePath+"p"+".pdf";
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
		
		// --------------- 이미지 재조정
		
//		BufferedImage originalImage = ImageIO.read(new File(imagePath+".png"));
//		BufferedImage scaledImage = Scalr.crop(originalImage, 0, 0, 500, 714, null);
//		BufferedImage resizedImage = Scalr.resize(scaledImage, 500, 714, null);
//		ImageIO.write(resizedImage, "png", new File(imagePath+".png"));
		
		
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
             
            contents.drawImage(pdImage, 0, 0, 500, 714);
             
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
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(imagePath+".pdf"));
        document.open();
        PdfReader reader = new PdfReader(pdfPath);
        PdfContentByte canvas = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        
        document.newPage();
        
        canvas.addTemplate(page, 0, 0);
        
        // 리스트 길이 만큼 저장
        int length = stamp.size();
        
        makeHeader(stamp).writeSelectedRows(0, length, 0, 2, 490-(80*length), document.top() + 20, canvas);
//        document.add();
        document.close();
        writer.close();
        reader.close();
        // 파일 삭제
//        File file = new File(imagePath+".png");
//        File pdfFile = new File(pdfPath);
//        
//        if( file.exists() ){ 
//        	if(file.delete()){
//        		pdfFile.delete();
//        		System.out.println("파일삭제 성공");
//        	} else{ 
//        		System.out.println("파일삭제 실패"); 
//        	} 
//        } else { 
//        	System.out.println("파일이 존재하지 않습니다."); 
//        }

        
	}
	
	private static PdfPTable makeHeader(List<AuthorizationStampDto> stamp) throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(stamp.size());
		float[] f = new float[stamp.size()];
		for (int i = 0; i < f.length; i++) {
			f[i] = 80;
		}
		table.setTotalWidth(f);
		
		for (AuthorizationStampDto s : stamp) {
			PdfPCell cell1 = new PdfPCell();
	        // 2. Inside that table, make each cell with specific height:
	        cell1.setFixedHeight(20);
	        cell1.setCellEvent(new PositionEvent(new Phrase(14, s.getId()), 0.5f, 1, Element.ALIGN_CENTER));
	        table.addCell(cell1);
		}
		table.completeRow();
		for (AuthorizationStampDto s : stamp) {
			PdfPCell cell1 = new PdfPCell();
	        // 2. Inside that table, make each cell with specific height:
	        cell1.setFixedHeight(50);
	        ImageToPdf imgEvent = new ImageToPdf(Image.getInstance(s.getStamp_image_link()));
	        cell1.setCellEvent(imgEvent);
//	        cell1.setCellEvent(new PositionEvent(new Phrase(14, s.getId()), 0.5f, 1, Element.ALIGN_CENTER));
	        table.addCell(cell1);
		}
		
//        PdfPCell cell1 = new PdfPCell();
        // 2. Inside that table, make each cell with specific height:
//        cell1.setFixedHeight(20);
        
        // 3. Each cell has the same background image
//        ImageToPdf imgEvent = new ImageToPdf(Image.getInstance("C:\\Users\\MS\\Desktop\\SE2\\reply.png"));
//        cell1.setCellEvent(imgEvent);
        // 4. Add text in front of the image at specific position
        // 글씨 저장
//        cell1.setCellEvent(new PositionEvent(new Phrase(14, "Top left"), 0, 1, Element.ALIGN_LEFT));
//        cell2.setCellEvent(new PositionEvent(new Phrase(14, "Top right"), 1, 1, Element.ALIGN_RIGHT));
//        cell3.setCellEvent(new PositionEvent(new Phrase(14, "Top center"), 0.5f, 1, Element.ALIGN_CENTER));
//        cell4.setCellEvent(new PositionEvent(new Phrase(14, "Bottom center"), 0.5f, 0, Element.ALIGN_CENTER));
        
        // Wrap it all up!
//        table.addCell(cell1);
//        table.addCell(cell2);
//        table.addCell(cell3);
//        table.addCell(cell4);
        // 줄바꿈
//        table.completeRow();
//        table.addCell(cell1);
//        table.addCell(cell1);
//        table.addCell(cell1);
//        table.addCell(cell1);
        return table;
	}

}
