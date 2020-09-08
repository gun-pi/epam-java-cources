package com.epam.university.java.core.task031;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerImpl implements Server {
    private boolean currentThreadIsWorking = true;
    private ServerSocket serverSocket;
    private final LinkedList<String> strings = new LinkedList<>();

    @Override
    public String readMessage() {
        if (strings.isEmpty()) {
            return "";
        } else {
            return strings.removeLast();
        }
    }

    @Override
    public void start() {
        Thread thread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(6666);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> {
                        try (BufferedReader in = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()))) {
                            while (currentThreadIsWorking) {
                                if (in.ready()) {
                                    strings.add(in.readLine());
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Override
    public void stop() {
        currentThreadIsWorking = false;
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
