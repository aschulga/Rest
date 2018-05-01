package client.view.book;

import client.controller.Handler;
import client.view.theme.DialogChangeTheme;
import client.view.theme.DialogCreateTheme;
import client.view.theme.DialogDeleteTheme;
import client.view.theme.ModelTableThemes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogBook {

    private JDialog dialog = new JDialog();
    private Handler handler;

    private JToolBar tb = new JToolBar();
    private JButton createButton = new JButton(new ImageIcon("images/add.png"));
    private JButton openButton = new JButton(new ImageIcon("images/show.png"));
    private JButton changeButton = new JButton(new ImageIcon("images/change.png"));
    private JButton deleteButton = new JButton(new ImageIcon("images/delete.png"));
    private JButton offButton = new JButton(new ImageIcon("images/back.png"));
    private int index;

    private ModelTableThemes model = new ModelTableThemes();
    private JTable table = new JTable(model);
    private JScrollPane jsp = new JScrollPane(table);

    public DialogBook(Handler handler, int index) {
        this.handler = handler;
        this.index = index;
    }

    public void show(){

        dialog.setTitle("Книга "+handler.getAllBooks().get(index).getTitle());
        dialog.setSize(400, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null);
        dialog.add(jsp, BorderLayout.CENTER);
        jsp.setPreferredSize(new Dimension(700, 500));

        tb.add(createButton);
        tb.add(openButton);
        tb.add(changeButton);
        tb.add(deleteButton);
        tb.add(offButton);

        dialog.add(tb, BorderLayout.NORTH);

        openButton.addActionListener(new openActionListener());
        createButton.addActionListener(new createActionListener());
        deleteButton.addActionListener(new deleteActionListener());
        changeButton.addActionListener(new changeActionListener());
        offButton.addActionListener(new offActionListener());


        dialog.pack();
        dialog.setVisible(true);
    }

    public class openActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.addNotation(handler.getAllThemesInBock(String.valueOf(index)));
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreateTheme dialogCreateTheme = new DialogCreateTheme(handler);
            dialogCreateTheme.createTheme(index);
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogDeleteTheme dialogDeleteTheme = new DialogDeleteTheme(handler);
            dialogDeleteTheme.delete(index);
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangeTheme dialogChangeTheme = new DialogChangeTheme(handler);
            dialogChangeTheme.change(index);
        }
    }

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dialog.dispose();
        }
    }
}
