package com.mp.mdpdf;

import com.mp.mdpdf.exception.ConversionException;
import com.mp.mdpdf.htmltopdf.HtmlPdfConverter;
import com.mp.mdpdf.htmltopdf.WaterMarkerGenerator;
import com.mp.mdpdf.mdtohtml.AtlassianMd2HtmlConverter;

import java.io.*;

/**
 * @author luojx
 * @date 2022/11/18 10:53
 */
public class Application {
    private static final String CSS_PATH = "D:\\Sobev\\mdpdf\\src\\main\\resources\\css\\zjk-markdown.css";
    private static final StringBuilder CSS_CONTENT = new StringBuilder();

    static {
        File cssFile = new File(CSS_PATH);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cssFile)));
            String line;
            while ((line = reader.readLine()) != null) {
                CSS_CONTENT.append(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ConversionException, IOException {
//        File mdFile = new File("D:\\App\\typora\\oppf\\aksk\\AKSK.md");
//        File mdFile = new File("D:\\App\\typora\\rust\\rust-http-server.md");
        File mdFile = new File("D:\\App\\typora\\rust\\rust-thread-pool.md");
        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(mdFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }

        String html = AtlassianMd2HtmlConverter.markdownToHtmlExtensions(sb.toString());
        System.out.println("html = " + html);
        html = "<style type=\"text/css\">\n" + CSS_CONTENT + "\n</style>\n" + html;
        //TODO: template返回html
        File file = new File("D:\\Sobev\\mdpdf\\src\\main\\resources\\pdf\\xx.pdf");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        
        HtmlPdfConverter.writeStringToOutputStreamAsPDF(html, outputStream, new WaterMarkerGenerator("5Y2V5Y+M5LyR6I2J5rOl6ams"));
        in.close();
        outputStream.close();
    }
}
