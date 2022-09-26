package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started!");
            while (true) {
                try (Socket clientSocet = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocet.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocet.getInputStream()));) {
                    System.out.println("New connection accepted!");
                    final String name = in.readLine();
                    out.println(String.format("Hi %s. your port is %d", name, clientSocet.getPort()));
                    out.println("Вы установили соединение с адреса " + clientSocet.getInetAddress());
                    out.println("Вы установили соединение с порта " + clientSocet.getPort());
                    out.println("Вы установили соединение на запушенном сервере с портом " + clientSocet.getLocalPort());
                    System.out.println(in.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}