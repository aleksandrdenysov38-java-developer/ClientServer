package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final ClientInfo clientInfo;
    private final List<ClientInfo> activeClients;

    public ClientHandler(Socket socket,
                         ClientInfo clientInfo,
                         List<ClientInfo> activeClients) {

        this.socket = socket;
        this.clientInfo = clientInfo;
        this.activeClients = activeClients;
    }

    @Override
    public void run() {

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(socket.getInputStream())
                        )
        ) {

            String message;

            while ((message = reader.readLine()) != null) {

                System.out.println(
                        "[" + clientInfo.getClientName() + "]: " + message
                );

                if (message.equalsIgnoreCase("exit")) {

                    System.out.println(
                            "[SERVER] "
                                    + clientInfo.getClientName()
                                    + " disconnected"
                    );

                    activeClients.remove(clientInfo);

                    socket.close();

                    break;
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "[SERVER] Client error "
                            + clientInfo.getClientName()
            );
        }
    }
}
