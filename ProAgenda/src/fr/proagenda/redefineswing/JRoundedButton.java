package fr.proagenda.redefineswing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Double;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class JRoundedButton extends JButton {
    private static final long serialVersionUID = 9032198251140247116L;
    String text;
    public JRoundedButton(String s) {
        super(s);
        text = s;
        setForeground(Color.DARK_GRAY);
        setOpaque(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusable(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isPressed()) {
            g.setColor(Color.red);
            g2.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
        }
        super.paintComponent(g);
        g2.setColor(Color.LIGHT_GRAY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1.2f));   
        g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 3),(getHeight() - 3), 15, 15));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(4, getHeight() - 3, getWidth() - 4, getHeight() - 3);
        g2.dispose();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        JRoundedButton xrButton = new JRoundedButton("XrButton");
        JButton jButton = new JButton("JButton");
        frame.getContentPane().add(xrButton);
        frame.getContentPane().add(jButton);
        frame.setSize(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}