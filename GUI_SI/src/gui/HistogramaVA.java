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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MAURI
 */
public class HistogramaVA extends JFrame {

    private int[] pixeles;
    private int[] valores;

    /**
     * Creates new form HistogramaValAbsol
     *
     * @param pixels
     */
    public HistogramaVA(int[] pixels) {
        this.setResizable(false);
        this.setSize(255, 255);
        this.setVisible(true);
        this.setTitle("Histograma ");
        pixeles = pixels;
        valores = new int[256];
        rellenar_datos(pixeles);
        redimensionar();
        JLabel n_h = new JLabel("0                                                                         255");
        this.add(new Grafico(), BorderLayout.CENTER);
        this.add(n_h, BorderLayout.SOUTH);
    }
    
    public void rellenar_datos (int[] pixels) {//Funcion para rellenar el array que plasmaremos en la grafica
        for (int i = 0; i < 255; i++) {
            valores[i] = 0;
        }
        for (int i = 0; i < pixels.length; i++) {
            valores[pixels[i]] += 1;
        }
    }
    
    public void redimensionar () {//Funcion para que la grafica siempre quede bien dentro de la ventana
        int max = 0;
        int lim_ventana = 250;//Limite puesto para que no sobrepase la ventana y quede bonito
        
        for (int i = 0; i < valores.length; i++) {
            if (max < valores[i])
                max = valores[i];               
        }
        
        if (max > lim_ventana) {
            for (int i = 0; i < valores.length; i++) {
                valores[i] = (valores[i] * lim_ventana)/max;
            }
        }
    }

    public class Grafico extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            for (int i = 0; i < 255; i++) {
                g.drawLine(i, 255, i, 255 - valores[i]);
            }
        }
    }
}
