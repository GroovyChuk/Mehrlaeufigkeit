import sun.misc.Lock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/**
 * Created by Kalaman on 13.02.17.
 */
public class PrimeResults {

    ArrayList<Integer> primeList;
    Semaphore semaphore = new Semaphore(0);
    Lock getNPrimesLock = new Lock();

    public PrimeResults(){
        primeList = new ArrayList<Integer>();
    }

    public Iterator<Integer> getNPrimes(int n){
        synchronized (getNPrimesLock) {
            if (n > primeList.size()) {
                try {
                    semaphore.acquire(n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            semaphore.release(n);
        }
        return primeList.subList(0,n).iterator();
    }

    public synchronized void foundPrime (int prime)
    {
        primeList.add(prime);
        semaphore.release();
    }
}
