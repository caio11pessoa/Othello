package Controllers.Chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

import Models.Player;
import Views.Board.BoardView;
import Views.Chat.ChatView;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ServerChatController {
    private final List<PrintWriter> clientWriters = new ArrayList<>();

    public void go(BoardView tabuleiro) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(4242));
            int a = 0;
            while (serverSocketChannel.isOpen()) {
                SocketChannel clientSocket = serverSocketChannel.accept();
                if (a == 0) {
                    ChatView chatView = new ChatView(Player.Branca);
                    chatView.go();
                    a++;
                    tabuleiro.setEnable();
                }
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, UTF_8));
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(clientSocket));
                System.out.println("Got a chat connection");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void tellEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        SocketChannel socket;

        public ClientHandler(SocketChannel clientSocket) {
            socket = clientSocket;
            reader = new BufferedReader(Channels.newReader(socket, UTF_8));
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}