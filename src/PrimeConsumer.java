import java.util.concurrent.BlockingQueue;

/**
 * Created by Kalaman on 13.02.17.
 */
public class PrimeConsumer implements Runnable {
    BlockingQueue<FindPrimeNumbers> blockingQueue;

    public PrimeConsumer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                FindPrimeNumbers findPrimeNumbers = blockingQueue.take();
                findPrimeNumbers.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
