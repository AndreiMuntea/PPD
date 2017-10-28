package Threads;

import Domain.Matrix;
import Domain.Number;

public interface IRunCallback <T extends Number> {
    void  runCallback(Matrix<T> firstMatrix, Domain.Matrix<T> secondMatrix, Matrix<T> result, Integer start, Integer end);
}
