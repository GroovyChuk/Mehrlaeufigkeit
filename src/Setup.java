import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class Setup {

	public static void main(String[] args) {
		
		// Konstante legen den Wertebereich fest
		final int maxRange = 10000;
		final int segments = 10;
		final int segmentSize=maxRange / segments;


		Executor primeExecutor = new PrimeExecutorThreadPerTask();

		/*int startIndex = 0;
		while (startIndex < maxRange) {
			final int endIndex = (startIndex + segmentSize < maxRange) ? startIndex + segmentSize : maxRange;

			final FindPrimeNumbers pn = new FindPrimeNumbers(startIndex, endIndex);

			primeExecutor.execute(pn);
			startIndex = endIndex;
		}
		*/

		Executor threadPoolExecutor = new PrimeExecutorThreadPool();

		PrimeResults primeResults = new PrimeResults();

		Thread checker = new Thread() {

			@Override
			public void run() {
				super.run();
				Iterator<Integer> iteratorPrime = primeResults.getNPrimes(1000);

				int i=0;
				while (iteratorPrime.hasNext())
				{
					System.out.println((i++) + ". " + iteratorPrime.next());
				}

			}
		};

		checker.start();

		int startIndex = 0;
		while (startIndex < maxRange) {
			final int endIndex = (startIndex + segmentSize < maxRange) ? startIndex + segmentSize : maxRange;

			threadPoolExecutor.execute(new FindPrimeNumbers(startIndex,endIndex,primeResults));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			startIndex = endIndex;
		}

	}	
	
}
