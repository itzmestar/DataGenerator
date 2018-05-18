import org.fluttercode.datafactory.impl.DataFactory;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataGenerator {
	
	public DataGenerator(float size){
		
	    BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);

	    DataThread reader = new DataThread(queue);
	    WriterThread writer = new WriterThread(queue);

	    Thread thread1 = new Thread(reader);
	    Thread thread2 = new Thread(writer);
	    thread1.start();
	    thread2.start();
	    
    	while(filesize("output.csv")<size){
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	try{
    	thread1.interrupt();
    	thread2.interrupt();
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	System.exit(0);
    	
	}
	public double filesize(String filename){
		File file = new File(filename);
		if(file.exists()){
			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);
			double gigabytes = (megabytes / 1024);
			
			return gigabytes;
		}
		return 0;
	}
	
    public static void main(String[] args) {
    	float size=1;
        if (args.length>0){
            size= Float.parseFloat(args[0]);
        }
    	DataGenerator dataGenerator= new DataGenerator(size);

    }
}