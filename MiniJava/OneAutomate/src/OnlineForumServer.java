import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class OnlineForumServer extends JFrame {
    private JTextArea chatArea;
    private ServerSocket serverSocket;

    public OnlineForumServer() {
        setTitle("Online Forum Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        try {
            serverSocket = new ServerSocket(12346);
            chatArea.append("Server started. Listening on port 1234...\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                chatArea.append("New client connected: " + clientSocket + "\n");

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OnlineForumServer().setVisible(true);
        });
    }

    class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            try {
                clientSocket = socket;
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String clientMessage;
                while ((clientMessage = reader.readLine()) != null) {
                    chatArea.append("Received message from client: " + clientMessage + "\n");

                    // Process the client message here

                    // Broadcast the message to all connected clients
                    // You can maintain a list of connected clients and iterate through them
                    // sending the message to each client's PrintWriter

                    // Example:
                    // for (ClientHandler client : connectedClients) {
                    //     client.writer.println(clientMessage);
                    // }
                }

                // Client disconnected
                chatArea.append("Client disconnected: " + clientSocket + "\n");
                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}