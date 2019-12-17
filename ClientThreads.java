package com.company.MySolution;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientThreads implements Runnable {
    final DataInputStream input;
    final DataOutputStream output;
    Socket socket;

    public ClientThreads(DataInputStream input, DataOutputStream output, Socket socket) {
        this.input = input;
        this.output = output;
        this.socket = socket;
    }

    @Override
    public void run() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String receivedQuestionNumber;
        String response;
        while (true) {
            try {
                output.writeUTF("Please enter one of questions number...\n" +
                        "1: What the date is today?\n" +
                        "2: What's the time?\n" +
                        "3: Exit");
                receivedQuestionNumber = input.readUTF();
                if (receivedQuestionNumber.equals("3")) {
                    this.socket.close();
                    System.out.println("The socket is closed successfully.");
                    break;
                }else if (receivedQuestionNumber.equals("1")) {
                    response = dateFormat.format(date);
                    output.writeUTF(response);
                }else if (receivedQuestionNumber.equals("2")) {
                    response = timeFormat.format(date);
                    output.writeUTF(response);
                }else {
                    output.writeUTF("Invalid Number!");
                    continue;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
