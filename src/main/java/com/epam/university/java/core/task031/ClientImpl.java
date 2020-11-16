package com.epam.university.java.core.task031;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientImpl implements Client {
    private Socket serverSocket;
    private BufferedWriter out;
    private boolean flag;

    @Override
    public void sendMessage(String message) {
        if (message == null) {
            flag = true;
        }

        try {
            out.write(message + "\n");
            out.flush();
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            Thread.sleep(100);
            serverSocket = new Socket(InetAddress.getLocalHost(), 8070);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    serverSocket.getOutputStream());
            out = new BufferedWriter(outputStreamWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            serverSocket.close();
            out.close();
            if (flag) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
