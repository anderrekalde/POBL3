package packInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 

public class AnimacionTexto extends JPanel {
    private int x;
    private int y;

    private static Image nombres;
    private static Image logo;
    private static Image fondo;
 
    /**
     * @param args
     */
    public AnimacionTexto() {
        x = 150;
        y = 600;
        
        try {                
            nombres = ImageIO.read(new File("iconos/nombre.png"));
            logo = ImageIO.read(new File("iconos/logo.png"));
            fondo = ImageIO.read(new File("iconos/fondoCreditos.jpg"));
         } catch (IOException ex) {
              // handle exception...
         }
       
      
    }
    public void paint(Graphics g) {
    	 
    	
        
        setOpaque(false);
        g.drawImage(fondo, 0, 0, null);
        g.drawImage(logo, x+225, y, null);
        g.drawImage(nombres, x, y+100, null);
        
        super.paint(g);
      
       
    }
    public void start() {
    	
        while(true){
            while(x <= getWidth()) {
                y--;
                x = getHeight()/2;
                if(y==-900){
                	MainFrame.CierraCreditos();
                }
                
                repaint();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }   
        }
    	
    }
   
    
    }
    
