package Brightness;

import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Consumer extends Main implements Runnable{
	Buffer buff; 																//variabila de tip Buffer ce faciliteaza transferul controlat de date intre threaduri
	int [][] photo = new int[Main.getPhotoWidth()][Main.getPhotoHeight()];  	//initializare matrice pixeli
	BufferedImage rgbPhoto = new BufferedImage(Main.getPhotoWidth(), Main.getPhotoHeight(), BufferedImage.TYPE_INT_RGB);	//imitializare matrice de pixeli pe format RGB
	
	//constructor
	Consumer(Buffer buffer) {
		this.buff = buffer;
	}
	
	//functie utilizata pentru executarea corecta de brightness (nu dorim iesirea din intervalul 0-255)
	public static int truncate(int value){
		if(value < 0){
			value = 0;
		} else if(value > 255){
			value = 255;
		}
		return value;
	}

	@Override
	public void run() {
		
		long startTime, finalTime, workingTime;
		BufferedImage buffImage;											//variabila folosita pentru primirea sfertului de imagine
		
		startTime = System.currentTimeMillis(); 							//se incepe cronometrul primirii de date
		
		for(int i = 0 ; i < 4 ; i++) {										//primesc cate un sfert din imagine prin buffer
			buffImage = buff.get();
			int start = (int) Math.round(i * (double)Main.getPhotoHeight()/4); 			//ma folosesc de start pentru a prelua exact portiunea din imagine care corespunde bufferului
			int end = Math.min((int) Math.round((i + 1) * (double)Main.getPhotoHeight()/4), Main.getPhotoHeight()); 		//ma folosesc de end pentru a prelua exact portiunea din imagine care corespunde bufferului
			for(int j = 0 ; j < Main.getPhotoWidth() ; j++)
				for(int k = start ; k < end ; k++)
					photo[j][k] = buffImage.getRGB(j,k - start);						//populez matricea de pixeli cu valorile trimise prin buffer
		}
		
		finalTime = System.currentTimeMillis();								//se termina cronometrul primirii de date
		workingTime = finalTime - startTime;								//se calculeaza diferenta dintre finish si start pentru a vedea valoarea de timp a executiei
		
		System.out.println("\n<Columns Recive Time>--------------------<");
		System.out.println("  -> Start time: " + startTime + " ms");
		System.out.println("  -> Final time: " + finalTime + " ms");
		System.out.println("  -> Working time = " + workingTime + " ms");
		System.out.println("-------------------------------------------<");
		
		startTime = System.currentTimeMillis();								//se incepe cronometrul modificarii de date
		
		for(int i = 0; i < Main.getPhotoWidth(); i++) {
			for(int j = 0; j < Main.getPhotoHeight(); j++) {

				Color color = new Color(photo[i][j], true); 				//se utilizeaza un obiect color pentru a segmenta pixeli in RGB
				//segmentarea rosu verde albastru 
				int red = color.getRed();	
				int green = color.getGreen();
				int blue = color.getBlue();	
				//modificarea acestor 3 valori pentru a satisface brightnessul dorit
				red = truncate(red + Integer.parseInt(Input.getBrightnessLevel()));	
				green = truncate(green + Integer.parseInt(Input.getBrightnessLevel()));
				blue = truncate(blue + Integer.parseInt(Input.getBrightnessLevel()));
				
				color = new Color(red, green, blue); //salvam noile valori ale culorilor 
				
				photo[i][j] = color.getRGB(); //refacem noul pixel din format rgb
				
				rgbPhoto.setRGB(i, j, photo[i][j]); //modifica pixelul din poza nu noul pixel
			}
		}
		
		finalTime = System.currentTimeMillis();								//se termina cronometrul modificatii de date
		workingTime = finalTime - startTime;								//se calculeaza diferenta dintre finish si start pentru a vedea valoarea de timp a executiei
		
		System.out.println("\n<Columns Modify Time>--------------------<");
		System.out.println("  -> Start time: " + startTime + " ms");
		System.out.println("  -> Final time: " + finalTime + " ms");
		System.out.println("  -> Working time = " + workingTime + " ms");
		System.out.println("-------------------------------------------<");
		
		startTime = System.currentTimeMillis();								//se incepe cronometrul scrierii de date
		
		try {
			File pictureOutFile = new File(getPath() + "\\" + getPhotoOutput() + ".bmp");
			ImageIO.write(rgbPhoto, "bmp", pictureOutFile);					//se executa crearea de imagine modificata
		} catch(IOException e) {
			System.out.println("The error: " + e);
		}
		
		finalTime = System.currentTimeMillis();								//se termina cronometrul scrierii de date
		workingTime = finalTime - startTime;								//se calculeaza diferenta dintre finish si start pentru a vedea valoarea de timp a executiei
		
		System.out.println("\n<Columns Write Time>--------------------<");
		System.out.println("  -> Start time: " + startTime + " ms");
		System.out.println("  -> Final time: " + finalTime + " ms");
		System.out.println("  -> Working time = " + workingTime + " ms");
		System.out.println("------------------------------------------<");
	}

}
