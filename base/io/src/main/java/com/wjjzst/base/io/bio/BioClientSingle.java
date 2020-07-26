package com.wjjzst.base.io.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: Wjj
 * @Date: 2020/7/26 10:22 上午
 * @desc:
 */
public class BioClientSingle {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket(BioServerConstants.IP, BioServerConstants.PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            String inputLine = null;
            while (true) {
                inputLine = scanner.nextLine(); // 读取数据
                if ("end".equals(inputLine) || "quit".equals(inputLine)) {
                    break;
                } else {
                    System.out.println("Client : " + inputLine);
                    // 写数据
                    out.writeUTF(inputLine);
                    // 读数据
                    String getLine = in.readUTF();
                    System.out.println("Server : " + getLine);
                }
            }
        } catch (Exception e) {
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }

    }
}
