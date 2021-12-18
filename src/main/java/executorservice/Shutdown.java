package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Shutdown {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // use your executor
        // ...

        // shutdown if you are done with it
        executorService.shutdown(); // --> executor will no longer accept new tasks

        try {
            if (!executorService.awaitTermination(1, java.util.concurrent.TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // --> interrupt signal sent to all threads
            }
        } catch (InterruptedException e) {
            // current thread may get interrupted waiting termination,
            // in this case, we better send interrupt to running threads
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
