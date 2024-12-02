package Views.Chat;

import java.awt.BorderLayout;

import javax.swing.*;

import Controllers.Chat.ClientChatController;
import Models.Player;

public class ChatView {
    ClientChatController controller ;

    public ChatView(Player player) {
        controller = new ClientChatController(player);
    }

    public void go() {
        JScrollPane scroller = createScrollableTextArea();
        controller.outgoing = new JTextField(20); // Coloca o valor no outgoing
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> controller.sendMessage(controller.player)); // chama o sendMessage
        JPanel mainPanel = new JPanel();
        mainPanel.add(scroller);
        mainPanel.add(controller.outgoing);
        mainPanel.add(sendButton);
        JFrame frame = new JFrame("chat: " + controller.player);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JScrollPane createScrollableTextArea() {
        controller.incoming = new JTextArea(15, 30);
        controller.incoming.setLineWrap(true);
        controller.incoming.setWrapStyleWord(true);
        controller.incoming.setEditable(false);
        JScrollPane scroller = new JScrollPane(controller.incoming);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }

}
