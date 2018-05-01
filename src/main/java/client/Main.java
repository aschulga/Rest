package client;

import client.controller.Handler;
import client.view.MyFrame;

import java.awt.*;

public class Main {
    public static void main(String args[]){
        Handler controller = new Handler();
        MyFrame frame = new MyFrame("Справочник по языку VBScript",new Dimension(600,400), controller);
        frame.init();
    }
}
