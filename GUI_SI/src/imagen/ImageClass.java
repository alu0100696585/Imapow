/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagen;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Luis
 */
public class ImageClass {
    //ATRIBUTOS
    private int img_size; //Tamaño de imagen
    private int length; //Largo de la imagen
    private int width; //Ancho de la imagen
    private byte image[]; //Array de bytes de la imagen
    private int colorValues[];  //Array de colores del histograma
    
    
    
    public ImageClass(String path){  //CONSTRUCTOR:Carga la imagen de la ruta seleccionada
   
        image = new byte[length*width];
        colorValues = new int[255];
        
        try{
            BufferedImage imagen2 = ImageIO.read(new File(path)); 
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            ImageIO.write(imagen2, "png", baos); 
            image=baos.toByteArray();
            

            //Calcular alto, ancho y tamaño//
            length = imagen2.getHeight(null);
            width = imagen2.getWidth(null);
            img_size = length*width;
            } 
        catch(Exception e){ 
            System.out.println("Some problem has occurred. Please try again"); 
            }

    };
    
    
    public ImageIcon getImage(){  //Obtiene un ImageIcon del array de bytes.
        ImageIcon image2 = new ImageIcon(image, "TIFF");
        return image2;
    };
    
    public int[] getColorValues(){ //Devuelve los valores del histograma de color
        for (int i=0;i<255;i++){
            colorValues[i]=0;
        }
        for (int i=0;i<img_size;i++){
            colorValues[image[i]]=colorValues[image[i]]+1;
        }
        return colorValues;
    };
    
    
    
    public int get_pos(int x, int y){ //Devuelve la posicion de la imagen de coordenadas XY
        return (x-1)*img_size+y-1;
    }
    
    
    
    public int get_size(){  // Devuelve tamaño de imagen
        return img_size;
    }
    
    
    public int get_length(){  // Devuelve largo de la imagen
        return length;
    }
    
    
    public int get_width(){  // Devuelve ancho de la imagen
        return width;
    }
    
}
