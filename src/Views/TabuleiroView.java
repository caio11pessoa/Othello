package Views;

import javax.swing.*;
import java.awt.*;

import Controllers.TabuleiroController;
import Models.Celula;

public class TabuleiroView {
    public static void main(String[] args) {
        comecarJogo();
    }

    static TabuleiroController controller = new TabuleiroController();

    public static void comecarJogo() {
        JFrame frame = new JFrame("Othello");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER)); // Layout alinhado ao centro
        painelSuperior.setPreferredSize(new Dimension(frame.getWidth(), 50));
        painelSuperior.setBackground(Color.LIGHT_GRAY);

        JLabel turnoLabel = new JLabel("Turno: Pretas");
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        painelSuperior.add(turnoLabel);

        JPanel painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(8, 8));
        painelTabuleiro.setBackground(Color.GREEN);

        JButton[][] tabuleiro = new JButton[8][8];
        Celula[][] tabCelulas = controller.tabuleiro.tabCelulas;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton botao = new JButton();
                switch (tabCelulas[i][j]) {
                    case VAZIO:
                        botao.setBackground(Color.GREEN);
                        break;
                    case CLICAVEL:
                        botao.setBackground(Color.GRAY);

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
                painelTabuleiro.add(botao);
            }
        }

        frame.add(painelSuperior, BorderLayout.NORTH); // Painel superior
        frame.add(painelTabuleiro, BorderLayout.CENTER); // Tabuleiro no centro

        frame.setVisible(true);
    }
}
