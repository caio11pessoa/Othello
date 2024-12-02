package Controllers.Game;
import java.io.*;

import java.net.*;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

import Views.Board.BoardView;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ServerGameController {
    private final List<PrintWriter> clientWriters = new ArrayList<>();
    public void go(BoardView tabuleiro){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        boolean hasInitGame = false;
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(4243));
            while (serverSocketChannel.isOpen()) {
                SocketChannel clientSocket = serverSocketChannel.accept();
                if (!hasInitGame) {
                    tabuleiro.comecarJogo();
                    hasInitGame = true;
                }
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, UTF_8));
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(clientSocket));
                System.out.println("Got a Game connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                    System.out.println("Play " + message);
                    tellEveryone(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
