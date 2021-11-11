package SocketProgramming;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class MyServer extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JPanel p1;
    ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9, icon10;
    Image img1, img2, img3, img4, img5;
    JButton b1;
    static JTextField txt;
    static JTextArea area;
    static ServerSocket ss ;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    MyServer() {
        //Panels
        p1 = new JPanel();
        p1.setBackground(new Color(208, 94, 70));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);

        //Icon
        icon1 = new ImageIcon("F:\\JAVA TASKS\\src\\3.png");
        img1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        icon2 = new ImageIcon(img1);
        l1 = new JLabel(icon2);
        l1.setBounds(5, 17, 30, 30);
        p1.add(l1);

        icon3 = new ImageIcon("F:\\JAVA TASKS\\src\\depositphotos_119703626-stock-illustration-vector-drawing-of-icon-girl.png");
        img2 = icon3.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        icon4 = new ImageIcon(img2);
        l2 = new JLabel(icon4);
        l2.setBounds(40, 5, 60, 60);
        p1.add(l2);

        //Name
        l3 = new JLabel("Isha Bhutto");
        l3.setBounds(110, 15, 100, 20);
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        p1.add(l3);

        //Active Now
        l4 = new JLabel("Active Now");
        l4.setBounds(110, 35, 100, 20);
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        p1.add(l4);

        icon5 = new ImageIcon("F:\\JAVA TASKS\\src\\video.png");
        img3 = icon5.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        icon6 = new ImageIcon(img3);
        l5 = new JLabel(icon6);
        l5.setBounds(250, 20, 30, 30);
        p1.add(l5);

        icon7 = new ImageIcon("F:\\JAVA TASKS\\src\\phone.png");
        img4 = icon7.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        icon8 = new ImageIcon(img4);
        l6 = new JLabel(icon8);
        l6.setBounds(300, 20, 35, 30);
        p1.add(l6);

        icon9 = new ImageIcon("F:\\JAVA TASKS\\src\\3icon.png");
        img5 = icon9.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        icon10 = new ImageIcon(img5);
        l7 = new JLabel(icon10);
        l7.setBounds(360, 20, 13, 25);
        p1.add(l7);

        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        txt = new RoundedJTextField1(15);
        txt.setBounds(10, 600, 300, 40);
        txt.setFont(new Font("Console", Font.PLAIN, 16));
        txt.setForeground(new Color(208, 94, 70));
        add(txt);

        b1 = new JButton("Send");
        b1.setBounds(320, 600, 70, 40);
        b1.setBackground(new Color(208, 94, 70));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.BOLD, 13));
        b1.setBorder(new LineBorder(new Color(208, 94, 70), 1));
        b1.setFocusable(false);
        b1.addActionListener(this);

        area = new JTextArea();
        area.setBounds(3, 80, 394, 500);
        area.setFont(new Font("Ariel", Font.PLAIN, 20));
        area.setForeground(Color.RED);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        add(p1);
        add(b1);
        add(area);
        getContentPane().setBackground(Color.WHITE);
        setSize(400, 650);
        setLocation(490, 30);
        dispose();
        setUndecorated(true);
        setLayout(new BorderLayout());
        setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String out = txt.getText();
            area.setText(area.getText()+"\n\t\t      "+out);
            txt.setText("");
            dout.writeUTF(out);
        }catch(Exception ec ){
            System.out.println("Exception Caught!");
        }
    }
    public static void main(String[] args) {
        new MyServer();
        try{
         ss = new ServerSocket(3333);
         s = ss.accept();

        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());

        String str2 = "";
        while(!(str2.equals("stop"))){
        str2 = din.readUTF();
        area.setText(area.getText().trim() + "\n" + str2);
        }
        }
        catch(Exception ec){
            System.out.println("Exception Caught!");
        }
/*
        String str = "", str2 = "";
        while (!str.equals("stop")) {
            str = din.readUTF();
            System.out.println("client says: " + str);
            str2 = br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();*/
    }
}
// implement a round-shaped JTextField

class RoundedJTextField1 extends JTextField {
    private Shape shape;

    public RoundedJTextField1(int size) {
        super(size);
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}
