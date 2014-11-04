/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
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
    
    FrameTransNoLin () {
        String num_tramos = JOptionPane.showInputDialog("Indique el numero de tramos: ");
        int nt = Integer.parseInt(num_tramos);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(nt, 2));
        
        for(int i = 0; i < nt*2; i++) {
            panel.add(new JTextField());
        }
        
        this.add(panel);
        this.setSize(300, 150);
        this.setTitle("Tramos transformaciones no lineales");
        this.setVisible(true);
 
    }
    
}
