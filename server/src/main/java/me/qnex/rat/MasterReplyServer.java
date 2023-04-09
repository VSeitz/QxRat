package me.qnex.rat;

import java.io.IOException;
import java.net.ServerSocket;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class MasterReplyServer {

    private static final int PORT = 1978;

    void onStartup(@Observes StartupEvent ev) {
        System.out.println("The Server is starting...");

        try (var serverSocket = new ServerSocket(PORT)) {
            while (!serverSocket.isClosed()) {
                try {
                    new ClientThread(serverSocket.accept()).start();
                } catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
                // new thread for a client
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }

    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("The Server is stopping...");
    }

}
