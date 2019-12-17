package com.company.MySolution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Server is waiting on port 8585...");
        ServerSocket server = new ServerSocket(8585);

        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                System.out.println( socket + ", is connected.");
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                Thread thread = new Thread(new ClientThreads(input, output, socket));
                thread.start();
            } catch (IOException e) {
                socket.close();
                e.printStackTrace();
            }
        }

    }
}
