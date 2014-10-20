/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.lang.Byte;
import java.lang.Double;
/**
 *
 * @author Luis
 */
public class ImageClass {
    //ATRIBUTOS
    private int img_size; //Tamaño de imagen
    private int length; //Largo de la imagen
    private int width; //Ancho de la imagen
    private int image[]; //Array de enteros de la imagen
    private int colorValues[];  //Array de colores del histograma
    
    
    
    public ImageClass(String path){  //CONSTRUCTOR:Carga la imagen de la ruta seleccionada
   
        colorValues = new int[256];
        byte[] imagebyte;
        
        try{
            //Convertir imagen a array de bytes
            BufferedImage imagen2 = ImageIO.read(new File(path)); 
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            ImageIO.write(imagen2, "png", baos); 
            imagebyte=baos.toByteArray();
            
            
            //Calcular alto, ancho y tamaño//
            length = imagen2.getHeight(null);
            width = imagen2.getWidth(null);
            img_size = length*width;
            
            
            //Convertir a array de enteros          
            Byte b;
            for(int i=0;i<img_size;i++){
                b = imagebyte[i];
                image[i] = b.intValue();
            }
            
            } 
        catch(Exception e){ 
            System.out.println("Some problem has occurred. Please try again"); 
            }
    };
    
    
    public ImageClass(int[] img, int l, int w){ // CONSTRUCTOR: array de int
        img_size = l * w; //Tamaño de imagen
        length = l; //Largo de la imagen
        width = w; //Ancho de la imagen
        image = img; //Array de bytes de la imagen
        colorValues = new int[256];
    }
    
    
    public ImageClass(ImageClass other){   // CONSTRUCTOR DE COPIA
        img_size = other.img_size; //Tamaño de imagen
        length = other.length; //Largo de la imagen
        width = other.width; //Ancho de la imagen
        image = other.image; //Array de bytes de la imagen
        colorValues = other.colorValues;
    }
    
    
    public byte[] imageToByte(){  // Convierte el array de bytes de la imagen en un array de enteros
        byte[] b = new byte[img_size];
        Byte[] img_Byte = new Byte[img_size];
        for(int i=0;i<img_size;i++){
            img_Byte[i] = (byte)image[i];
            b[i] = img_Byte[i].byteValue();
        }
        return b;
    }
    
   
    
    
    public ImageIcon getImage(){  //Obtiene un ImageIcon del array de bytes.
        ImageIcon image2 = new ImageIcon(this.imageToByte(), "Image");
        return image2;
    };
    
    
    public void saveImage(String filename){ // Guarda la imagen en un fichero de nombre filename
        RenderedImage rendered = null;
        BufferedImage buffered = new BufferedImage(length, width, BufferedImage.TYPE_INT_RGB); 
        Graphics2D g = buffered.createGraphics();  
        g.drawImage(this.getImage().getImage(), 0, 0, null);  
        g.dispose();  
        rendered = buffered;
        
        try {
            ImageIO.write(rendered, "bmp", new File(filename));
        } 
        catch(Exception e){ 
            System.out.println("Some problem has occurred. Please try again"); 
        }
    }
    
    
    
    public int[] getColorValues(){ //Devuelve los valores del histograma de color
        for (int i=0;i<255;i++){
            colorValues[i]=0;
        }
        for (int i=0;i<img_size;i++){
            colorValues[image[i]]=colorValues[image[i]]+1;
        }
        return colorValues;
    };
    
    
    
    public int getPos(int x, int y){ //Devuelve la posicion de la imagen de coordenadas XY
        return (x-1)*img_size+y-1;
    }
    
    
    
    public int getSize(){  // Devuelve tamaño de imagen
        return img_size;
    }
    
    
    public int getLength(){  // Devuelve largo de la imagen
        return length;
    }
    
    
    public int getWidth(){  // Devuelve ancho de la imagen
        return width;
    }
    
    
    public void updateImage(){ //Recoge todas las funciones necesarias que actualicen una imagen
        getColorValues();
        //Otras funciones que hagan falta mas adelante//
    }
        
        
    public void showHistogram(){ // Muestra el histograma de color de la imagen
        //SACAR VENTANA???
    }
    
    public int imgBrightness(){ // Devuelve el brillo (media de color) de la imagen
        int suma = 0;
        for(int i=0;i<img_size;i++){
            suma = suma + image[i];
        }
        return (suma/img_size);           
    }

    
    
    public int imgContrast(){  // Devuelve el contraste (Desv. Tipica)
        int average = this.imgBrightness();
        double c = 0;
        Double contrast = new Double(c);
        
        for(int i=0;i<img_size;i++){
            contrast = contrast + (image[i] - average) * (image[i] - average);
        }
        contrast = contrast / img_size;
        contrast = Math.sqrt(contrast);
        return contrast.intValue();
    }
    
    
    
    public int[] imgNegative(){  // Devuelve el negativo (complemento) de la imagen
        int[] neg_img = new int[img_size];
        int L = 255;
        
        for (int i=0;i<img_size;i++){
            neg_img[i] = image[i] - L;
        }
        return neg_img;
    }
    
    
    public int[] imgBinary(int threshold){   // Devuelve la imagen binarizada a partir de un umbral
        int[] bin_img = new int[img_size];
        
        for (int i=0;i<img_size;i++){
            if (image[i] >= threshold)
                bin_img[i] = 255;
            else bin_img[i] = 0;
        }
        return bin_img;
    }
    
    
    public int[] linealTrans(int A, int B){  //Aplica la transformación lineal de forma Y = AX+B
       int[] newimg = new int[img_size];
         for (int i=0;i<img_size;i++){
            newimg[i] = image[i] * A + B;
        }
        return newimg;
    }
    
    
    public int[] imgSetByC(int bright, int contrast){  // Cambia el brillo y contraste de la imagen
        int currentB = this.imgBrightness();
        int currentC = this.imgContrast();
        int[] newimg = new int[img_size];
           
        int newCo = contrast / currentC;
        int newBr = bright - (newCo*currentB);
        
        for (int i=0;i<img_size;i++){
            newimg[i] = image[i] * newCo + newBr;
        }
        return newimg;
    }
    
    
    public int[] ROI(int x, int y, int len, int wid){  // Genera una subimagen de la imagen actual
        int[] roi = new int[len*wid];
        int ind = 0;
        for(int i=0; i<img_size; i++){
            if(i>= (x-1)*img_size+y-1 && i <= ((x+len)-1)*img_size+(y+wid)-1){
                roi[ind]=image[i];
                ind++;
            }
        }
        return roi;
    }
    
    
    
    public int colorInPos(int x, int y){  // Devuelve el valor de color del punto X,Y
        return image[getPos(x,y)];
    }
    
    
    public int getEnthropy(){  //Devuelve la entropia de la imagen
        Double ProbI;
	Double enthropy = 0.0;
	for( int i = 0; i < 256; i++ ) {
            ProbI = (double) colorValues[i] / img_size;
            if (ProbI > 0.0)
                enthropy += ProbI * (Math.log(ProbI) / Math.log(2.0));	
        }
	enthropy *= -1;
        return enthropy.intValue();
    }
    
}



