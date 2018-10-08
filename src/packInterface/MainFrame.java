package packInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import packCustomWindow.PopUp;
import packCustomWindow.PopUpTiempo;
import packMain.Main;

public class MainFrame {
	JFrame ventana;
	static JFrame frameCreditos;
	JFrame help, musica, luz, persiana;
	JLabel conectado, estadoConexion;
	
	ImageIcon imagenLuzOff = new ImageIcon ("iconos/luzoff.png");
	ImageIcon imagenLuzOn = new ImageIcon ("iconos/luzon.png");
	ImageIcon imagenPersianaOff = new ImageIcon ("iconos/persianaoff.png");
	ImageIcon imagenPersianaOn = new ImageIcon ("iconos/persianaon.png");
	ImageIcon imagenMusicaOff = new ImageIcon ("iconos/musicaoff.png");
	ImageIcon imagenMusicaOn = new ImageIcon ("iconos/musicaon.png");
	
	JButton botonLuz,botonPersiana, botonMusica, botonLogo, botonWeb, botonHelp;
	Font font = new Font("Arial", Font.BOLD, 18); 
	//ENCENDIDO APAGADO
	boolean btnLuz, btnPersiana, btnMusica;
	//DISPONIBILIDAD
	boolean conexion, persianaDisp, luzDisp, musicaDisp;
	Color color=new Color(0, 99, 177);
	
	
	private PopUp popup;
	
	
	public MainFrame(){
		cargarProp();
		ventana = new JFrame("Ratón Virtual");
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon ImageIcon = new ImageIcon("./iconos/MANO.png");
        Image image = ImageIcon.getImage();
       
		
        ventana.setIconImage(image);
        ventana.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try {
					guardarProp();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
	}
	
	
	private void guardarProp() throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream out = new FileOutputStream("config.properties");
		FileInputStream in = new FileInputStream("config.properties");
		Properties props = new Properties();

		props.load(in);
		in.close();
		props.setProperty("btnLuz", Boolean.toString(btnLuz));
		props.setProperty("btnPersiana", Boolean.toString(btnPersiana));
		props.setProperty("btnMusica", Boolean.toString(btnMusica));
		
		props.setProperty("persianaDisp", Boolean.toString(persianaDisp));
		props.setProperty("luzDisp", Boolean.toString(luzDisp));
		props.setProperty("musicaDisp", Boolean.toString(musicaDisp));
		props.store(out, null);
		out.close();
			
			

		}
	
	
	private void cargarProp() {
		
		InputStream input = null;

		try {
			Properties prop = new Properties();
			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);
			btnLuz=Boolean.parseBoolean(prop.getProperty("btnLuz"));
			btnPersiana=Boolean.parseBoolean(prop.getProperty("btnPersiana"));
			btnMusica=Boolean.parseBoolean(prop.getProperty("btnMusica"));
			persianaDisp=Boolean.parseBoolean(prop.getProperty("persianaDisp"));
			luzDisp=Boolean.parseBoolean(prop.getProperty("luzDisp"));
			musicaDisp=Boolean.parseBoolean(prop.getProperty("musicaDisp"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	  }
	

	private Container crearPanelVentana() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new BorderLayout());
	
		panel.add(crearPanelDcha(),BorderLayout.EAST);
		panel.add(crearPanelBotones(), BorderLayout.CENTER );
		panel.add(crearPanelArriba(), BorderLayout.NORTH);
		return panel;
	}

	
	private Component crearPanelDcha() {
		// TODO Auto-generated method stub
		int alto=1080, ancho=720;
		JPanel panel = new JPanel(new BorderLayout()){
			 @Override
		        protected void paintComponent(Graphics grphcs) {
		            super.paintComponent(grphcs);
		            Graphics2D g2d = (Graphics2D) grphcs;
		            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                    RenderingHints.VALUE_ANTIALIAS_ON);
		            GradientPaint gp = new GradientPaint(0, 0,
		                    color, 0, getHeight(),
		                   Color.WHITE);
		            g2d.setPaint(gp);
		            g2d.fillRect(0, 0, getWidth(), getHeight()); 

		        }};
	
		panel.setPreferredSize(new Dimension(ancho, alto));
		
		
		ImageIcon img = new ImageIcon ("iconos/ayuda.png");
		JLabel background = new JLabel(img);
		

		
		panel.add(background);
		
		return panel;
	}

				
	private Component crearPanelArriba() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(color);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		JPanel panel2= new JPanel(new GridLayout(1,2));
		JPanel panel3= new JPanel(new GridLayout(1,2));
		
		
		conectado=new JLabel("Estado: ");
		conectado.setFont(font);
		conectado.setHorizontalAlignment(JLabel.CENTER);
		
		if(conexion==true){
		estadoConexion= new JLabel("Conectado");
		estadoConexion.setForeground(Color.green);
		}else{
			estadoConexion= new JLabel("Sin Conexión");
			estadoConexion.setForeground(Color.red);
		}
		estadoConexion.setFont(font);
		estadoConexion.setHorizontalAlignment(JLabel.CENTER);
		
		
		ImageIcon muLogo = new ImageIcon ("iconos/logo.png");
		botonLogo=new JButton(muLogo);
		botonLogo.setOpaque(false);
		botonLogo.setContentAreaFilled(false);
		botonLogo.setBorderPainted(false);
		botonLogo.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
						creditos();	  
				 }} );
		ImageIcon supLogo = new ImageIcon ("iconos/support.png");
		botonHelp=new JButton(supLogo);
		botonHelp.setOpaque(false);
		botonHelp.setContentAreaFilled(false);
		botonHelp.setBorderPainted(false);
		botonHelp.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
				 popup = new PopUp("Help", "ayuda");  
				 }} );

		ImageIcon webLogo = new ImageIcon ("iconos/webLogo.png");
		botonWeb=new JButton(webLogo);
		botonWeb.setOpaque(false);
		botonWeb.setContentAreaFilled(false);
		botonWeb.setBorderPainted(false);
		botonWeb.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
				abrirWeb();
				 
				 }} );
		panel.add(panel3,BorderLayout.EAST);
		panel.add(botonWeb, BorderLayout.CENTER);
		panel3.add(botonHelp);
		panel3.add(botonLogo);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		panel2.add(conectado);
		panel2.add(estadoConexion);
		panel.add(panel2, BorderLayout.WEST);
		return panel;
	}

	


	protected void abrirWeb() {
	
		// TODO Auto-generated method stub
		 try {
				Desktop.getDesktop().browse(new URL("http://www.housemotion.com").toURI());
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}


	private void creditos() {
		// TODO Auto-generated method stub
		   Thread thread = new Thread("New Thread") {
			      public void run(){
			     
		 frameCreditos = new JFrame("Creditos");
         frameCreditos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         AnimacionTexto scrolling = new AnimacionTexto();
    
         
         frameCreditos.getContentPane().add(scrolling);
         frameCreditos.setSize(1100, 600);
         frameCreditos.setVisible(true);
         
         scrolling.start();
         
			      }
			      
		   };


		   thread.start();	
				
				
		
	}

	
	

	
	private Container crearPanelBotones() {
		

		JPanel panel = new JPanel(new GridLayout(2,2,0,25))
		{
			 @Override
		        protected void paintComponent(Graphics grphcs) {
		            super.paintComponent(grphcs);
		            Graphics2D g2d = (Graphics2D) grphcs;
		            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                    RenderingHints.VALUE_ANTIALIAS_ON);
		            GradientPaint gp = new GradientPaint(0, 0,
		                    color, 0, getHeight(),
		                   Color.WHITE);
		            g2d.setPaint(gp);
		            g2d.fillRect(0, 0, getWidth(), getHeight()); 

		        }
			 
			
		};
		
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		

		//BOTON LUZ
		
		
		if(btnLuz==false){
			botonLuz=new JButton(imagenLuzOff);
		}else{
			botonLuz=new JButton(imagenLuzOn);
		}
		botonLuz.setOpaque(false);
		botonLuz.setContentAreaFilled(false);
		botonLuz.setBorderPainted(false);	
		if(luzDisp==false){
			botonLuz.setEnabled(false);}
		botonLuz.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				toggleLights();
			
			}
		} );
		
		//BOTON PERSIANA
		
		if(btnPersiana==false){
			botonPersiana=new JButton(imagenPersianaOff);
			}else{
			botonPersiana=new JButton(imagenPersianaOn);
				}
		botonPersiana.setOpaque(false);
		botonPersiana.setContentAreaFilled(false);
		botonPersiana.setBorderPainted(false);
		if(persianaDisp==false){
		botonPersiana.setEnabled(false);}
		botonPersiana.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				togglePersiana();
				
				
			}
		} );
		
		//BOTON MUSICA
		if(btnMusica==false){
			botonMusica=new JButton(imagenMusicaOff);
			}else{
				botonMusica=new JButton(imagenMusicaOn);
				}
		botonMusica.setOpaque(false);
		botonMusica.setContentAreaFilled(false);
		botonMusica.setBorderPainted(false);
		if(musicaDisp==false){
			botonMusica.setEnabled(false);}
		botonMusica.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) { 
				toggleMusic();
			 }
		});
		
		
		
		panel.add(botonMusica);
		panel.add(botonPersiana);
		panel.add(botonLuz);
		return panel;
	}
	

	public void toggleLights(){
		if (btnLuz== true){ 
			botonLuz.setIcon(imagenLuzOff);		
			btnLuz=false;
			popup = new PopUpTiempo("Luz", "LuzEncender",2700);
			/*
			luz = new JFrame("Luz");
			ImageIcon ImageIcon = new ImageIcon("iconos/ayuda.png");
			Image image = ImageIcon.getImage();
			luz.setIconImage(image);
			ImageIcon img = new ImageIcon ("iconos/gif/LuzEncender.gif");
			JLabel background = new JLabel(img);
			
	
			luz.setLocation(1000, 100);
			luz.setSize(620, 331);
			
			
			luz.setVisible(true);
			luz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			luz.add(background);
			*/
		 }else{
			 btnLuz=true;
			 botonLuz.setIcon(imagenLuzOn);	
			popup = new PopUpTiempo("Luz", "LuzApagar",2400);

		 }
	}
	public void togglePersiana(){
		if (btnPersiana== true){ 
			 botonPersiana.setIcon(imagenPersianaOff);
			 btnPersiana=false;
			 popup = new PopUpTiempo("Persiana", "VentanaAbrir",3500);
			 
		 }else{
			 btnPersiana=true;
			 botonPersiana.setIcon(imagenPersianaOn);	
			 popup = new PopUpTiempo("Persiana", "VentanaCerrar", 3000);

		 }
	}
	public void toggleMusic(){
		if (btnMusica== true){ 
			botonMusica.setIcon(imagenMusicaOff);
			 btnMusica=false;
			 popup = new PopUpTiempo("Music","EncenderRadio",3500);
			 /*
			 musica = new JFrame("music");
				ImageIcon ImageIcon = new ImageIcon("iconos/ayuda.png");
				Image image = ImageIcon.getImage();
				musica.setIconImage(image);
				ImageIcon img = new ImageIcon ("iconos/gif/EncenderRadio.gif");
				JLabel background = new JLabel(img);
				
		
				musica.setLocation(1000, 100);
				musica.setSize(620, 331);
				
				
				musica.setVisible(true);
				musica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				musica.add(background);
				*/
		 }else{
			 btnMusica=true;
			 botonMusica.setIcon(imagenMusicaOn);	
			 popup = new PopUpTiempo("Music","ApagarRadio",1700);

		 }
	}


	public void conexion(boolean estado) {
		// TODO Auto-generated method stub
		if(estado==true){
		estadoConexion.setText("Conectado");
		estadoConexion.setForeground(Color.green);
		}else{
			estadoConexion.setText("Desconectado");
			estadoConexion.setForeground(Color.red);
		}
	}


	public static void CierraCreditos() {
		// TODO Auto-generated method stub
		frameCreditos.dispose();
	}
	
}