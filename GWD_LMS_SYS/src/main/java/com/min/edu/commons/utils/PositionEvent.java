package com.min.edu.commons.utils;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

public class PositionEvent implements PdfPCellEvent {

	protected Phrase content;
    protected float wPct;
    protected float hPct;
    protected int alignment;
 
    public PositionEvent(Phrase content, float wPct, float hPct, int alignment) {
        this.content = content;
        this.wPct = wPct;
        this.hPct = hPct;
        this.alignment = alignment;
    }
 
	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
        float x = position.getLeft() + wPct * position.getWidth();
        float y = position.getBottom() + hPct * (position.getHeight() - content.getLeading());
        ColumnText.showTextAligned(canvas, alignment, content, x, y, 0);
    }

}
