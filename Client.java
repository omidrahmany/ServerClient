package com.company.MySolution;


import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 8585);

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println(input.readUTF());
                String question = scanner.nextLine();
                output.writeUTF(question);
                if (question.equals("3")) {
                    System.out.println("The Socket is closed.");
                    socket.close();
                    break;
                }
                String answer = input.readUTF();
                System.out.println(answer);
            }
            scanner.close();
            input.close();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
