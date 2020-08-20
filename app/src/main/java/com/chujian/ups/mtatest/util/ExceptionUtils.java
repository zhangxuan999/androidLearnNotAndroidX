package com.chujian.ups.mtatest.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class ExceptionUtils {

    public static String printStackTrace(Throwable throwable) {

        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        throwable.printStackTrace(printWriter);

        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String result = info.toString();
        printWriter.close();
        return result;
    }
}
