package Brightness;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main extends ThreadMaster{
	private static int imageHeight;		
	private static int imageWidth;
	public static File photoPath = null;

	// Getter imageHeight
	public static int getPhotoHeight() {
		return imageHeight;
	}
	// Setter imageHeight
	public static void setPhotoHeight(int imageHeight) {
		Main.imageHeight = imageHeight;
	}
	// Getter imageWidth
	public static int getPhotoWidth() {
		return imageWidth;
	}
	// Setter imageWidth
	public static void setPhotoWidth(int imageWidth) {
		Main.imageWidth = imageWidth;
	}
	// Getter photoPath
	public static File getPhoto() {
		return photoPath;
	}
	// Setter photoPath
	public static void setPhoto(File photoPath) {
		Main.photoPath = photoPath;
	}
	
	public static void main(String[] args) {
		if(args.length != 4) { 
			System.out.println("Insuficient number of arguments");
			System.out.println("Insert data from keyboard\n");
		    inputRead();					//metoda din Input pentru citirea de la tastatura
		} else {
			//se citeste din linie de comanda
			String path = args[0]; 			
			String photoInput = args[1];	
			String photoOutput = args[2];	
			String brightnessLevel= args[3];
			//se seteaza valorile citite din linie de comanda in clasa abstracta Input
			setPath(path);					
			setPhotoInput(photoInput);		
			setPhotoOutput(photoOutput);	
			setBrightnessLevel(brightnessLevel);
		}
		
		//Afisare variabile introduse in clasa Input mai sus
		System.out.println("\n<Photo Details>----------------------<");
		System.out.println("   -> The path is: " + getPath());
		System.out.println("   -> Picture name: " + getPhotoInput() + ".bmp");
		System.out.println("   -> Changed picture name: " + getPhotoOutput() + ".bmp");
		System.out.println("   -> Brightness adjustment: " + getBrightnessLevel());
		System.out.println("-------------------------------------<\n");
		
		//Aflam lungime inaltime pentru poza selectata
		try {
			photoPath = new File(getPath() + "\\" + getPhotoInput() + ".bmp"); //Obtinem fileul de la pathul dat mai sus
			BufferedImage photo = ImageIO.read(photoPath);										//Introducem in variabila photo transformarea in ImageIO a Fileului
			
			setPhotoWidth(photo.getWidth());		//setam valoarea latimii
			setPhotoHeight(photo.getHeight());		//setam valoarea inaltimii
			//afisam valorile setate mai sus
			System.out.println("\n<Photo Size>----------------------<");
			System.out.println("  -> Height pixels: " + getPhotoHeight());
			System.out.println("  -> Width pixels:  " + getPhotoWidth());
			System.out.println("----------------------------------<\n");
		} catch(IOException e) {
			System.out.println("The error: " + e); //exceptie in cazul in care fisierul nu este recunoscut
		}
		
		threadMaster(); 	//Apelam functia din ThreadMaster care porneste executia producer consumer
		

		System.out.println("\n<Methods test>----------------------<");
		
		//Testare metoda abstracta din interfata
		ThreadMaster object = new ThreadMaster();
		object.interfaceMessage();
		
		//Testare metoda abstracta din clasa abstracta
		object.abstractMethod();

		//Testare Varargs
		ThreadMaster.varargs("Test1", "Test2", "Test3", "Test4");
		
		System.out.println("------------------------------------<\n");
		

		
	}
}
