package com.test.java.practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JoaMoreno
 */
public class miniChatClient implements Runnable{

    private int PORT;
    private String mensaje;

    public Cliente(int PORT, String mensaje){
        this.PORT = PORT;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        
        final String HOST = "127.0.0.1";

        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, PORT);
            
            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF(mensaje);

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(miniChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
