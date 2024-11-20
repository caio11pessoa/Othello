import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        String modo = JOptionPane.showInputDialog("Digite 'servidor' ou 'cliente':");

        if ("servidor".equalsIgnoreCase(modo)) {
            iniciarServidor();
        } else if ("cliente".equalsIgnoreCase(modo)) {
            iniciarCliente();
        } else {
            System.out.println("Modo inválido. Use 'servidor' ou 'cliente'.");
        }
    }

    public static void iniciarServidor() {
        try (ServerSocket servidor = new ServerSocket(12345)) {
            System.out.println("Servidor ouvindo na porta 12345");
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                try (ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream())) {
                    saida.writeObject(new Date());
                    saida.writeObject("Olá, é um prazer me conectar com você");
                }

                cliente.close();
            }

        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }

    public static void iniciarCliente() {
        try (Socket cliente = new Socket("localhost", 12345);
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream())) {

            while (true) {
                Object recebido = entrada.readObject();

                if (recebido instanceof Date) {
                    Date dataAtual = (Date) recebido;
                    JOptionPane.showMessageDialog(null, "Data recebida do servidor: " + dataAtual);
                } else if (recebido instanceof String) {
                    String mensagem = (String) recebido;
                    JOptionPane.showMessageDialog(null, "Mensagem recebida do servidor: " + mensagem);
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
