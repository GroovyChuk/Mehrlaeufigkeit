import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

/**
 * Created by Kalaman on 13.02.17.
 */
public class PrimeExecutorThreadPool implements Executor {

    final int NTHREADS = 20;
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(200);

    public PrimeExecutorThreadPool()
    {
        for(int i=0;i<NTHREADS;++i)
        {
            new Thread(){

                @Override
                public void run() {
                    while (true){
                        try {
                            blockingQueue.take().run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        }
    }

    @Override
    public void execute(Runnable command) {
        try {
            blockingQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
