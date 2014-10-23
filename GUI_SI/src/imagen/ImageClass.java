/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author ImapowSL
 * 
 */



public class ImageClass {
    
    //ATRIBUTOS
    private BufferedImage picture;  //Imagen
    private int img_size;           //Tamaño de imagen
    private int height;             //Alto de la imagen
    private int width;              //Ancho de la imagen
    private ArrayList<Color> image; //Color de cada pixel en la imagen 
    private int pixels[];           //Array de enteros de la imagen (en escala de grises)
    //private int colorValues[];     //Array de colores del histograma 

    public int[] getPixels() {
        return pixels;
    }
    
    //constantes para la conversion a gris
    public static final double NTSC_R = 0.299;
    public static final double NTSC_G = 0.587;
    public static final double NTSC_B = 0.114;
    
    
    
    public ImageClass(BufferedImage img){  //CONSTRUCTOR: Carga la imagen de la ruta seleccionada
   
        picture = img;
        image = new ArrayList<Color>();
       // colorValues = new int[256];
  
        
        try{
            
            //Calcular alto, ancho y tamaño
            height = img.getHeight();
            width = img.getWidth();
            img_size = height*width;
            
            pixels = new int[img_size];
            
            
            //Rellenar array de la imagen    
            for(int i = 0; i<width; i++){
                for(int j = 0; j<height; j++){
  
                int srcPixel = img.getRGB(i, j);
                
                Color c = new Color(srcPixel);
                
                image.add(c);
                
                int r = (0xff & (srcPixel >> 16));  //Desplaza el entero srcPixel 16 bits a la derecha
                int g = (0xff & (srcPixel >> 8));
                int b = (0xff & (srcPixel));
                
                //int promedio = (int)((r+g+b)/3);
                    
                pixels[i*height+j] =(int)(r*NTSC_R + g*NTSC_G + b*NTSC_B);
                    
                }
            }
            
            } 
        catch(Exception e){ 
            e.printStackTrace();
            System.out.println("\nBuilding image... Some problem has occurred. Please try again"); 
        }
    };
    
    
    public ImageClass(int[] img, int l, int w){ // CONSTRUCTOR: array de int
        img_size = l * w; //Tamaño de imagen
        height = l;       //Largo de la imagen
        width = w;        //Ancho de la imagen
        pixels = img;     //Array de bytes de la imagen
        //colorValues = new int[256];
    }
    
    
    public ImageClass(ImageClass other){   // CONSTRUCTOR DE COPIA
        img_size = other.img_size; //Tamaño de imagen
        height = other.height;     //Largo de la imagen
        width = other.width;       //Ancho de la imagen
        image = other.image;       //Imagen
        pixels = other.pixels;     //Array de pixeles
        //colorValues = other.colorValues;
    }
    
 /*   
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
            pixels[i]=0;
        }
        for (int i=0;i<img_size;i++){
            pixels[i] = image.get(i);
        }
        return pixels;
    };
    */
   
    
    public BufferedImage get_picture(){
        return picture;
    }
    
    public int getPos(int x, int y){ //Devuelve la posicion de la imagen de coordenadas XY
        return (x-1)*img_size+y-1;
    }
    
    
    
    public int getSize(){  // Devuelve tamaño de imagen
        return img_size;
    }
    
    
    public int getHeight(){  // Devuelve largo de la imagen
        return height;
    }
    
    
    public int getWidth(){  // Devuelve ancho de la imagen
        return width;
    }
    
    
    public void updateImage(){ //Recoge todas las funciones necesarias que actualicen una imagen
        //getColorValues();
        //Otras funciones que hagan falta mas adelante//
    }
        
        
    public void showHistogram(){ // Muestra el histograma de color de la imagen
        //SACAR VENTANA???
    }
    
    public int imgBrightness(){ // Devuelve el brillo (media de color) de la imagen
        int suma = 0;
        for(int i=0;i<img_size;i++){
            suma = suma + pixels[i];
        }
        return (suma/img_size);           
    }

    
    
    public int imgContrast(){  // Devuelve el contraste (Desv. Tipica)
        int average = this.imgBrightness();
        double c = 0;
        Double contrast = new Double(c);
        
        for(int i=0;i<img_size;i++){
            contrast = contrast + (pixels[i] - average) * (pixels[i] - average);
        }
        contrast = contrast / img_size;
        contrast = Math.sqrt(contrast);
        return contrast.intValue();
    }
    
    
    
    public int[] imgNegative(){  // Devuelve el negativo (complemento) de la imagen
        int[] neg_img = new int[img_size];
        int L = 255;
        
        for (int i=0;i<img_size;i++){
            neg_img[i] = pixels[i] - L;
        }
        return neg_img;
    }
    
    
    public int[] imgBinary(int threshold){   // Devuelve la imagen binarizada a partir de un umbral
        int[] bin_img = new int[img_size];
        
        for (int i=0;i<img_size;i++){
            if (pixels[i] >= threshold)
                bin_img[i] = 255;
            else bin_img[i] = 0;
        }
        return bin_img;
    }
    
    
    public int[] linealTrans(int A, int B){  //Aplica la transformación lineal de forma Y = AX+B
       int[] newimg = new int[img_size];
         for (int i=0;i<img_size;i++){
            newimg[i] = pixels[i] * A + B;
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
            newimg[i] = pixels[i] * newCo + newBr;
        }
        return newimg;
    }
    
    
    public int[] ROI(int x, int y, int len, int wid){  // Genera una subimagen de la imagen actual
        int[] roi = new int[len*wid];
        int ind = 0;
        for(int i=0; i<img_size; i++){
            if(i>= (x-1)*img_size+y-1 && i <= ((x+len)-1)*img_size+(y+wid)-1){
                roi[ind]=pixels[i];
                ind++;
            }
        }
        return roi;
    }
    
    
    
    public int colorInPos(int x, int y){  // Devuelve el valor de color del punto X,Y
        return pixels[getPos(x,y)];
    }
    
    
    public int getEnthropy(){  //Devuelve la entropia de la imagen
        Double ProbI;
	Double enthropy = 0.0;
	for( int i = 0; i < 256; i++ ) {
            ProbI = (double) pixels[i] / img_size; // pixels ?? 
            if (ProbI > 0.0)
                enthropy += ProbI * (Math.log(ProbI) / Math.log(2.0));	
        }
	enthropy *= -1;
        return enthropy.intValue();
    }

    public BufferedImage escalaGrises() {
        
        BufferedImage eg = picture;
        
        //Variable que almacenará los píxeles
        Color colorAux;
                 
        //Recorremos la imagen píxel a píxel
        for( int i = 0; i < width; i++ ){
            for( int j = 0; j < height; j++ ){
                //Almacenamos el color del píxel
                colorAux = new Color(pixels[i*height+j],pixels[i*height+j],pixels[i*height+j]);
                //Asignamos el nuevo valor al BufferedImage
                eg.setRGB(i, j, colorAux.getRGB());
            }
        }
        //Retornamos la imagen
        picture = eg;
        return picture;
    }
    
    public BufferedImage Roi(int ix, int iy, Rectangle rec){
    
        BufferedImage roi = new BufferedImage(rec.width,rec.height,BufferedImage.TYPE_INT_RGB);
        
        int dx = ix;
        int dy = iy;
       
        
        for(int i = 0; i<rec.width; i++){
            for(int j = 0; j<rec.height; j++){
                
                    roi.setRGB(i, j, picture.getRGB(i + dx, j + dy));
                
            }
        }
        
        picture = roi;
        return roi;
    }
}


