package sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import javax.swing.*;


public class Main extends JPanel implements ActionListener {

    Timer timer;

    public Main(){
        timer = new Timer(10, this);
        timer.start();
    }

    private static int maxWidth;
    private static int maxHeight;
    private double angle = 0;
    private double radius = 100;

    private double scale = 1;
    private double delta = 0.01;

    private double center_x = 1;
    private double center_y = 1;

    double dx = 1;
    double dy = 1;

    double[][] cart = {
            {-78, 23}, {78, 23}, {78, -23}, {-78 + 50, -23}, {-78, -23 - 50}
    };


    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(new Color(1.0f, 0.64705884f, 0.0f));
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.clearRect(0, 0, maxWidth+1, maxHeight+1);
        g2d.setRenderingHints(rh);


        BasicStroke bs = new BasicStroke(20, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs);
        g2d.setColor(Color.RED);
        g2d.drawRect(10, 10, maxWidth - 20, maxHeight - 20);

        GradientPaint gp = new GradientPaint(1, 8, Color.ORANGE, 30, 10, Color.BLUE, true);
        g2d.setPaint(gp);

        g2d.translate(maxWidth / 2, maxHeight / 2);

        g2d.translate(center_x, center_y);
        GeneralPath cart_obj = new GeneralPath();
        cart_obj.moveTo(cart[0][0], cart[0][1]);

        for (int i = 1; i < cart.length; i++) {
            cart_obj.lineTo(cart[i][0], cart[i][1]);
        }
        cart_obj.closePath();



        g2d.scale(scale, 0.99);
        g2d.fill(cart_obj);

        bs = new BasicStroke((float) 0.5);
        g2d.setStroke(bs);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(-Math.round(156/4), 23, Math.round(156/4), -23+80+20);
        g2d.drawLine(Math.round(156/4), 23, -Math.round(156/4), -23+80+20);
        g2d.drawLine(78, -23, 78+40, -23-30);

        g2d.setColor(new Color(0.5411765f, 0.16862746f, 0.8862745f));
        g2d.fillOval(-Math.round(156/4)-20, -23+80, 40, 40);
        g2d.fillOval(Math.round(156/4)-20, -23+80, 40, 40);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LAB 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Main());
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        if (scale < 0.01) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }


        scale += delta;
        angle += 0.05;
        center_x = radius*Math.cos(angle);
        center_y = radius*Math.sin(angle);
        repaint();
    }
}
