package Controllers.Chat;

import javax.swing.*;

import Models.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ClientChatController {
    public JTextArea incoming;
    public JTextField outgoing;
    private BufferedReader reader;
    private PrintWriter writer;
    public Player player;

    public ClientChatController(Player player) {
        this.player = player;
        setUpNetworking();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 4242);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);
            reader = new BufferedReader(Channels.newReader(socketChannel, UTF_8));
            writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));
            System.out.println("Networking Chat established.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(Player player) {
        writer.println(player + ": " + outgoing.getText());
        writer.flush();
        outgoing.setText("");
        outgoing.requestFocus();
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    incoming.append(message + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
