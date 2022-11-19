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
        File mdFile = new File("D:\\App\\typora\\oppf\\aksk\\AKSK.md");
        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(mdFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }
        
        String html = AtlassianMd2HtmlConverter.markdownToHtmlExtensions(sb.toString());
        html  = "<style type=\"text/css\">\n" + css + "\n</style>\n" + html;
//        System.out.println(html);
        File file = new File("D:\\Sobev\\mdpdf\\src\\main\\resources\\pdf\\xx.pdf");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        HtmlPdfConverter.writeStringToOutputStreamAsPDF(html, outputStream);
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
    
    private static final String css = ".markdown-body {\n" +
            "  -ms-text-size-adjust: 100%;\n" +
            "  -webkit-text-size-adjust: 100%;\n" +
            "  margin: 0;\n" +
            "  color: #24292f;\n" +
            "  background-color: #ffffff;\n" +
            "  font-family: -apple-system,BlinkMacSystemFont,\"Segoe UI\",Helvetica,Arial,sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\";\n" +
            "  font-size: 16px;\n" +
            "  line-height: 1.5;\n" +
            "  word-wrap: break-word;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .octicon {\n" +
            "  display: inline-block;\n" +
            "  fill: currentColor;\n" +
            "  vertical-align: text-bottom;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h1:hover .anchor .octicon-link:before,\n" +
            ".markdown-body h2:hover .anchor .octicon-link:before,\n" +
            ".markdown-body h3:hover .anchor .octicon-link:before,\n" +
            ".markdown-body h4:hover .anchor .octicon-link:before,\n" +
            ".markdown-body h5:hover .anchor .octicon-link:before,\n" +
            ".markdown-body h6:hover .anchor .octicon-link:before {\n" +
            "  width: 16px;\n" +
            "  height: 16px;\n" +
            "  content: ' ';\n" +
            "  display: inline-block;\n" +
            "  background-color: currentColor;\n" +
            "  -webkit-mask-image: url(\"data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' version='1.1' aria-hidden='true'><path fill-rule='evenodd' d='M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z'></path></svg>\");\n" +
            "  mask-image: url(\"data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' version='1.1' aria-hidden='true'><path fill-rule='evenodd' d='M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z'></path></svg>\");\n" +
            "}\n" +
            "\n" +
            ".markdown-body details,\n" +
            ".markdown-body figcaption,\n" +
            ".markdown-body figure {\n" +
            "  display: block;\n" +
            "}\n" +
            "\n" +
            ".markdown-body summary {\n" +
            "  display: list-item;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [hidden] {\n" +
            "  display: none !important;\n" +
            "}\n" +
            "\n" +
            ".markdown-body a {\n" +
            "  background-color: transparent;\n" +
            "  color: #0969da;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body a:active,\n" +
            ".markdown-body a:hover {\n" +
            "  outline-width: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body abbr[title] {\n" +
            "  border-bottom: none;\n" +
            "  text-decoration: underline dotted;\n" +
            "}\n" +
            "\n" +
            ".markdown-body b,\n" +
            ".markdown-body strong {\n" +
            "  font-weight: 600;\n" +
            "}\n" +
            "\n" +
            ".markdown-body dfn {\n" +
            "  font-style: italic;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h1 {\n" +
            "  margin: .67em 0;\n" +
            "  font-weight: 600;\n" +
            "  padding-bottom: .3em;\n" +
            "  font-size: 2em;\n" +
            "  border-bottom: 1px solid hsla(210,18%,87%,1);\n" +
            "}\n" +
            "\n" +
            ".markdown-body mark {\n" +
            "  background-color: #fff8c5;\n" +
            "  color: #24292f;\n" +
            "}\n" +
            "\n" +
            ".markdown-body small {\n" +
            "  font-size: 90%;\n" +
            "}\n" +
            "\n" +
            ".markdown-body sub,\n" +
            ".markdown-body sup {\n" +
            "  font-size: 75%;\n" +
            "  line-height: 0;\n" +
            "  position: relative;\n" +
            "  vertical-align: baseline;\n" +
            "}\n" +
            "\n" +
            ".markdown-body sub {\n" +
            "  bottom: -0.25em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body sup {\n" +
            "  top: -0.5em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body img {\n" +
            "  border-style: none;\n" +
            "  max-width: 100%;\n" +
            "  box-sizing: content-box;\n" +
            "  background-color: #ffffff;\n" +
            "}\n" +
            "\n" +
            ".markdown-body code,\n" +
            ".markdown-body kbd,\n" +
            ".markdown-body pre,\n" +
            ".markdown-body samp {\n" +
            "  font-family: monospace,monospace;\n" +
            "  font-size: 1em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body figure {\n" +
            "  margin: 1em 40px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body hr {\n" +
            "  box-sizing: content-box;\n" +
            "  overflow: hidden;\n" +
            "  background: transparent;\n" +
            "  border-bottom: 1px solid hsla(210,18%,87%,1);\n" +
            "  height: .25em;\n" +
            "  padding: 0;\n" +
            "  margin: 24px 0;\n" +
            "  background-color: #d0d7de;\n" +
            "  border: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body input {\n" +
            "  font: inherit;\n" +
            "  margin: 0;\n" +
            "  overflow: visible;\n" +
            "  font-family: inherit;\n" +
            "  font-size: inherit;\n" +
            "  line-height: inherit;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [type=button],\n" +
            ".markdown-body [type=reset],\n" +
            ".markdown-body [type=submit] {\n" +
            "  -webkit-appearance: button;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [type=button]::-moz-focus-inner,\n" +
            ".markdown-body [type=reset]::-moz-focus-inner,\n" +
            ".markdown-body [type=submit]::-moz-focus-inner {\n" +
            "  border-style: none;\n" +
            "  padding: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [type=button]:-moz-focusring,\n" +
            ".markdown-body [type=reset]:-moz-focusring,\n" +
            ".markdown-body [type=submit]:-moz-focusring {\n" +
            "  outline: 1px dotted ButtonText;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [type=checkbox],\n" +
            ".markdown-body [type=radio] {\n" +
            "  box-sizing: border-box;\n" +
            "  padding: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [type=number]::-webkit-inner-spin-button,\n" +
            ".markdown-body [type=number]::-webkit-outer-spin-button {\n" +
            "  height: auto;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [type=search] {\n" +
            "  -webkit-appearance: textfield;\n" +
            "  outline-offset: -2px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [type=search]::-webkit-search-cancel-button,\n" +
            ".markdown-body [type=search]::-webkit-search-decoration {\n" +
            "  -webkit-appearance: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ::-webkit-input-placeholder {\n" +
            "  color: inherit;\n" +
            "  opacity: .54;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ::-webkit-file-upload-button {\n" +
            "  -webkit-appearance: button;\n" +
            "  font: inherit;\n" +
            "}\n" +
            "\n" +
            ".markdown-body a:hover {\n" +
            "  text-decoration: underline;\n" +
            "}\n" +
            "\n" +
            ".markdown-body hr::before {\n" +
            "  display: table;\n" +
            "  content: \"\";\n" +
            "}\n" +
            "\n" +
            ".markdown-body hr::after {\n" +
            "  display: table;\n" +
            "  clear: both;\n" +
            "  content: \"\";\n" +
            "}\n" +
            "\n" +
            ".markdown-body table {\n" +
            "  border-spacing: 0;\n" +
            "  border-collapse: collapse;\n" +
            "  display: block;\n" +
            "  width: max-content;\n" +
            "  max-width: 100%;\n" +
            "  overflow: auto;\n" +
            "}\n" +
            "\n" +
            ".markdown-body td,\n" +
            ".markdown-body th {\n" +
            "  padding: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body details summary {\n" +
            "  cursor: pointer;\n" +
            "}\n" +
            "\n" +
            ".markdown-body details:not([open])>*:not(summary) {\n" +
            "  display: none !important;\n" +
            "}\n" +
            "\n" +
            ".markdown-body kbd {\n" +
            "  display: inline-block;\n" +
            "  padding: 3px 5px;\n" +
            "  font: 11px ui-monospace,SFMono-Regular,SF Mono,Menlo,Consolas,Liberation Mono,monospace;\n" +
            "  line-height: 10px;\n" +
            "  color: #24292f;\n" +
            "  vertical-align: middle;\n" +
            "  background-color: #f6f8fa;\n" +
            "  border: solid 1px rgba(175,184,193,0.2);\n" +
            "  border-bottom-color: rgba(175,184,193,0.2);\n" +
            "  border-radius: 6px;\n" +
            "  box-shadow: inset 0 -1px 0 rgba(175,184,193,0.2);\n" +
            "}\n" +
            "\n" +
            ".markdown-body h1,\n" +
            ".markdown-body h2,\n" +
            ".markdown-body h3,\n" +
            ".markdown-body h4,\n" +
            ".markdown-body h5,\n" +
            ".markdown-body h6 {\n" +
            "  margin-top: 24px;\n" +
            "  margin-bottom: 16px;\n" +
            "  font-weight: 600;\n" +
            "  line-height: 1.25;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h2 {\n" +
            "  font-weight: 600;\n" +
            "  padding-bottom: .3em;\n" +
            "  font-size: 1.5em;\n" +
            "  border-bottom: 1px solid hsla(210,18%,87%,1);\n" +
            "}\n" +
            "\n" +
            ".markdown-body h3 {\n" +
            "  font-weight: 600;\n" +
            "  font-size: 1.25em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h4 {\n" +
            "  font-weight: 600;\n" +
            "  font-size: 1em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h5 {\n" +
            "  font-weight: 600;\n" +
            "  font-size: .875em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h6 {\n" +
            "  font-weight: 600;\n" +
            "  font-size: .85em;\n" +
            "  color: #57606a;\n" +
            "}\n" +
            "\n" +
            ".markdown-body p {\n" +
            "  margin-top: 0;\n" +
            "  margin-bottom: 10px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body blockquote {\n" +
            "  margin: 0;\n" +
            "  padding: 0 1em;\n" +
            "  color: #57606a;\n" +
            "  border-left: .25em solid #d0d7de;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ul,\n" +
            ".markdown-body ol {\n" +
            "  margin-top: 0;\n" +
            "  margin-bottom: 0;\n" +
            "  padding-left: 2em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ol ol,\n" +
            ".markdown-body ul ol {\n" +
            "  list-style-type: lower-roman;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ul ul ol,\n" +
            ".markdown-body ul ol ol,\n" +
            ".markdown-body ol ul ol,\n" +
            ".markdown-body ol ol ol {\n" +
            "  list-style-type: lower-alpha;\n" +
            "}\n" +
            "\n" +
            ".markdown-body dd {\n" +
            "  margin-left: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body tt,\n" +
            ".markdown-body code {\n" +
            "  font-family: ui-monospace,SFMono-Regular,SF Mono,Menlo,Consolas,Liberation Mono,monospace;\n" +
            "  font-size: 12px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body pre {\n" +
            "  margin-top: 0;\n" +
            "  margin-bottom: 0;\n" +
            "  font-family: ui-monospace,SFMono-Regular,SF Mono,Menlo,Consolas,Liberation Mono,monospace;\n" +
            "  font-size: 12px;\n" +
            "  word-wrap: normal;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .octicon {\n" +
            "  display: inline-block;\n" +
            "  overflow: visible !important;\n" +
            "  vertical-align: text-bottom;\n" +
            "  fill: currentColor;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ::placeholder {\n" +
            "  color: #6e7781;\n" +
            "  opacity: 1;\n" +
            "}\n" +
            "\n" +
            ".markdown-body input::-webkit-outer-spin-button,\n" +
            ".markdown-body input::-webkit-inner-spin-button {\n" +
            "  margin: 0;\n" +
            "  -webkit-appearance: none;\n" +
            "  appearance: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-c {\n" +
            "  color: #6e7781;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-c1,\n" +
            ".markdown-body .pl-s .pl-v {\n" +
            "  color: #0550ae;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-e,\n" +
            ".markdown-body .pl-en {\n" +
            "  color: #8250df;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-smi,\n" +
            ".markdown-body .pl-s .pl-s1 {\n" +
            "  color: #24292f;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-ent {\n" +
            "  color: #116329;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-k {\n" +
            "  color: #cf222e;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-s,\n" +
            ".markdown-body .pl-pds,\n" +
            ".markdown-body .pl-s .pl-pse .pl-s1,\n" +
            ".markdown-body .pl-sr,\n" +
            ".markdown-body .pl-sr .pl-cce,\n" +
            ".markdown-body .pl-sr .pl-sre,\n" +
            ".markdown-body .pl-sr .pl-sra {\n" +
            "  color: #0a3069;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-v,\n" +
            ".markdown-body .pl-smw {\n" +
            "  color: #953800;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-bu {\n" +
            "  color: #82071e;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-ii {\n" +
            "  color: #f6f8fa;\n" +
            "  background-color: #82071e;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-c2 {\n" +
            "  color: #f6f8fa;\n" +
            "  background-color: #cf222e;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-sr .pl-cce {\n" +
            "  font-weight: bold;\n" +
            "  color: #116329;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-ml {\n" +
            "  color: #3b2300;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-mh,\n" +
            ".markdown-body .pl-mh .pl-en,\n" +
            ".markdown-body .pl-ms {\n" +
            "  font-weight: bold;\n" +
            "  color: #0550ae;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-mi {\n" +
            "  font-style: italic;\n" +
            "  color: #24292f;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-mb {\n" +
            "  font-weight: bold;\n" +
            "  color: #24292f;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-md {\n" +
            "  color: #82071e;\n" +
            "  background-color: #FFEBE9;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-mi1 {\n" +
            "  color: #116329;\n" +
            "  background-color: #dafbe1;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-mc {\n" +
            "  color: #953800;\n" +
            "  background-color: #ffd8b5;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-mi2 {\n" +
            "  color: #eaeef2;\n" +
            "  background-color: #0550ae;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-mdr {\n" +
            "  font-weight: bold;\n" +
            "  color: #8250df;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-ba {\n" +
            "  color: #57606a;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-sg {\n" +
            "  color: #8c959f;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .pl-corl {\n" +
            "  text-decoration: underline;\n" +
            "  color: #0a3069;\n" +
            "}\n" +
            "\n" +
            ".markdown-body [data-catalyst] {\n" +
            "  display: block;\n" +
            "}\n" +
            "\n" +
            ".markdown-body g-emoji {\n" +
            "  font-family: \"Apple Color Emoji\",\"Segoe UI Emoji\",\"Segoe UI Symbol\";\n" +
            "  font-size: 1em;\n" +
            "  font-style: normal !important;\n" +
            "  font-weight: 400;\n" +
            "  line-height: 1;\n" +
            "  vertical-align: -0.075em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body g-emoji img {\n" +
            "  width: 1em;\n" +
            "  height: 1em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body::before {\n" +
            "  display: table;\n" +
            "  content: \"\";\n" +
            "}\n" +
            "\n" +
            ".markdown-body::after {\n" +
            "  display: table;\n" +
            "  clear: both;\n" +
            "  content: \"\";\n" +
            "}\n" +
            "\n" +
            ".markdown-body>*:first-child {\n" +
            "  margin-top: 0 !important;\n" +
            "}\n" +
            "\n" +
            ".markdown-body>*:last-child {\n" +
            "  margin-bottom: 0 !important;\n" +
            "}\n" +
            "\n" +
            ".markdown-body a:not([href]) {\n" +
            "  color: inherit;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .absent {\n" +
            "  color: #cf222e;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .anchor {\n" +
            "  float: left;\n" +
            "  padding-right: 4px;\n" +
            "  margin-left: -20px;\n" +
            "  line-height: 1;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .anchor:focus {\n" +
            "  outline: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body p,\n" +
            ".markdown-body blockquote,\n" +
            ".markdown-body ul,\n" +
            ".markdown-body ol,\n" +
            ".markdown-body dl,\n" +
            ".markdown-body table,\n" +
            ".markdown-body pre,\n" +
            ".markdown-body details {\n" +
            "  margin-top: 0;\n" +
            "  margin-bottom: 16px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body blockquote>:first-child {\n" +
            "  margin-top: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body blockquote>:last-child {\n" +
            "  margin-bottom: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body sup>a::before {\n" +
            "  content: \"[\";\n" +
            "}\n" +
            "\n" +
            ".markdown-body sup>a::after {\n" +
            "  content: \"]\";\n" +
            "}\n" +
            "\n" +
            ".markdown-body h1 .octicon-link,\n" +
            ".markdown-body h2 .octicon-link,\n" +
            ".markdown-body h3 .octicon-link,\n" +
            ".markdown-body h4 .octicon-link,\n" +
            ".markdown-body h5 .octicon-link,\n" +
            ".markdown-body h6 .octicon-link {\n" +
            "  color: #24292f;\n" +
            "  vertical-align: middle;\n" +
            "  visibility: hidden;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h1:hover .anchor,\n" +
            ".markdown-body h2:hover .anchor,\n" +
            ".markdown-body h3:hover .anchor,\n" +
            ".markdown-body h4:hover .anchor,\n" +
            ".markdown-body h5:hover .anchor,\n" +
            ".markdown-body h6:hover .anchor {\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h1:hover .anchor .octicon-link,\n" +
            ".markdown-body h2:hover .anchor .octicon-link,\n" +
            ".markdown-body h3:hover .anchor .octicon-link,\n" +
            ".markdown-body h4:hover .anchor .octicon-link,\n" +
            ".markdown-body h5:hover .anchor .octicon-link,\n" +
            ".markdown-body h6:hover .anchor .octicon-link {\n" +
            "  visibility: visible;\n" +
            "}\n" +
            "\n" +
            ".markdown-body h1 tt,\n" +
            ".markdown-body h1 code,\n" +
            ".markdown-body h2 tt,\n" +
            ".markdown-body h2 code,\n" +
            ".markdown-body h3 tt,\n" +
            ".markdown-body h3 code,\n" +
            ".markdown-body h4 tt,\n" +
            ".markdown-body h4 code,\n" +
            ".markdown-body h5 tt,\n" +
            ".markdown-body h5 code,\n" +
            ".markdown-body h6 tt,\n" +
            ".markdown-body h6 code {\n" +
            "  padding: 0 .2em;\n" +
            "  font-size: inherit;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ul.no-list,\n" +
            ".markdown-body ol.no-list {\n" +
            "  padding: 0;\n" +
            "  list-style-type: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ol[type=\"1\"] {\n" +
            "  list-style-type: decimal;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ol[type=a] {\n" +
            "  list-style-type: lower-alpha;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ol[type=i] {\n" +
            "  list-style-type: lower-roman;\n" +
            "}\n" +
            "\n" +
            ".markdown-body div>ol:not([type]) {\n" +
            "  list-style-type: decimal;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ul ul,\n" +
            ".markdown-body ul ol,\n" +
            ".markdown-body ol ol,\n" +
            ".markdown-body ol ul {\n" +
            "  margin-top: 0;\n" +
            "  margin-bottom: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body li>p {\n" +
            "  margin-top: 16px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body li+li {\n" +
            "  margin-top: .25em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body dl {\n" +
            "  padding: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body dl dt {\n" +
            "  padding: 0;\n" +
            "  margin-top: 16px;\n" +
            "  font-size: 1em;\n" +
            "  font-style: italic;\n" +
            "  font-weight: 600;\n" +
            "}\n" +
            "\n" +
            ".markdown-body dl dd {\n" +
            "  padding: 0 16px;\n" +
            "  margin-bottom: 16px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body table th {\n" +
            "  font-weight: 600;\n" +
            "}\n" +
            "\n" +
            ".markdown-body table th,\n" +
            ".markdown-body table td {\n" +
            "  padding: 6px 13px;\n" +
            "  border: 1px solid #d0d7de;\n" +
            "}\n" +
            "\n" +
            ".markdown-body table tr {\n" +
            "  background-color: #ffffff;\n" +
            "  border-top: 1px solid hsla(210,18%,87%,1);\n" +
            "}\n" +
            "\n" +
            ".markdown-body table tr:nth-child(2n) {\n" +
            "  background-color: #f6f8fa;\n" +
            "}\n" +
            "\n" +
            ".markdown-body table img {\n" +
            "  background-color: transparent;\n" +
            "}\n" +
            "\n" +
            ".markdown-body img[align=right] {\n" +
            "  padding-left: 20px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body img[align=left] {\n" +
            "  padding-right: 20px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .emoji {\n" +
            "  max-width: none;\n" +
            "  vertical-align: text-top;\n" +
            "  background-color: transparent;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.frame {\n" +
            "  display: block;\n" +
            "  overflow: hidden;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.frame>span {\n" +
            "  display: block;\n" +
            "  float: left;\n" +
            "  width: auto;\n" +
            "  padding: 7px;\n" +
            "  margin: 13px 0 0;\n" +
            "  overflow: hidden;\n" +
            "  border: 1px solid #d0d7de;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.frame span img {\n" +
            "  display: block;\n" +
            "  float: left;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.frame span span {\n" +
            "  display: block;\n" +
            "  padding: 5px 0 0;\n" +
            "  clear: both;\n" +
            "  color: #24292f;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.align-center {\n" +
            "  display: block;\n" +
            "  overflow: hidden;\n" +
            "  clear: both;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.align-center>span {\n" +
            "  display: block;\n" +
            "  margin: 13px auto 0;\n" +
            "  overflow: hidden;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.align-center span img {\n" +
            "  margin: 0 auto;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.align-right {\n" +
            "  display: block;\n" +
            "  overflow: hidden;\n" +
            "  clear: both;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.align-right>span {\n" +
            "  display: block;\n" +
            "  margin: 13px 0 0;\n" +
            "  overflow: hidden;\n" +
            "  text-align: right;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.align-right span img {\n" +
            "  margin: 0;\n" +
            "  text-align: right;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.float-left {\n" +
            "  display: block;\n" +
            "  float: left;\n" +
            "  margin-right: 13px;\n" +
            "  overflow: hidden;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.float-left span {\n" +
            "  margin: 13px 0 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.float-right {\n" +
            "  display: block;\n" +
            "  float: right;\n" +
            "  margin-left: 13px;\n" +
            "  overflow: hidden;\n" +
            "}\n" +
            "\n" +
            ".markdown-body span.float-right>span {\n" +
            "  display: block;\n" +
            "  margin: 13px auto 0;\n" +
            "  overflow: hidden;\n" +
            "  text-align: right;\n" +
            "}\n" +
            "\n" +
            ".markdown-body code,\n" +
            ".markdown-body tt {\n" +
            "  padding: .2em .4em;\n" +
            "  margin: 0;\n" +
            "  font-size: 85%;\n" +
            "  background-color: rgba(175,184,193,0.2);\n" +
            "  border-radius: 6px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body code br,\n" +
            ".markdown-body tt br {\n" +
            "  display: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body del code {\n" +
            "  text-decoration: inherit;\n" +
            "}\n" +
            "\n" +
            ".markdown-body pre code {\n" +
            "  font-size: 100%;\n" +
            "}\n" +
            "\n" +
            ".markdown-body pre>code {\n" +
            "  padding: 0;\n" +
            "  margin: 0;\n" +
            "  word-break: normal;\n" +
            "  white-space: pre;\n" +
            "  background: transparent;\n" +
            "  border: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .highlight {\n" +
            "  margin-bottom: 16px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .highlight pre {\n" +
            "  margin-bottom: 0;\n" +
            "  word-break: normal;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .highlight pre,\n" +
            ".markdown-body pre {\n" +
            "  padding: 16px;\n" +
            "  overflow: auto;\n" +
            "  font-size: 85%;\n" +
            "  line-height: 1.45;\n" +
            "  background-color: #f6f8fa;\n" +
            "  border-radius: 6px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body pre code,\n" +
            ".markdown-body pre tt {\n" +
            "  display: inline;\n" +
            "  max-width: auto;\n" +
            "  padding: 0;\n" +
            "  margin: 0;\n" +
            "  overflow: visible;\n" +
            "  line-height: inherit;\n" +
            "  word-wrap: normal;\n" +
            "  background-color: transparent;\n" +
            "  border: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .csv-data td,\n" +
            ".markdown-body .csv-data th {\n" +
            "  padding: 5px;\n" +
            "  overflow: hidden;\n" +
            "  font-size: 12px;\n" +
            "  line-height: 1;\n" +
            "  text-align: left;\n" +
            "  white-space: nowrap;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .csv-data .blob-num {\n" +
            "  padding: 10px 8px 9px;\n" +
            "  text-align: right;\n" +
            "  background: #ffffff;\n" +
            "  border: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .csv-data tr {\n" +
            "  border-top: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .csv-data th {\n" +
            "  font-weight: 600;\n" +
            "  background: #f6f8fa;\n" +
            "  border-top: 0;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .footnotes {\n" +
            "  font-size: 12px;\n" +
            "  color: #57606a;\n" +
            "  border-top: 1px solid #d0d7de;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .footnotes ol {\n" +
            "  padding-left: 16px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .footnotes li {\n" +
            "  position: relative;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .footnotes li:target::before {\n" +
            "  position: absolute;\n" +
            "  top: -8px;\n" +
            "  right: -8px;\n" +
            "  bottom: -8px;\n" +
            "  left: -24px;\n" +
            "  pointer-events: none;\n" +
            "  content: \"\";\n" +
            "  border: 2px solid #0969da;\n" +
            "  border-radius: 6px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .footnotes li:target {\n" +
            "  color: #24292f;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .footnotes .data-footnote-backref g-emoji {\n" +
            "  font-family: monospace;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .task-list-item {\n" +
            "  list-style-type: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .task-list-item label {\n" +
            "  font-weight: 400;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .task-list-item.enabled label {\n" +
            "  cursor: pointer;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .task-list-item+.task-list-item {\n" +
            "  margin-top: 3px;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .task-list-item .handle {\n" +
            "  display: none;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .task-list-item-checkbox {\n" +
            "  margin: 0 .2em .25em -1.6em;\n" +
            "  vertical-align: middle;\n" +
            "}\n" +
            "\n" +
            ".markdown-body .contains-task-list:dir(rtl) .task-list-item-checkbox {\n" +
            "  margin: 0 -1.6em .25em .2em;\n" +
            "}\n" +
            "\n" +
            ".markdown-body ::-webkit-calendar-picker-indicator {\n" +
            "  filter: invert(50%);\n" +
            "}";
}
