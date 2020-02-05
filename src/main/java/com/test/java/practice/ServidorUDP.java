package com.test.java.practice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author JoaMoreno
 */
public class ServidorUDP {

    public static void main(String[] args) {

        final int PORT = 5000;
        byte[] buffer = new byte[1024];

        try {

            System.out.println(" * Server UDP started on PORT: " + PORT);
            DatagramSocket socketUDP = new DatagramSocket(PORT);

            while (true) {

                DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                socketUDP.receive(request);
                System.out.println(" * Request received");

                String message = new String(request.getData());
                System.out.println(message);

                int ClientPORT = request.getPort();
                InetAddress direction = request.getAddress();

                message = "¡Hello from Server!";
                buffer = message.getBytes();

                DatagramPacket response = new DatagramPacket(buffer, buffer.length, direction, ClientPORT);

                socketUDP.send(response);
                System.out.println(" * Response sent");
            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}