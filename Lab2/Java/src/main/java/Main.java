import Domain.DoubleNumber;
import Domain.Matrix;
import Domain.Number;
import Threads.IRunCallback;
import Threads.WorkerThread;

public class Main {

    static Double getMilliSeconds(Long nanoseconds){
        return nanoseconds / 1000000.0;
    }

    public static <T extends Number>  double doWork(
        Matrix<T> firstMatrix,
        Matrix<T> secondMatrix,
        Matrix<T> result,
        IRunCallback<T> callback,
        Integer noThreads
    ) throws InterruptedException {
        Integer quotient = firstMatrix.getNoRows() / noThreads;
        Integer remainder = firstMatrix.getNoRows() % noThreads;
        Integer start = 0;
        Integer end = quotient;
        WorkerThread[] threads = new WorkerThread[noThreads];

        for (Integer i = 0; i < noThreads; ++i) {
            if (i < remainder){
                end++;
            }
            threads[i] = new WorkerThread<>(callback, firstMatrix, secondMatrix, result, start, end);

            start = end;
            end += quotient;
        }

        Long begin = System.nanoTime();

        for(Integer i = 0; i < noThreads; ++i){
            threads[i].start();
        }

        for (Integer i = 0; i < noThreads; ++i) {
            threads[i].join();
        }

        Long finish = System.nanoTime();
        return getMilliSeconds(finish - begin);
    }
}
