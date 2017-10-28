import Domain.Matrix;
import Domain.MatrixThreadContext;
import Domain.MultiplicationCallback;
import FileHelper.CorruptedFileException;
import FileHelper.FileReader;
import FileHelper.FileWriter;
import Threads.IThreadCallback;
import Threads.WorkerThread;

import java.io.IOException;
import java.util.Random;

public class Main {

    static Double getMiliSeconds(Long nanoseconds){
        return nanoseconds / 1000000.0;
    }

    static void doWorkMultiThreaded(
            Matrix firstMatrix,
            Matrix secondMatrix,
            Matrix result,
            IThreadCallback<MatrixThreadContext> callback,
            Integer noThreads
    ) throws InterruptedException {
        if (noThreads <= 0){
            throw  new RuntimeException("There should be at least 1 thread!");
        }

        if (noThreads > firstMatrix.getNoLines()){
            throw new RuntimeException("NoThreads should be <= number of lines of first matrix!");
        }

        Long begin = System.nanoTime();
        Integer quotient = firstMatrix.getNoLines() / noThreads;
        Integer remainder = firstMatrix.getNoLines() % noThreads;
        Integer start = 0;
        Integer end = quotient;
        WorkerThread[] threads = new WorkerThread[noThreads];

        for (Integer i = 0; i < noThreads; ++i) {
            if (remainder > 0) {
                remainder--;
                end++;
            }

            MatrixThreadContext threadContext = new MatrixThreadContext(firstMatrix, secondMatrix, result, start, end);
            threads[i] = new WorkerThread<>(threadContext, callback);
            threads[i].start();

            start = end;
            end += quotient;
        }

        for (Integer i = 0; i < noThreads; ++i) {
            threads[i].join();
        }

        Long finish = System.nanoTime();
        System.out.println("Multi threaded: " + getMiliSeconds(finish - begin));
    }

    static void doWorkSingleThreaded(
            Matrix firstMatrix,
            Matrix secondMatrix,
            Matrix result,
            IThreadCallback<MatrixThreadContext> callback
    ){
        Long begin = System.nanoTime();

        MatrixThreadContext threadContext = new MatrixThreadContext(firstMatrix,secondMatrix,result, 0, firstMatrix.getNoLines());
        callback.RunCallback(threadContext);

        Long finish = System.nanoTime();
        System.out.println("Single threaded: " + getMiliSeconds(finish - begin));
    }

    public static void main(String[] args) throws InterruptedException, IOException, CorruptedFileException {
//        Matrix p1 = new Matrix(1000, 1000, new Random());
//        Matrix p2 = new Matrix(1000, 1000, new Random());
//        FileWriter.writeMatrix(p1, "a.txt");
//        FileWriter.writeMatrix(p2, "b.txt");

        Matrix a = FileReader.ReadMatrixFromFile("a.txt");
        Matrix b = FileReader.ReadMatrixFromFile("b.txt");

        Integer noThreads = 2;
        Matrix c = new Matrix(a.getNoLines(), b.getNoColumns());
        Matrix d = new Matrix(a.getNoLines(), b.getNoColumns());

        doWorkSingleThreaded(a,b,c, new MultiplicationCallback());
        doWorkMultiThreaded(a,b,d,new MultiplicationCallback(), noThreads);

        System.out.println(c.equals(d));
        FileWriter.writeMatrix(c, "r.txt");

    }
}
