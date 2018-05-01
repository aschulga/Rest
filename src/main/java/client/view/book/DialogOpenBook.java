package client.view.book;

import client.controller.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogOpenBook {
    private JDialog dialog = new JDialog();
    private Handler handler;

    public DialogOpenBook(Handler handler) {
        this.handler = handler;
    }

    public void openBook() {

        dialog.setSize(500, 500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel numberLabel = new JLabel("Номер книги: ");
        JTextField numberTextField = new JTextField(10);

        JButton openButton = new JButton("Открыть");

        JPanel panelOpenButton = new JPanel();
        panelOpenButton.setLayout(new GridBagLayout());

        panelOpenButton.add(numberLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        panelOpenButton.add(numberTextField, new GridBagConstraints(0, 1, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        dialog.add(panelOpenButton, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(openButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((numberTextField.getText()).trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поле не заполнено");
                    return;
                } else {
                    DialogBook bookDialog = new DialogBook(handler, Integer.parseInt(numberTextField.getText()) - 1);
                    bookDialog.show();
                    dialog.dispose();
                }
            }
        });
    }
}
