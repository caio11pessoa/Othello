package Views.Home;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controllers.Home.HomeController;

public class HomeView {
    HomeController controller = new HomeController();

    public static void main(String[] args) {
        new HomeView().go();
    }

    public void go() {
        JFrame frame = new JFrame("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        // Painel para os radio buttons
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        // Criação dos radio buttons
        JRadioButton option1 = new JRadioButton("Quero jogar como Servidor");
        JRadioButton option2 = new JRadioButton("Quero jogar como Cliente");

        // Agrupando os radio buttons para que apenas um possa ser selecionado
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);

        // Adicionando os radio buttons ao painel
        optionsPanel.add(option1);
        optionsPanel.add(option2);

        // Criação do botão "Começar"
        JButton startButton = new JButton("Começar");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option1.isSelected()) {
                    controller.openServer(frame);
                } else if (option2.isSelected()) {
                    controller.openClient(frame);
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, selecione uma opção.");
                }
            }
        });

        // Adiciona o painel de opções e o botão ao frame
        frame.add(optionsPanel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        // Torna o frame visível
        frame.setVisible(true);
    }
}
