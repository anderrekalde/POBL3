package packCustomWindow;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Toolkit;

public class PopUpTiempo extends PopUp {

	public PopUpTiempo(String pTitleName, String pGifName, int pTime){
		super(pTitleName, pGifName);
		
		(new Timer(pTime, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				
			}
		})).start();
		
	}
	
}
