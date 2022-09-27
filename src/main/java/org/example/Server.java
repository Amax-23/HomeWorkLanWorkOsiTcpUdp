package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8989;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {// стартуем сервер один(!) раз
            System.out.println("Server started!");
            System.out.println("Waiting connection on server port:" + PORT);
            String city = "";
            while (true) {
                try (Socket clientSocet = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocet.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocet.getInputStream()));) {
                    if (city.length() == 0) {
                        out.println("???");
                        city = in.readLine();
                        out.println("OK");
                        System.out.println(city);
                    } else {
                        out.println("Последний введенный город - " + city + ", Вам на - " + city.charAt(city.length() - 1));
                        String city2 = in.readLine();
                        if (city.charAt(city.length() - 1) == city2.charAt(0)) {
                            out.println("OK!");
                            city = city2;
                        } else {
                            out.println("NOT OK!");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}