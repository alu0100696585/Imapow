/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JButton;

/**
 *
 * @author God
 */
public class RotacionAbsoluta extends javax.swing.JFrame {

    /**
     * Creates new form RotacionAbsoluta
     */
    public RotacionAbsoluta(GUI root){
        initComponents();
        aceptar.addActionListener(root);
    }
    
    public JButton getAceptar(){
        return aceptar;
    }
    
    public double getGrados(){
        String str = textGrados.getText();
        double grados = Double.parseDouble(str);
        
        return grados;
    }
    
    public boolean getOpcBilineal(){
        return opcBilineal.isSelected();
    }
    
    public boolean getInterpolacion(){
        return noInter.isSelected();
    }
    
    public boolean getHistogramaAbs(){
        return histAbsolutos.isSelected();
    }
    
    public boolean getHistogramaAc(){
        return histAcumulativos.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        opcVecino = new javax.swing.JRadioButton();
        opcBilineal = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        textGrados = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        histAbsolutos = new javax.swing.JRadioButton();
        histAcumulativos = new javax.swing.JRadioButton();
        aceptar = new javax.swing.JButton();
        noInter = new javax.swing.JRadioButton();

        setTitle("Rotación absoluta");
        setResizable(false);

        jLabel1.setText("Interpolación");

        opcVecino.setText("Vecino");
        opcVecino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcVecinoActionPerformed(evt);
            }
        });

        opcBilineal.setText("Bilineal");
        opcBilineal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcBilinealActionPerformed(evt);
            }
        });

        jLabel2.setText("Grados");

        jLabel3.setText("Histograma");

        histAbsolutos.setText("Valores absolutos");

        histAcumulativos.setText("Valores acumulativos");

        aceptar.setText("Aceptar");

        noInter.setText("Sin interpolar");
        noInter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noInterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(opcBilineal)
                            .addComponent(opcVecino))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(textGrados, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(noInter)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(histAcumulativos)
                        .addGap(18, 18, 18)
                        .addComponent(aceptar))
                    .addComponent(histAbsolutos)
                    .addComponent(jLabel3))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcVecino)
                    .addComponent(textGrados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opcBilineal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noInter)
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(histAbsolutos)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(histAcumulativos)
                    .addComponent(aceptar))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void noInterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noInterActionPerformed
        // TODO add your handling code here:
        opcBilineal.setSelected(false);
        opcVecino.setSelected(false);
    }//GEN-LAST:event_noInterActionPerformed

    private void opcVecinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcVecinoActionPerformed
        // TODO add your handling code here:
        opcBilineal.setSelected(false);
        noInter.setSelected(false);
    }//GEN-LAST:event_opcVecinoActionPerformed

    private void opcBilinealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcBilinealActionPerformed
        // TODO add your handling code here:
        opcVecino.setSelected(false);
        noInter.setSelected(false);
    }//GEN-LAST:event_opcBilinealActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RotacionAbsoluta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RotacionAbsoluta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RotacionAbsoluta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RotacionAbsoluta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JRadioButton histAbsolutos;
    private javax.swing.JRadioButton histAcumulativos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton noInter;
    private javax.swing.JRadioButton opcBilineal;
    private javax.swing.JRadioButton opcVecino;
    private javax.swing.JTextField textGrados;
    // End of variables declaration//GEN-END:variables
}
