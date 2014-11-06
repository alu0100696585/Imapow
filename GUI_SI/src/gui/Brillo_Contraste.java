/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.JButton;


/**
 *
 * @author MAURI
 */
public class Brillo_Contraste extends javax.swing.JFrame {

    
    /**
     * Creates new form Brillo_Contraste
     */
    public Brillo_Contraste(GUI root) {
        initComponents();
        sliderBrillo.addChangeListener(root);
        sliderContraste.addChangeListener(root);
        Aceptar.addActionListener(root);
    }
    
    public void set_sliderBrillo(int x){
        sliderBrillo.setValue(x);
    }
    
    public void set_sliderContraste(int x){
        sliderContraste.setValue(x);
    }
    
    public int get_sliderBrillo(){
        return sliderBrillo.getValue();
    }
    
    public int get_sliderContraste(){
        return sliderContraste.getValue();
    }
    
    public JButton getAceptar(){
        return Aceptar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sliderBrillo = new javax.swing.JSlider();
        sliderContraste = new javax.swing.JSlider();
        labelBrillo = new javax.swing.JLabel();
        labelContraste = new javax.swing.JLabel();
        Aceptar = new javax.swing.JButton();

        setTitle("Brillo y Contraste");
        setMinimumSize(new java.awt.Dimension(400, 400));
        getContentPane().setLayout(null);

        sliderBrillo.setMaximum(255);
        sliderBrillo.setValue(125);
        getContentPane().add(sliderBrillo);
        sliderBrillo.setBounds(50, 80, 280, 26);

        sliderContraste.setMaximum(127);
        sliderContraste.setToolTipText("");
        sliderContraste.setValue(64);
        getContentPane().add(sliderContraste);
        sliderContraste.setBounds(50, 180, 280, 26);

        labelBrillo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelBrillo.setText("Brillo");
        getContentPane().add(labelBrillo);
        labelBrillo.setBounds(170, 40, 50, 19);

        labelContraste.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelContraste.setText("Contraste");
        getContentPane().add(labelContraste);
        labelContraste.setBounds(160, 140, 70, 19);

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });
        getContentPane().add(Aceptar);
        Aceptar.setBounds(120, 230, 140, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Brillo_Contraste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Brillo_Contraste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Brillo_Contraste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Brillo_Contraste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
      /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Brillo_Contraste().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JLabel labelBrillo;
    private javax.swing.JLabel labelContraste;
    private javax.swing.JSlider sliderBrillo;
    private javax.swing.JSlider sliderContraste;
    // End of variables declaration//GEN-END:variables
}
