package Threads;

public interface IThreadCallback<T> {
    void RunCallback(T context);
}
