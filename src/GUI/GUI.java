package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    private JTextField textField11;
    private JTextField textField12;

    public GUI() {
        JLabel label1 = new JLabel("Надпись label1");
        JLabel label11 = new JLabel("Надпись label11");
        JLabel label12 = new JLabel("Надпись label12");

        textField11 = new JTextField("Текстовое поле textField11");
        textField12 = new JTextField("Текстовое поле textField12");

        JButton button1 = new JButton("Кнопка button1");

        JTable table;
        JScrollPane scrollPane;

        Controller controller=new Controller();

        MyTableModel myTableModel;
        setSize(700, 500);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        Container p1 = new Container(); //контейнер для левой части окна
        add(p1);
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.add(label1);
        p1.add(label11);
        textField11.setSize(80,30);
        p1.add(textField11);
        p1.add(label12);
        textField12.setSize(80,30);
        p1.add(textField12);
        p1.add(button1);
        Container p2=new Container();//контейнер для правой части окна
        p2.setLayout(new FlowLayout());
        add(p2);
        table=new JTable();
        scrollPane = new JScrollPane(table); // scrollPane ЭТО ОБЯЗАТЕЛЬНО
        p2.add(scrollPane);
        setVisible(true);
    }


}
