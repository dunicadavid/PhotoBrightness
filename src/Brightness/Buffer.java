package Brightness;

import java.awt.image.BufferedImage;

public class Buffer extends Main{
	BufferedImage buffImage = null ;				//creare buffer pentru transferul a cate un sfert din imagine odata
	
	boolean transfer = true;							//true- producerul utilizeaza bufferul false- consumerul are acces la buffer

	int producerCounter = 1; //contorul coloanei
	int consumerCounter = 1; //contorul coloanei
		
	public synchronized void put(BufferedImage value) {
		while(transfer == false) {						//se verifica ordinea de intrare
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		transfer = false;
		buffImage = value;															//se introduce in buffer un sfert de poza
		System.out.println("Producer send " + producerCounter + " part of image");	//se afiseaza contorul transferului trimis de producer
		try {
			Thread.sleep(100);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		producerCounter++;														//se incrementeaza contor
		notifyAll();		
	}
	
	public synchronized BufferedImage get() {
		while (transfer != false) {						//se verifica ordinea de intrare
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		transfer = true;
		notifyAll();			
		System.out.println("Consumer recive " + consumerCounter + " part of image");	//se afiseaza contorul transferului primis de consumer
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		consumerCounter++;														//se incrementeaza contor
		return buffImage;															//se returneaza partea din imagine din buffer
	}
}
