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
    private int colorValues[];     //Array de colores del histograma 
    
    //constantes para la conversion a gris
    public static final double NTSC_R = 0.299;
    public static final double NTSC_G = 0.587;
    public static final double NTSC_B = 0.114;
    
    
    
    public ImageClass(BufferedImage img){  //CONSTRUCTOR: Carga la imagen de la ruta seleccionada
   
        picture = img;
        image = new ArrayList<Color>();
        colorValues = new int[256];
  
        
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
        colorValues = new int[256];
    }
    
    
    public ImageClass(ImageClass other){   // CONSTRUCTOR DE COPIA
        img_size = other.img_size; //Tamaño de imagen
        height = other.height;     //Largo de la imagen
        width = other.width;       //Ancho de la imagen
        image = other.image;       //Imagen
        pixels = other.pixels;     //Array de pixeles
        colorValues = other.colorValues;
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
    
*/
    public int[] getPixels() {  //Devuelve el array de valores de color de la imagen
        return pixels;
    }
    
    
    public int[] getColorValues(){ //Devuelve los valores del histograma de color
        for (int i=0;i<255;i++){
            colorValues[i]=0;
        }
        for (int i=0;i<img_size;i++){
            colorValues[pixels[i]]+=1;
        }
        return colorValues;
    };
    
    
    public int[] getAcumulativeValues(){// Devuelve los valores del histograma acumulativo.
        int [] acumulativeValues = new int[256];
        for (int i=0;i<255;i++){
            acumulativeValues[i]=0;
        }
        for (int i=0;i<img_size;i++){
            acumulativeValues[pixels[i]]= acumulativeValues[pixels[i]-1] + colorValues[pixels[i]]+1;
        }
        return acumulativeValues;
    }
    
   
    
    public BufferedImage get_picture(){
        return picture;
    }
    
    public int getPos(int x, int y){ //Devuelve la posicion de la imagen de coordenadas XY
        return x*width+y;
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
    
    
    public BufferedImage toBuffImg(int[] pix, int h, int w){
        BufferedImage newimg = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                newimg.setRGB(i, j, pix[getPos(i,j)]);
            }
        }
        return newimg;
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
    
    
    
    public BufferedImage imgNegative(){  // Devuelve el negativo (complemento) de la imagen
        int[] trans_table = new int[256];
        int L = 255;
        
        for (int i=0;i<255;i++){
            trans_table[i] = i - L;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage imgBinary(int threshold){   // Devuelve la imagen binarizada a partir de un umbral
        int[] trans_table = new int[256];
        
        for (int i=0;i<255;i++){
            if (i >= threshold)
                trans_table[i] = 255;
            else trans_table[i] = 0;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage linealTrans(int A, int B){  //Aplica la transformación lineal de forma Y = AX+B
       int[] trans_table = new int[256];
         for (int i=0;i<255;i++){
            trans_table[i] = i * A + B;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage imgSetByC(int bright, int contrast){  // Cambia el brillo y contraste de la imagen
        int currentB = this.imgBrightness();
        int currentC = this.imgContrast();
        int[] trans_table = new int[256];
           
        int newCo = contrast / currentC;
        int newBr = bright - (newCo*currentB);
        
        for (int i=0;i<255;i++){
            trans_table[i] = i * newCo + newBr;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
  /*  
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
    */
    
    
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

    public BufferedImage escalaGrises() {   // Devuelve la imagen en escala de grises
        
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
        return eg;
    }
    
    public BufferedImage Roi(int ix, int iy, Rectangle rec){    //Devuelve la imagen recortada dentro de rec.
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
    
    
    
    public BufferedImage linealTransZones(int n_trans, int[] init_zones, int[] end_zones, int[] A, int[] B){
    // Aplica transformaciones lineales con parametros A[j] y B[j] según en que rango está su valor de color (init_zobes y end_zones)
    // n_trans es el número de tramos en los que se va a hacer transformación. El valor menor del rango va en init_zone 
    // y el mayor en end_zones.
        int[] trans_table = new int[256];
            
        for(int i=0;i<255;i++){
            for(int j=0;j>n_trans;j++){
                if(i > init_zones[j] && i < end_zones[j]){
                    trans_table[i] = i * A[j] + B[j];
                }
            }
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    public int imgMaxColor(){ // Devuelve el valor máximo de color de la imagen
        int max = 0;
         for (int i=0;i<img_size;i++){
             if(pixels[i]>max){
             max = pixels[i];
             }
         }
        return max;
    }
    
    public int imgMinColor(){ // Devuelve el valor minimo de color de la imagen
        int min = 999999;
         for (int i=0;i<img_size;i++){
             if(pixels[i]<min){
             min = pixels[i];
             }
         }
        return min;
    }
    
    public BufferedImage equalization(){  // Devuelve la imagen con el histograma equalizado
        int[] trans_table = new int[256];
        int [] acHisto = this.getAcumulativeValues();
        
        for (int i=0;i<255;i++){
            trans_table[i] = Math.max(0, Math.round((256/img_size)*acHisto[i])-1);
            //System.out.println(trans_table[i]);
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage paintInRed(int []pix, int h, int w, int threshold){
        Color red = new Color(255, 0, 0); // Color rojo

        BufferedImage newimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if(pix[i] < threshold){
                    newimg.setRGB(i, j, pix[getPos(i,j)]);
                }
                else newimg.setRGB(i, j, red.getRGB());
            }
        }
        return newimg;
    }
    
    public BufferedImage compare(ImageClass im, int threshold){  
    //Devuelve la imagen difetencia de comparar la imagen actual con la imagen im, con un umbral de error threshold
        int [] newimg = new int[img_size];
        if (this.img_size == im.img_size){
            int [] pixels2 = im.getPixels();
            for (int i=0;i<img_size;i++){
                    newimg[i] = Math.abs(this.pixels[i] - pixels2[i]);
            }
        }
        else System.out.println("ERROR: Las imágenes deben ser del mismo tamaño");
        
        return paintInRed(newimg, height, width, threshold);
    }
    
    
    
    public BufferedImage gamma(int g){   // Corrección gamma de una imagen
        int [] trans_table = new int[256];

        for (int i=0;i<255;i++){
            trans_table[i] = (int) Math.pow((double)(i/255), (double)g)*255;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage HistogramSpecification(ImageClass im2){ // Especificacion del histograma con otra imagen de muestra
        int[] imhisto = this.getAcumulativeValues();
        int[] imhisto2 = im2.getAcumulativeValues();
        int[] trans_table = new int[256];
        
        for (int i=0;i<255;i++){
            imhisto[i]/=img_size;
            imhisto2[i]/=im2.img_size;
        }
        
        for (int i=0;i<255;i++){
            for (int j=0;j<255;j++){
                if (imhisto[i] == imhisto2[j]){
                    trans_table[i] = j;
                }
                else if (imhisto[i] < imhisto[j]){
                    if(j != 0 && j != 255){
                        int aux = Math.abs(imhisto[i] - imhisto2[j]);
                        int aux2 = Math.abs(imhisto[i] - imhisto2[j-1]);
                        if(aux < aux2){
                            trans_table[i] = j;
                        }
                        else trans_table[i] = j-1;
                    }
                    else{
                        trans_table[i] = j;
                            
                    }
                }
            }
        }
        return TRANSFORM(pixels, trans_table);
    }
    
    
    
    
    public BufferedImage TRANSFORM(int[] pix, int[] table){ //Utiliza la matriz de transformacion para generar la nueva imagen
        int [] newimg = new int[img_size];
        for(int i=0;i<img_size;i++){
            newimg[i] = table[pix[i]];
        }
        return toBuffImg(newimg, height, width);
    }
    
    
}



