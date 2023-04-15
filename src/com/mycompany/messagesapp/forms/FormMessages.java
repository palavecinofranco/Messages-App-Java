package com.mycompany.messagesapp.forms;

import com.mycompany.messagesapp.dao.MessageDao;
import com.mycompany.messagesapp.models.Message;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class FormMessages {
    private JList listMessage;
    private JTextArea txtMessage;
    private JTextField txtAuthor;
    private JButton createMessageButton;
    private JPanel ventana;
    private JButton deleteMessageButton;
    private JButton editMessageButton;
    private JLabel labelId;
    private JLabel labelIdValue;

    public void updateList(){
        DefaultListModel data = new DefaultListModel<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        MessageDao.readMessages().forEach(d->{
            String dataMessage = "Author: " + d.getAuthor() + " --- Messagge: " + d.getMessage() + " // " + d.getMessageDate().format(formatter);
            data.addElement(dataMessage);
        });
        this.listMessage.setModel(data);
    }
    public FormMessages() {
        updateList();
    createMessageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message message = new Message(txtMessage.getText(), txtAuthor.getText());
            if (!StringUtils.isEmptyOrWhitespaceOnly(labelIdValue.getText())){
                message.setId(Integer.parseInt(labelIdValue.getText()));
            }
            MessageDao.loadMessage(message);
            updateList();
            JOptionPane.showMessageDialog(ventana, "Mensaje enviado");
            txtMessage.setText(null);
            txtAuthor.setText(null);
            labelIdValue.setText(null);
        }


    });
        deleteMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listMessage.getSelectedIndex();
                MessageDao.deleteMessage(MessageDao.readMessages().get(index).getId());
                updateList();
                JOptionPane.showMessageDialog(ventana, "Mensaje eliminado");
            }
        });
        editMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listMessage.getSelectedIndex();
                txtMessage.setText(MessageDao.readMessages().get(index).getMessage());
                txtAuthor.setText(MessageDao.readMessages().get(index).getAuthor());
                labelIdValue.setText(Integer.toString(MessageDao.readMessages().get(index).getId()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormMessages");
        frame.setContentPane(new FormMessages().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
