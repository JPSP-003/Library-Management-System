import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class OnlineForumClient extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private PrintWriter writer;

    public OnlineForumClient() {
        setTitle("Online Forum Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        messageField = new JTextField();
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        add(messageField, BorderLayout.SOUTH);

        try {
            Socket socket = new Socket("localhost", 12346);
            writer = new PrintWriter(socket.getOutputStream(), true);

            Thread serverThread = new Thread(new ServerHandler(socket));
            serverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = messageField.getText();
        writer.println(message);
        messageField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OnlineForumClient().setVisible(true);
        });
    }

    class ServerHandler implements Runnable {
        private Socket socket;
        private BufferedReader reader;

        public ServerHandler(Socket socket) {
            try {
                this.socket = socket;
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String serverMessage;
                while ((serverMessage = reader.readLine()) != null) {
                    chatArea.append("Received message from server: " + serverMessage + "\n");
                }

                // Server disconnected
                chatArea.append("Server disconnected\n");
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}