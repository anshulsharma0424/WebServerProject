//package SingleThreaded; // Omitting this

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8010; // Ensure client connects on the same port
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port: " + port);

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to " + clientSocket.getRemoteSocketAddress());

                handleClient(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        try (PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // Read message from Client
            String message = fromClient.readLine();
            System.out.println("Received from Client: " + message);

            // Respond to Client
            toClient.println("Hello from Server!");
        }

        clientSocket.close(); // Close client connection
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
