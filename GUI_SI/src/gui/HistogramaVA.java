/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author MAURI
 */
public class HistogramaVA extends JFrame {

    private int[] pixeles;

    /**
     * Creates new form HistogramaValAbsol
     *
     * @param pixels
     */
    public HistogramaVA(int[] pixels) {
        this.setResizable(true);
        this.setSize(500, 300);
        this.setVisible(true);
        pixeles = pixels;
        this.add(new Grafico(), BorderLayout.CENTER);
    }

    public class Grafico extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GRAY);
            g.drawLine(0, 260, getWidth(), 260);
            g.drawLine(10, 0, 10, getHeight());
            g.setColor(Color.BLACK);
            for (int i = 0; i < pixeles.length; i++) {
                g.drawLine((i/100) + 10, 260, (i/100) + 10, 260 - (pixeles[i]));
                //System.out.println(pixeles[i]);
            }
        }
    }
}
