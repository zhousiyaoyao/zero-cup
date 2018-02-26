package com.lingbei.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 郭 小洋 on 2016/11/18.
 */
public class LogUtil {
    public static String logName = ".txt";
    public static PrintStream printStream = null;
    public static int preHour = 0;

    public static void openNewLog() throws IOException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        preHour = hour;
        String testServerLog = "server_log";
        String pathYear = "C:/" + File.separator + testServerLog + File.separator + year;
        String pathMonth = "C:/" + File.separator + testServerLog + File.separator + year + File.separator + month;
        String filePath = pathMonth + File.separator + year + "-" + month + "-" + day + "_" + hour + logName;
        File file = new File(pathYear);
        if (!file.exists()) {
            file.mkdir();
            new File(pathMonth).mkdirs();
            new File(filePath).createNewFile();
        } else if (!new File(pathMonth).exists()) {
            new File(pathMonth).mkdirs();
            new File(filePath).createNewFile();
        } else if (!new File(filePath).exists()) {
            new File(filePath).createNewFile();
        }
        if (printStream != null) {
            printStream.close();
            printStream = null;
        }
        printStream = new PrintStream(new FileOutputStream(new File(filePath), true));
    }

    public static boolean log(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            if (calendar.get(Calendar.HOUR_OF_DAY) != preHour) {
                openNewLog();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n");
            sb.append("*************************************************************\r\n");
            sb.append(TimeStamp2Date(System.currentTimeMillis()));
            sb.append(str);
            sb.append("\r\n");
            String s = sb.toString();
            printStream.print(s);
            System.out.println(s);
        } catch (Exception e) {
        }
        return true;
    }

    public static String TimeStamp2Date(long timestampString) {
        String date = "";
        if (timestampString == 0) {
            date = "";
        } else {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestampString));
        }
        return date;
    }
}