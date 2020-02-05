package com.test.java.practice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author JoaMoreno
 */
public class ClienteUDP {
    public static void main(String[] args) {

        final int SERVER_PORT = 5000;
        byte[] buffer = new byte[1024];

        try {

            InetAddress serverDirection = InetAddress.getByName("localhost");

            var socketUDP = new DatagramSocket();

            String message = "Hello from Client!";
            buffer = message.getBytes();

            DatagramPacket question = new DatagramPacket(buffer, buffer.length, serverDirection, SERVER_PORT);

            System.out.println(" * Datagram sent");
            socketUDP.send(question);

            DatagramPacket request = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(request);
            System.out.println(" * Request received");

            message = new String(request.getData());
            System.out.println(message);

            socketUDP.close();

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}