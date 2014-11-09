/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;
import imagen.ImageClass;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author God
 */
public class ImageFrame extends JFrame implements MouseListener, MouseMotionListener {

    private ImageClass imagen;
    private JLienzo lienzo;
    private int index;//variable indice de la ventana
    private int vColor;//valor del color en el punto x y
    private JLabel lValorColor;//label para valor del color en el punto x y
    private JLabel lPos;//label para posicion del puntero en la imagen
    private JPanel etiquetas;
    public int ix;
    public int iy;
    public int fx;
    public int fy; // variables para la seleccion sobre la imagen

    ImageFrame(BufferedImage img, GUI root) {
        //creando ventana
        this.setResizable(false);
        this.setMinimumSize(null);
        Image icon = new ImageIcon(getClass().getResource("/gui/imapow.png")).getImage();
        setIconImage(icon);

        this.addFocusListener(root);

        //inicializacion de labels
        lValorColor = new JLabel("valor de gris: ");
        lPos = new JLabel("x: y: ");
        etiquetas = new JPanel();
        etiquetas.setLayout(new GridLayout(1,2));
        
        //indicamos tamaño de la ventana segun la imagen
        this.setSize(img.getWidth() + 6, img.getHeight() + lValorColor.getHeight() + 50);

        //inicializacion del indice
        index = -1;

        //Almacenando la informacion de la imagen
        imagen = new ImageClass(img);

        //cargando imagen en el lienzo    
        lienzo = new JLienzo(img, this);

        //añadiendo el lienzo
        this.add(lienzo, BorderLayout.CENTER);

        //añadiendo labels
        etiquetas.add(lValorColor);
        etiquetas.add(lPos);
        this.add(etiquetas, BorderLayout.SOUTH);

        lienzo.repaint();
        
        //ventana visible
        this.setVisible(true);

    }

    ;

    public JLienzo getLienzo() {
        return lienzo;
    }

    public ImageClass getImagen() {
        return imagen;
    }

    public void setImagen(ImageClass imagen) {
        this.imagen = imagen;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage get_img() {
        return imagen.get_picture();
    }

    public BufferedImage EscalaGrises() {

        return imagen.escalaGrises();

    }

    public BufferedImage Ecualizar() {

        return imagen.equalization();

    }

    public BufferedImage roi(int x, int y, int xf, int yf) {
        Rectangle rec = new Rectangle(new Point(x, y));
        rec.add(new Point(xf, yf));
        return imagen.Roi(x, y, rec);
    }
    
    public BufferedImage TransformLin(int [] val){
        
        int ntrans = val.length/4;
        int [] init = new int[ntrans];
        int [] end = new int[ntrans];
        int [] A = new int[ntrans];
        int [] B = new int[ntrans];
        
        for(int i = 0; i<ntrans; i++){
            //inicializando zonas
            init[i] = val[ 4*i ];
            end[i] = val[ 4*i + 2];
            //analizando parametros A y B
            //pendiente (A)
            A[i] = (val[ 4*i + 3 ] - val[ 4*i + 1 ]) / (val[ 4*i + 2 ] - val[ 4*i ]);
            //eje coordenadas (B)
            B[i] = val[ 4*i + 3 ] - (A[i] * val[ 4*i + 2 ]);  
        }
        
        
        return imagen.linealTransZones(ntrans, init, end, A, B);    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ix = e.getX();
        iy = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        fx = e.getX();
        fy = e.getY();

        Rectangle recc = new Rectangle(new Point(ix, iy));
        recc.add(new Point(fx, fy));
        lienzo.setRectangle(recc);
        lienzo.repaint();

    }
    
    public void ByC(int b,int c, boolean guardar){
        
        if(guardar){
            ImageClass nwImg = new ImageClass(imagen.imgSetByC(b, c));
            imagen = nwImg;        
            lienzo.setImag(nwImg.get_picture());
        }
        else{
            lienzo.setImag(imagen.imgSetByC(b, c));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lValorColor.setText("valor de gris: " + imagen.colorInPos(e.getX(), e.getY()));
        lPos.setText("x: " + e.getX() + " y: " + e.getY());
        //lienzo.repaint();
    }

}
