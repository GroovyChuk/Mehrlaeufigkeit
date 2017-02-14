import java.util.concurrent.Executor;

/**
 * Created by Kalaman on 13.02.17.
 */
public class PrimeExecutorThreadPerTask implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();    //ThreadPerTask
    }
}
