package Threads;

public class WorkerThread<T> extends Thread{

    public WorkerThread(T threadContext, IThreadCallback<T> threadCallback) {
        this.threadContext = threadContext;
        this.threadCallback = threadCallback;
    }

    @Override
    public void run() {
        threadCallback.RunCallback(threadContext);
    }

    private T threadContext;
    private IThreadCallback<T> threadCallback;
}
