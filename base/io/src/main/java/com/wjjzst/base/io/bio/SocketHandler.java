package com.wjjzst.base.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * @Author: Wjj
 * @Date: 2020/7/26 9:51 上午
 * @desc:
 */
public class SocketHandler implements Runnable {
    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            /* 旧版
            in = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) > 0) { // 阻塞
                 System.out.println("input is:" + new String(buffer, 0, length));
                 out = socket.getOutputStream();
                 out.write("success".getBytes());
                 System.out.println("end");
                 // return;
            }*/
            while (true) {
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                String readLine = dataIn.readUTF();
                if (readLine == null) {
                    continue;
                }
                if ("end".equals(readLine) || "quit".equals(readLine)) {
                    break;
                }
                System.out.println("Client : " + readLine);
                dataOut.writeUTF(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
