package com.mp.mdpdf.htmltopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author luojx
 * @date 2022/11/18 10:54
 */
public class HtmlPdfConverter {

    public static void writeStringToOutputStreamAsPDF(String html, OutputStream os) {
        writeToOutputStreamAsPDF(new ByteArrayInputStream(html.getBytes()), os);
    }

    public static void writeToOutputStreamAsPDF(InputStream html, OutputStream os) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
            document.open();
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            worker.parseXHtml(pdfWriter, document, html, Charset.forName("UTF-8"), new AsianFontProvider());
            document.close();
        } catch (Exception e) {
        }
    }
}

/**
 * 用于中文显示的Provider
 */
class AsianFontProvider extends XMLWorkerFontProvider {
    @Override
    public Font getFont(final String fontName, String encoding, float size, final int style) {
        try {
//            BaseFont font = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont font = BaseFont.createFont("font/MapleMono-SC-NF-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            BaseFont font = BaseFont.createFont("font/simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            return new Font(font, size, style);
        } catch (Exception e) {
        }
        return super.getFont(fontName, encoding, size, style);
    }
}
