/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import imagen.ImageClass;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ImapowSL
 *
 */
public class GUI extends javax.swing.JFrame implements FocusListener, ChangeListener {

    private BufferedImage imageActual;
    private ArrayList<ImageFrame> imagenes;
    private int indiceVentana;
    private Brillo_Contraste bc;

    public int getIndiceVentana() {
        return indiceVentana;
    }

    public void setIndiceVentana(int indiceVentana) {
        this.indiceVentana = indiceVentana;
    }

    /**
     * Creates new form GUI
     */
    public GUI() {
        imagenes = new ArrayList<ImageFrame>();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu_gui = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        Cargar = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        Ver = new javax.swing.JMenu();
        EscalaGrises = new javax.swing.JMenuItem();
        HistogramaValAbsol = new javax.swing.JMenuItem();
        HistogramaValAcu = new javax.swing.JMenuItem();
        Diferencia = new javax.swing.JMenuItem();
        HistEspecific = new javax.swing.JMenuItem();
        Edicion = new javax.swing.JMenu();
        Recortar = new javax.swing.JMenuItem();
        Ecualizar = new javax.swing.JMenuItem();
        Gamma = new javax.swing.JMenuItem();
        TransNoLin = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        Brillo_Contraste = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Imapow");
        setMinimumSize(new java.awt.Dimension(340, 25));
        getContentPane().setLayout(null);

        Archivo.setText("Archivo");

        Cargar.setText("Cargar...");
        Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarActionPerformed(evt);
            }
        });
        Archivo.add(Cargar);

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        Archivo.add(Salir);

        menu_gui.add(Archivo);

        Ver.setText("Ver");

        EscalaGrises.setText("Pasar a escala de grises");
        EscalaGrises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscalaGrisesActionPerformed(evt);
            }
        });
        Ver.add(EscalaGrises);

        HistogramaValAbsol.setText("Histograma valores absolutos");
        HistogramaValAbsol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistogramaValAbsolActionPerformed(evt);
            }
        });
        Ver.add(HistogramaValAbsol);

        HistogramaValAcu.setText("Histograma valores acumulados");
        HistogramaValAcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistogramaValAcuActionPerformed(evt);
            }
        });
        Ver.add(HistogramaValAcu);

        Diferencia.setText("Diferencia con otra imágen");
        Diferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiferenciaActionPerformed(evt);
            }
        });
        Ver.add(Diferencia);

        HistEspecific.setText("Especificacioón histograma");
        HistEspecific.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistEspecificActionPerformed(evt);
            }
        });
        Ver.add(HistEspecific);

        menu_gui.add(Ver);

        Edicion.setText("Edicion");

        Recortar.setText("Recortar");
        Recortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecortarActionPerformed(evt);
            }
        });
        Edicion.add(Recortar);

        Ecualizar.setText("Ecualizar");
        Ecualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EcualizarActionPerformed(evt);
            }
        });
        Edicion.add(Ecualizar);

        Gamma.setText("Corrección gamma");
        Gamma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GammaActionPerformed(evt);
            }
        });
        Edicion.add(Gamma);

        TransNoLin.setText("Transformaciones no lineales");
        TransNoLin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransNoLinActionPerformed(evt);
            }
        });
        Edicion.add(TransNoLin);

        menu_gui.add(Edicion);

        jMenu1.setText("Herramientas");

        Brillo_Contraste.setText("Brillo y Contraste");
        Brillo_Contraste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Brillo_ContrasteActionPerformed(evt);
            }
        });
        jMenu1.add(Brillo_Contraste);

        menu_gui.add(jMenu1);

        setJMenuBar(menu_gui);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarActionPerformed
        // TODO add your handling code here:

        imagenes.add(new ImageFrame(abrirImagen(), this));
        imagenes.get(imagenes.size() - 1).setIndex(imagenes.size() - 1);

    }//GEN-LAST:event_CargarActionPerformed

    //Método que devuelve una imagen abierta desde archivo
    //Retorna un objeto BufferedImagen
    public BufferedImage abrirImagen() {

        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp = null;

        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();

        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");

        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("BMP", "bmp");
        selector.setFileFilter(filtroImagen);

        //Abrimos el cuadro de diálog
        int flag = selector.showOpenDialog(null);

        //Comprobamos que pulse en aceptar
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada = selector.getSelectedFile();

                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);

            }
            catch (Exception e) {
            }

        }
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual = bmp;
        //Retornamos el valor
        return bmp;
    }

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_SalirActionPerformed

    private void EscalaGrisesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscalaGrisesActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            imagenes.add(new ImageFrame(imageActual, this));               //crear nueva ventana
            imagenes.get(imagenes.size() - 1).setIndex(imagenes.size() - 1);
            imagenes.get(imagenes.size() - 1).EscalaGrises();          //aplicar escala grises

        }
    }//GEN-LAST:event_EscalaGrisesActionPerformed

    private void RecortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecortarActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            imagenes.add(new ImageFrame(imageActual, this));      //crear nueva ventana
            imagenes.get(imagenes.size() - 1).setIndex(imagenes.size() - 1);
            //cargando el recuadro de la imagen anterior en la nueva imagen 
            imagenes.get(imagenes.size() - 1).roi(imagenes.get(getIndiceVentana()).ix, imagenes.get(getIndiceVentana()).iy, imagenes.get(getIndiceVentana()).fx, imagenes.get(getIndiceVentana()).fy); //aplicar recorte
        }
    }//GEN-LAST:event_RecortarActionPerformed

    private void HistogramaValAbsolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistogramaValAbsolActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            HistogramaVA histograma = new HistogramaVA(imagenes.get(getIndiceVentana()).getImagen().getPixels());
            histograma.repaint();
            histograma.setVisible(true);
        }
    }//GEN-LAST:event_HistogramaValAbsolActionPerformed

    private void HistogramaValAcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistogramaValAcuActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            HistogramaVAC histograma = new HistogramaVAC(imagenes.get(getIndiceVentana()).getImagen().getPixels());
            histograma.repaint();
            histograma.setVisible(true);
        }
    }//GEN-LAST:event_HistogramaValAcuActionPerformed

    private void EcualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EcualizarActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            imagenes.add(new ImageFrame(imagenes.get(getIndiceVentana()).Ecualizar(),this));
            imagenes.get(imagenes.size() - 1).setIndex(imagenes.size() - 1);
        }
    }//GEN-LAST:event_EcualizarActionPerformed

    private void DiferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiferenciaActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {

            String th = JOptionPane.showInputDialog("Ingrese el umbral de comparacion: ");
            
            imagenes.add(new ImageFrame(imagenes.get(getIndiceVentana()).getImagen().compare(new ImageClass(abrirImagen()), Integer.parseInt(th)),this));
            imagenes.get(imagenes.size() - 1).setIndex(imagenes.size() - 1);
        }
    }//GEN-LAST:event_DiferenciaActionPerformed

    private void GammaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GammaActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {

            String g = JOptionPane.showInputDialog("Ingrese la corrección gamma: ");

            
            imagenes.add(new ImageFrame(imagenes.get(getIndiceVentana()).getImagen().gamma(Integer.parseInt(g)),this));
            imagenes.get(imagenes.size() - 1).setIndex(imagenes.size() - 1);
            

        }
    }//GEN-LAST:event_GammaActionPerformed

    private void HistEspecificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistEspecificActionPerformed
        // TODO add your handling code here:

        if (imageActual == null) {
            JOptionPane.showMessageDialog(null, "No existe una imagen cargada previamente, por favor, cargue una en Archivo->Cargsr..",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            
            imagenes.add(new ImageFrame(imagenes.get(getIndiceVentana()).getImagen().HistogramSpecification(new ImageClass(abrirImagen())),this));
            imagenes.get(imagenes.size() - 1).setIndex(imagenes.size() - 1);
        }
    }//GEN-LAST:event_HistEspecificActionPerformed

    private void Brillo_ContrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Brillo_ContrasteActionPerformed
        // TODO add your handling code here:7
        bc = new Brillo_Contraste(this);
        bc.setVisible(true);
    }//GEN-LAST:event_Brillo_ContrasteActionPerformed

    private void TransNoLinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransNoLinActionPerformed
        // TODO add your handling code here:
        
        FrameTransNoLin ventanaTNL = new FrameTransNoLin();
    }//GEN-LAST:event_TransNoLinActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        GUI programa = new GUI();
        programa.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Archivo;
    private javax.swing.JMenuItem Brillo_Contraste;
    private javax.swing.JMenuItem Cargar;
    private javax.swing.JMenuItem Diferencia;
    private javax.swing.JMenuItem Ecualizar;
    private javax.swing.JMenu Edicion;
    private javax.swing.JMenuItem EscalaGrises;
    private javax.swing.JMenuItem Gamma;
    private javax.swing.JMenuItem HistEspecific;
    private javax.swing.JMenuItem HistogramaValAbsol;
    private javax.swing.JMenuItem HistogramaValAcu;
    private javax.swing.JMenuItem Recortar;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuItem TransNoLin;
    private javax.swing.JMenu Ver;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menu_gui;
    // End of variables declaration//GEN-END:variables

    @Override
    public void focusGained(FocusEvent e) {

        ImageFrame aux = (ImageFrame) e.getSource();

        indiceVentana = aux.getIndex();

        imageActual = aux.getImagen().get_picture();

    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int b = bc.get_sliderBrillo();
        int c = bc.get_sliderContraste();
        
        imagenes.get(getIndiceVentana()).ByC(b,c);
    }
}
