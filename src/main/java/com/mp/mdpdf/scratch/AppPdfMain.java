package com.mp.mdpdf.scratch;

import com.mp.mdpdf.htmltopdf.HtmlPdfConverter;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luojx
 * @date 2022/11/18 16:13
 */
public class AppPdfMain {
    public static void main(String[] args) throws IOException {
        String mdFile = "D:\\App\\typora\\oppf\\aksk\\新增、注销用户数据.md";
        MarkdownEntity html = MarkDown2HtmlWrapper.ofFile(mdFile);
//        System.out.println(html.toString());
        html.setHtml(readHtml("C:\\Users\\DELL\\Downloads\\vscode.html"));
        File file = new File("D:\\Sobev\\mdpdf\\src\\main\\resources\\pdf\\xx.pdf");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        HtmlPdfConverter.writeStringToOutputStreamAsPDF(html.toString(), outputStream);
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
