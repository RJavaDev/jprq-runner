package org.example;

import org.example.controller.AppController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 1999;


        try {
            ServerSocket serverSocket;
            serverSocket = new ServerSocket(port);
            Socket socket;
            System.out.println("Server started on port " + port);

            AppController.runCommand();

            socket = serverSocket.accept();
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}