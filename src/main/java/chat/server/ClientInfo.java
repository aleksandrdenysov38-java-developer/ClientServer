package chat.server;

import java.net.Socket;
import java.time.LocalDateTime;

public class ClientInfo {

    private final String clientName;
    private final LocalDateTime connectionTime;
    private final Socket socket;

    public ClientInfo(String clientName, LocalDateTime connectionTime, Socket socket) {
        this.clientName = clientName;
        this.connectionTime = connectionTime;
        this.socket = socket;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }

    public Socket getSocket() {
        return socket;
    }
}
