/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MAURI
 */
public class FrameTransNoLin extends JFrame {
    
    JPanel panel;
    JButton aceptar;
    
    FrameTransNoLin () {
        String num_tramos = JOptionPane.showInputDialog("Indique el numero de tramos: ");
        int nt = Integer.parseInt(num_tramos);
        
        panel = new JPanel();
        aceptar = new JButton("Aceptar");
        panel.setLayout(new GridLayout(nt, 4));
        
        for(int i = 0; i < nt*4; i++) {
            panel.add(new JTextField());
        }
        
        this.add(panel, BorderLayout.CENTER);
        this.add(aceptar, BorderLayout.SOUTH);
        this.setSize(300, 150);
        this.setTitle("Tramos transformaciones no lineales");
        this.setVisible(true);
 
    }
    
}
