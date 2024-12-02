package Controllers.Home;

import java.awt.*;

import javax.swing.JFrame;

import Controllers.Chat.ServerChatController;
import Controllers.Game.ServerGameController;
import Models.Player;
import Views.Board.BoardView;
import Views.Chat.ChatView;
import Views.OpenServer.OpenServerView;

public class HomeController {
    public void openServer(Frame frame) {
        frame.dispose();
        new OpenServerView().go();
        BoardView tabuleiro = new BoardView(Player.Branca);
        // tabuleiro.comecarJogo();

        // Servidor do chat
        new Thread(() -> {
            new ServerChatController().go(tabuleiro);
        }).start();
        
        // Servidor do Jogo
        new Thread(() -> {
            new ServerGameController().go(tabuleiro);
        }).start();
    }

    public void openClient(JFrame frame) {
        frame.dispose();
        new Thread(() -> {
            ChatView chatView = new ChatView(Player.Preta);
            chatView.go();
        }).start();

        BoardView tabuleiro = new BoardView(Player.Preta);
        tabuleiro.comecarJogo();
    }
}
