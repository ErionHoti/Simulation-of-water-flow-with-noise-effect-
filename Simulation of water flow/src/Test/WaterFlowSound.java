package Test;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;
import javax.sound.sampled.*;

public class WaterFlowSound extends JApplet {
    public static void main(String s[]) {
        JFrame frame = new JFrame();
        frame.setTitle("WaterFlow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JApplet applet = new WaterFlowSound();
        applet.init();
        frame.getContentPane().add(applet);
        frame.pack();
        frame.setVisible(true);
        try {
            playSound("Sound/RiverSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    public void init() {
        JPanel panel = new WaterPanel1();
        getContentPane().add(panel);
    }

    public static void playSound(String soundFile)
            throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

}

class WaterPanel1 extends JPanel implements Runnable {
    Point2D.Double[] pts = new Point2D.Double[1200];

    public WaterPanel1() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.blue.brighter());
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point2D.Double(Math.random(), Math.random());
        }
        Thread thread = new Thread(this);
        thread.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        		
        background(g);
        fence(g);
        plants(g, 0);

        // Rrjedha e lumit
        Stroke stroke = new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(stroke);
        g.setColor(new Color(33, 151, 209));

        for (int i = 0; i < pts.length; i++) {
            int x = (int) (650 * pts[i].x);
            int y = (int) (480 * pts[i].y);
            int h = (int) (25 * Math.random());

            g.drawLine(x, y + 320, x + h + 50, y + 320);
            g.drawLine(x, y + 320 + 10, x + h + 50 + 30, y + 320 + 10);
            g.drawLine(x - 50, y + 320 + 10, x + h + 50 + 30, y + 320 + 10);
            g.drawLine(x - 100, y + 320 + 10, x + h + 50 + 30, y + 320 + 10);
            g.drawLine(x - 150, y + 320 + 10, x + h + 50 + 30, y + 320 + 10);
            
           
            

        }
        g.setColor(new Color(46, 28, 15));
        g.fillRect(0, 450, 640, 30);

        plants(g,145);
        tree(g);
        stone(g);

    }

    public static void background(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(320, 0, new Color(128, 221, 255), 320, 120, new Color(255, 206, 92));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 640, 120);

        GradientPaint gp1 = new GradientPaint(320, 120, new Color(255, 206, 92), 320, 240, new Color(251, 255, 194));
        g2d.setPaint(gp1);
        g2d.fillRect(0, 120, 640, 120);

        GradientPaint gp2 = new GradientPaint(320, 240, new Color(150, 217, 255), 320, 360, new Color(33, 151, 209));
        g2d.setPaint(gp2);
        g2d.fillRect(0, 240, 640, 120);

        GradientPaint gp3 = new GradientPaint(320, 360, new Color(33, 151, 209), 320, 480, new Color(37, 79, 143));
        g2d.setPaint(gp3);
        g2d.fillRect(0, 360, 640, 120);

        GradientPaint gp4 = new GradientPaint(320, 220, new Color(127, 196, 67), 320, 300, new Color(75, 107, 47));
        g2d.setPaint(gp4);
        g2d.fillRect(0, 220, 640, 80);
    }

    public static void fence(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < 16; i++) {
            g2d.setColor(new Color(92, 57, 31));
            g2d.fillPolygon(new int[] { 10 + i * 40, 40 + i * 40, 40 + i * 40, 25 + i * 40, 10 + i * 40 },
                    new int[] { 300, 300, 210, 200, 210 }, 5);
            g2d.setColor(Color.black);
            g2d.drawLine(20 + i * 40, 300, 20 + i * 40, 205);
            g2d.drawLine(30 + i * 40, 300, 30 + i * 40, 205);
        }

        g.setColor(new Color(92, 57, 31));
        g.fillRect(0, 245, 640, 30);
        g.setColor(Color.black);
        g.drawLine(0, 255, 640, 255);
        g.drawLine(0, 265, 640, 265);

        g.setColor(new Color(46, 28, 15));
        g.fillRect(0, 300, 640, 20);
    }

    public static void plants(Graphics g, int y) {
        Graphics2D g2d = (Graphics2D) g;
        for (int j = 0; j < 20; j++) {
            g.setColor(new Color(52, 71, 34));
            Stroke stroke = new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2d.setStroke(stroke);
            Shape curve = new QuadCurve2D.Double(12 + j * 32, 316 + y, 20 + j * 32, 300 + y, 8 + j * 32, 280 + y);
            g2d.draw(curve);
            Shape curve1 = new QuadCurve2D.Double(15 + j * 32, 316 + y, 10 + j * 32, 300 + y, 3 + j * 32, 310 + y);
            g2d.draw(curve1);
            Shape curve2 = new QuadCurve2D.Double(15 + j * 32, 316 + y, 10 + j * 32, 285 + y, 3 + j * 32, 290 + y);
            g2d.draw(curve2);
            Shape curve3 = new QuadCurve2D.Double(15 + j * 32, 316 + y, 20 + j * 32, 300 + y, 32 + j * 32, 285 + y);
            g2d.draw(curve3);
            Shape curve4 = new QuadCurve2D.Double(20 + j * 32, 316 + y, 15 + j * 32, 300 + y, 18 + j * 32, 280 + y);
            g2d.draw(curve4);
            Shape curve5 = new QuadCurve2D.Double(17 + j * 32, 316 + y, 12 + j * 32, 295 + y, 30 + j * 32, 305 + y);
            g2d.draw(curve5);

            g2d.setColor(new Color(30, 41, 20));
            Stroke stroke1 = new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2d.setStroke(stroke1);
            Shape curve6 = new QuadCurve2D.Double(12 + j * 32, 316 + y, 20 + j * 32, 300 + y, 8 + j * 32, 280 + y);
            g2d.draw(curve6);
            Shape curve7 = new QuadCurve2D.Double(15 + j * 32, 316 + y, 10 + j * 32, 300 + y, 3 + j * 32, 310 + y);
            g2d.draw(curve7);
            Shape curve8 = new QuadCurve2D.Double(15 + j * 32, 316 + y, 10 + j * 32, 285 + y, 3 + j * 32, 290 + y);
            g2d.draw(curve8);
            Shape curve9 = new QuadCurve2D.Double(15 + j * 32, 316 + y, 20 + j * 32, 300 + y, 32 + j * 32, 285 + y);
            g2d.draw(curve9);
            Shape curve10 = new QuadCurve2D.Double(20 + j * 32, 316 + y, 15 + j * 32, 300 + y, 18 + j * 32, 280 + y);
            g2d.draw(curve10);
            Shape curve11 = new QuadCurve2D.Double(17 + j * 32, 316 + y, 12 + j * 32, 295 + y, 30 + j * 32, 305 + y);
            g2d.draw(curve11);
        }
    }

    public static void tree(Graphics g) {
        g.setColor(new Color(66, 33, 5));
        int[] a = new int[50];

        a[0] = a[49] = 0;
        for (int i = 1; i < 49; i++) {
            a[i] = 30 + i;
            a[a.length - i - 1] = 30 + i;
        }

        int[] b = new int[50];
        b[0] = b[1] = 280;
        b[49] = b[48] = 480;
        for (int i = 2; i < 48; i++) {
            b[i] = 280 + (i + 1) * 4;
        }
        g.fillPolygon(a, b, 50);

        leaves(g);

        g.setColor(new Color(66, 33, 5));
        int[] a1 = { 78, 83, 88, 105, 110, 115, 117, 132, 140, 145, 150, 157, 130, 126, 118, 108, 100, 85, 77, 70, 60,
                55, 40 };
        int[] b1 = { 280, 270, 260, 250, 240, 230, 220, 210, 200, 190, 180, 170, 180, 190, 200, 210, 220, 230, 240, 250,
                260, 270, 280 };
        g.fillPolygon(a1, b1, 23);

        int[] a2 = { 40, 38, 37, 36, 35, 33, 33, 32, 30, 20, 15, 0, 3, 4, 5, 8, 10, 12, 10, 10, 9, 5, 0 };
        int[] b2 = { 280, 270, 260, 250, 240, 230, 220, 210, 200, 190, 180, 170, 180, 190, 200, 210, 220, 230, 240, 250,
                260, 270, 280 };
        g.fillPolygon(a2, b2, 23);

        int[] a3 = { 96, 95, 93, 93, 92, 80, 80, 70, 50, 53, 54, 55, 58, 70, 72, 70, 70 };
        int[] b3 = { 250, 235, 225, 215, 205, 195, 185, 175, 160, 185, 195, 205, 215, 225, 235, 245, 250 };
        g.fillPolygon(a3, b3, 17);

        g.setColor(new Color(52, 71, 34));
        g.fillOval(40, 160, 60, 40);
    }

    public static void leaves(Graphics g) {
        g.setColor(new Color(52, 71, 34));
        g.fillOval(30, 150, 60, 40);
        g.fillOval(60, 130, 60, 40);
        g.fillOval(90, 120, 60, 40);
        g.fillOval(120, 150, 60, 40);
        g.fillOval(150, 140, 60, 40);
        g.fillOval(130, 120, 60, 40);
        g.fillOval(20, 130, 60, 40);
        g.fillOval(-10, 170, 60, 40);
        g.fillOval(-30, 150, 60, 40);
        g.fillOval(50, 160, 80, 40);
        g.fillOval(130, 160, 60, 40);
        g.fillOval(130, 120, 60, 40);
        g.fillOval(0, 120, 60, 40);

    }

    public static void stone(Graphics g) {
      // g.setColor(Color.gray);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(500, 348, new Color(169,169,169), 500, 380, Color.gray.darker());
        g2d.setPaint(gp);
        int[] a4 = { 450, 360, 380, 420, 460, 470, 480, 510, 530, 540, 560 };
        int[] b4 = { 380, 365, 360, 355, 350, 348, 350, 355, 360, 365, 370 };
        g2d.fillPolygon(a4, b4, 11);
//        g.setColor(new Color(169,169,169));
//        g.drawPolygon(a4, b4, 11);
    }

    public void run() {
        while (true) {
            for (int i = 0; i < pts.length; i++) {
                double x = pts[i].getX();
                double y = pts[i].getY();
                x += 0.1 * Math.random();
                if (x > 1) {
                    x = Math.random();
                    y = 3 * Math.random();
                }
                pts[i].setLocation(x, y);
            }
            repaint();
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
            }
        }
    }

}

