package userinterface;

import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    private JPanel rootPanel;
    private JTextField txtName;
    private JTextField txtPhone;
    private JButton buttonSave;
    private JButton buttonCancel;

    private ContactBusiness mContactBusiness;

    public ContactForm() {

        setContentPane(rootPanel); //Painel de conteúdo
        setSize(500, 250); //Tamanho da janela
        setVisible(true); //Ficar visível

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();
    }

    private void setListeners() {

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String name = txtName.getText();
                    String phone = txtPhone.getText();

                    mContactBusiness.save(name, phone);

                    new MainForm();
                    dispose();

                } catch (Exception excp) {

                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());

                }

            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();

            }
        });

    }

}
