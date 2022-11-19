package com.mp.mdpdf;

import com.mp.mdpdf.exception.ConversionException;
import com.mp.mdpdf.htmltopdf.HtmlPdfConverter;
import com.mp.mdpdf.htmltopdf.WaterMarkerGenerator;
import com.mp.mdpdf.mdtohtml.AtlassianMd2HtmlConverter;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

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
        File mdFile = new File("D:\\App\\typora\\easy-api.md");
        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(mdFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }
        
        String html = AtlassianMd2HtmlConverter.markdownToHtmlExtensions(sb.toString());
        html  = "<style type=\"text/css\">\n" + CSS_CONTENT + "\n</style>\n" + html;
//        System.out.println(html);
        File file = new File("D:\\Sobev\\mdpdf\\src\\main\\resources\\pdf\\xx.pdf");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        HtmlPdfConverter.writeStringToOutputStreamAsPDF(html, outputStream, new WaterMarkerGenerator("1e587116-067d-4c9c-a9e3-3dd5c102f77b"));
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
//        byte[] buf = new byte[4096];
//        int len;
//        while ((len = byteArrayInputStream.read(buf, 0, buf.length)) > 0) {
//            String val = new String(buf, 0, len);
//            System.out.println("val = " + val);
//        }
        outputStream.close();
    }

    public static String readHtml(String filePath) {
        File file = new File(filePath);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<String> lines = reader.lines().collect(Collectors.toList());
            String content = lines.stream().map(line -> line + "\n").reduce("", (s1, s2) -> s1 + s2);
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
