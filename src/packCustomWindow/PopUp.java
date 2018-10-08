package packCustomWindow;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUp extends JFrame {
	//private JFrame window;
	private ImageIcon imageIcon;
	private Image image;
	
	
	public PopUp(String pTitleName, String pGifName){
		//window = new JFrame(pTitleName);
		imageIcon = new ImageIcon("iconos/MANO.png");
		image = imageIcon.getImage();
		
		
		
		this.setIconImage(image);
		imageIcon = new ImageIcon ("iconos/gif/"+pGifName+".gif");
		JLabel background = new JLabel(imageIcon);
		
		imageIcon.getImage().flush();
		
		this.setLocation(1000, 100);
		this.setSize(620, 360);
		this.getContentPane().setBackground(Color.white);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(background);
}
}
