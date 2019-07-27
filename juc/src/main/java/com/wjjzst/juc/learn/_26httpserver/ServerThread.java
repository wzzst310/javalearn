package com.wjjzst.juc.learn._26httpserver;

import java.io.*;
import java.net.Socket;
import java.util.Date;

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

    public ServerThread(Socket client) {
        this.client = client;
        init();
    }

    private void init() {
        try {
            ins = client.getInputStream();
            out = client.getOutputStream();
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

    private void go() throws IOException {
        System.out.println("客户端连接成功");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        String line = reader.readLine();
        // 第一行请求行
        System.out.println(line);
        // 给用户响应
        PrintWriter pw = new PrintWriter(out);
        InputStream i = new FileInputStream("C:/Users/Administrator/IdeaProjects/javalearn/juc/src/main/resources/index1.html");
        BufferedReader fr = new BufferedReader(new InputStreamReader(i));
        pw.println("HTTP/1.1 200 OK");
        pw.println("Content-Type: text/html;charset=utf-8");
        pw.println("Content-Length: " + i.available());
        pw.println("Server: hello");
        pw.println("Date: " + new Date());
        pw.println("");
        pw.flush();
        String c = null;
        while ((c = fr.readLine()) != null) {
            pw.print(c);
        }
        pw.flush();
        pw.close();
        fr.close();
        reader.close();
        client.close();

    }
}
