package me.qnex.rat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

    protected Socket socket;

    public ClientThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
        try {
            var inp = socket.getInputStream();
            var brinp = new BufferedReader(new InputStreamReader(inp), 2);
            var out = new DataOutputStream(socket.getOutputStream());

            while (socket.isConnected()) {
                var line = brinp.readLine();
                if (line == null) {
                    break;
                }
                System.out.println("Received: " + line);
                out.writeBytes("Received: " + line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
