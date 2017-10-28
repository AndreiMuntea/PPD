package Threads;

import Domain.Matrix;
import Domain.Number;

public class MatrixSum<T extends Number> implements IRunCallback<T> {
    @SuppressWarnings("unchecked")
    @Override
    public void runCallback(Matrix<T> firstMatrix, Matrix<T> secondMatrix, Matrix<T> result, Integer start, Integer end) {
        for(int i = start; i < end; ++i){
            for(int j = 0; j < firstMatrix.getNoColumns(); ++j){
                T el1 = firstMatrix.get(i,j);
                T el2 = secondMatrix.get(i,j);
                T res = (T)el1.add(el2);
                result.set(res,i,j);
            }
        }
    }
}
