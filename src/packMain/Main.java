package packMain;

import java.io.IOException;

import com.leapmotion.leap.Controller;

import packDigital.SeriekoLineaKontrolatzailea;
import packInterface.MainFrame;
import packLeapMotion.GestureController;

public class Main {

	static int contrase�a;
	public static void main(String[] args) {
		
		SeriekoLineaKontrolatzailea digital= new SeriekoLineaKontrolatzailea(null);
		do{
		try {
			contrase�a=digital.leerContrase�a();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		if(contrase�a==1){
		MainFrame mainWindow = new MainFrame();
		
		GestureController listener = new GestureController(mainWindow);
		Controller controller = new Controller();
		
		controller.addListener(listener);
		
		System.out.println("Press Enter to quit...");
		try{
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		controller.removeListener(listener);
	}}while(contrase�a==1);
		
	}
}
