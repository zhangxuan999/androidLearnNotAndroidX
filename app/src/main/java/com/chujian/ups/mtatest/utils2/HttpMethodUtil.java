package com.chujian.ups.mtatest.utils2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpMethodUtil {

    public static String doPost(String url,String params){
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        String content = null;
        StringBuffer sbf = new StringBuffer();
        try{
            URL u = new URL(url);
            conn = (HttpURLConnection)u.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setReadTimeout(50000);
            conn.setConnectTimeout(60000);
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("content-Type","application/json");

            writer = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
            writer.print(params);
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((content = reader.readLine())!=null){
                sbf.append(content);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(writer!=null){
                writer.close();
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            conn.disconnect();
        }
        return sbf.toString();

    }

    public static String doGet(String url){
        StringBuffer sbf = new StringBuffer();
        HttpURLConnection conn = null;
        BufferedReader br = null;
        String content = null;
        try{
            URL u = new URL(url);
            conn = (HttpURLConnection)u.openConnection();
            conn.setReadTimeout(50000);
            conn.setConnectTimeout(60000);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            if(conn.getResponseCode()==200){
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                while((content=br.readLine())!=null){
                    sbf.append(content);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            conn.disconnect();
        }
        return sbf.toString();
    }

}
