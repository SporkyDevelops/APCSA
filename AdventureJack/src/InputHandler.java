import java.io.IOException;
import java.io.Reader;

public class InputHandler extends Thread {
    private final Reader reader;
    private final App app;
    private volatile boolean running = true;

    public InputHandler(Reader reader, App app){
        this.reader = reader;
        this.app = app;
    }

    public void exitHandler(){
        Thread.currentThread().interrupt();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            while(running){
                int input = reader.read();

                app.inputHandle(input);

                try {
                    Thread.sleep(64);
                } catch (InterruptedException e) {}
            }
        } catch(IOException e){
            System.err.println("Err:" + e.getMessage());
        }
    }
}
