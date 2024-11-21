import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

import Controllers.Servidor;
import Views.TabuleiroView;

public class App {
    public static void main(String[] args) throws Exception {
        String modo = JOptionPane.showInputDialog("Digite 'servidor' ou 'cliente':");
        TabuleiroView tabuleiroView = new TabuleiroView();

        if ("servidor".equalsIgnoreCase(modo)) {
            // Servidor.iniciarServidor();
            tabuleiroView.comecarJogo();
        } else if ("cliente".equalsIgnoreCase(modo)) {
            Servidor.iniciarCliente();
        } else {
            System.out.println("Modo inválido. Use 'servidor' ou 'cliente'.");
        }
    }

   
}
