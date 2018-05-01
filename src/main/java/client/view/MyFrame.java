package client.view;

import client.controller.Handler;
import client.view.book.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame {

    private String title;
    private Dimension d;
    private Handler handler;
    private JFrame frame = new JFrame();

    private JToolBar tb = new JToolBar();
    private JButton createButton = new JButton(new ImageIcon("images/add.png"));
    private JButton openButton = new JButton(new ImageIcon("images/show.png"));
    private JButton changeButton = new JButton(new ImageIcon("images/change.png"));
    private JButton deleteButton = new JButton(new ImageIcon("images/delete.png"));
    private JButton openBookButton = new JButton(new ImageIcon("images/openBook.png"));
    private JButton offButton = new JButton(new ImageIcon("images/exit.png"));

    private ModelTableBooks model = new ModelTableBooks();
    private JTable table = new JTable(model);
    private JScrollPane jsp = new JScrollPane(table);

    public MyFrame(String title, Dimension d, Handler handler) {
        this.title = title;
        this.d = d;
        this.handler = handler;
    }

    public void init(){

        frame.setTitle(title);
        frame.setSize(d);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(jsp, BorderLayout.CENTER);
        jsp.setPreferredSize(new Dimension(700, 500));

        tb.add(createButton);
        tb.add(openButton);
        tb.add(changeButton);
        tb.add(deleteButton);
        tb.add(openBookButton);
        tb.add(offButton);

        frame.getContentPane().add(tb, BorderLayout.NORTH);

        openButton.addActionListener(new openActionListener());
        createButton.addActionListener(new createActionListener());
        deleteButton.addActionListener(new deleteActionListener());
        changeButton.addActionListener(new changeActionListener());
        openBookButton.addActionListener(new openBookButtonActionListener());
        offButton.addActionListener(new offActionListener());

        frame.setVisible(true);
        frame.pack();
    }

    public class openActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.addNotation(handler.getAllBooks());
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreateBook addStudentDialog = new DialogCreateBook(handler);
            addStudentDialog.create();
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogDeleteBook searchStudentDialog = new DialogDeleteBook(handler);
            searchStudentDialog.delete();
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangeBook changeDialog = new DialogChangeBook(handler);
            changeDialog.change();
        }
    }

    public class openBookButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogOpenBook openBookDialog = new DialogOpenBook(handler);
            openBookDialog.openBook();
        }
    }

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
