package Controllers.Game;

import Views.Board.BoardView;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.*;

import Controllers.Board.BoardController;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ClientGameController {
    public String incoming;
    public String outgoing;
    private BufferedReader reader;
    private PrintWriter writer;
    BoardView tabuleiroView;

    public ClientGameController(BoardView tabuleiroView) {
        this.tabuleiroView = tabuleiroView;
        setUpNetworking();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 4243);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);
            reader = new BufferedReader(Channels.newReader(socketChannel, UTF_8));
            writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));
            System.out.println("Networking game established.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage() {
        writer.println(outgoing);
        writer.flush();
        outgoing = "";
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("play " + message);
                    incoming = message;
                    String[] messageSplit = message.split("[,\\.\\s]");
                    System.out.println("messageSplit: "+messageSplit[0]);
                    int tabI = Integer.parseInt(messageSplit[0]);
                    int tabJ = Integer.parseInt(messageSplit[1]);
                    tabuleiroView.controller.escolherCelula(tabI, tabJ);
                    tabuleiroView.updateButtons();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}