package com.epam.university.java.core.task031;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientImpl implements Client {
    private Socket serverSocket;
    private BufferedWriter out;

    @Override
    public void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            serverSocket = new Socket("localhost", 6666);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
