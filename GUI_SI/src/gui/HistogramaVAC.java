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
public class HistogramaVAC extends JFrame {

    private int[] pixeles;

    /**
     * Creates new form HistogramaValAbsol
     *
     * @param pixels
     */
    public HistogramaVAC(int[] pixels) {
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
            int acumulado = 0;
            for (int i = 0; i < pixeles.length; i++) {
                acumulado += pixeles[i]/8;
                g.drawLine((i/250) + 10, 260, (i/250) + 10, 260 - (acumulado/3500));
                //System.out.println(pixeles[i]);
            }
        }
    }
}
