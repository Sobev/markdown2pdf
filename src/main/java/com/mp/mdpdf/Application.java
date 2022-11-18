package com.mp.mdpdf;

import com.mp.mdpdf.exception.ConversionException;
import com.mp.mdpdf.htmltopdf.HtmlPdfConverter;
import com.mp.mdpdf.mdtohtml.AtlassianMd2HtmlConverter;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luojx
 * @date 2022/11/18 10:53
 */
public class Application {
    public static void main(String[] args) throws ConversionException, IOException {
//        File mdFile = new File("D:\\App\\typora\\easy-api.md");
        File mdFile = new File("D:\\App\\typora\\oppf\\aksk\\新增、注销用户数据.md");
        FileInputStream fileInputStream = new FileInputStream(mdFile);
        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(mdFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }
        
        String html = AtlassianMd2HtmlConverter.markdownToHtmlExtensions(sb.toString());
//        String html = "<style type=\"text/css\">\n" + css + "\n</style>\n" + readHtml("C:\\Users\\DELL\\Downloads\\vscode.html");
        html  = "<style type=\"text/css\">\n" + css + "\n</style>\n" + html;
        System.out.println(html);
        File file = new File("D:\\Sobev\\mdpdf\\src\\main\\resources\\pdf\\xx.pdf");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        HtmlPdfConverter.writeStringToOutputStreamAsPDF(html, outputStream);
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
    
    private static final String css = "/* https://github.com/microsoft/vscode/blob/master/extensions/markdown-language-features/media/markdown.css */\n" +
            "/*---------------------------------------------------------------------------------------------\n" +
            " *  Copyright (c) Microsoft Corporation. All rights reserved.\n" +
            " *  Licensed under the MIT License. See License.txt in the project root for license information.\n" +
            " *--------------------------------------------------------------------------------------------*/\n" +
            "\n" +
            "body {\n" +
            "    font-family: var(--vscode-markdown-font-family, -apple-system, BlinkMacSystemFont, \"Segoe WPC\", \"Segoe UI\", \"Ubuntu\", \"Droid Sans\", sans-serif);\n" +
            "    font-size:  14px;\n" +
            "    padding: 0 26px;\n" +
            "    line-height:  22px;\n" +
            "    word-wrap: break-word;\n" +
            "}\n" +
            "\n" +
            "#code-csp-warning {\n" +
            "    position: fixed;\n" +
            "    top: 0;\n" +
            "    right: 0;\n" +
            "    color: white;\n" +
            "    margin: 16px;\n" +
            "    text-align: center;\n" +
            "    font-size: 12px;\n" +
            "    font-family: sans-serif;\n" +
            "    background-color: #444444;\n" +
            "    cursor: pointer;\n" +
            "    padding: 6px;\n" +
            "    box-shadow: 1px 1px 1px rgba(0, 0, 0, .25);\n" +
            "}\n" +
            "\n" +
            "#code-csp-warning:hover {\n" +
            "    text-decoration: none;\n" +
            "    background-color: #007acc;\n" +
            "    box-shadow: 2px 2px 2px rgba(0, 0, 0, .25);\n" +
            "}\n" +
            "\n" +
            "body.scrollBeyondLastLine {\n" +
            "    margin-bottom: calc(100vh - 22px);\n" +
            "}\n" +
            "\n" +
            "body.showEditorSelection .code-line {\n" +
            "    position: relative;\n" +
            "}\n" +
            "\n" +
            "body.showEditorSelection .code-active-line:before,\n" +
            "body.showEditorSelection .code-line:hover:before {\n" +
            "    content: \"\";\n" +
            "    display: block;\n" +
            "    position: absolute;\n" +
            "    top: 0;\n" +
            "    left: -12px;\n" +
            "    height: 100%;\n" +
            "}\n" +
            "\n" +
            "body.showEditorSelection li.code-active-line:before,\n" +
            "body.showEditorSelection li.code-line:hover:before {\n" +
            "    left: -30px;\n" +
            "}\n" +
            "\n" +
            ".vscode-light.showEditorSelection .code-active-line:before {\n" +
            "    border-left: 3px solid rgba(0, 0, 0, 0.15);\n" +
            "}\n" +
            "\n" +
            ".vscode-light.showEditorSelection .code-line:hover:before {\n" +
            "    border-left: 3px solid rgba(0, 0, 0, 0.40);\n" +
            "}\n" +
            "\n" +
            ".vscode-light.showEditorSelection .code-line .code-line:hover:before {\n" +
            "    border-left: none;\n" +
            "}\n" +
            "\n" +
            ".vscode-dark.showEditorSelection .code-active-line:before {\n" +
            "    border-left: 3px solid rgba(255, 255, 255, 0.4);\n" +
            "}\n" +
            "\n" +
            ".vscode-dark.showEditorSelection .code-line:hover:before {\n" +
            "    border-left: 3px solid rgba(255, 255, 255, 0.60);\n" +
            "}\n" +
            "\n" +
            ".vscode-dark.showEditorSelection .code-line .code-line:hover:before {\n" +
            "    border-left: none;\n" +
            "}\n" +
            "\n" +
            ".vscode-high-contrast.showEditorSelection .code-active-line:before {\n" +
            "    border-left: 3px solid rgba(255, 160, 0, 0.7);\n" +
            "}\n" +
            "\n" +
            ".vscode-high-contrast.showEditorSelection .code-line:hover:before {\n" +
            "    border-left: 3px solid rgba(255, 160, 0, 1);\n" +
            "}\n" +
            "\n" +
            ".vscode-high-contrast.showEditorSelection .code-line .code-line:hover:before {\n" +
            "    border-left: none;\n" +
            "}\n" +
            "\n" +
            "img {\n" +
            "    max-width: 100%;\n" +
            "    max-height: 100%;\n" +
            "}\n" +
            "\n" +
            "a {\n" +
            "    text-decoration: none;\n" +
            "}\n" +
            "\n" +
            "a:hover {\n" +
            "    text-decoration: underline;\n" +
            "}\n" +
            "\n" +
            "a:focus,\n" +
            "input:focus,\n" +
            "select:focus,\n" +
            "textarea:focus {\n" +
            "    outline: 1px solid -webkit-focus-ring-color;\n" +
            "    outline-offset: -1px;\n" +
            "}\n" +
            "\n" +
            "hr {\n" +
            "    border: 0;\n" +
            "    height: 2px;\n" +
            "    border-bottom: 2px solid;\n" +
            "}\n" +
            "\n" +
            "h1 {\n" +
            "    padding-bottom: 0.3em;\n" +
            "    line-height: 1.2;\n" +
            "    border-bottom-width: 1px;\n" +
            "    border-bottom-style: solid;\n" +
            "}\n" +
            "\n" +
            "h1, h2, h3 {\n" +
            "    font-weight: normal;\n" +
            "}\n" +
            "\n" +
            "table {\n" +
            "    border-collapse: collapse;\n" +
            "}\n" +
            "\n" +
            "table > thead > tr > th {\n" +
            "    text-align: left;\n" +
            "    border-bottom: 1px solid;\n" +
            "}\n" +
            "\n" +
            "table > thead > tr > th,\n" +
            "table > thead > tr > td,\n" +
            "table > tbody > tr > th,\n" +
            "table > tbody > tr > td {\n" +
            "    padding: 5px 10px;\n" +
            "}\n" +
            "\n" +
            "table > tbody > tr + tr > td {\n" +
            "    border-top: 1px solid;\n" +
            "}\n" +
            "\n" +
            "blockquote {\n" +
            "    margin: 0 7px 0 5px;\n" +
            "    padding: 0 16px 0 10px;\n" +
            "    border-left-width: 5px;\n" +
            "    border-left-style: solid;\n" +
            "}\n" +
            "\n" +
            "code {\n" +
            "    font-family: Menlo, Monaco, Consolas, \"Droid Sans Mono\", \"Courier New\", monospace, \"Droid Sans Fallback\";\n" +
            "    font-size: 1em;\n" +
            "    line-height: 1.357em;\n" +
            "}\n" +
            "\n" +
            "body.wordWrap pre {\n" +
            "    white-space: pre-wrap;\n" +
            "}\n" +
            "\n" +
            "pre:not(.hljs),\n" +
            "pre.hljs code > div {\n" +
            "    padding: 16px;\n" +
            "    border-radius: 3px;\n" +
            "    overflow: auto;\n" +
            "}\n" +
            "\n" +
            "pre code {\n" +
            "    /*color: var(--vscode-editor-foreground);*/\n" +
            "    color: #444444;\n" +
            "    tab-size: 4;\n" +
            "}\n" +
            "\n" +
            "/** Theming */\n" +
            "\n" +
            ".vscode-light pre {\n" +
            "    background-color: rgba(220, 220, 220, 0.4);\n" +
            "}\n" +
            "\n" +
            ".vscode-dark pre {\n" +
            "    background-color: rgba(10, 10, 10, 0.4);\n" +
            "}\n" +
            "\n" +
            ".vscode-high-contrast pre {\n" +
            "    background-color: rgb(0, 0, 0);\n" +
            "}\n" +
            "\n" +
            ".vscode-high-contrast h1 {\n" +
            "    border-color: rgb(0, 0, 0);\n" +
            "}\n" +
            "\n" +
            ".vscode-light table > thead > tr > th {\n" +
            "    border-color: rgba(0, 0, 0, 0.69);\n" +
            "}\n" +
            "\n" +
            ".vscode-dark table > thead > tr > th {\n" +
            "    border-color: rgba(255, 255, 255, 0.69);\n" +
            "}\n" +
            "\n" +
            ".vscode-light h1,\n" +
            ".vscode-light hr,\n" +
            ".vscode-light table > tbody > tr + tr > td {\n" +
            "    border-color: rgba(0, 0, 0, 0.18);\n" +
            "}\n" +
            "\n" +
            ".vscode-dark h1,\n" +
            ".vscode-dark hr,\n" +
            ".vscode-dark table > tbody > tr + tr > td {\n" +
            "    border-color: rgba(255, 255, 255, 0.18);\n" +
            "}\n" +
            "\n" +
            "/* Tomorrow Theme */\n" +
            "/* http://jmblog.github.com/color-themes-for-google-code-highlightjs */\n" +
            "/* Original theme - https://github.com/chriskempson/tomorrow-theme */\n" +
            "/* Tomorrow Comment */\n" +
            ".hljs-comment,\n" +
            ".hljs-quote {\n" +
            "    color: #8e908c;\n" +
            "}\n" +
            "\n" +
            "/* Tomorrow Red */\n" +
            ".hljs-variable,\n" +
            ".hljs-template-variable,\n" +
            ".hljs-tag,\n" +
            ".hljs-name,\n" +
            ".hljs-selector-id,\n" +
            ".hljs-selector-class,\n" +
            ".hljs-regexp,\n" +
            ".hljs-deletion {\n" +
            "    color: #c82829;\n" +
            "}\n" +
            "\n" +
            "/* Tomorrow Orange */\n" +
            ".hljs-number,\n" +
            ".hljs-built_in,\n" +
            ".hljs-builtin-name,\n" +
            ".hljs-literal,\n" +
            ".hljs-type,\n" +
            ".hljs-params,\n" +
            ".hljs-meta,\n" +
            ".hljs-link {\n" +
            "    color: #f5871f;\n" +
            "}\n" +
            "\n" +
            "/* Tomorrow Yellow */\n" +
            ".hljs-attribute {\n" +
            "    color: #eab700;\n" +
            "}\n" +
            "\n" +
            "/* Tomorrow Green */\n" +
            ".hljs-string,\n" +
            ".hljs-symbol,\n" +
            ".hljs-bullet,\n" +
            ".hljs-addition {\n" +
            "    color: #718c00;\n" +
            "}\n" +
            "\n" +
            "/* Tomorrow Blue */\n" +
            ".hljs-title,\n" +
            ".hljs-section {\n" +
            "    color: #4271ae;\n" +
            "}\n" +
            "\n" +
            "/* Tomorrow Purple */\n" +
            ".hljs-keyword,\n" +
            ".hljs-selector-tag {\n" +
            "    color: #8959a8;\n" +
            "}\n" +
            "\n" +
            ".hljs {\n" +
            "    display: block;\n" +
            "    overflow-x: auto;\n" +
            "    color: #4d4d4c;\n" +
            "    padding: 0.5em;\n" +
            "}\n" +
            "\n" +
            ".hljs-emphasis {\n" +
            "    font-style: italic;\n" +
            "}\n" +
            "\n" +
            ".hljs-strong {\n" +
            "    font-weight: bold;\n" +
            "}\n" +
            "\n" +
            "/*\n" +
            " * Markdown PDF CSS\n" +
            " */\n" +
            "body {\n" +
            "    font-family: -apple-system, BlinkMacSystemFont, \"Segoe WPC\", \"Segoe UI\", \"Ubuntu\", \"Droid Sans\", sans-serif, \"Meiryo\";\n" +
            "    padding: 0 12px;\n" +
            "}\n" +
            "\n" +
            "pre {\n" +
            "    background-color: #f8f8f8;\n" +
            "    border: 1px solid #cccccc;\n" +
            "    border-radius: 3px;\n" +
            "    overflow-x: auto;\n" +
            "    white-space: pre-wrap;\n" +
            "    overflow-wrap: break-word;\n" +
            "}\n" +
            "\n" +
            "pre:not(.hljs) {\n" +
            "    padding: 23px;\n" +
            "    line-height: 19px;\n" +
            "}\n" +
            "\n" +
            "blockquote {\n" +
            "    background: rgba(127, 127, 127, 0.1);\n" +
            "    border-color: rgba(0, 122, 204, 0.5);\n" +
            "}\n" +
            "\n" +
            ".emoji {\n" +
            "    height: 1.4em;\n" +
            "}\n" +
            "\n" +
            "code {\n" +
            "    font-size: 14px;\n" +
            "    line-height: 19px;\n" +
            "}\n" +
            "\n" +
            "/* for inline code */\n" +
            ":not(pre):not(.hljs) > code {\n" +
            "    color: #C9AE75; /* Change the old color so it seems less like an error */\n" +
            "    font-size: inherit;\n" +
            "}\n" +
            "\n" +
            "/* Page Break : use <div class=\"page\"/> to insert page break\n" +
            "-------------------------------------------------------- */\n" +
            ".page {\n" +
            "    page-break-after: always;\n" +
            "}\n" +
            "\n" +
            ".markdown-body {\n" +
            "    color: #444444;\n" +
            "}";
}
