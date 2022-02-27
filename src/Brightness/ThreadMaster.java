package Brightness;

public class ThreadMaster extends Input{

	//pentru testarea metodei abstracta din clasa abstracta
	@Override
	public void abstractMethod() {
		System.out.println("  -> Message from abstract method.");
	}
	
	//pentru testarea varargs
	public static void varargs(String ...args) {
		System.out.println("  -> Number of arguments used for this method = " + args.length);
	}
	
	//pentru startul si joinul threadurilor
	public static void threadMaster() {
		Buffer buff = new Buffer();
		
		//creare thread produser si consumer
		Thread threadPrd = new Thread(new Producer(buff));
		Thread threadCns = new Thread(new Consumer(buff));
		
		//pornire thread producer si consumer
		threadPrd.start();
		threadCns.start();
		
		//asteptarea threadurilor
		try {
			threadPrd.join();
			threadCns.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
