package Views.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Controllers.Board.BoardController;
import Controllers.Game.ClientGameController;
import Models.Player;

public class BoardView {
    public Boolean isEnable = true;
    JPanel painelTabuleiro;
    JLabel turnoLabel;
    ClientGameController clientGameController;

    public static void main(String[] args) {
        new BoardView(Player.Branca).comecarJogo();
        new BoardView(Player.Preta).comecarJogo();
    }
    public BoardView(Player player){
        controller = new BoardController(player);
    }

    public BoardController controller;

    public void updateButtons() {
        painelTabuleiro.removeAll(); // Remove todos os componentes antigos

        JButton[][] tabuleiro = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int localI = i;
                int localJ = j;
                JButton botao = new JButton();
                switch (controller.tabuleiro.tabCelulas[i][j]) {
                    case VAZIO:
                        botao.setBackground(Color.GREEN);
                        break;
                    case CLICAVEL:
                        botao.setBackground(Color.GRAY);
                        botao.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                clientGameController.outgoing = String.valueOf(localI) + " " +String.valueOf(localJ);
                                clientGameController.sendMessage();
                            }
                        });
                        botao.setEnabled(isEnable);;
                        break;
                    case BRANCA:
                        botao.setBackground(Color.WHITE);
                        break;
                    case PRETA:
                        botao.setBackground(Color.BLACK);
                        break;
                    default:
                        botao.setBackground(Color.GREEN);
                        break;
                }

                botao.setOpaque(true);
                botao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tabuleiro[i][j] = botao;
                painelTabuleiro.add(botao); // Adiciona o botão ao painel
            }
        }
        setarTurno();

        // Revalida e repinta o painel após adicionar os novos botões
        painelTabuleiro.revalidate();
        painelTabuleiro.repaint();
    }

    public void setarTurno() {
        turnoLabel.setText("Turno: " + controller.turno);
    }

    public void comecarJogo() {
        clientGameController = new ClientGameController(this);
        JFrame frame = new JFrame("Othello");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER)); // Layout alinhado ao centro
        painelSuperior.setPreferredSize(new Dimension(frame.getWidth(), 50));
        painelSuperior.setBackground(Color.LIGHT_GRAY);

        JLabel playerLabel = new JLabel("Player: " + controller.player);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        painelSuperior.add(playerLabel);

        turnoLabel = new JLabel("Turno: " + controller.turno);
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        painelSuperior.add(turnoLabel);

        painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(8, 8));
        painelTabuleiro.setBackground(Color.GREEN);

        updateButtons();

        frame.add(painelSuperior, BorderLayout.NORTH); // Painel superior
        frame.add(painelTabuleiro, BorderLayout.CENTER); // Tabuleiro no centro

        frame.setVisible(true);
    }
    public void setEnable() {
        System.err.println("setando para enable");
        isEnable = true;
        updateButtons();
    }
    public void setDisable() {
        System.err.println("setando para disabled");
        isEnable = false;
        updateButtons();
    }
}
