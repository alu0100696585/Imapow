/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author God
 */
public class JLienzo extends JPanel{
    
    BufferedImage img;
    Rectangle rec;
    
    JLienzo(BufferedImage x, ImageFrame root){
        super();
        img = x;
        rec = new Rectangle(0,0,0,0);
        
        addMouseListener(root);
        addMouseMotionListener(root);
    }
    
    public void setImag(BufferedImage x){
        img = x;
        repaint();
    }
    
    public void setRectangle(Rectangle r){
        rec = r;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 1, 1, null);
        g.drawRect(rec.x, rec.y, rec.width, rec.height);
    }
}
