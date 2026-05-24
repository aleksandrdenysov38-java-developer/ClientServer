package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static final int PORT = 8080;

    private static final List<ClientInfo> activeClients = new ArrayList<>();

    private static int clientCounter = 1;

    public static void main(String[] args) {

        System.out.println("[SERVER] Server is started...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {

                Socket socket = serverSocket.accept();



                String clientName = "client-" + clientCounter++;

                ClientInfo clientInfo = new ClientInfo(
                        clientName,
                        LocalDateTime.now(),
                        socket
                );



                activeClients.add(clientInfo);

                System.out.println("[SERVER] " + clientName + " successfully connected");


                ClientHandler clientHandler =
                        new ClientHandler(socket, clientInfo, activeClients);

                Thread thread = new Thread(clientHandler);

                thread.start();
            }

        } catch (IOException e) {
            System.out.println("[SERVER] error: " + e.getMessage());
        }
    }
}
