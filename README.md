# markdown2pdf
convert markdown to pdf

### usage
run Application.main, change your markdown file path and choose your css path

### change your font 
AsianFontProvider.class, here you can chage your font

`BaseFont font = BaseFont.createFont("font/simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);`

### add water marker(optional)

`HtmlPdfConverter.writeStringToOutputStreamAsPDF(html, outputStream, new WaterMarkerGenerator("1e587116-067d-4c9c-a9e3-3dd5c102f77b"));`

### result
![image](https://user-images.githubusercontent.com/59504723/202838509-30f2db68-a49d-4a19-90a0-4b5cabf81813.png)
