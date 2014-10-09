/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagen;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Luis
 */
public class ImageClass {
    //ATRIBUTOS
    private int img_size;
    private int length;
    private int width;
    private byte image[];
    private int colorValues[];
    
    
    
    public ImageClass(String path){  //Carga la imagen de la ruta seleccionada
        //CALCULAR LENGTH Y WIDTH//
        colorValues = new int[255];
        image = new byte[length*width];
        //RELLENAR IMAGEN CON EL MAPA DE BITS DE LA RUTA
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
    
    public int pos(int x, int y){
        return (x-1)*img_size+y-1;
    }
    
    
}
