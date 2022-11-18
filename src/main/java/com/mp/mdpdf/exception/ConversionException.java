package com.mp.mdpdf.exception;

/**
 * @author luojx
 * @date 2022/11/18 11:29
 */
public class ConversionException extends Exception{
    public static final ConversionException MARKDOWN_TO_HTML_EXCEPTION = new ConversionException("An error occurred while trying to convert from markdown to html");
    public static final ConversionException HTML_TO_PDF_EXCEPTION = new ConversionException("An error occurred while trying to convert from html to pdf");

    public ConversionException() {
    }

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(Throwable cause) {
        super(cause);
    }

    public ConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
