/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MAURI
 */
public class FrameTransLin extends JFrame {

    private JPanel panel;
    private JButton aceptar;
    private ArrayList<JTextField> puntos;
    private int num_pnts;

    FrameTransLin(GUI root) {
        String num_tramos = JOptionPane.showInputDialog("Indique el numero de tramos: ");
        int nt = Integer.parseInt(num_tramos);

        Image icon = new ImageIcon(getClass().getResource("/gui/imapow.png")).getImage();
        setIconImage(icon);

        num_pnts = nt * 4;

        panel = new JPanel();
        aceptar = new JButton("Aceptar");
        panel.setLayout(new GridLayout(nt, 4));
        puntos = new ArrayList<JTextField>();

        for (int i = 0; i < num_pnts; i++) {
            puntos.add(new JTextField());
        }

        for (int i = 0; i < num_pnts; i++) {
            panel.add(puntos.get(i));
        }

        aceptar.addActionListener(root);

        this.add(panel, BorderLayout.CENTER);
        this.add(aceptar, BorderLayout.SOUTH);
        this.setSize(300, 150);
        this.setTitle("Tramos transformaciones no lineales");
        this.setVisible(true);

    }

    public JButton getAceptar() {
        return aceptar;
    }

    public int[] get_valores() {

        int[] val = new int[num_pnts];

        for (int i = 0; i < num_pnts; i++) {
            val[i] = Integer.parseInt(puntos.get(i).getText());
        }

        return val;

    }

}
