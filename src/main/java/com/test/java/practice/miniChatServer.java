package com.test.java.practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JoaMoreno
 */
public class miniChatServer extends Observable implements Runnable{

    private int PORT;

    public Server(int PORT){
        this.PORT = PORT;
    }

    @Override
    public void run() {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;

        try {
            servidor = new ServerSocket(PORT);
            System.out.println("Server Started");

            // Siempre estara escuchando peticiones
            while(true){
                // Espero la conexion con el Cliente
                sc = servidor.accept();
                
                System.out.println("Client Connected");
                in = new DataInputStream(sc.getInputStream());

                // Leo el mensaje del cliente
                String mensaje = in.readUTF();

                System.out.println(mensaje);

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                // Cierro el socket
                sc.close();

                System.out.println("Client Desconected");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(miniChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}