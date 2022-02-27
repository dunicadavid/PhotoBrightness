package Brightness;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Producer extends Main implements Runnable{
	Buffer buff; 		//variabila de tip Buffer ce faciliteaza transferul controlat de date intre threaduri
	
	//Constructor
	Producer(Buffer buffer) {
		this.buff = buffer;
	}
	//pentru testare metoda abstracta din clasa abstracta
	public void abstractMethod() {}
	
	
	@Override
	public void run() {

		long startTime = System.currentTimeMillis(); 	//se incepe cronometrul trimiterii de date
		
		try {									//Introducem in variabila photo transformarea in ImageIO a Fileului
			for (int i = 0 ; i < 4 ; i++){					//se executa de 4 ori pentru ca trimitem cate un sfert prin buffer
				int start = (int) Math.round(i * (double)Main.getPhotoHeight()/4); 				//start pentru inceputul zonei de copiere a pozei in buffer
				int end = Math.min((int) Math.round((i + 1) * (double)Main.getPhotoHeight()/4), Main.getPhotoHeight());			//end pentru finalul zonei de copiere in buffer
				BufferedImage photo = ImageIO.read(Main.photoPath);												//se citeste cate un sfert din imagine			
				BufferedImage send = photo.getSubimage(0, start, Main.getPhotoWidth(), end - start);			//se introduce in send cate un sfert din imagine
				buff.put(send);								//se trimite sfertul din send in buffer pentru a fi preluat de consumer
			}
			
		} catch(IOException e) {
			System.out.println("The error: " + e); //exceptie in cazul in care fisierul nu este recunoscut
		}
		
		long finalTime = System.currentTimeMillis(); 	//se inchide cronometrul
		long workingTime = finalTime - startTime;		//se calculeaza diferenta dintre finish si start pentru a vedea valoarea de timp a executiei
		
		System.out.println("\n<Columns Send Time>----------------------<");
		System.out.println("  -> Start time: " + startTime + " ms");
		System.out.println("  -> Final time: " + finalTime + " ms");
		System.out.println("  -> Working time = " + workingTime + " ms");
		System.out.println("-------------------------------------------<");
		
	}
}