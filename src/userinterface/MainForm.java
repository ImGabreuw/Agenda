package userinterface;

import business.ContactBusiness;
import entities.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {

    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel lblContactCount;

    private ContactBusiness mContactBusiness;
    private String mName = "";
    private String mPhone = "";

    public MainForm() {

        setContentPane(rootPanel); //Painel de conteúdo
        setSize(500, 250); //Tamanho da janela
        setVisible(true); //Ficar visível

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Pegar o tamanho do monitor
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2); //Deixar o programa no meio da tela

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Finalizar o programa quando clicar no 'x'

        mContactBusiness = new ContactBusiness();

        setListeners();
        loadContacts();

    }

    private void loadContacts() {

        List<ContactEntity> contactList = mContactBusiness.getList();

        String[] columnNames = {"Nome", "Telefone"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity contact : contactList) {
            Object[] o = new Object[2];

            o[0] = contact.getName();
            o[1] = contact.getPhone();

            model.addRow(o);
        }

        tableContacts.clearSelection();
        tableContacts.setModel(model);

        lblContactCount.setText(mContactBusiness.getContactCountDescription());
    }

    private void setListeners() {

        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });

        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {
                    if (tableContacts.getSelectedRow() != -1) {
                        mName = tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                        mPhone = tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                    }
                }

            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    mContactBusiness.delete(mName, mPhone);
                    loadContacts();

                    mName = "";
                    mPhone = "";

                } catch (Exception excp) {

                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());

                }
            }
        });

    }
}
