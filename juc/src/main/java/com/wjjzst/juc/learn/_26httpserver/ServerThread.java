package com.wjjzst.juc.learn._26httpserver;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wjj
 * @Date: 2019/7/25 8:30
 * @desc:
 */
public class ServerThread implements Runnable {
    private Socket client;
    private InputStream ins;
    private OutputStream out;
    private PrintWriter pw;
    private BufferedReader br;

    private static Map<String, String> contentMap = new HashMap<>();

    static {
        contentMap.put("html", "text/html;charset=utf-8");
        contentMap.put("jpg", "img/jpeg");
        contentMap.put("png", "img/jpeg");
        contentMap.put("ico", "image/x-icon");
    }

    public ServerThread(Socket client) {
        this.client = client;
        init();
    }

    private void init() {
        try {
            ins = client.getInputStream();
            out = client.getOutputStream();
            String osName = System.getProperty("os.name");
            if (osName.startsWith("Mac OS")) {
                webroot = "/Users/wjj/IdeaProjects/javalearn/juc/src/main/resources";
            } else if (osName.startsWith("Windows")) {
                webroot = "C:/Users/Administrator/IdeaProjects/javalearn/juc/src/main/resources";
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            go();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String webroot = "";


    private void go() throws IOException {
        System.out.println("客户端连接成功");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        String line = reader.readLine();
        line = line.split(" ")[1];//.replace("/", "\\");
        // 第一行请求行
        System.out.println(line);
        if ("/".equals(line)) {
            line += "index.html";
        }
        // 给用户响应
        PrintWriter pw = new PrintWriter(out);
        InputStream i = new FileInputStream(webroot + line);
        // BufferedReader fr = new BufferedReader(new InputStreamReader(i));
        pw.println("HTTP/1.1 200 OK");
        pw.println("Date: " + new Date());
        // pw.println("Content-Encoding: gzip");
        String substring = line.substring(line.lastIndexOf("."));
        pw.println("Content-Type: " + contentMap.get(line.substring(line.lastIndexOf(".") + 1)));
        pw.println("Content-Length: " + i.available());
        pw.println("Server: hello");
        pw.println("");
        pw.flush();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = i.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }

        /*String c = null;
        while ((c = fr.readLine()) != null) {
            pw.print(c);
        }*/
        pw.flush();
        i.close();
        pw.close();
        reader.close();
        client.close();
    }
}
