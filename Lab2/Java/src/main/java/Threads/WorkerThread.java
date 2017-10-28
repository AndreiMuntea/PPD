package Threads;

import Domain.Matrix;
import Domain.Number;

public class WorkerThread<T extends Number> extends Thread {

    public WorkerThread(IRunCallback<T> callback, Matrix<T> firstMatrix, Matrix<T> secondMatrix, Matrix<T> result, Integer start, Integer end){
        this.threadCallback = callback;
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.result = result;
        this.end = end;
        this.start = start;
    }

    @Override
    public void run() {
        threadCallback.runCallback(firstMatrix, secondMatrix, result, start, end);
    }

    private IRunCallback<T> threadCallback;
    private Matrix<T> firstMatrix;
    private Matrix<T> secondMatrix;
    private Matrix<T> result;
    private Integer start;
    private Integer end;
}
