import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Logo extends JPanel {

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 350, 350);
        g.setColor(Color.GRAY);
        g.fillOval(0, 0, 250, 210);
        g.setColor(Color.WHITE);
        g.fillOval(20, 20, 210, 170);

        g.setColor(Color.BLACK);
        g.drawLine(280, 20, 20, 110); //верхняя правая
        g.drawLine(280, 20, 45, 160); //нижняя правая

        g.drawLine(80, 90, 0,20);     //нижняя левая
        g.drawLine(120, 75, 0,20);    //верхняя левая

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(80,170, 100,50);

        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(50.0f));
        g.drawString("UAZ", 78, 210); // Текст
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UAZ Logo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        Logo circles = new Logo();
        frame.add(circles);
        frame.setVisible(true);
    }

}
