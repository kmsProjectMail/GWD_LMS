package com.min.edu.commons.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

public class ImageToPdf implements PdfPCellEvent {

	protected Image img;
    public ImageToPdf(Image img) {
        this.img = img;
    }
	
	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		img.scaleAbsolute(position.getWidth(), position.getHeight());
	    img.setAbsolutePosition(position.getLeft(), position.getBottom());
	    PdfContentByte canvas = canvases[PdfPTable.BACKGROUNDCANVAS];
	    try {
	        canvas.addImage(img);
	    } catch (DocumentException ex) {
	        // do nothing
	    }
	}
	
}
