package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleJFrame extends JFrame
{
    public int a = 0;
    SimpleJFrame(String s)
    {
        super (s);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Тут код менеджера расположения
        setLayout(new GridLayout(4,1));

        // Текст и фон?
        JLabel l1=new JLabel();
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        add(l1);

        // Фон?
        JTextField t1=new JTextField();
        t1.setBackground(Color.pink);
        add(t1);

        // Кнопка
        JButton b1=new JButton();
        b1.setText("За-пуск");
        add(b1);
        //




        setVisible(true);
        // Лучше сначало отрисовка, а после только будет реакции описывать кнопок всяких к примеру


        //реакция на нажатие кнопки
        class ButtonListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                l1.setText(String.valueOf(a));
                a = a+10;

            }
        }
        ButtonListener buttonListener=new ButtonListener();
        b1.addActionListener(buttonListener);
    }

}
