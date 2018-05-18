import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterThread implements Runnable{

  protected BlockingQueue<String> blockingQueue = null;
  File file = new File("output.csv");
  FileWriter fr = null;
  BufferedWriter br = null;
  
  public WriterThread(BlockingQueue<String> blockingQueue){

      this.blockingQueue = blockingQueue;     
  }

  @Override
  public void run() {
	  //String dataWithNewLine=data+System.getProperty("line.separator");

    try {
    	fr = new FileWriter(file);
    	br = new BufferedWriter(fr);

        while(true){
            String buffer = blockingQueue.take();
            //Check whether end of file has been reached
            if(buffer.equals("EOF")){ 
                break;
            }
            br.write(buffer);
        }               


    } catch (FileNotFoundException e) {

        e.printStackTrace();
    } catch(InterruptedException e){

    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
    	try {
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

  }

}