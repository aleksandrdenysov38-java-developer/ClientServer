package chat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {

        try (
                Socket socket = new Socket(HOST, PORT);

                PrintWriter writer =
                        new PrintWriter(socket.getOutputStream(), true);

                Scanner scanner = new Scanner(System.in)
        ) {

            System.out.println("[CLIENT] Connected to server");
            System.out.println("Enter message:");
            System.out.println("For exit type: exit");

            while (true) {

                String message = scanner.nextLine();

                writer.println(message);

                if (message.equalsIgnoreCase("exit")) {

                    System.out.println("[CLIENT] Disconnection...");
                    break;
                }
            }

        } catch (IOException e) {

            System.out.println("[CLIENT] error: " + e.getMessage());
        }
    }
}
