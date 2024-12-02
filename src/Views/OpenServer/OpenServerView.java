package Views.OpenServer;

import java.awt.*;

import javax.swing.*;

public class OpenServerView {
    public void go(){
         // Criação do frame para a tela de carregamento
        JFrame loadingFrame = new JFrame("Conectando ao Servidor");
        loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingFrame.setSize(300, 150);
        loadingFrame.setLayout(new BorderLayout());

        // Adiciona uma mensagem
        JLabel messageLabel = new JLabel("Servidor aberto", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        loadingFrame.add(messageLabel, BorderLayout.NORTH);

        // Adiciona a barra de progresso
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Animação de carregamento contínuo
        loadingFrame.add(progressBar, BorderLayout.CENTER);

        // Torna o frame visível
        loadingFrame.setVisible(true);
    }
}
