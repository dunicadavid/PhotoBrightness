package Brightness;

import java.util.Scanner;

public abstract class Input implements Interface{		//clasa abstracta
	
	//variabile ce sunt setate la inceputul programului prin citire de la tastatura sau linia de comanda
	private static String path; 
	private static String photoOutput;
	private static String photoInput;
	private static String brightnessLevel;
	
	// Getter path
	public static String getPath() {
		return path;
	}
	// Setter path
	public static void setPath(String name) {
		path = name;
	}
	// Getter photoOutput
	public static String getPhotoOutput() {
		return photoOutput;
	}
	// Setter photoOutput
	public static void setPhotoOutput(String string) {
		photoOutput = string;
	}
	// Getter photoInput
	public static String getPhotoInput() {
		return photoInput;
	}
	// Setter photoInput
	public static void setPhotoInput(String string) {
		photoInput = string;
	}
	// Getter brightnessLevel
	public static String getBrightnessLevel() {
		return brightnessLevel;
	}
	// Setter brightnessLevel
	public static void setBrightnessLevel(String string) {
		brightnessLevel = string;
	}
	
	// pentru testare metoda abstracta din interfata
	public void interfaceMessage() {
		System.out.println("  -> Message from method declared in Interface.");
	}
	
	//pentru testare metoda abstracta din clasa abstracta
	abstract public void abstractMethod();

	//metoda ce faciliteaza citirea de la tastatura
	public static void inputRead() {
		System.out.println("Insert photo informations below: ");
		
		Scanner in = new Scanner(System.in);					//se declara obiectul scanner
		
		System.out.print("Insert path: ");
		path = in.nextLine();									//citire de la tastatura prima linie
		
		System.out.print("Insert picture name: ");
		photoInput = in.nextLine();								//citire de la tastatura a doua linie
		
		System.out.print("Insert result picture name: ");
		photoOutput = in.nextLine();							//citire de la tastatura a 3-a linie
		
		System.out.print("Insert brightness level ( -100 : 100 ): ");
		brightnessLevel = in.nextLine();						//citire de la tastatura a 4-a linie
		
		//se verifica depasirea parametrului de brightness
		while (Integer.parseInt(brightnessLevel) > 100 || Integer.parseInt(brightnessLevel) < -100) {
			System.out.print("Insert a corect brightness level ( -100 : 100 ): ");
			brightnessLevel = in.nextLine();					//se reciteste ultimul parametru in cazul in care nu satisface conditia
		}
		
		in.close();												//se inchide scanner
	}
}
