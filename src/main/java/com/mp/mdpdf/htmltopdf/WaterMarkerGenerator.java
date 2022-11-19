package com.mp.mdpdf.htmltopdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import java.io.IOException;

/**
 * @author luojx
 * @date 2022/11/19 15:16
 */
public class WaterMarkerGenerator extends PdfPageEventHelper {
    private String watermarkContent ="";
    private Integer fontSize = 20;

    public WaterMarkerGenerator(String watermarkContent, Integer fontSize) {
        this.watermarkContent = watermarkContent;
        this.fontSize = fontSize;
    }

    public WaterMarkerGenerator() {
    }

    public WaterMarkerGenerator(String watermarkContent) {
        this.watermarkContent = watermarkContent;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        try {
            PdfContentByte waterMar = writer.getDirectContentUnder();
            waterMar.beginText();
            // set Opacity
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.1f);
            waterMar.setFontAndSize(BaseFont.createFont("font/simfang.ttf", BaseFont.IDENTITY_H,
                    BaseFont.NOT_EMBEDDED), fontSize);
            waterMar.setGState(gs);
            // set align content X Y rotation
            waterMar.showTextAligned(Element.ALIGN_RIGHT, watermarkContent, 450, 900, 20);
            waterMar.showTextAligned(Element.ALIGN_RIGHT, watermarkContent, 450, 700, 20);
            waterMar.showTextAligned(Element.ALIGN_RIGHT, watermarkContent, 450, 500, 20);
            waterMar.showTextAligned(Element.ALIGN_RIGHT, watermarkContent, 450, 300, 20);
            waterMar.showTextAligned(Element.ALIGN_RIGHT, watermarkContent, 450, 100, 20);
            // set color
            waterMar.setColorFill(BaseColor.GRAY);
            //ending ;)
            waterMar.endText();
            waterMar.stroke();
            super.onStartPage(writer, document);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
