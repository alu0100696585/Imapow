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
   
    public static final int MAX = 999999;
    
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
    
    public int getGris(boolean modo){ // Devuelve el valor de gris maximo si es verdadero y el minimo si es false

        int comp;
        
        comp = modo ? 0 : MAX;
        
        for(int i=0; i<pixels.length; i++){
            if(modo){
                if(comp < pixels[i]){
                    comp = pixels[i];
                }
            }
            else{
                if(comp > pixels[i]){
                    comp = pixels[i];
                }
            }
        }
        
        return comp;
        
    }
    
    
    public int[] getColorValues(){ //Devuelve los valores del histograma de color
        for (int i=0;i<256;i++){
            colorValues[i]=0;
        }
        for (int i=0;i<img_size;i++){
            colorValues[pixels[i]]+=1;
        }
        return colorValues;
    };
    
    
    public int[] getAcumulativeValues(){// Devuelve los valores del histograma acumulativo.
        int [] acumulativeValues = this.getColorValues();

        for (int i=1;i<256;i++){
            acumulativeValues[i] += acumulativeValues[i-1];
        }
        return acumulativeValues;
    }

    public void setPicture(BufferedImage img) {
        
        try{
            
            //Calcular alto, ancho y tamaño
            height = img.getHeight();
            width = img.getWidth();
            img_size = height*width;
            picture = img;
            
            
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
    }
    
   
    
    public BufferedImage get_picture(){
        return picture;
    }
    
    public int getPos(int col, int fila){ //Devuelve la posicion de la imagen de coordenadas XY
        return col*height+fila;
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

        
    public int imgBrightness(){ // Devuelve el brillo (media de color) de la imagen
        int suma = 0;
        for(int i=0;i<img_size;i++){
            suma = suma + pixels[i];
        }
        return (suma/img_size);           
    }

    
    
    public int imgContrast(){  // Devuelve el contraste (Desv. Tipica)
        int average = this.imgBrightness();
        double contrast = 0;
        //Double contrast = new Double(c);
        
        for(int i=0;i<img_size;i++){
            contrast = contrast + (pixels[i] - average) * (pixels[i] - average);
        }
        
        contrast = contrast / img_size;
        contrast = Math.sqrt(contrast);
        return (int) contrast;
    }
    
    
    
    public BufferedImage imgNegative(){  // Devuelve el negativo (complemento) de la imagen
        int[] trans_table = new int[256];
        int L = 256;
        
        for (int i=0;i<256;i++){
            trans_table[i] = L - i;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage imgBinary(int threshold){   // Devuelve la imagen binarizada a partir de un umbral
        int[] trans_table = new int[256];
        
        for (int i=0;i<256;i++){
            if (i >= threshold)
                trans_table[i] = 256;
            else trans_table[i] = 0;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage linealTrans(int A, int B){  //Aplica la transformación lineal de forma Y = AX+B
       int[] trans_table = new int[256];
         for (int i=0;i<256;i++){
            trans_table[i] = i * A + B;
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage imgSetByC(int bright, int contrast){  // Cambia el brillo y contraste de la imagen
        int currentB = this.imgBrightness();
        int currentC = this.imgContrast();
        int[] trans_table = new int[256];
           
        double newCo = (double)contrast / (double)currentC;
        double newBr = bright - (newCo*currentB);
        
        for (int i=0;i<256;i++){
            trans_table[i] = (int)(i * newCo + newBr);
        }
        
        //System.out.println(currentB + " " + currentC + " / " + bright + " " + contrast);
        
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
        double ProbI;
	double enthropy = 0.0;
        int [] hist = this.getColorValues();
        
	for( int i = 0; i < 256; i++ ) {
            ProbI = (double) hist[i] / img_size; // ColorValues
            if (ProbI > 0.0)
                enthropy += ProbI * (Math.log(ProbI) / Math.log(2));	
        }
	enthropy *= -1;
        return (int)enthropy;
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
                Color colorbw = new Color(pixels[getPos(i+dx, j+dy)], pixels[getPos(i+dx, j+dy)], pixels[getPos(i+dx, j+dy)]);
                roi.setRGB(i, j, colorbw.getRGB());              
            }
        }      
        return roi;
    }
    
    public BufferedImage linealTransZones(int n_trans, int[] init_zones, int[] end_zones, double[] A, double[] B){
    // Aplica transformaciones lineales con parametros A[j] y B[j] según en que rango está su valor de color (init_zobes y end_zones)
    // n_trans es el número de tramos en los que se va a hacer transformación. El valor menor del rango va en init_zone 
    // y el mayor en end_zones.
        int[] trans_table = new int[256];
            
        for(int i=0;i<256;i++){
            for(int j=0;j<n_trans;j++){
                if(i >= init_zones[j] && i <= end_zones[j]){
                    trans_table[i] = (int)Math.round(i * A[j] + B[j]);
                    //System.out.println(i + " " + trans_table[i] + " " + A[j] + " " + B[j] + " " + (i * A[j] + B[j]));
                }
            }
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    /*
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
    */
    
    public BufferedImage equalization(){  // Devuelve la imagen con el histograma equalizado
        int[] trans_table = new int[256];
        int [] acHisto = this.getAcumulativeValues();
        
        for (int i=0;i<256;i++){
            trans_table[i] = (int) Math.max(0, Math.round(((double)256/(double)img_size)*acHisto[i])-1);
            //System.out.println(trans_table[i]);
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage paintInRed(int []pix, int h, int w, int threshold){
        Color red = new Color(255, 0, 0); // Color rojo

        BufferedImage newimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                if(pix[getPos(i,j)] < threshold){
                    Color colorbw = new Color(pix[getPos(i,j)], pix[getPos(i,j)],pix[getPos(i,j)]);
                    newimg.setRGB(i, j, colorbw.getRGB());
                }
                else {
                    newimg.setRGB(i, j, red.getRGB());
                    }
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
    
    
    
    public BufferedImage gamma(double g){   // Corrección gamma de una imagen
        int [] trans_table = new int[256];
        double a, b;
        for (int i=0;i<256;i++){
            a = (double)i/255;
            b = Math.pow(a,g);
            trans_table[i] = (int) (b*255);
        }
        return TRANSFORM(pixels,trans_table);
    }
    
    
    public BufferedImage HistogramSpecification(ImageClass im2){ // Especificacion del histograma con otra imagen de muestra
        int[] imhisto = this.getAcumulativeValues();
        int[] imhisto2 = im2.getAcumulativeValues();
        
        double []imh1 = new double[256];
        double []imh2 = new double[256];
        
        for(int i=0;i<256;i++){
            imh1[i]=(double)imhisto[i];
            imh2[i]=(double)imhisto2[i];
        }
        int[] trans_table = new int[256];
        
        for (int i=0;i<256;i++){
            imh1[i]/=img_size;
            imh2[i]/=im2.img_size;
        }
        
        for (int i=0;i<256;i++){
            for (int j=0;j<256;j++){
                if (imh1[i] == imh2[j]){
                    trans_table[i] = j;
                    break;
                }
                else if (imh1[i] < imh2[j]){
                    if(j != 0 && j != 255){
                        double aux = Math.abs(imh1[i] - imh2[j]);
                        double aux2 = Math.abs(imh1[i] - imh2[j-1]);
                        if(aux < aux2){
                            trans_table[i] = j;
                            break;
                        }
                        else {
                            trans_table[i] = j-1;
                            break;
                        }
                    }
                    else{
                        trans_table[i] = j;       
                    }
                }
            }
        }
        return TRANSFORM(pixels, trans_table);
    }
    
    public BufferedImage mirrorVertical(){
        
        BufferedImage newimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int h = height;
        int jfin = h - 1;
        
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                newimg.setRGB(i, jfin, picture.getRGB(i, j));
                jfin--;
            }
            jfin = h - 1;
        }
        
        return newimg;
    }
    
    public BufferedImage mirrorHorizontal(){
        
        BufferedImage newimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int w = width;
        int h = height;
        
        int ifin = w -1;
        
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                newimg.setRGB(ifin, j, picture.getRGB(i, j));
           }
            ifin--;
        }
        
        return newimg;
    }
    public BufferedImage traspuesta(){
        
        BufferedImage newimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                newimg.setRGB(j, i, picture.getRGB(i, j));
           }
        }
        
        return newimg;
    }
    
    public BufferedImage Scale(float incH, float incV, boolean mode){
    //Aumenta o reduce la imagen a la escala inc. Vecino + proximo (mode=0). Media (mode=1)
        //Resize de la imagen resultado
        int newWidth =Math.round(width * incH); 
        int newHeight=Math.round(height * incV);
        BufferedImage newimg = new BufferedImage(newWidth,height,BufferedImage.TYPE_INT_RGB);
        BufferedImage newimg2 = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
        
        if (mode == false){ //False equivale al metodo de interpolacion de vecino mas proximo
            for(int i = 0; i<width; i++){
                for(int j = 0; j<height; j++){
                    if(incH >= 1){
                        if(i%Math.round(incH) == 0){
                            newimg.setRGB(Math.round(i*incH), j, picture.getRGB(i, j));
                        }
                        else newimg.setRGB(Math.round(i*incH), j, newimg.getRGB(i-1, j));
                    }
                    //else /*reducir*/ 
               }
            }
            
            for(int i = 0; i<newWidth; i++){
                for(int j = 0; j<height; j++){
                    if(incV >= 1){
                        if(j%Math.round(incV) == 0){
                            newimg2.setRGB(i, Math.round(j*incV), newimg.getRGB(i, j));
                        }
                        else newimg2.setRGB(i, Math.round(j*incV), newimg.getRGB(i, j-1));
                    }
                    //else /*reducir*/ 
               }
            }
            
        
/*        //INTERPOLACION VECINO MAS PROXIMO
            for(int i = 0; i<newWidth; i++){
                 for(int j = 0; j<newHeight; j++){
                    if(inc > 1 && !(Math.round(i%inc) == 0 || Math.round(j%inc) == 0)){ 
                        //System.out.println("ENTRA");
                        if(j==0){
                            newimg.setRGB(i, j, newimg.getRGB(i,j-1));
                        }
                        else newimg.setRGB(i, j, newimg.getRGB(i-1,j));
                        }
                }*/
            }
 
        return newimg2;
    }

    
    public BufferedImage toBuffImg(int[] pix, int h, int w){
        BufferedImage newimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                Color bwcolor = new Color(pix[getPos(i,j)],pix[getPos(i,j)],pix[getPos(i,j)]);
                newimg.setRGB(i, j, bwcolor.getRGB());
            }
        }
        return newimg;
    }
    
    
    public BufferedImage TRANSFORM(int[] pix, int[] table){ //Utiliza la matriz de transformacion para generar la nueva imagen
        int [] newimg = new int[img_size];
        
        for (int i=0;i<256;i++){
            if (table[i]>255)
                table[i] = 255;
            if (table[i]<0)
                table[i] = 0;
        }

        for(int i=0;i<img_size;i++){
            newimg[i] = table[pix[i]];
            //System.out.println(pix[i] + " " + table[pix[i]]);
        }
        return toBuffImg(newimg, height, width);
    }
    
    
}



//COMENTARIO RANDOM