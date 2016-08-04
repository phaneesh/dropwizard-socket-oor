package io.dropwizard.socket.oor.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author phaneesh
 */
public class OorSocketServer implements Runnable {

    private ServerSocket serverSocket;

    private boolean running;

    public OorSocketServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(0);
        this.running = true;
    }

    public void run() {
        while(running) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                DataOutputStream out =
                        new DataOutputStream(clientSocket.getOutputStream());
                out.writeUTF("OK\n");
                out.close();
            } catch (IOException e) {
                throw new RuntimeException("Error accepting client connection", e);
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error stopping server", e);
        }
    }

    public void stop() {
        this.running = false;
    }

}
