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
class MyClient extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JPanel p1;
    ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9, icon10;
    Image img1, img2, img3, img4, img5;
    JButton b1;
    static JTextField txt;
    static JTextArea area;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    MyClient(){
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

        icon3 = new ImageIcon("F:\\JAVA TASKS\\src\\happy-girls-icon-vector-young-woman-icon-illustration-face-people-icon-flat-cartoon-style-person-head-isolated-avatar-74926713.png");
        img2 = icon3.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        icon4 = new ImageIcon(img2);
        l2 = new JLabel(icon4);
        l2.setBounds(40, 5, 60, 60);
        p1.add(l2);

        //Name
        l3 = new JLabel("Areesha");
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

        txt = new RoundedJTextField2(15);
        txt.setBounds(10, 600, 300, 40);
        txt.setFont(new Font("Ariel", Font.PLAIN, 16));
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
        getContentPane().setBackground(Color.white);
        setSize(400, 650);
        setLocation(940, 30);
        dispose();
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
        String out = txt.getText();
        area.setText(area.getText()+"\n\t\t        "+out);
            txt.setText("");
            dout.writeUTF(out);
          //  dout.flush();
        }
        catch(Exception ec){
            System.out.println("Exception Caught");
        }
    }

    public static void main(String args[]){
        new MyClient();
        try {
            s = new Socket("localhost", 3333);

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            String str2 = "";
            while(!(str2.equals("stop"))) {
            str2 = din.readUTF();
            area.setText(area.getText().trim() + "\n" + str2);
            }
        }
        catch(Exception ec){
            System.out.println("Exception Caught!");
        }
        /*String str="",str2="";
        while(!str.equals("stop")){
            str=br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2=din.readUTF();
            System.out.println("Server says: "+str2);
        }
        dout.close();
        s.close();*/
    }
}
// implement a round-shaped JTextField

class RoundedJTextField2 extends JTextField {
    private Shape shape;

    public RoundedJTextField2(int size) {
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
